package br.com.impacta.cusro.lab_001_credenciais;

/**
 * Created by Raphael on 03/02/2018.
 */

class AuthService {
    public boolean Auth(User user) {
        if (!user.get_name().toLowerCase().equals("raphael") || !user.get_password().equals("123456")) {
            return false;
        }

        return true;
    }
}
