package br.com.impacta.curso.prj_009_multitelas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Calculo extends AppCompatActivity {

    private Context context;

    private Button btn_m100;
    private TextView tv_valor;

    private boolean status_recreate = false;
    private int parametro_valor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculo);
        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();

        recuperarParametros();

        btn_m100 = findViewById(R.id.calculo_btn_m100);
        tv_valor = findViewById(R.id.calculo_tv_parametro);

        atualizarTela();
    }

    private void atualizarTela() {
        tv_valor.setText(String.valueOf(parametro_valor));
        btn_m100.setEnabled(!status_recreate);
    }

    private void recuperarParametros() {
        if (getIntent().getIntExtra(Constantes.PARAMETRO_TIPO, -1) == 1) {
            status_recreate = true;
        } else {
            status_recreate = false;
        }

        parametro_valor = getIntent().getIntExtra(Constantes.PARAMETRO_VALOR, -1);
    }

    private void initActions() {

        btn_m100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int resultado = parametro_valor * 100;
                Intent data = new Intent();
                data.putExtra(Constantes.PARAMETRO_RETORNO, resultado);
                setResult(RESULT_OK, data);

                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {
        chamarTelaInicial(status_recreate);
    }

    private void chamarTelaInicial(boolean recreate) {
        if (recreate) {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        }
        //super.onBackPressed();
        finish();
    }
}
