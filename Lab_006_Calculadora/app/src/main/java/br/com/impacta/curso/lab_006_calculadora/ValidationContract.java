package br.com.impacta.curso.lab_006_calculadora;

/**
 * Created by Raphael on 17/02/2018.
 */

import java.util.ArrayList;
import java.util.List;

public class ValidationContract {

    private List<String> errors;

    ValidationContract() {
        errors = new ArrayList<String>();
    }

    public boolean isNumber(String value, String message) {
        try {
            if (value != null && value.length() > 0)
                Integer.parseInt(value);

            return true;
        }
        catch(Exception ex) {
            errors.add(message);
            return false;
        }
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


    public boolean isRequired(String value, String message) {

        if (value == null || value.trim().isEmpty())
        {
            errors.add(message);
            return false;
        }

        return true;
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

