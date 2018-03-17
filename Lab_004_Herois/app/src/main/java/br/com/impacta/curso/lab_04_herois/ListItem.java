package br.com.impacta.curso.lab_04_herois;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by rcaru on 24/02/2018.
 */

public class ListItem<T> extends HashMap<String, String> {


    private String _dataValueField;
    private String _dataTextField;
    private String _dataText;
    private String _dataValue;
    private T _item;

    public T getItem() {
        return _item;
    }

    ListItem(String[] fields, T item) {
         _item = item;

        for (int i = 0; i < fields.length; i++) {
            put(fields[i], getField(fields[i]));
        }

    }

    private String getField(String fieldname) {
        String value = "Unspecified";
        try {
            value = _item.getClass().getMethod("get" + fieldname).invoke(_item).toString();
        }
        catch(Exception ex)
        {


        }
        return value;
    }




}
