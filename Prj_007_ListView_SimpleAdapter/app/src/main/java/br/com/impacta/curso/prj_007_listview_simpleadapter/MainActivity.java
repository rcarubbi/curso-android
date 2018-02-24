package br.com.impacta.curso.prj_007_listview_simpleadapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Context _context;
    private ListView lv_contatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);
        initVars();
        initActions();
    }

    private void initVars() {
        _context = getBaseContext();
        lv_contatos = findViewById(R.id.lv_contatos);
        initCol();
    }

    private void initCol() {
        String[] From = new String[] { "texto01", "texto02" };
        int[] To = new int[] { R.id.celula_tv_nome, R.id.celula_tv_telefone};

        lv_contatos.setAdapter(
                new SimpleAdapter(_context,
                        seedContacts(20),
                        R.layout.celula,
                        From,
                        To 
        ));
    }

    private List<HashMap<String, String>> seedContacts(int quantity) {
        ArrayList<HashMap<String, String>> items = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("id", String.valueOf(i));
            item.put("texto01", "Nome - " + String.valueOf(i));
            item.put("texto02", "Telefone - " + String.valueOf(i));
            items.add(item);
        }
        return items;
    }

    private void initActions() {
    }
}
