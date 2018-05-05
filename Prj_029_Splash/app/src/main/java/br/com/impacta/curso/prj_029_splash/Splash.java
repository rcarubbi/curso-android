package br.com.impacta.curso.prj_029_splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by nalmir on 28/04/2018.
 */

public class Splash extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3 * 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        //
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Intent mIntent = new Intent(
                                Splash.this,
                                MainActivity.class);
                        //
                        startActivity(mIntent);
                        //
                        finish();
                    }
                },
                SPLASH_TIME_OUT
        );
    }
}
