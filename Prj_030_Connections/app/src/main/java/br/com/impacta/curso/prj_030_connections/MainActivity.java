package br.com.impacta.curso.prj_030_connections;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private Button btn_tc;

//    private ConnectivityManager cm;

    private ConexaoStatus receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);

        initVars();
        initActions();

        // acao a ser executada
        receiver = new ConexaoStatus();

        // deteccao do evento
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        //

        // JUNTAR - Eu estou escutando a sinalizacao desse evento
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        // deixo de escutar o sinalizacao desse evento
        unregisterReceiver(receiver);
        //
        super.onDestroy();
    }

    private void initVars() {
        context = getBaseContext();
        //
        btn_tc = (Button) findViewById(R.id.btn_tc);
    }

    private void initActions() {
        btn_tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        context,
                        verificacaoStatusConexao(context),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

    }

    // a acao que sera executado quando o evento for detectado
    private class ConexaoStatus extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            btn_tc.performClick();
        }
    }

    private String verificacaoStatusConexao(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(CONNECTIVITY_SERVICE);
        //
        NetworkInfo wifi = cm.
                getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        // Tableta sem operadora sera configurado com null
        NetworkInfo mobile = cm.
                getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi != null && wifi.isAvailable() && wifi.isConnected()) {
            return "wifi";
        }
        //
        if (mobile != null && mobile.isAvailable() && mobile.isConnected()) {
            return "mobile";
        }
        //
        return "SEM CONEXAO";
    }
}
