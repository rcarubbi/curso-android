package br.com.impacta.curso.prj_010_dbase.view.act01;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.impacta.curso.prj_010_dbase.R;
import br.com.impacta.curso.prj_010_dbase.banco.Constantes;
import br.com.impacta.curso.prj_010_dbase.banco.HMAux;
import br.com.impacta.curso.prj_010_dbase.dao.ContatoDao;
import br.com.impacta.curso.prj_010_dbase.view.act02.Act02_main;

public class Act01_main extends AppCompatActivity {

    private Context context;
    private ContatoDao contatoDao;
    private ListView lv_contatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act01_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();
        contatoDao = new ContatoDao(context);
        lv_contatos = findViewById(R.id.act01_main_lvcontatos);
        lv_contatos.setAdapter(new ArrayAdapter<HMAux>(
                context,
                android.R.layout.simple_list_item_1,
                contatoDao.obterListaContatos()
        ));

    }

    private void initActions() {
        lv_contatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HMAux item = (HMAux) parent.getItemAtPosition(position);
                chamarAct02(Long.parseLong(item.get(HMAux.ID)));
            }
        });
    }

    private void chamarAct02(long id){
        Intent intent = new Intent(context, Act02_main.class);
        intent.putExtra(Constantes.PARAMETRO_ID, id);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.act01_main_novo_contato)
        {
            chamarAct02(-1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
