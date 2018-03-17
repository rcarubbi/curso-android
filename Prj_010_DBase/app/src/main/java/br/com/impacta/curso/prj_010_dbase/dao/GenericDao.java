package br.com.impacta.curso.prj_010_dbase.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import br.com.impacta.curso.prj_010_dbase.banco.Dao;

/**
 * Created by rcaru on 11/03/2018.
 */

public class GenericDao<T> extends Dao {

    private String nomeTabela;
    private ArrayList<String> colunas;
    private ArrayList<Method> getters;
    private Type modelType;

    public GenericDao(Context context, IKeyRetrievable keyRetrievableHandler) {
        super(context);

        this.keyRetrievableHandler = keyRetrievableHandler;
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        modelType = type.getActualTypeArguments()[0];
        nomeTabela = modelType.getClass().getName();
        getters = new ArrayList<>();
        colunas = new ArrayList<>();

        for (Method getter: modelType.getClass().getMethods()) {
            if (getter.getName().startsWith("get")) {
                getters.add(getter);
                colunas.add(getColumnName(getter));
            }
        }
    }

    private String getColumnName(Method getter) {
        String getterName = getter.getName();
        return getterName.substring(3, getterName.length());
    }

    private Object invokeMethod(Method method, T instance) {
        Object value = null;
        try {
            value = method.invoke(instance);
        } catch (Exception ex) {

        }

        return value;
    }

    public void inserir(T model) {
        abrirBanco();

        ContentValues cv = new ContentValues();

        for (int i = 0; i < colunas.size(); i++) {
            cv.put(colunas.get(i), invokeMethod(getters.get(i), model).toString());
        }

        db.insert(nomeTabela, null, cv);

        fecharBanco();
    }

    public interface IKeyRetrievable {
        Method getKeyMethod();
    }
    public interface ICursorConvertible<T> {
        T convert(Cursor cursor);
    }


    private IKeyRetrievable keyRetrievableHandler;



    public void alterar(T model) {
        abrirBanco();

        String filtro =  getColumnName(keyRetrievableHandler.getKeyMethod()) + " = ? ";

        ContentValues cv = new ContentValues();

        for (int i = 0; i < colunas.size(); i++) {
            if (getters.get(i).getName() != keyRetrievableHandler.getKeyMethod().getName()) {
                cv.put(colunas.get(i), invokeMethod(getters.get(i), model).toString());
            }
        }

        db.update(nomeTabela, cv, filtro, new String[]{invokeMethod(keyRetrievableHandler.getKeyMethod(), model).toString()});

        fecharBanco();
    }

    public void apagar(long id) {

        abrirBanco();

        String filtro =  getColumnName(keyRetrievableHandler.getKeyMethod()) + " = ? ";
        db.delete(nomeTabela, filtro, new String[]{String.valueOf(id)});

        fecharBanco();
    }


    public T obterByID(long id, ICursorConvertible<T> convertHandler) {
        T model = null;
        abrirBanco();

        Cursor cursor = null;
        StringBuilder comando = new StringBuilder();
        comando.append(" select * from ");
        comando.append(nomeTabela);
        comando.append(" where ");
        comando.append(getColumnName(keyRetrievableHandler.getKeyMethod()));
        comando.append(" = ? ");


        try {
            cursor = db.rawQuery(comando.toString(), new String[]{String.valueOf(id)});

            while (cursor.moveToNext()) {
                model = convertHandler.convert(cursor);
            }

            cursor.close();
        } catch (Exception ex) {

        }

        fecharBanco();

        return model;
    }

    public long proximoID() {
        long proID = 1;

        abrirBanco();

        Cursor cursor = null;

        StringBuilder comando = new StringBuilder();
        comando.append("select max(");
        comando.append(getColumnName(keyRetrievableHandler.getKeyMethod()));
        comando.append(") + 1 as id from ");
        comando.append(nomeTabela);


        try {
            cursor = db.rawQuery(comando.toString(), null);

            if (cursor.moveToNext()) {
                proID = cursor.getLong(0);
            }

            if (proID == 0){
                proID = 1;
            }

            cursor.close();
        } catch (Exception ex) {

        }

        fecharBanco();

        return proID;
    }

    public ArrayList<T> obterLista(ICursorConvertible<T> convertHandler, String orderBy) {
        ArrayList<T> list = new ArrayList<>();
        abrirBanco();

        Cursor cursor = null;
        StringBuilder comando = new StringBuilder();
        comando.append(" select idcontato, nome from ");
        comando.append(nomeTabela);
        comando.append(" order by ");
        comando.append(orderBy);


        try {
            cursor = db.rawQuery(comando.toString(),null);

            while (cursor.moveToNext()) {
                list.add(convertHandler.convert(cursor));
            }

            cursor.close();
        } catch (Exception ex) {

        }

        fecharBanco();

        return list;
    }
}
