package br.com.impacta.curso.prj_033_bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.UUID;

public class TelaPrincipal extends AppCompatActivity {

    private static final int PROCESSO_HBT = 10;
    private static final int PROCESSO_CBT = 20;

    private Context context;

    private Button btn_h_bt;
    private Button btn_d_bt;
    private Button btn_c_bt;

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mDispositivo = null;
    private BluetoothSocket mSocket = null;

    // Identificacao Servico
    private UUID myUUDI = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaprincipal);

        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();
        //
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //
        btn_h_bt = (Button) findViewById(R.id.btn_h_bt);
        btn_d_bt = (Button) findViewById(R.id.btn_d_bt);
        btn_c_bt = (Button) findViewById(R.id.btn_c_bt);
    }

    private void initActions() {

        btn_h_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBluetoothAdapter == null) {
                    exibirMensagem("Sem BT ou com Defeito");
                } else if (!mBluetoothAdapter.isEnabled()) {
                    chamarTelaH_BT();
                } else {
                    exibirMensagem("BT ativado");
                }
            }
        });

        btn_d_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBluetoothAdapter != null &&
                        mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.disable();
                    //
                    exibirMensagem("BT Desabilitado!!!");
                }
            }
        });

        btn_c_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarTelaDispositivos();
            }
        });



    }

    private void chamarTelaDispositivos() {
        Intent mIntent = new Intent(context, ListaDevices.class);
        //
        startActivityForResult(mIntent, PROCESSO_CBT);
    }

    private void chamarTelaH_BT() {
        Intent mIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        //
        startActivityForResult(mIntent, PROCESSO_HBT);
    }

    private void exibirMensagem(String texto) {
        Toast.makeText(
                context,
                texto,
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PROCESSO_HBT:
                verficarRAtivacao(resultCode);
                break;
            case PROCESSO_CBT:
                verifcarEndereco(resultCode, data);
                break;
            default:
                break;
        }
    }

    private void verficarRAtivacao(int resultCode) {
        if (resultCode == RESULT_OK) {
            exibirMensagem("BT Habilitado");
        } else {
            exibirMensagem("BT Desabilitado");
        }
    }

    private void verifcarEndereco(int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            String endereco = data.getStringExtra("endereco");
            //
            mDispositivo =
                    mBluetoothAdapter.getRemoteDevice(endereco);
            //
            try{
                // sockete inicializado
                mSocket = mDispositivo.
                        createRfcommSocketToServiceRecord(myUUDI);
                mSocket.connect();
                // acesso ao fluxo de entrada da informacao vindo do acessorio
                //mSocket.getInputStream();


            } catch (Exception e){
                exibirMensagem("Erro: Erro de Conexao");
            }

        } else {
            exibirMensagem("Cancelamento!");
        }
    }
}
