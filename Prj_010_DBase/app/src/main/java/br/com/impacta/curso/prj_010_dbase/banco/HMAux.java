package br.com.impacta.curso.prj_010_dbase.banco;

import java.util.HashMap;

/**
 * Created by rcaru on 10/03/2018.
 */

public class HMAux extends HashMap<String, String> {

    public static final String ID = "id";
    public static final String TEXTO_01 = "texto01";
    public static final String TEXTO_02 = "texto02";
    public static final String TEXTO_03 = "texto03";
    public static final String TEXTO_04 = "texto04";
    public static final String TEXTO_05 = "texto05";

    @Override
    public String toString() {
        return this.get(TEXTO_01);
    }
}
