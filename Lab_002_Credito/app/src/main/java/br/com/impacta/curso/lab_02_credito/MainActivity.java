package br.com.impacta.cusro.lab_02_credito;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private UIHelper uiHelper;
    private EditText et_nome;
    private EditText et_idade;
    private CheckBox cb_sp;
    private Button btn_analisar;
    private  ValidationContract validator;
    private Cliente cliente;
    private LinearLayout ll_nome;
    private LinearLayout ll_idade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);

        initVars();
        initAcitons();
    }

    private void initAcitons() {
        btn_analisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibirCredito();
            }
        });
    }

    private void exibirCredito() {
        String nome = et_nome.getText().toString();
        String idade = et_idade.getText().toString();
        boolean isSp = cb_sp.isChecked();

       if (!validator.isRequired(nome, "Informe o nome"))
       {
           uiHelper.addHighlight(ll_nome);
       }
       else
       {
           uiHelper.removeHightlight(ll_nome);
       }

       if (!validator.isRequired(idade, "Informe a idade") ||
        !validator.isNumber(idade, "Idade inválida"))
       {
           uiHelper.addHighlight(ll_idade);
       }
       else {
           uiHelper.removeHightlight(ll_idade);
       }

        if (!validator.isValid()) {
            uiHelper.showShortToast(validator.getErrors());
            validator.clear();
        } else {
            cliente = new Cliente(nome, Integer.parseInt(idade), isSp);
            Locale locale = new Locale("pt", "BR");
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            uiHelper.showShortToast(String.format("Credito disponível: %s", currencyFormatter.format(cliente.analisarCredito())));
        }
    }


    private void initVars() {
        ll_nome = findViewById(R.id.ll_nome);
        ll_idade = findViewById(R.id.ll_idade);
        uiHelper = new UIHelper(getBaseContext());
        validator = new ValidationContract();
        et_nome = findViewById(R.id.et_nome);
        et_idade = findViewById(R.id.et_idade);
        cb_sp = findViewById(R.id.cb_sp);
        btn_analisar = findViewById(R.id.btn_analisar);
    }
}
