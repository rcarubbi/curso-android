package br.com.impacta.curso.prj_013_json;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rcaru on 17/03/2018.
 */

public class Contato {

    public static final String IDCONTATO = "idcontato";
    public static final String NOME = "nome";


    private long idcontato;
    private String nome;

    public long getIdcontato() {
        return idcontato;
    }

    public String getNome() {
        return nome;
    }

    public Contato() {
        this.idcontato = -1;
        this.nome = "Sem nome";
    }

    public Contato(JSONObject json) {
        try {
            this.idcontato = json.getLong(IDCONTATO);
            this.nome = json.getString(NOME);
        } catch (JSONException e) {
            this.idcontato = -1;
            this.nome = "Sem nome";
        }
    }

    public Contato(long idcontato, String nome) {
        this.idcontato = idcontato;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public JSONObject toJSONObject() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(IDCONTATO, getIdcontato());
            jsonObject.put(NOME, getNome());
            return jsonObject;
        } catch(Exception e) {
            return null;
        }
    }
}
