package br.com.impacta.curso.prj_022_mvp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements UsuarioContract.mView {

    private Context context;

    private EditText et_nome;
    private EditText et_senha;

    private Button btn_cancelar;
    private Button btn_login;

    private UsuarioContract.mPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();
        //
        et_nome = (EditText) findViewById(R.id.et_nome);
        et_senha = (EditText) findViewById(R.id.et_senha);
        //
        btn_cancelar = (Button) findViewById(R.id.btn_cancelar);
        btn_login = (Button) findViewById(R.id.btn_login);
        //
        mPresenter = new Presenter(
                new RepositorioUsuario(
                        new UsuarioDao()
                ),
                this
        );
    }

    private void initActions() {
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.validar(
                        et_nome.getText().toString(),
                        et_senha.getText().toString()
                );
            }
        });
    }


    @Override
    public void cancelar() {
        et_nome.setText("");
        et_senha.setText("");
        //
        et_nome.requestFocus();
    }

    @Override
    public void exibirMensagem(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void callAct02() {
        Toast.makeText(this, "Indo para a tela 02", Toast.LENGTH_SHORT).show();
    }
}
