package br.com.impacta.curso.prj_016_fragmento;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by rcaru on 24/03/2018.
 */

public class F01 extends Fragment {

    public interface IF01 {
        void mudarTexto(String texto);
    }

    private IF01 delegate;

    public void setOnMudarTextoListener(IF01 delegate) {
        this.delegate = delegate;
    }

    private Context context;
    private CheckBox cb_android;
    private Button bt_acionar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f01, container, false);

        initVars(view);
        initActions();
        return view;
    }

    private void initActions() {
        bt_acionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_android.isChecked()) {
                    Toast.makeText(context, "Acionado", Toast.LENGTH_SHORT).show();
                } else {
                    if (delegate != null) {
                        delegate.mudarTexto("Mudou!!");
                    }
                }
            }
        });
    }

    private void initVars(View view) {
        context = getActivity();

        cb_android = view.findViewById(R.id.f01_cb_android);
        bt_acionar = view.findViewById(R.id.f01_bt_acionar);

    }
}
