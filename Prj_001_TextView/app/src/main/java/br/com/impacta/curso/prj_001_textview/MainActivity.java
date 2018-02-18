package br.com.impacta.curso.prj_001_textview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_hugo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);

        // Iniciar a minha variavel a partir do XML da tela
        tv_hugo = (TextView) findViewById(R.id.tv_sistema);
        tv_hugo.setText("Impacta");
        tv_hugo.setTextColor(getResources()
                .getColor(R.color.azul));
    }
}
