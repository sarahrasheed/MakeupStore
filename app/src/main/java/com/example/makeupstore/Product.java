package com.example.makeupstore;

public class Product {
    private int id;
    private String name;
    private double price;
    private String imageName;
    private int quantity;

    public Product(int id, String name, double price, String imageName, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageName = imageName;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getImageName() { return imageName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}

