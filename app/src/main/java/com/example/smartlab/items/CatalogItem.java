package com.example.smartlab.items;

public class CatalogItem {
    private int id;
    private int category;
    private String name;
    private String description;
    private String price;
    private String timeResult;
    private String preparation;
    private String bio;

    private int patients;

    public CatalogItem(int id, int category, String name, String description, String price, String timeResult, String preparation, String bio, int patients) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.timeResult = timeResult;
        this.preparation = preparation;
        this.bio = bio;

        this.patients = patients;
    }
    public int getId() {
        return id;
    }
    public int getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getTimeResult() {
        return timeResult;
    }

    public String getPreparation() {
        return preparation;
    }

    public String getBio() {
        return bio;
    }

    public int getPatients() {
        return patients;
    }

    public void setPatients(int patients) {
        this.patients = patients;
    }
}
