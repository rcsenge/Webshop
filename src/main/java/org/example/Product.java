package org.example;

public class Product {
    private int product_id; //id
    private String name;
    private String description;
    private int unit_price; //price
    private int quantity_in_stock; //in_stock
    private Brand brand; //string
    private Type type; //product_type
    private String category;

    public Product() {

    }

    public Product(int product_id, String name, String description, int unit_price, int quantity_in_stock, Brand brand, Type type) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.unit_price = unit_price;
        this.quantity_in_stock = quantity_in_stock;
        this.brand = brand;
        this.type = type;
        this.category = String.valueOf(type);
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public void setQuantity_in_stock(int quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

