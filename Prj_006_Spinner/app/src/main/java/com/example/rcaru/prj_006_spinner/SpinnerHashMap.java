package com.example.rcaru.prj_006_spinner;

import java.util.HashMap;

/**
 * Created by rcaru on 24/02/2018.
 */

public class SpinnerHashMap extends HashMap<String, String> {

    public static final String ID = "id";
    public static final String FIELD_NAME = "fieldname";

    public String getText() {
        return get(get(FIELD_NAME));
    }

    @Override
    public String toString() {
        return getText();
    }
}
