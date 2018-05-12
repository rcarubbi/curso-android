package br.com.impacta.curso.prj_033_bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Set;

/**
 * Created by nalmir on 05/05/2018.
 */

public class ListaDevices extends AppCompatActivity {

    private Context context;

    private BluetoothAdapter mBluetoothAdapter;
    private ListView lv_dispositivos;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.listadevices);

        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();
        //
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //
        lv_dispositivos = (ListView) findViewById(R.id.lv_dispositivos);
        //
        // ArrayList
        Set<BluetoothDevice> dispositivos =
                mBluetoothAdapter.getBondedDevices();
        //
        ArrayAdapter<String> adapterDevices = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1
        );

        if (dispositivos.size() > 0) {
            for (BluetoothDevice dAux : dispositivos) {
                String nomeBT = dAux.getName();
                String macBT = dAux.getAddress();
                //
                adapterDevices.add(nomeBT + ":" + macBT);
            }
        }
        //
        lv_dispositivos.setAdapter(adapterDevices);
    }

    private void initActions() {
        lv_dispositivos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String valor = (String) parent.getItemAtPosition(position);
                //
                retornaMacAddress(valor.substring(valor.length() - 17));
            }
        });
    }

    private void retornaMacAddress(String mac) {
        Intent mIntent = new Intent();
        mIntent.putExtra("endereco", mac);
        setResult(RESULT_OK, mIntent);
        //
        finish();
    }

}
