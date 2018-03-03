package br.com.impacta.curso.prj_007_listview_interface;

/**
 * Created by rcaru on 03/03/2018.
 */

class Post {

    private long id;
    private String texto;
    private boolean gostei;

    public Post(long id, String texto, boolean gostei) {
        this.id = id;
        this.texto = texto;
        this.gostei = gostei;
    }

    public long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public boolean getGostei() {
        return gostei;
    }

    public void setGostei(boolean gostei) {
        this.gostei = gostei;
    }
}
