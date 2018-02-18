package br.com.impacta.cusro.prj_003_button;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_01;
    private Button btn_02;
    private Button btn_03;
private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);

            initVars();
            initActions();

        }

        private void initVars() {
            context = getBaseContext();
            btn_01 = (Button) findViewById(R.id.btn_01);
            btn_02 = (Button) findViewById(R.id.btn_02);
            btn_03 = (Button) findViewById(R.id.btn_03);
        }


        private void initActions() {
            btn_01.setOnClickListener(actionMenu);
            btn_02.setOnClickListener(actionMenu);
            btn_03.setOnClickListener(actionMenu);
        }

        private View.OnClickListener actionMenu = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String resultado;
                switch (view.getId()){
                    case R.id.btn_01:
                        resultado = "Acionado pelo B01";
                        btn_02.setOnClickListener(null);
                        break;
                    case R.id.btn_02:
                        resultado = "Acionado pelo B02";
                        break;
                    case R.id.btn_03:
                        resultado = "Acionado pelo B03";
                        btn_02.setOnClickListener(actionMenu);
                        break;
                    default:
                        resultado = "Outro Botão";
                        break;
                }
                Toast.makeText(context, resultado, Toast.LENGTH_SHORT).show();
            }
        };
}
