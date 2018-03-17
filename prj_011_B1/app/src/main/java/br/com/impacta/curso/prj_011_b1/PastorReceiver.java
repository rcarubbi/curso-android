package br.com.impacta.curso.prj_011_b1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by rcaru on 17/03/2018.
 */

public class PastorReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int indice = intent.getIntExtra("indice", -1);


        Toast.makeText(context, "B1 - Indice: " + String.valueOf(indice), Toast.LENGTH_SHORT).show();
    }
}
