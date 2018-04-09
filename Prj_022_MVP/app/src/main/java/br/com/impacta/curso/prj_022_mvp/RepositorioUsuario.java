package br.com.impacta.curso.prj_022_mvp;

/**
 * Created by nalmir on 07/04/2018.
 */

public class RepositorioUsuario {

    Dao usuarioDao;

    public RepositorioUsuario(Dao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Usuario repObterUsuario(long idusuario) {
        return usuarioDao.getUsuarioById(idusuario);
    }

    public boolean repUserCredentials(String nome, String senha){
        return usuarioDao.getCredentiasl(nome, senha);
    }



}
