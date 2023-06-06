package com.example.smartlab;

public class CategoryItem {
    private int id;
    private String title;

    public CategoryItem(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
}
