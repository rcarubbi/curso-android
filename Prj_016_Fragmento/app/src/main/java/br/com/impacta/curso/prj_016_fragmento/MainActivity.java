package br.com.impacta.curso.prj_016_fragmento;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    private Context context;

    private F01 f01;
    private F02 f02;
    private FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();
        fm = getSupportFragmentManager();
        f01 = (F01) fm.findFragmentById(R.id.telainicial_f1);
        f01.setOnMudarTextoListener(new F01.IF01() {
            @Override
            public void mudarTexto(String texto) {
                f02.modificarTexto(texto);
            }
        });
        f02 = (F02) fm.findFragmentById(R.id.telainicial_f2);

    }

    private void initActions() {
    }

}
