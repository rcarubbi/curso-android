package br.com.impacta.curso.prj_010_dbase.model;

/**
 * Created by Raphael on 17/02/2018.
 */

import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class ValidationContract {

    private List<String> errors;

    public ValidationContract() {
        errors = new ArrayList<String>();
    }

    public boolean isNumber(EditText et, String message) {
        String value = et.getText().toString();
        return isNumber(value, message);
    }

    private boolean isNumber(String value) {
        try {
            if (value != null && value.length() > 0)
                Integer.parseInt(value);

            return true;
        }
        catch(Exception ex) {

            return false;
        }
    }

    public boolean isNumber(String value, String message) {
       if (!isNumber(value)) {
           errors.add(message);
           return false;
       } else {
           return true;
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

    public boolean isRequired(EditText et, String message) {
        String value = et.getText().toString();
        return isRequired(value, message);
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

    public boolean isGreaterThan(String value, int minValue, String message) {
        if (isNumber(value)) {
            if (Integer.parseInt(value) <= minValue) {
                errors.add(message);
                return false;
            }
            else
                return true;
        }
        else
            return false;

    }


    public boolean isLessThan(String value, int maxValue, String message) {
        if (isNumber(value)) {
            if (Integer.parseInt(value) >= maxValue) {
                errors.add(message);
                return false;
            }
            else
                return true;
        }
        else
            return false;

    }

    public boolean isGreaterThan(EditText et, int minValue, String message) {
        String value = et.getText().toString();
        return isGreaterThan(value, minValue, message);
    }

    public boolean isLessThan(EditText et, int maxValue, String message) {
        String value = et.getText().toString();

        return isLessThan(value, maxValue, message);
    }
}

