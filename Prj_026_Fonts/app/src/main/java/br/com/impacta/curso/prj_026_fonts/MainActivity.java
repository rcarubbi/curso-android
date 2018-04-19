package br.com.impacta.curso.prj_026_fonts;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_android;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);

        initVars();
        initActions();
    }

    private void initVars() {
        tv_android = (TextView) findViewById(R.id.tv_android);
        Typeface font = Typeface.createFromAsset(getAssets(), "JollyLodger-Regular.ttf");
        tv_android.setTypeface(font);
        tv_android.setTextSize(50);
    }

    private void initActions() {

    }

}
