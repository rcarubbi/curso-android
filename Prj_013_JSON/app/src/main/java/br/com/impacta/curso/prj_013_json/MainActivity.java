package br.com.impacta.curso.prj_013_json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_gerarJson;
    private Button btn_LerJson;

    private Button btn_gerarGson;
    private Button btn_LerGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        initVars();
        initActions();
    }

    private List<Contato> gerarContatos(int quantidade) {
        List<Contato> contatos = new ArrayList<>();
        //
        for (int i = 1; i <= quantidade; i++) {
            Contato contato = new Contato(i, "Nome - " + String.valueOf(i));
            contatos.add(contato);
        }
        //
        return contatos;
    }


    private List<JSONObject> gerarContatosJSON(int quantidade) {
        List<JSONObject> contatos = new ArrayList<>();
        //
        for (int i = 1; i <= quantidade; i++) {
            Contato contato = new Contato(i, "Nome - " + String.valueOf(i));
            contatos.add(contato.toJSONObject());
        }
        //
        return contatos;
    }

    private void initVars() {
        btn_gerarJson = findViewById(R.id.btn_gerarJson);
        btn_LerJson = findViewById(R.id.btn_lerJson);
        btn_gerarGson = findViewById(R.id.btn_gerarGson);
        btn_LerGson = findViewById(R.id.btn_lerGson);

    }

    private void initActions() {

        btn_gerarJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONArray ja = new JSONArray(gerarContatosJSON(20));
                JSONObject transmissao = new JSONObject();
                try {
                    transmissao.put("contatos", ja);
                    Log.d("JSON", ja.toString());
                    Log.d("JSON", transmissao.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        btn_LerJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder server = new StringBuilder();
                server.append("{\"contatos\":[{\"idcontato\":1,\"nome\":\"Nome - 1\"}," +
                        "{\"idcontato\":2,\"nome\":\"Nome - 2\"}," +
                        "{\"idcontato\":3,\"nome\":\"Nome - 3\"}," +
                        "{\"idcontato\":4,\"nome\":\"Nome - 4\"}," +
                        "{\"idcontato\":5,\"nome\":\"Nome - 5\"}," +
                        "{\"idcontato\":6,\"nome\":\"Nome - 6\"}," +
                        "{\"idcontato\":7,\"nome\":\"Nome - 7\"}," +
                        "{\"idcontato\":8,\"nome\":\"Nome - 8\"}," +
                        "{\"idcontato\":9,\"nome\":\"Nome - 9\"}," +
                        "{\"idcontato\":10,\"nome\":\"Nome - 10\"}," +
                        "{\"idcontato\":11,\"nome\":\"Nome - 11\"}," +
                        "{\"idcontato\":12,\"nome\":\"Nome - 12\"}," +
                        "{\"idcontato\":13,\"nome\":\"Nome - 13\"}," +
                        "{\"idcontato\":14,\"nome\":\"Nome - 14\"}," +
                        "{\"idcontato\":15,\"nome\":\"Nome - 15\"}," +
                        "{\"idcontato\":16,\"nome\":\"Nome - 16\"}," +
                        "{\"idcontato\":17,\"nome\":\"Nome - 17\"}," +
                        "{\"idcontato\":18,\"nome\":\"Nome - 18\"}," +
                        "{\"idcontato\":19,\"nome\":\"Nome - 19\"}," +
                        "{\"idcontato\":20,\"nome\":\"Nome - 20\"}]}");

                try {
                    JSONObject recebimento = new JSONObject(server.toString());
                    JSONArray ja = recebimento.getJSONArray("contatos");
                    ArrayList<Contato> contatos = new ArrayList<>();
                    for (int i = 0; i < ja.length(); i++) {
                        contatos.add(new Contato(ja.getJSONObject(i)));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


        btn_gerarGson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                Transmissao t = new Transmissao();
                t.contatos = gerarContatos(20);
                server = gson.toJson(t);
            }
        });


        btn_LerGson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                Transmissao rec = gson.fromJson(server.toString(),
                        Transmissao.class);

            }
        });
    }

    private String server;
}
