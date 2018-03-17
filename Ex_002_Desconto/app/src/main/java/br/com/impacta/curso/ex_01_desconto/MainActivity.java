package br.com.impacta.cusro.ex_01_desconto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText et_price;
    private EditText et_discount;
    private double full_price = 2000.0;
    private double discount = 0;

    private TextWatcher tw_discount = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            et_price.removeTextChangedListener(tw_price);

            try {

                double typed_discount = Double.parseDouble(et_discount.getText().toString());

                double discounted_price = (1 - typed_discount / 100) * full_price;
                DecimalFormat f = new DecimalFormat("##.00");
                et_price.setText(String.valueOf(f.format(discounted_price)));

            } catch (Exception ex) {
                et_price.setText(String.valueOf(full_price));
            }
            et_price.addTextChangedListener(tw_price);
        }
    };

    private TextWatcher tw_price = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            et_discount.removeTextChangedListener(tw_discount);

            try {
                double typed_price = Double.parseDouble(et_price.getText().toString());
                double discount_rate = (1 - typed_price / full_price) * 100;
                DecimalFormat f = new DecimalFormat("##.00");
                et_discount.setText(String.valueOf(f.format(discount_rate)));
            } catch (Exception ex) {
                et_discount.setText("");
            }


            et_discount.addTextChangedListener(tw_discount);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);

        initVars();
        initActions();

    }

    private void initVars() {
        et_discount = (EditText) findViewById(R.id.et_discount);
        et_price = (EditText) findViewById(R.id.et_price);
        et_discount.setText("");
        et_price.setText(String.valueOf(full_price));
    }


    private void initActions() {
        et_discount.addTextChangedListener(tw_discount);
        et_price.addTextChangedListener(tw_price);
    }
}
