package br.com.impacta.curso.prj_018_fragmento_dyn;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Context context;
    private Button btn_f01;
    private Button btn_f02;
    private int indice = 1;
    private F01 f01;
    private F02 f02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);

        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();
        btn_f01 = findViewById(R.id.telainicial_btn_f01);
        btn_f02 = findViewById(R.id.telainicial_btn_f02);
        f01 = new F01();
        f02 = new F02();

        setFragment(indice);
    }

    private void initActions() {
        btn_f01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indice != 1) {
                    indice = 1;
                    setFragment(indice);
                }
            }
        });

        btn_f02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indice != 2) {
                    indice = 2;
                    setFragment(indice);
                }
            }
        });
    }

    public void setFragment(int indice) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch(indice) {
            case 1:
                ft.replace(R.id.telainicial_ll, f01, "f01");
                break;
            case 2:
                ft.replace(R.id.telainicial_ll, f02, "f02");
                break;
        }
        ft.commit();
    }
}
