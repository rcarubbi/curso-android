package br.com.impacta.curso.lab_003_promocao;

/**
 * Created by rcaru on 24/02/2018.
 */

public class Product {
    private long _id;
    private double _price;
    private String _description;

    public long getId() {
        return _id;
    }

    public double getPrice() {
        return _price;
    }

    public boolean isInSaleOff() {
        return (_id + 1) % 2 == 0;
    }

    public String getDescription() {
        return _description;
    }

    Product(long id, String description, double price) {
        _id = id;
        _price = price;
        _description = description;
    }

    Product(long seedIndex) {
        _id = seedIndex;
        _price = seedIndex * 1.5;
        _description = "Produto " + String.valueOf(seedIndex + 1);
    }
}
