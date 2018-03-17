package br.com.impacta.curso.prj_011_bs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int indice = 0;
    private Button btn_abacaxi;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);

        initVars();
        initActions();
    }

    private void initVars() {
        btn_abacaxi = findViewById(R.id.btn_abacaxi);
        context = getBaseContext();
    }

    private void initActions() {
        btn_abacaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("ABACAXI");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra("indice", ++indice);
                context.sendBroadcast(intent);
            }
        });
    }
}
