package br.com.impacta.cusro.lab_001_credenciais;

/**
 * Created by Raphael on 03/02/2018.
 */

public class User {

    private String _name;
    private String _password;

    public User(String name, String password) {
        _name = name.trim();
        _password = password.trim();
    }

    public String get_name() {
        return _name;
    }

    public String get_password() {
        return _password;
    }


}
