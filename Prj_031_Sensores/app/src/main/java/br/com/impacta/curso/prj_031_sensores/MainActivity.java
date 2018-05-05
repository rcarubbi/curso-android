package br.com.impacta.curso.prj_031_sensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Context context;

    private TextView tv_x;
    private TextView tv_y;
    private TextView tv_z;

    private SensorManager sm;

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
        sm = (SensorManager)
                context.getSystemService(SENSOR_SERVICE);
        //
        tv_x = (TextView) findViewById(R.id.tv_x);
        tv_y = (TextView) findViewById(R.id.tv_y);
        tv_z = (TextView) findViewById(R.id.tv_z);
        //
        int versao = Build.VERSION.SDK_INT; //Versao Android
        String fabricante = Build.MANUFACTURER;
        String modelo = Build.MODEL;
        String product = Build.PRODUCT;
        String hardware = Build.HARDWARE;
        String serial = Build.SERIAL;


        int i = 10;

    }

    private void initActions() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        //
        sm.registerListener(
                this,
                sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        //
        sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values.length == 3) {
            tv_x.setText(String.valueOf(event.values[0]));
            tv_y.setText(String.valueOf(event.values[1]));
            tv_z.setText(String.valueOf(event.values[2]));
        } else {
            tv_x.setText("Erro");
            tv_y.setText("Erro");
            tv_z.setText("Erro");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
