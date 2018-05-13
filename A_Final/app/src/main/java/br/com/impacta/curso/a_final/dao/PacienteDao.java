package br.com.impacta.curso.a_final.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.impacta.curso.a_final.banco.Dao;
import br.com.impacta.curso.a_final.banco.HMAux;
import br.com.impacta.curso.a_final.model.Paciente;

/**
 * Created by nalmir on 12/05/2018.
 */

public class PacienteDao extends Dao {

    public static final String TABELA = "pacientes";
    public static final String IDPACIENTE = "idpaciente";
    public static final String NOME = "nome";

    public PacienteDao(Context context) {
        super(context);
    }

    // Lista de Pacientes
    public ArrayList<HMAux> obterListaPacientes() {
        ArrayList<HMAux> pacientes = new ArrayList<>();
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {

            StringBuilder comando = new StringBuilder();

            comando
                    .append(" select idpaciente, nome from ")
                    .append(TABELA)
                    .append(" order by ")
                    .append(NOME);

            cursor = db.rawQuery(comando.toString(), null);

            while (cursor.moveToNext()) {
                HMAux aux = new HMAux();
                //
                aux.put(
                        HMAux.ID,
                        cursor.getString(cursor.getColumnIndex(IDPACIENTE))
                );
                //
                aux.put(
                        HMAux.TEXTO_01,
                        cursor.getString(cursor.getColumnIndex(NOME))
                );
                //
                pacientes.add(aux);
            }

            cursor.close();

        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return pacientes;
    }

    // Inserir Lista de Pacientes
    public void inserirListaPaicentes(ArrayList<Paciente> pacientes) {
        abrirBanco();
        //
        db.beginTransaction(); // trava o acesso de gravacao do banco inteiro
        //
        try {
            db.delete(TABELA, null, null);

            ContentValues cv = new ContentValues();

            for (Paciente pAux : pacientes) {
                cv.clear();
                cv.put(IDPACIENTE, pAux.getIdpaciente());
                cv.put(NOME, pAux.getNome());
                // ...
                db.insert(TABELA, null, cv);
            }

            db.setTransactionSuccessful(); // Ok efetive da gravacao

        } catch (Exception e) {
        } finally {
            db.endTransaction(); // libero o banco para gravacoes
        }
        //
        fecharBanco();
    }

}
