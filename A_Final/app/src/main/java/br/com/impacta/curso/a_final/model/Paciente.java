package br.com.impacta.curso.a_final.model;

/**
 * Created by nalmir on 12/05/2018.
 */

public class Paciente {

    private long idpaciente;
    private String nome;

    public Paciente() {
        this.idpaciente = -1;
        this.nome = "sem nome";
    }

    public Paciente(long idpaciente, String nome) {
        this.idpaciente = idpaciente;
        this.nome = nome;
    }

    public long getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(long idpaciente) {
        this.idpaciente = idpaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
