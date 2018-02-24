package com.example.rcaru.prj_006_spinner;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Spinner sp_nomes;
    private ArrayAdapter<String> adapter_nomes;
    private ArrayList<String> nomes;


    private Spinner sp_produtos;
    private ArrayAdapter<Produto> adapter_produtos;
    private ArrayList<Produto> produtos;


    private Spinner sp_produtos_hm;
    private ArrayAdapter<SpinnerHashMap> adapter_produtos_hm;
    private ArrayList<SpinnerHashMap> produtos_hm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);

        initVars();
        initActions();
    }




    private void initVars() {
        context = getBaseContext();
        initClasse();
        initEducational();
        initEspecial();


    }

    private void initEspecial() {
        sp_produtos_hm = findViewById(R.id.sp_produtos_hm);
        gerarProdutos_hm(100);
        adapter_produtos_hm = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                produtos_hm);

        adapter_produtos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_produtos_hm.setAdapter(adapter_produtos_hm);
        sp_produtos_hm.setSelection(procurarIndiceProduto_hm(sp_produtos_hm, "Produto HM - 10"));
        SpinnerHashMap produto_hm = (SpinnerHashMap) sp_produtos_hm.getSelectedItem();
    }

    private int procurarIndiceProduto_hm(Spinner spinner, String texto) {
        for (int i = 1; i < spinner.getCount(); i++) {
            SpinnerHashMap item = (SpinnerHashMap) spinner.getItemAtPosition(i);
            if (item.getText().equalsIgnoreCase(texto)){
                return i;
            }
        }

        return 0;
    }

    private void gerarProdutos_hm(int quantidade) {
        produtos_hm = new ArrayList<>();

        SpinnerHashMap inicial = new SpinnerHashMap();
        inicial.put(SpinnerHashMap.FIELD_NAME, "Descricao");
        inicial.put("Descricao", "< Selecionar um produto >");
        inicial.put(SpinnerHashMap.ID, "-1");
        produtos_hm.add(inicial);

        for (int i = 0; i < quantidade; i++) {
            SpinnerHashMap elemento = new SpinnerHashMap();
            elemento.put(SpinnerHashMap.FIELD_NAME, "Descricao");
            elemento.put("Descricao", "Produto HM - " + String.valueOf(i));
            elemento.put(SpinnerHashMap.ID, String.valueOf(i));
            produtos_hm.add(elemento);
        }

    }


    private void initEducational() {
        sp_nomes = findViewById(R.id.sp_nomes);
        gerarNomes(100);
        adapter_nomes= new ArrayAdapter<String>(
                context,
                android.R.layout.simple_spinner_item,
                nomes);

        adapter_nomes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_nomes.setAdapter(adapter_nomes);
        sp_nomes.setSelection(procurarIndice(sp_nomes, "Nome - 10"));
        String nome = (String) sp_nomes.getSelectedItem();
    }

    private void gerarNomes(int quantidade) {
        nomes = new ArrayList<>();

        nomes.add(" < Selecione um nome > ");
        for (int i = 0; i < quantidade; i++) {
             nomes.add("Nome - " + String.valueOf(i));
        }
    }


    private int procurarIndiceProduto(Spinner spinner, String texto) {
        for (int i = 1; i < spinner.getCount(); i++) {
            Produto nome = (Produto) spinner.getItemAtPosition(i);
            if (nome.getNome().equalsIgnoreCase(texto)){
                return i;
            }
        }

        return 0;
    }

    private int procurarIndice(Spinner spinner, String texto) {
        for (int i = 1; i < spinner.getCount(); i++) {
            String nome = (String) spinner.getItemAtPosition(i);
            if (nome.equalsIgnoreCase(texto)){
                return i;
            }
        }

        return 0;
    }

    private void initClasse() {
        sp_produtos = findViewById(R.id.sp_produtos);
        gerarProdutos(100);
        adapter_produtos = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                produtos);

        adapter_produtos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_produtos.setAdapter(adapter_produtos);
        sp_produtos.setSelection(procurarIndiceProduto(sp_produtos, "Produto - 10"));
        Produto produto = (Produto) sp_produtos.getSelectedItem();
    }


    private void gerarProdutos(int quantidade) {
        produtos = new ArrayList<>();
        Produto p = new Produto(-1, "< Selecionar um produto >", 0, 0, "" );

        produtos.add(p);
        for (int i = 0; i < quantidade; i++) {
            Produto elemento = new Produto(i, "Produto - " + String.valueOf(i), 0.5 * i, 2 * i, "Barcode - " + String.valueOf(i));
            produtos.add(elemento);
        }
    }


    private void initActions() {

    }
}
