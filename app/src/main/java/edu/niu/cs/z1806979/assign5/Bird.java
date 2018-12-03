package edu.niu.cs.z1806979.assign5;

/**
 *    Class:  Bird
 *
 *    @author Ben Lane
 *    @author Jinhong Yao
 *
 *    Bird sighting model. Contains all the data of a single bird sighting.
 */
public class Bird {
    private int id;             // Sighting ID.
    private String type;        // Type of bird.
    private double quantity;    // Number of birds seen.
    private String time;        // Time of the sighting.
    private String date;        // Date of the sighting.
    private String weather;     // Weather during the sighting.

    /**
     * Constructor method for the bird class.
     *
     * @param id        ID of bird
     * @param type      type of bird
     * @param quantity  number of birds seen in the sighting
     * @param time      time of day (morning, afternoon, evening)
     * @param date      what day the sighting occurred
     * @param weather   weather during the sighting
     *
     */
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

    public String getWeather() { return weather; }

    public void setWeather(String weather) { this.weather = weather; }

    public String toString() {
        return type + " " + quantity + " " + time + " " + date + " " + weather;
    }
}

