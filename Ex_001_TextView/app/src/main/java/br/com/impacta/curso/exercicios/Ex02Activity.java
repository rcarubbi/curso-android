package br.com.impacta.curso.exercicios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Ex02Activity extends AppCompatActivity {

    private String username;
    private String password;
    private TextView tv_username, tv_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex02);

        this.username = "Sr. Avenida Paulista";
        this.password = "**********";

        tv_username = (TextView) findViewById(R.id.tv_username);
        tv_password = (TextView) findViewById(R.id.tv_password);

        tv_username.setText(this.username);
        tv_password.setText(this.password);
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(Ex02Activity.this, Ex03Activity.class);
        startActivity(intent);
    }

    public void previousActivity(View view) {
        Intent intent = new Intent(Ex02Activity.this, Ex01Activity.class);
        startActivity(intent);
    }
}
