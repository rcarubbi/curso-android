package br.com.impacta.curso.prj_019_drawer;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class TelaInicialActivity extends AppCompatActivity {

    private Context context;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private FConteudo mFConteudo;
    private FOpcoes mFOpcoes;

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);

        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();
        //
        fm = getSupportFragmentManager();
        //
        mDrawerLayout = (DrawerLayout) findViewById(R.id.telainicial_drawer);
        mFConteudo = (FConteudo) fm.findFragmentById(R.id.telainicial_fconteudo);

        mFOpcoes = (FOpcoes) fm.findFragmentById(R.id.telainicial_fopcoes);
        mFOpcoes.setOnMudarValorListener(new FOpcoes.IFOpcoes() {
            @Override
            public void mudarValor(String texto) {
                mFConteudo.setMudarValorFragmento(texto);
                //
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        //
        mDrawerToggle = new ActionBarDrawerToggle(
                TelaInicialActivity.this,
                mDrawerLayout,
                R.string.drawer_aberto,
                R.string.drawer_fechado
        );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
//
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

    }

    private void initActions() {

    }


    // Menus
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

        // Gambetinha
        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.opc) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
