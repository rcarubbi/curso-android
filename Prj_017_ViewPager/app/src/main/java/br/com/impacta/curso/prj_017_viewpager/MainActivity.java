package br.com.impacta.curso.prj_017_viewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private ViewPager pager;
    private ArrayList<Fragment> telas;

    private F01 f01;
    private F02 f02;
    private F03 f03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);

        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();

        pager = findViewById(R.id.telainicial_mpager);

        gerarTelas();
    }

    private void gerarTelas() {
        telas = new ArrayList<>();
        f01 = new F01();
        f02 = new F02();
        f03 = new F03();
        telas.add(f01);
        telas.add(f02);
        telas.add(f03);
        pager.setAdapter(
                new TelasAdapter(
                        getSupportFragmentManager(),
                        telas
                )
        );
    }

    private class TelasAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> data;

        public TelasAdapter(FragmentManager fm, ArrayList<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }

        @Override
        public int getCount() {
            return data.size();
        }
    }

    private void initActions() {
    }
}
