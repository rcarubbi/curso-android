package br.com.impacta.curso.lab_05_herios_baseadapter;

/**
 * Created by rcaru on 03/03/2018.
 */

public class Heroi {

    private long id;
    private String nome;
    private int imagem;

    public Heroi(long id, String nome, int imagem) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getImagem() {
        return imagem;
    }
}
