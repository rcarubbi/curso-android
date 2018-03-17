package br.com.impacta.curso.prj_012_thread;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by rcaru on 17/03/2018.
 */

public class DS2 extends IntentService {


    public static boolean status = false;

    public DS2(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        status = true;
        int indice = 0;
        try {
            indice = 0;
            while (indice < 25) {
                Thread.sleep(1000);
                indice++;
                Log.d("DS2", String.valueOf(indice));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            status = false;
        }
    }
}
