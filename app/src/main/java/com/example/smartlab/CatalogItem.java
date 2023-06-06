package com.example.smartlab;

public class CatalogItem {
    private int category;
    private String name;
    private String description;
    private double price;
    private String timeResult;
    private String preparation;
    private String bio;

    public CatalogItem(int category, String name, String description, double price, String timeResult, String preparation, String bio) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.timeResult = timeResult;
        this.preparation = preparation;
        this.bio = bio;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTimeResult() {
        return timeResult;
    }

    public void setTimeResult(String timeResult) {
        this.timeResult = timeResult;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
