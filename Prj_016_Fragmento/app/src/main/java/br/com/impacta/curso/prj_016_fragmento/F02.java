package br.com.impacta.curso.prj_016_fragmento;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by rcaru on 24/03/2018.
 */

public class F02 extends Fragment {

    private Context context;
    private TextView tv_valor;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f02, container, false);

        initVars(view);
        initAcitons();
        return view;
    }

    private void initAcitons() {

    }

    private void initVars(View view) {
        context = getActivity();
        tv_valor = view.findViewById(R.id.f02_tv_valor);
    }

    public void modificarTexto(String texto) {
        tv_valor.setText(texto);
    }
}
