package br.com.impacta.cusro.lab_001_credenciais;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raphael on 03/02/2018.
 */

public class ValidationContract {

    private List<String> errors;

    ValidationContract() {
        errors = new ArrayList<String>();
    }


    public void hasMinLength(String value, int minLength, String message) {
        if (value != null && !value.trim().isEmpty() && value.trim().length() < minLength)
        {
            errors.add(message);
        }
    }


    public void hasMaxLength(String value, int maxLength, String message) {
        if (value != null && value.trim().length() > maxLength)
        {
            errors.add(message);
        }
    }


    public void isRequired(String value, String message) {
        if (value == null || value.trim().isEmpty())
        {
            errors.add(message);
        }
    }

    public boolean isValid()
    {
        return errors.isEmpty();
    }

    public void clear(){
        errors.clear();
    }

    public String getErrors() {
        StringBuilder stb = new StringBuilder();
        for (String error: errors) {
            stb.append(error + "\n");
        }

        String strErrors = stb.toString();

        return strErrors.substring(0, strErrors.length() - 1);
    }

}
