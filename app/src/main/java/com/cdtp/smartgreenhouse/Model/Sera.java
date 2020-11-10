package com.cdtp.smartgreenhouse.Model;

public class Sera {
    private String title, description;
    private int image;
    private double temperature;

    public Sera(String title, String description, int image, double temperature){
        this.title = title;
        this.description = description;
        this.image = image;
        this.temperature = temperature;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
