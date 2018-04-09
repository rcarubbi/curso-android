package br.com.impacta.curso.prj_021_alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static long SEGUNDO = 1000;

    private Context context;

    private AlarmManager al;

    private Button btn_au;
    private Button btn_ar;
    private Button btn_ac;

    private PendingIntent pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);


        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();
        //
        al = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        //
        btn_au = (Button) findViewById(R.id.btn_au);
        btn_ar = (Button) findViewById(R.id.btn_ar);
        btn_ac = (Button) findViewById(R.id.btn_ac);
    }

    private void initActions() {

        btn_au.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mIntent = new Intent(context, AlarmeAcao.class);

                pi = PendingIntent.getBroadcast(
                        context,
                        10,
                        mIntent,
                        0
                );

                long timeStartProcesso = System.currentTimeMillis() +
                        (5 * SEGUNDO);

                al.set(
                        AlarmManager.RTC_WAKEUP,
                        timeStartProcesso,
                        pi
                );

            }
        });

        btn_ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, AlarmeAcao.class);

                pi = PendingIntent.getBroadcast(
                        context,
                        10,
                        mIntent,
                        0
                );

                long timeStartProcesso = System.currentTimeMillis() +
                        (5 * SEGUNDO);

                al.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        timeStartProcesso,
                        (5 * SEGUNDO),
                        pi
                );

            }
        });

        btn_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                al.cancel(pi);
            }
        });

    }


}
