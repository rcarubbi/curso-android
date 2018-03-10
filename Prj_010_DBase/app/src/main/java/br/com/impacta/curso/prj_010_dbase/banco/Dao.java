package br.com.impacta.curso.prj_010_dbase.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by rcaru on 10/03/2018.
 */

public abstract class Dao {
    private Context context;
    protected SQLiteDatabase db;

    public Dao(Context context) {
        this.context = context;
    }

    public void abrirBanco() {

        SQLiteHelper varHelper = new SQLiteHelper(
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
