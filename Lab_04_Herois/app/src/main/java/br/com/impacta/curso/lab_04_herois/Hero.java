package br.com.impacta.curso.lab_04_herois;

/**
 * Created by rcaru on 24/02/2018.
 */

public class Hero {
    private int _id;
    private String _name;
    private int _image;
    private String _power;

    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public int getImage() {
        return _image;
    }

    public String getPower() {
        return _power;
    }

    Hero(int id, String name, int image, String power) {
        _id = id;
        _name = name;
        _image = image;
        _power = power;
    }



}
