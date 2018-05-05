package br.com.impacta.curso.prj_032_camera;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private ImageView iv_foto;
    private Button btn_tf;

    private File file;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);

        initVars(savedInstanceState);
        initActions();
    }

    private void initVars(Bundle savedInstanceState) {
        context = getBaseContext();
        //
        iv_foto = (ImageView) findViewById(R.id.iv_foto);
        btn_tf = (Button) findViewById(R.id.btn_tf);
        //
        if (savedInstanceState == null) {
            path = "/sdcard/DBTeste/foto_"
                    + String.valueOf(System.currentTimeMillis()) +
                    ".jpg";
            file = new File("/sdcard/DBTeste");
            if (!file.exists()) {
                file.mkdir();
            }
            //
            file = new File(path);
        } else {
            path = savedInstanceState.getString("path");
            file = (File) savedInstanceState.getSerializable("file");
        }

    }

    private void initActions() {
        btn_tf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                //
                startActivityForResult(mIntent, 10);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK) {
                    tratarImagem();
                } else {
                    Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }

    private void tratarImagem() {
        // capturou as dimensoes do imageview
        int target_w = iv_foto.getWidth();
        int target_h = iv_foto.getHeight();

        // capturar as dimensoes da imagem
        BitmapFactory.Options options
                = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        int w = options.outWidth;
        int h = options.outHeight;

        int escalaReducao = Math.min((w / target_w), (h / target_h));

        options.inJustDecodeBounds = false;
        options.inSampleSize = escalaReducao;

        Bitmap bm = BitmapFactory.decodeFile(path, options);

        iv_foto.setImageBitmap(bm);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("path", path);
        outState.putSerializable("file", file);
        //
        super.onSaveInstanceState(outState);
    }
}
