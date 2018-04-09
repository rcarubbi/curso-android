package br.com.impacta.curso.prj_019_drawer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by nalmir on 07/04/2018.
 */

public class FOpcoes extends Fragment {
    private Context context;
    private Button btn_mudar_valor;

    public interface IFOpcoes {
        void mudarValor(String texto);
    }

    private IFOpcoes delegate;

    public void setOnMudarValorListener(IFOpcoes delegate) {
        this.delegate = delegate;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fopcoes,
                container,
                false
        );

        initVars(view);
        initActions();

        return view;
    }

    private void initVars(View view) {
        context = getActivity();

        btn_mudar_valor = (Button)
                view.findViewById(R.id.fopcoes_btn_mudar_valor);
    }

    private void initActions() {
        btn_mudar_valor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (delegate != null){
                    delegate.mudarValor("Android");
                }
            }
        });
    }
}
