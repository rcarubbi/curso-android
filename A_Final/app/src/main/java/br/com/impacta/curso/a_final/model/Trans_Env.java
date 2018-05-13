package br.com.impacta.curso.a_final.model;

/**
 * Created by nalmir on 12/05/2018.
 */

public class Trans_Env {

    private String user; // nome usuario
    private String pwd; // senha md5
    private int pacientes; // quantidade de pacientes que o WEBService deve gerar



    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getPacientes() {
        return pacientes;
    }

    public void setPacientes(int pacientes) {
        this.pacientes = pacientes;
    }
}
