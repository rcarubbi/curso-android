package br.com.impacta.curso.lab_006_calculadora;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_num1;
    private EditText et_num2;
    private Button btn_somar;
    private static final int PROCESSO_SOMA = 10;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();
        et_num1 = findViewById(R.id.et_num1);
        et_num2 = findViewById(R.id.et_num2);
        btn_somar = findViewById(R.id.btn_somar);
    }

    private void initActions() {
        btn_somar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String n1 = et_num1.getText().toString();
                String n2 = et_num2.getText().toString();

                ValidationContract validation = new ValidationContract();
                validation.isRequired(n1, "Primeiro número é obrigatório");
                validation.isRequired(n2, "Segundo número é obrigatório");
                validation.isNumber(n1, "Primeiro número inválido");
                validation.isNumber(n2, "Segundo número inválido");

                if (!validation.isValid()) {
                    Toast.makeText(context, validation.getErrors(), Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(context, Confirm.class);
                    intent.putExtra(Constantes.NUM1, Integer.parseInt(n1));
                    intent.putExtra(Constantes.NUM2, Integer.parseInt(n2));
                    startActivityForResult(intent, PROCESSO_SOMA);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case PROCESSO_SOMA:
                showResult(resultCode, data);
                break;
            default:
                break;
        }

    }

    private void showResult(int resultCode, Intent data) {
        String result = "Cancelado";
        if (resultCode == RESULT_OK) {
            result = String.valueOf(data.getIntExtra(Constantes.RESULTADO_SOMA, -1));
        }
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }
}
