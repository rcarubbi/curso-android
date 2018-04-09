package br.com.impacta.curso.prj_022_mvp;

/**
 * Created by nalmir on 07/04/2018.
 */

public class UsarioDaoMock implements Dao {

    @Override
    public Usuario getUsuarioById(long idusuario) {
        if (idusuario == 0) {
            return null;
        } else {
            Usuario mAux = new Usuario();
            mAux.setIdusuario(idusuario);
            mAux.setNome("Nome - " + String.valueOf(idusuario));
            mAux.setSenha("T123");
            //
            return mAux;
        }
    }

    @Override
    public boolean getCredentiasl(String nome, String senha) {
        if (nome.trim().isEmpty()) {
            return false;
        }
        //
        if (senha.trim().isEmpty()) {
            return false;
        }
        //
        if (!nome.trim().equalsIgnoreCase("Hugo")
                || !senha.trim().equals("T123")) {
            return false;
        }
        //
        return true;
    }
}
