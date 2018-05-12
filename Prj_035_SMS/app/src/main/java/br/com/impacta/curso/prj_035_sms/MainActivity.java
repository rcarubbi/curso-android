package br.com.impacta.curso.prj_035_sms;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private Button btn_send;

    private SmsManager sm;


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
        sm = SmsManager.getDefault();
        //
        btn_send = (Button) findViewById(R.id.btn_sms);
    }

    private void initActions() {
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm.sendTextMessage(
                        "999999",
                        null,
                        "Seja bem vindo",
                        null,
                        null
                );
            }
        });
    }

}
