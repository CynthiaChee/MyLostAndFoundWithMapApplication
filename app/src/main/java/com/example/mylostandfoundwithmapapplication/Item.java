package com.example.mylostandfoundwithmapapplication;

import androidx.annotation.Nullable;

public class Item {

    private Integer id;
    private String status;
    private String name;
    private String phone;
    private String description;
    private String date;
    private String location;
    private Double latitude;
    private Double longitude;

    public Item(@Nullable Integer id, String status, String name, String phone,
                String description, String date, String location,
                Double latitude, Double longitude) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLatitude() { return latitude;}
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude;}
    public void setLongitude(Double longitude) { this.longitude = longitude; }
}
