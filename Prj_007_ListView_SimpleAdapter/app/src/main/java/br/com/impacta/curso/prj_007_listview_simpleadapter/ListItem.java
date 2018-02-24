package br.com.impacta.curso.prj_007_listview_simpleadapter;

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

    ListItem(String dataValueField, String dataTextField, T item) {
        _dataTextField = dataTextField;
        _dataValueField = dataValueField;
        _item = item;
        LoadValues();
        put(_dataValueField, _dataValue);
        put(_dataTextField, _dataText);
    }

    private void LoadValues() {
        try {
            _dataValue = _item.getClass().getMethod("get" + _dataValueField).invoke(_item).toString();
            _dataText = _item.getClass().getMethod("get" + _dataTextField).invoke(_item).toString();
        }
        catch(Exception ex)
        {
            _dataValue = "-1";
            _dataText = "Unspecified";
        }
    }


    public String getDataValue() {
       return get(_dataValueField);
    }

    public String getDataText() {
        return get(_dataTextField);
    }

    @Override
    public String toString() {
        return getDataText();
    }
}
