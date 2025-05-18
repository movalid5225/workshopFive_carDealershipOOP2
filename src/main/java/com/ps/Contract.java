package com.ps;

public abstract class Contract {
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;


    public Contract( Vehicle vehicleSold, String date, String customerName, String customerEmail) {
        this.vehicleSold = vehicleSold;
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getDate() {
        return date;
    }
    public String getCustomerName() {
        return customerName;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();
}
