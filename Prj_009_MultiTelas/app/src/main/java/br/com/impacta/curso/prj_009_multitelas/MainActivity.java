package br.com.impacta.curso.prj_009_multitelas;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button btn_cs;
    private Button btn_cr;
    private Button btn_ct;
    private Context context;

    private static final int PROCESSO_M100 = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();
        btn_cs = findViewById(R.id.activity_main_btn_cs);
        btn_cr = findViewById(R.id.activity_main_btn_cr);
        btn_ct = findViewById(R.id.activity_main_btn_ct);
    }

    private void initActions() {
        btn_cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Calculo.class);
                intent.putExtra(Constantes.PARAMETRO_TIPO, 1);
                intent.putExtra(Constantes.PARAMETRO_VALOR, 50);
                startActivity(intent);
                finish();
                Toast.makeText(context, "Teste", Toast.LENGTH_SHORT).show();
            }
        });

        btn_cr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Calculo.class);
                intent.putExtra(Constantes.PARAMETRO_TIPO, 2);
                intent.putExtra(Constantes.PARAMETRO_VALOR, 100);

                startActivityForResult(intent, PROCESSO_M100);


            }
        });



        btn_ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "973166092"));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PROCESSO_M100:
                processar_M100(resultCode, data);
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void processar_M100(int resultCode, Intent data) {
        String resultado = null;
        if (resultCode == RESULT_OK) {
            resultado = String.valueOf(data.getIntExtra(Constantes.PARAMETRO_RETORNO, -1));
        } else {
            resultado = "Cancelado!";
        }

        Toast.makeText(context, resultado, Toast.LENGTH_SHORT).show();
    }
}
