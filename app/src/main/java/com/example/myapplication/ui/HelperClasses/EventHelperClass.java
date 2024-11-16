package com.example.myapplication.ui.HelperClasses;

public class EventHelperClass {
    int image;
    String title, category, description;

    public EventHelperClass(int image, String title, String category, String description) {
        this.image = image;
        this.title = title;
        this.category = category;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
