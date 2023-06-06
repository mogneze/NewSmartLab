package com.example.smartlab;

public class NewsItem {

    private String title;
    private String comment;
    private double price;

    public NewsItem(String title, String comment, double price) {
        this.title = title;
        this.comment = comment;
        this.price = price;
    }
    public String getTitle() {
        return title;
    }
    public String getComment() {
        return comment;
    }
    public double getPrice() {
        return price;
    }
}
