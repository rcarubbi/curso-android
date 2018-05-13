package br.com.impacta.curso.a_final.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nalmir on 10/03/2018.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            StringBuilder sb = new StringBuilder();
            //
            sb.append("CREATE TABLE IF NOT EXISTS [pacientes] (\n" +
                    "  [idpaciente] INT NOT NULL, \n" +
                    "  [nome] TEXT NOT NULL, \n" +
                    "  CONSTRAINT [] PRIMARY KEY ([idpaciente]));");

            String [] comandos = sb.toString().split(";");

            for (int i = 0; i < comandos.length; i++) {
                db.execSQL(comandos[i].toLowerCase());
            }

        } catch (Exception e){
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            StringBuilder sb = new StringBuilder();
            //
            sb.append("DROP TABLE IF EXISTS [pacientes];");

            String [] comandos = sb.toString().split(";");

            for (int i = 0; i < comandos.length; i++) {
                db.execSQL(comandos[i].toLowerCase());
            }

        } catch (Exception e){
        }

        onCreate(db);
    }
}
