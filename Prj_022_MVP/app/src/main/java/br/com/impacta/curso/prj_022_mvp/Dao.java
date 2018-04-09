package br.com.impacta.curso.prj_022_mvp;

/**
 * Created by nalmir on 07/04/2018.
 */

public interface Dao {

    Usuario getUsuarioById(long idusuario);

    boolean getCredentiasl(String nome, String senha);

}
