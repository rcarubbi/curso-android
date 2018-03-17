package br.com.impacta.curso.lab_05_herios_baseadapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv_herois;
    private Context context;
    private CustomAdapter<Heroi> customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);
        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();
        lv_herois = findViewById(R.id.lv_herois);
        int[] to = { R.id.celula_iv_icone, R.id.celula_tv_nome};
        String[] from = { "Imagem", "Nome"};
        
        customAdapter = new CustomAdapter<>(context, gerarHerois(), R.layout.celula, from, to);
        lv_herois.setAdapter(customAdapter);
    }

    private List<Heroi> gerarHerois() {
        List<Heroi> retorno = new ArrayList<>();
        retorno.add(new Heroi(1, "Heroi 1", R.drawable.avenger01));
        retorno.add(new Heroi(2, "Heroi 2", R.drawable.avenger02));
        retorno.add(new Heroi(3, "Heroi 3", R.drawable.avenger03));
        return retorno;
    }

    private void initActions() {
        lv_herois.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                customAdapter.setSelectedId(id);
            }
        });
    }
}
