package br.com.impacta.curso.prj_023_gps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private Button btn_gps;
    private Button btn_rede;
    private Button btn_parar;

    private TextView tv_latitude;
    private TextView tv_longitude;

    private Button btn_lbs;
    private Button btn_verificar;

    private double latitude;
    private double longitude;

    private LocationManager lm;

    private xeretaListener receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);

        initVars();
        initActions();

        receiver = new xeretaListener(); // acao que deve ser executada. Quando?
        IntentFilter filter = new IntentFilter("android.location.PROVIDERS_CHANGED");
        //
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    private void initVars() {
        context = getBaseContext();
        //
        lm = (LocationManager)
                context.getSystemService(LOCATION_SERVICE);
        //
        btn_gps = (Button) findViewById(R.id.btn_gps);
        btn_rede = (Button) findViewById(R.id.btn_rede);
        btn_parar = (Button) findViewById(R.id.btn_parar);

        tv_latitude = (TextView) findViewById(R.id.tv_latitude);
        tv_longitude = (TextView) findViewById(R.id.tv_longitude);

        btn_lbs = (Button) findViewById(R.id.btn_lbs);
        btn_verificar = (Button) findViewById(R.id.btn_verificar);
    }

    private void initActions() {
        btn_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habilitarDesabilitarB(false);
                limparTela();
                //
                lm.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        0,
                        0,
                        servico_localizacao
                );
            }
        });

        btn_rede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habilitarDesabilitarB(false);
                limparTela();
                //
                lm.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        0,
                        0,
                        servico_localizacao
                );
            }
        });

        btn_parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habilitarDesabilitarB(true);
                //
                lm.removeUpdates(servico_localizacao);
                //
                String uriLocal = "geo:0,0?q=" +
                        String.valueOf(latitude).replace(",", ".") +
                        "," +
                        String.valueOf(longitude).replace(",", ".");
                //
                Intent mInent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(uriLocal)
                );
                startActivity(mInent);
            }
        });

        btn_lbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelephonyManager tm = (TelephonyManager)
                        context.getSystemService(TELEPHONY_SERVICE);

                GsmCellLocation location = (GsmCellLocation) tm.getCellLocation();

                StringBuilder resultado = new StringBuilder();
                resultado
                        .append("CellID: ")
                        .append(location.getCid())
                        .append("\n")
                        .append("Lac: ")
                        .append(location.getLac())
                        .append("\n")
                        .append("IMEI: ")
                        .append(tm.getDeviceId());
                //
                Toast.makeText(
                        context,
                        resultado.toString(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        btn_verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultado = null;
                //
                if (verificarServico()) {
                    resultado = "GPS Habilitado!!!";
                } else {
                    resultado = "GPS Desabilitado!!!";
                }
                //
                Toast.makeText(
                        context,
                        resultado,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean verificarServico() {
        if ((lm != null) && (
                lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                        lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                )){

            return true;

        } else {
            return false;
        }
    }

    private void limparTela() {
        tv_latitude.setText("0");
        tv_longitude.setText("0");
    }

    private void habilitarDesabilitarB(boolean status) {
        btn_gps.setEnabled(status);
        btn_rede.setEnabled(status);
    }

    private LocationListener servico_localizacao = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            //
            tv_latitude.setText(String.valueOf(latitude));
            tv_longitude.setText(String.valueOf(longitude));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private class xeretaListener extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            btn_verificar.performClick();
        }
    }

}
