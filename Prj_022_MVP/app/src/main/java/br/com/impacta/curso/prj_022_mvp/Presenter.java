package br.com.impacta.curso.prj_022_mvp;

/**
 * Created by nalmir on 07/04/2018.
 */

public class Presenter implements UsuarioContract.mPresenter {

    private RepositorioUsuario mRepositorioUsuario;
    private UsuarioContract.mView mView;

    public Presenter(RepositorioUsuario mRepositorioUsuario, UsuarioContract.mView mView) {
        this.mRepositorioUsuario = mRepositorioUsuario;
        this.mView = mView;
    }

    @Override
    public void validar(String nome, String senha) {
        boolean status = mRepositorioUsuario.repUserCredentials(nome, senha);
        //
        if (status){
            mView.callAct02();
        } else {
            mView.exibirMensagem("Erro");
        }
    }
}
