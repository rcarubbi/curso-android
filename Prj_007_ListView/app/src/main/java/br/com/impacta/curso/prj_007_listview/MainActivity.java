package br.com.impacta.curso.prj_007_listview;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv_contatos;
    private Context _context;

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

        lv_contatos.setAdapter(new ArrayAdapter<>(
                _context,
                R.layout.simple_list_item_1,
                gerarContatos(100)
        ));
    }

    private ArrayList<SpinnerHashMap> gerarContatos(int quantidade) {
        ArrayList<SpinnerHashMap> contatos = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            SpinnerHashMap item = new SpinnerHashMap();
            item.put(SpinnerHashMap.ID, String.valueOf(i));
            item.put(SpinnerHashMap.FIELD_NAME, "Texto");
            item.put("Texto", "Nome - " + String.valueOf(i));
            contatos.add(item);
        }
        return contatos;
    }

    private void initActions() {
        lv_contatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SpinnerHashMap item = (SpinnerHashMap) parent.getItemAtPosition(position);
                Toast.makeText(_context, item.getText(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}




