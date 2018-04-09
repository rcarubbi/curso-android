package br.com.impacta.curso.prj_022_mvp;

/**
 * Created by nalmir on 07/04/2018.
 */

public interface UsuarioContract {

    interface mView {
        void cancelar();

        void exibirMensagem(String texto);

        void callAct02();
    }

    interface mPresenter{
        void validar(String nome, String senha);
    }


}
