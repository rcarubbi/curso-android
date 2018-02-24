package br.com.impacta.curso.lab_04_herois;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv_herois;
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
        lv_herois = findViewById(R.id.lv_herois);
        String[] From = new String[] { "Name", "Image" };
        int[] To = new int[] { R.id.celula_tv_nome, R.id.celula_iv_foto};

        lv_herois.setAdapter(new SimpleAdapter(
                _context,
                seedHeroes(),
                R.layout.celula,
                From,
                To
        ));
    }

    private ArrayList<ListItem<Hero>> seedHeroes() {
        ArrayList<ListItem<Hero>> heroes = new ArrayList<>();
        heroes.add(new ListItem<>(new String[] {"Id", "Name", "Image", "Power"}, new Hero(1, "Hawkeye", R.drawable.avenger01, "Accuracy")));
        heroes.add(new ListItem<>(new String[] {"Id", "Name", "Image", "Power"}, new Hero(2, "Marvel War Machine", R.drawable.avenger02, "Armor")));
        heroes.add(new ListItem<>(new String[] {"Id", "Name", "Image", "Power"}, new Hero(3, "Thor", R.drawable.avenger03, "God of Thunder")));
        heroes.add(new ListItem<>(new String[] {"Id", "Name", "Image", "Power"}, new Hero(4, "Nick Fury", R.drawable.avenger04, "Strategist")));
        heroes.add(new ListItem<>(new String[] {"Id", "Name", "Image", "Power"}, new Hero(5, "Loki", R.drawable.avenger05, "Force Field, flight, hypnosis")));
        heroes.add(new ListItem<>(new String[] {"Id", "Name", "Image", "Power"}, new Hero(6, "Ironman", R.drawable.avenger06, "Inteligence")));
        heroes.add(new ListItem<>(new String[] {"Id", "Name", "Image", "Power"}, new Hero(7, "Hulk", R.drawable.avenger07, "Unlimited Power")));
        heroes.add(new ListItem<>(new String[] {"Id", "Name", "Image", "Power"}, new Hero(8, "Antman", R.drawable.avenger08, "Shrink Himself")));
        heroes.add(new ListItem<>(new String[] {"Id", "Name", "Image", "Power"}, new Hero(9, "Captain America", R.drawable.avenger09, "Shield")));
        heroes.add(new ListItem<>(new String[] {"Id", "Name", "Image", "Power"}, new Hero(10, "Black Widow", R.drawable.avenger10, "Fight skills")));
        heroes.add(new ListItem<>(new String[] {"Id", "Name", "Image", "Power"}, new Hero(11, "Phil Coulson", R.drawable.avenger11, "None")));
        return heroes;
    }

    private void initActions() {
        lv_herois.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItem<Hero> item = (ListItem<Hero>) parent.getItemAtPosition(position);
                Toast.makeText(_context, item.get("Power"), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
