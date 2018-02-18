package br.com.impacta.cusro.lab_02_credito;

/**
 * Created by Raphael on 17/02/2018.
 */

public class Cliente {
    private String _nome;
    private int _idade;
    private Boolean _isSP;

    public double analisarCredito() {
        double credito = 0;
        if (_isSP) {
            if (_idade >= 25) {
                credito = 5000;
            } else {
                credito = 1000;
            }
        }
        else
        {
            if (_idade >= 25) {
                credito = 2500;
            }
            else
            {
                credito = 0;
            }
        }
        return credito;
    }

    public Cliente(String nome, int idade, boolean isSP) {
        _nome = nome;
        _idade = idade;
        _isSP = isSP;
    }
}
