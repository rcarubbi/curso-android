package br.com.impacta.curso.prj_019_drawer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by nalmir on 07/04/2018.
 */

public class FConteudo extends Fragment {

    private Context context;

    private TextView tv_valor;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fconteudo,
                container,
                false
        );

        initVars(view);
        initActions();

        return view;
    }

    private void initVars(View view) {
        context = getActivity();
        //
        tv_valor = (TextView)
                view.findViewById(R.id.fconteudo_tv_valor);
    }

    private void initActions() {
    }

    public void setMudarValorFragmento(String texto){
        if (tv_valor != null){
            tv_valor.setText(texto);
        }
    }
}
