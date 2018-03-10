package br.com.impacta.curso.prj_010_dbase.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rcaru on 10/03/2018.
 */

public class SQLiteHelper extends SQLiteOpenHelper {


    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            StringBuilder sb =new StringBuilder();
            sb.append("CREATE TABLE IF NOT EXISTS [contatos] (\n");
            sb.append("[idcontato] int not null, \n");
            sb.append("[nome] TEXT NOT NULL,\n");
            sb.append("[telefone] INT NOT NULL,\n");
            sb.append("[idade] INT NOT NULL,\n");
            sb.append("CONSTRAINT [] PRIMARY KEY ([idcontato]));");

            String[] comandos = sb.toString().split(";");
            for (String comando: comandos
                 ) {
                db.execSQL(comando);
            }

        }
        catch (Exception e) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
