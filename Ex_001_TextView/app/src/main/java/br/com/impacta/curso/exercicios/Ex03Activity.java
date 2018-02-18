package br.com.impacta.curso.exercicios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Ex03Activity extends AppCompatActivity {

    private User _user;

    private TextView tv_primaryPhone, tv_secondaryPhone, tv_primaryEmail, tv_secondaryEmail;


    private static final String PRIMARY_PHONE_TYPE = "Casa";
    private static final String SECONDARY_PHONE_TYPE = "Trabalho";
    private static final String PRIMARY_EMAIL_TYPE = "Pessoal";
    private static final String SECONDARY_EMAIL_TYPE = "Trabalho";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex03);

        LoadUser();

        tv_primaryPhone = (TextView) findViewById(R.id.tv_primaryPhone);
        tv_secondaryPhone = (TextView) findViewById(R.id.tv_secondaryPhone);
        tv_primaryEmail = (TextView) findViewById(R.id.tv_primaryEmail);
        tv_secondaryEmail = (TextView) findViewById(R.id.tv_secondaryEmail);

        tv_primaryPhone.setText(_user.getFirstPhoneByType(PRIMARY_PHONE_TYPE).getNumber());
        tv_secondaryPhone.setText(_user.getFirstPhoneByType(SECONDARY_PHONE_TYPE).getNumber());
        tv_primaryEmail.setText(_user.getFirstEmailByType(PRIMARY_EMAIL_TYPE).getAddress());
        tv_secondaryEmail.setText(_user.getFirstEmailByType(SECONDARY_PHONE_TYPE).getAddress());


    }

    private void LoadUser() {
        _user = new User();
        _user.addEmail(new Email("rcarubbi@mail.com", PRIMARY_EMAIL_TYPE));
        _user.addEmail(new Email("raphael.neto@atento.com", SECONDARY_EMAIL_TYPE));
        _user.addPhone(new Phone("(0xx11) 9 9888-1111", PRIMARY_PHONE_TYPE));
        _user.addPhone(new Phone("(0xx11) 9 9888-2222", SECONDARY_PHONE_TYPE));
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(Ex03Activity.this, Ex04Activity.class);
        startActivity(intent);
    }

    public void previousActivity(View view) {
        Intent intent = new Intent(Ex03Activity.this, Ex02Activity.class);
        startActivity(intent);
    }
}
