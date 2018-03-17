package br.com.impacta.curso.prj_010_dbase.view.act02;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.impacta.curso.prj_010_dbase.R;
import br.com.impacta.curso.prj_010_dbase.banco.Constantes;
import br.com.impacta.curso.prj_010_dbase.dao.ContatoDao;
import br.com.impacta.curso.prj_010_dbase.model.Contato;
import br.com.impacta.curso.prj_010_dbase.model.ValidationContract;
import br.com.impacta.curso.prj_010_dbase.view.act01.Act01_main;

public class Act02_main extends AppCompatActivity {

    private ContatoDao contatoDao;
    private Context context;
    private long idAtual;
    private EditText et_nome;
    private EditText et_codigo;
    private EditText et_telefone;
    private EditText et_idade;
    private Button bt_salvar;
    private Button bt_excluir;
    private Contato contatoCorrente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act02_main);
        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();
        contatoDao = new ContatoDao(context);
        recuperarParametros();

        et_codigo = findViewById(R.id.act02_main_et_codigo);
        et_nome = findViewById(R.id.act02_main_et_nome);
        et_telefone = findViewById(R.id.act02_main_et_telefone);
        et_idade = findViewById(R.id.act02_main_et_idade);
        bt_excluir = findViewById(R.id.bt_excluir);
        bt_salvar = findViewById(R.id.bt_salvar);
        //Log.d("HUDOID", String.valueOf(idAtual));
        prepararFormulario();
    }

    private void prepararFormulario() {
        if (idAtual == -1) {
            bt_excluir.setEnabled(false);

        } else {
            bt_excluir.setEnabled(true);
            contatoCorrente = contatoDao.obterContatoByID(idAtual);
            et_codigo.setText(String.valueOf(contatoCorrente.getIdcontato()));
            et_nome.setText(contatoCorrente.getNome());
            et_telefone.setText(contatoCorrente.getTelefone());
            et_idade.setText(String.valueOf(contatoCorrente.getIdade()));
        }

    }

    private void recuperarParametros() {
        idAtual = getIntent().getLongExtra(Constantes.PARAMETRO_ID, 0);
    }

    private void initActions() {
        bt_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contatoDao.apagarContato(idAtual);
                chamarAct01();
            }
        });

        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidationContract vc = new ValidationContract();
                vc.isRequired(et_nome, "Nome é obrigatório");
                vc.isRequired(et_idade, "Idade é obrigatório");
                vc.isNumber(et_idade, "Idade inválida");
                vc.isGreaterThan(et_idade, 0, "A idade tem que ser maior que zero");
                vc.isLessThan(et_idade, 100, "A idade tem que ser menor que 100");
                if (vc.isValid()) {
                    salvar();
                    chamarAct01();
                } else {
                    exibirMensagem(vc.getErrors());
                }
            }
        });
    }

    private void exibirMensagem(String mensagem) {
        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
    }

    private void salvar() {
        if (idAtual == -1) {
            Contato novoContato = new Contato(contatoDao.proximoID(),
                    et_nome.getText().toString(),
                    et_telefone.getText().toString(),
                    Integer.parseInt(et_idade.getText().toString()));
            contatoDao.inserirContatoDao(novoContato);
        } else {
            contatoCorrente.setNome(et_nome.getText().toString());
            contatoCorrente.setTelefone(et_telefone.getText().toString());
            contatoCorrente.setIdade(Integer.parseInt(et_idade.getText().toString()));
            contatoDao.alterarContato(contatoCorrente);
        }

    }

    private void alerta() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(Act02_main.this);
        alerta.setTitle("Saída de cadastro");
        alerta.setMessage("Deseja realmente sair?");
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                chamarAct01();
            }
        });
        alerta.setNegativeButton("Não", null);
        alerta.setCancelable(false);
        alerta.show();
    }

    private void chamarAct01() {
        Intent intent = new Intent(context, Act01_main.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        alerta();

    }
}
