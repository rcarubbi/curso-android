package br.com.impacta.curso.prj_015_gdownload;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private ImageView iv_foto;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();
        iv_foto = findViewById(R.id.iv_foto);

    }

    private void initActions() {
        iv_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ServicoDownload().execute();
            }
        });
    }

    private class ServicoDownload extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Download");
            progressDialog.setMessage("Executando o download");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                ToolBox.GDownload(
                        Constantes.Imagem,
                        Constantes.FLOCAL
                );

            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            iv_foto.setImageBitmap(BitmapFactory.decodeFile(Constantes.FLOCAL));

            progressDialog.dismiss();
        }

    }


}
