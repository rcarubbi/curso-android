package br.com.impacta.curso.lab_003_promocao;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ListView lv_produtos;
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
        lv_produtos = findViewById(R.id.lv_produtos);
        lv_produtos.setAdapter(new ArrayAdapter<>(
                _context,
                android.R.layout.simple_list_item_1,
                seedProducts(100)
        ));
    }

    private ArrayList<ListItem<Product>> seedProducts(int quantity) {
        ArrayList<ListItem<Product>> list = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            ListItem<Product> item = new ListItem<Product>("Id", "Description", new Product(i));
            list.add(item);
        }
        return list;
    }

    private void initActions() {
        lv_produtos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItem<Product> item = (ListItem<Product>) parent.getItemAtPosition(position);
                Locale locale = new Locale("pt", "BR");
                NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);


                String message = currencyFormatter.format(item.getItem().getPrice());
                if (item.getItem().isInSaleOff()) {
                    message += " - Promoção";
                }
                Toast.makeText(_context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
