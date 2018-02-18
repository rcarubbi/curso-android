package br.com.impacta.curso.prj_005_imageview;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_imagem_interna;
    private ImageView iv_imagem_externa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            initVars();
            initActions();
        }

        private void initVars() {
            iv_imagem_interna = findViewById(R.id.iv_imagem_interna);
            iv_imagem_externa = findViewById(R.id.iv_imagem_externa);
        }


        private void initActions() {
            iv_imagem_interna.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iv_imagem_interna.setImageResource(R.drawable.java);
                }
            });

            iv_imagem_externa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String caminho = "/sdcard/DCIM/t1.jpg";
                    iv_imagem_externa.setImageBitmap(BitmapFactory.decodeFile(caminho));
                }
            });
        }
}
