package com.models;

import java.math.BigDecimal;

public class Product {
    private BigDecimal cost;
    private String name;
    private String location;

    public Product() {}

    public Product(String name, String location, BigDecimal cost) {
        this.name = name;
        this.location = location;
        this.cost = cost;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
