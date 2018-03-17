package br.com.impacta.curso.prj_010_dbase.model;

/**
 * Created by rcaru on 10/03/2018.
 */

public class Contato {

    private long idcontato;
    private String nome;
    private String telefone;
    private int idade;

    public Contato(long idcontato, String nome, String telefone, int idade) {
        this.idcontato = idcontato;
        this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;
    }

    public long getIdcontato() {
        return idcontato;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
