package com.ps;

public class Vehicle {
    private int vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;


    public Vehicle(){}


    public Vehicle(String color, int year, int vin, double price, String vehicleType, int odometer, String model, String make) {
        this.color = color;
        this.year = year;
        this.vin = vin;
        this.price = price;
        this.vehicleType = vehicleType;
        this.odometer = odometer;
        this.model = model;
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format(
                " Model: %s | Make: %s | Year: %d| Mileage: %,d mi | Type: %s | Color: %s | Price: $%,.2f | VIN: %d",
                model, make, year, odometer, vehicleType, color, price, vin
        );
    }
}
