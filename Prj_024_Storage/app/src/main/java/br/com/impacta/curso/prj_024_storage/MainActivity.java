package br.com.impacta.curso.prj_024_storage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private Button btn_gravar_ce;
    private Button btn_ler_ce;
    //
    private Button btn_gravar_sd;
    private Button btn_ler_sd;
    //
    private Button btn_gravar_sb;
    private Button btn_ler_sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);

        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();
        //
        btn_gravar_ce = (Button) findViewById(R.id.btn_gravar_ce);
        btn_ler_ce = (Button) findViewById(R.id.btn_ler_ce);
        btn_gravar_sd = (Button) findViewById(R.id.btn_gravar_sd);
        btn_ler_sd = (Button) findViewById(R.id.btn_ler_sd);
        btn_gravar_sb = (Button) findViewById(R.id.btn_gravar_sb);
        btn_ler_sb = (Button) findViewById(R.id.btn_ler_sb);
    }

    private void initActions() {
        btn_gravar_ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String extStorage = System.getenv("EXTERNAL_STORAGE");
                //
                gravarFF("Impacta", extStorage + "/ce", "teste.txt");
            }
        });

        btn_ler_ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String extStorage = System.getenv("EXTERNAL_STORAGE");
                //
                String resultado = lerFF(extStorage + "/ce", "teste.txt");
                //
                Toast.makeText(context, resultado, Toast.LENGTH_SHORT).show();
            }
        });

        btn_gravar_sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File [] path = getExternalFilesDirs(null);
                //
                String extStorage = path[1].getPath();
                //
                gravarFF("Impacta", extStorage + "/ce", "teste.txt");
            }
        });

        btn_ler_sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File [] path = getExternalFilesDirs(null);
                //
                String extStorage = path[1].getPath();
                //
                String resultado = lerFF(extStorage + "/ce", "teste.txt");
                //
                Toast.makeText(context, resultado, Toast.LENGTH_SHORT).show();
            }
        });

        btn_gravar_sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File path = getFilesDir();
                //
                String extStorage = path.getPath();
                //
                gravarFF("Impacta", extStorage + "/ce", "teste.txt");
            }
        });

        btn_ler_sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File path = getFilesDir();
                //
                String extStorage = path.getPath();
                //
                String resultado = lerFF(extStorage + "/ce", "teste.txt");
                //
                Toast.makeText(context, resultado, Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void gravarFF(String texto, String caminho, String arquivo) {
        File diretorio = new File(caminho);
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }
        FileWriter f;
        //
        try {
            f = new FileWriter(
                    caminho + "/" + arquivo,
                    true
            );
            //
            f.write(texto);
            f.flush();
            f.close();
        } catch (Exception e) {
        }
    }

    private String lerFF(String caminho, String arquivo) {
        StringBuilder conteudo = new StringBuilder();
        //
        File diretorio = new File(caminho);
        if (!diretorio.exists()) {
            return "Erro";
        }
        //
        File mArquivo = new File(caminho + "/" + arquivo);
        if (!mArquivo.exists()) {
            return "Erro";
        }
        //
        try {
            BufferedReader input = new BufferedReader(new FileReader(mArquivo));
            String linha = null;
            //
            while ((linha = input.readLine()) != null) {
                conteudo.append(linha);
            }
            //
            input.close();
        } catch (Exception e) {
        }
        //
        return conteudo.toString();
    }

}
