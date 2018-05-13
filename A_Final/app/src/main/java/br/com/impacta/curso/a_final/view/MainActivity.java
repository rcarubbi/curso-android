package br.com.impacta.curso.a_final.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

import br.com.impacta.curso.a_final.R;
import br.com.impacta.curso.a_final.banco.Constantes;
import br.com.impacta.curso.a_final.banco.HMAux;
import br.com.impacta.curso.a_final.dao.PacienteDao;
import br.com.impacta.curso.a_final.model.Paciente;
import br.com.impacta.curso.a_final.service.Sincronismo;
import br.com.impacta.curso.mlib.ToolBox;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private PacienteDao pacienteDao;

    private ListView lv_pacientes;

    private NovosDados novosDados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);

        initVars();
        initActions();

        //
        novosDados = new NovosDados(); // execucao de acao
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constantes.EVENTO); // filtar a sinalizacao de um evento
        filter.addCategory(Intent.CATEGORY_DEFAULT);

        registerReceiver(novosDados, filter);

        // inicializar o servico
        Intent mIntent = new Intent(context, Sincronismo.class);
        startService(mIntent);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(novosDados);
        super.onDestroy();
    }

    private void initVars() {
        context = getBaseContext();
        //
        pacienteDao = new PacienteDao(context);
        //
        lv_pacientes = (ListView)
                findViewById(R.id.telainicial_lv_pacientes);
        //
        montarLista();
    }

    private void montarLista() {
        String[] De = {HMAux.TEXTO_01};
        int[] Para = {android.R.id.text1};

        ArrayList<HMAux> lp = pacienteDao.obterListaPacientes();

        lv_pacientes.setAdapter(
                new SimpleAdapter(
                        context,
                        pacienteDao.obterListaPacientes(),
                        android.R.layout.simple_list_item_1,
                        De,
                        Para
                )
        );
    }

    private void initActions() {

    }

    private class NovosDados extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            montarLista();
        }
    }

}
