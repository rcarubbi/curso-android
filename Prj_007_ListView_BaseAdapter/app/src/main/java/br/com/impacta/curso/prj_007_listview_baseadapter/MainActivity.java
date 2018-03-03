package br.com.impacta.curso.prj_007_listview_baseadapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv_contatos;
    private Context context;
    private CustomAdapter<Contato> customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);
        initVars();
        initActions();
    }

    private void initVars() {
        lv_contatos = findViewById(R.id.tela_inicial_lv_contatos);
        context=  getBaseContext();
        String[] from = { "Nome", "Telefone"};
        int[] to = {R.id.celula_tv_nome, R.id.celula_tv_telefone};
        customAdapter = new CustomAdapter<>(context, R.layout.celula, montarContatos(100), from, to);
        lv_contatos.setAdapter(customAdapter);
    }

    private List<Contato> montarContatos(int count) {
        List<Contato> retorno = new ArrayList<>();
        for (int i = 0; i< count; i++) {
            retorno.add(new Contato(i, "Contato" + String.valueOf(i+1), "97316609" + String.valueOf(i)));
        }
        return retorno;
    }


    private void initActions() {
        lv_contatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                customAdapter.setSelectedId(id);
            }
        });
    }
}
