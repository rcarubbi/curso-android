package br.com.impacta.curso.lab_006_calculadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Confirm extends AppCompatActivity {
    private Button btn_confirm;
    private TextView tv_operation;
    private int n1;
    private int n2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        initVars();
        initActions();
    }

    private void initVars() {
        n1 = getIntent().getIntExtra(Constantes.NUM1, -1);
        n2 = getIntent().getIntExtra(Constantes.NUM2, -1);



        tv_operation = findViewById(R.id.tv_operation);
        tv_operation.setText(String.valueOf(n1) + " +  " + String.valueOf(n2));
        btn_confirm = findViewById(R.id.btn_confirmar);
    }

    private void initActions() {
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = n1 + n2;
                Intent data = new Intent();



                data.putExtra(Constantes.RESULTADO_SOMA, result);

                setResult(RESULT_OK, data);
                onBackPressed();
            }
        });
    }


}
