package br.com.impacta.cusro.prj_002_edittext;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private EditText et_nome;
    private Button btn_mv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);

        initVars();
        initActions();
    }

    private void initActions() {
        btn_mv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View c){
                processamentoTexto();
            }
        });
    }


    private void processamentoTexto() {
        String nome = et_nome.getText().toString();
        Toast.makeText(context, nome, Toast.LENGTH_SHORT).show();
    }

    private void initVars() {
        context = getBaseContext();
        et_nome = (EditText)findViewById(R.id.et_nome);
        btn_mv   = (Button)findViewById((R.id.btn_mv));
    }


}
