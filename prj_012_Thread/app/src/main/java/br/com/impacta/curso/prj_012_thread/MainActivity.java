package br.com.impacta.curso.prj_012_thread;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_c;
    private Button btn_pd;
    private Button btn_play;
    private Button btn_stop;
    private Button btn_ds2;
    private int indice;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();
        tv_c = findViewById(R.id.tv_c);
        btn_pd = findViewById(R.id.btn_pd);
        btn_play = findViewById(R.id.btn_play);
        btn_stop = findViewById(R.id.btn_stop);
        btn_ds2 = findViewById(R.id.btn_ds2);
    }

    private void initActions() {
        btn_pd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // processoDemorado();
                processoDemoradoComAsyncTask();
            }
        });

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TocadorService.class);
                startService(intent);
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TocadorService.class);
                stopService(intent);
            }
        });

        btn_ds2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!DS2.status) {
                    Intent intent = new Intent(context, DS2.class);
                    startService(intent);
                }
            }
        });
    }

    private void processoDemoradoComAsyncTask() {
        new Sincronismo().execute();
    }

    private class Sincronismo extends AsyncTask<Void, Integer, Void> {

        // Roda na UI Thread
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            btn_pd.setEnabled(false);
            tv_c.setText("0");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                indice = 0;
                while (indice < 25) {
                    Thread.sleep(1000);
                    indice++;
                    publishProgress(indice);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        // Roda na UI Thread
        @Override
        protected void onProgressUpdate(Integer... values) {
            tv_c.setText(String.valueOf(values[0]));
            super.onProgressUpdate(values);
        }

        // Roda na UI Thread
        @Override
        protected void onPostExecute(Void aVoid) {
            btn_pd.setEnabled(true);
            super.onPostExecute(aVoid);
        }


    }

    private void processoDemorado() {
        btn_pd.setEnabled(false);

        try {
            indice = 0;
            while (indice < 25) {
                Thread.sleep(1000);
                indice++;
                tv_c.setText(String.valueOf(indice));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        btn_pd.setEnabled(true);
    }
}
