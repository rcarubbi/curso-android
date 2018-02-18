package br.com.impacta.curso.exercicios;


class Phone {

    private String _number;
    private String _type;

    Phone(String number, String type) {
        _number = number;
        _type = type;
    }

    String getType() {
        return _type;
    }

    String getNumber() {
        return _number;
    }
}
