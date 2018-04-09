package br.com.impacta.curso.prj_022_mvp;

/**
 * Created by nalmir on 07/04/2018.
 */

public class Usuario {

    private long idusuario;
    private String nome;
    private String senha;

    public long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(long idusuario) {
        this.idusuario = idusuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
