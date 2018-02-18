package br.com.impacta.curso.exercicios;


class Email {
    private String _address;
    private String _type;

    Email(String address, String type) {
        _address = address;
        _type = type;
    }

    String getType() {
        return _type;
    }

    String getAddress() {
        return _address;
    }
}
