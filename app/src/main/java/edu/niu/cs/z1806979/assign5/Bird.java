package edu.niu.cs.z1806979.assign5;

public class Bird {
    private int id;
    private String type;
    private double quantity;
    private String time;
    private String date;
    private String weather;

    public Bird(int id, String type, double quantity, String time, String date, String weather) {
        this.id = id;
        this.type = type;
        this.quantity = quantity;
        this.time = time;
        this.date = date;
        this.weather = weather;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        }
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String toString() {
        return id + " " + type + " " + quantity + " " + time + " " + date + " " + weather;
    }
}

