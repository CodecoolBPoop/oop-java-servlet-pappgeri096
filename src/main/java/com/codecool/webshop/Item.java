package com.codecool.webshop;

public class Item {
    private static int count = 0;
    private int id;
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
        this.id  = count++;
    }
}
