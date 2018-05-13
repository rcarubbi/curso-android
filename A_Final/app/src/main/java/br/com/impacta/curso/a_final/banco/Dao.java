package br.com.impacta.curso.a_final.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nalmir on 10/03/2018.
 */

public class Dao {

    private Context context;
    protected SQLiteDatabase db;

    public Dao(Context context) {
        this.context = context;
    }

    public void abrirBanco() {
        SQLiteOpenHelper varHelper = new SQLiteHelper(
                context,
                Constantes.BANCO,
                null,
                Constantes.VERSAO
        );

        this.db = varHelper.getWritableDatabase();
    }

    public void fecharBanco() {
        if (db != null) {
            db.close();
        }
    }
}
