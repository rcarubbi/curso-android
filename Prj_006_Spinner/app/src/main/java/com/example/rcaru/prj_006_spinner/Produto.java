package com.example.rcaru.prj_006_spinner;

/**
 * Created by rcaru on 24/02/2018.
 */

public class Produto {
    private long _id;
    private String _nome;
    private double _preco;
    private int _quantidade;
    private String _barcode;

    public long getId() {
        return _id;
    }

    public String getNome() {
        return _nome;
    }

    public double getPreco() {
        return _preco;
    }

    public int getQuantidade() {
        return _quantidade;
    }

    public String getBarcode() {
        return _barcode;
    }

    Produto(int id, String nome, double preco, int quantidade, String barcode) {
        this._id = id;
        this._nome = nome;
        this._preco   = preco;
        this._quantidade = quantidade;
        this._barcode = barcode;
    }

    @Override
    public String toString() {
        return this._nome;
    }
}
