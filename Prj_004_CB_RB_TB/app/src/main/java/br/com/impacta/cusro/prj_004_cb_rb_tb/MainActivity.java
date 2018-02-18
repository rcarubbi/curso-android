package br.com.impacta.cusro.prj_004_cb_rb_tb;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private CheckBox cb_android;
    private CheckBox cb_ios;
    private RadioGroup rg;
    private RadioButton rb_m;
    private RadioButton rb_f;
    private ToggleButton tb_tomada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);

        initVars();
        initAcitons();

    }

    private void initAcitons() {
       FromDB_ToScreen();
       FromScreen_ToDB();
    }

    private void FromScreen_ToDB() {
        int ios = 1;
        int android= -1;
        String sexo = "";
        boolean tomada = false;

        if (cb_ios.isChecked()){
            ios = 1;
        } else {
            ios = 0;
        }

        if (cb_android.isChecked()) {
            android = 1;
        }
        else {
            android = 0;
        }

        switch (rg.getCheckedRadioButtonId()) {
            case R.id.rb_m:
                sexo = "m";
                break;
            case R.id.rb_f:
                sexo = "f";
                break;
            default:
                break;
        }

        tomada = tb_tomada.isChecked();

        cb_ios.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String resultado = "";

                if (isChecked) {
                    resultado = "Eu sei iOS";
                }
                else {
                    resultado = "Eu NÃO sei iOS";
                }

                Toast.makeText(context, resultado, Toast.LENGTH_SHORT).show();
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                String sexo = "";
                switch (rg.getCheckedRadioButtonId()) {
                    case R.id.rb_m:
                        sexo = "Masculino";
                        break;
                    case R.id.rb_f:
                        sexo = "Feminino";
                        break;
                    default:
                        break;
                }

                Toast.makeText(context, sexo, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void FromDB_ToScreen() {
        int ios = 1;
        int android = 0;
        String sexo = "m";
        boolean tomada = true;

        if (ios == 1) {
            cb_ios.setChecked(true);
        }else {
            cb_ios.setChecked(false);
        }

        if (android == 1) {
            cb_android.setChecked(true);
        }
        else
        {
            cb_android.setChecked(false);
        }

        switch(sexo) {
            case "m":
                rb_m.setChecked(true);
                break;
            case "f":
                rb_f.setChecked(false);
                break;
            default:
                break;
        }

        tb_tomada.setChecked(tomada);
    }

    private void initVars() {
        context = getBaseContext();
        cb_ios   = (CheckBox) findViewById(R.id.cb_ios);
        cb_android = (CheckBox) findViewById(R.id.cb_android);

        rg = (RadioGroup) findViewById(R.id.rg);
        rb_f = (RadioButton) findViewById(R.id.rb_f);
        rb_m = (RadioButton) findViewById(R.id.rb_m);

        tb_tomada = (ToggleButton) findViewById(R.id.tb_tomada);
    }
}
