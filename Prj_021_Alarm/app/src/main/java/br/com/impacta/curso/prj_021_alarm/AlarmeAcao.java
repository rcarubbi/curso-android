package br.com.impacta.curso.prj_021_alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by nalmir on 07/04/2018.
 */

public class AlarmeAcao extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("ALARM", "Alarme Executado!!!");
    }
}
