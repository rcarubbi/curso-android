package br.com.impacta.curso.prj_007_listview_baseadapter;

/**
 * Created by rcaru on 03/03/2018.
 */

public class Contato {
    private int id;
    private String nome;
    private String telefone;

    public Contato(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }
}
