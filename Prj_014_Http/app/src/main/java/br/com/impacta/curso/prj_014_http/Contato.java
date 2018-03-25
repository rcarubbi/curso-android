package br.com.impacta.curso.prj_014_http;

/**
 * Created by rcaru on 24/03/2018.
 */

public class Contato {

    private String nome;
    private long idcontato;

    public Contato(long idcontato, String nome) {
        this.nome = nome;
        this.idcontato = idcontato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getIdcontato() {
        return idcontato;
    }

    public void setIdcontato(long idcontato) {
        this.idcontato = idcontato;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
