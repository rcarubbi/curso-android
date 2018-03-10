package br.com.impacta.curso.prj_010_dbase.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.impacta.curso.prj_010_dbase.banco.Dao;
import br.com.impacta.curso.prj_010_dbase.banco.HMAux;
import br.com.impacta.curso.prj_010_dbase.model.Contato;

/**
 * Created by rcaru on 10/03/2018.
 */

public class ContatoDao extends Dao {

    public static final String TABELA = "contatos";
    public static final String IDCONTATO = "idcontato";
    public static final String NOME = "nome";
    public static final String TELEFONE = "telefone";
    public static final String IDADE = "idade";

    public String[] column = {
            IDCONTATO, NOME, TELEFONE, IDADE
    };

    public ContatoDao(Context context) {
        super(context);
    }

    public void inserirContatoDao(Contato contato) {
        abrirBanco();

        ContentValues cv = new ContentValues();
        cv.put(IDCONTATO, contato.getIdcontato());
        cv.put(NOME, contato.getNome());
        cv.put(TELEFONE, contato.getTelefone());
        cv.put(IDADE, contato.getIdade());

        db.insert(TABELA, null, cv);

        fecharBanco();
    }

    public void alterarContato(Contato contato) {
        abrirBanco();

        String filtro = IDCONTATO + " = ? ";

        ContentValues cv = new ContentValues();
        cv.put(NOME, contato.getNome());
        cv.put(TELEFONE, contato.getTelefone());
        cv.put(IDADE, contato.getIdade());

        db.update(TABELA, cv, filtro, new String[]{String.valueOf(contato.getIdcontato())});

        fecharBanco();
    }

    public void apagarContato(long idcontato) {

        abrirBanco();

        String filtro = IDCONTATO + " = ? ";
        db.delete(TABELA, filtro, new String[]{String.valueOf(idcontato)});

        fecharBanco();
    }

    public Contato obterContatoByID(long id) {
        Contato contato = null;

        abrirBanco();

        Cursor cursor = null;
        StringBuilder comando = new StringBuilder();
        comando.append(" select * from ");
        comando.append(TABELA);
        comando.append(" where ");
        comando.append(IDCONTATO);
        comando.append(" = ? ");


        try {
            cursor = db.rawQuery(comando.toString(), new String[]{String.valueOf(id)});

            while (cursor.moveToNext()) {
                contato = new Contato(
                        cursor.getLong(cursor.getColumnIndex(IDCONTATO)),
                        cursor.getString(cursor.getColumnIndex(NOME)),
                        cursor.getString(cursor.getColumnIndex(TELEFONE)),
                        cursor.getInt(cursor.getColumnIndex(IDADE))
                );
            }

            cursor.close();
        } catch (Exception ex) {

        }

        fecharBanco();

        return contato;
    }

    public long proximoID() {
        long proID = 1;

        abrirBanco();

        Cursor cursor = null;

        StringBuilder comando = new StringBuilder();
        comando.append("select max(idcontato) + 1 as id from ");
        comando.append(TABELA);


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

    public ArrayList<HMAux> obterListaContatos() {
        ArrayList<HMAux> contatos = new ArrayList<>();
        abrirBanco();

        Cursor cursor = null;
        StringBuilder comando = new StringBuilder();
        comando.append(" select idcontato, nome from ");
        comando.append(TABELA);
        comando.append(" order by ");
        comando.append(NOME);


        try {
            cursor = db.rawQuery(comando.toString(),null);

            while (cursor.moveToNext()) {
                HMAux item = new HMAux();
                item.put(HMAux.ID, cursor.getString(cursor.getColumnIndex(IDCONTATO)));
                item.put(HMAux.TEXTO_01, cursor.getString(cursor.getColumnIndex(NOME)));
                contatos.add(item);
            }

            cursor.close();
        } catch (Exception ex) {

        }

        fecharBanco();

        return contatos;
    }
}
