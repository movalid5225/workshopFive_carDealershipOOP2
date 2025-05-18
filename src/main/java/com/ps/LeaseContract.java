package com.ps;

public class LeaseContract extends Contract{

    public LeaseContract(Vehicle vehicleSold, String date, String customerName, String customerEmail) {
        super(vehicleSold, date, customerName, customerEmail);
    }

    @Override
    public double getTotalPrice() {
        Vehicle v = getVehicleSold();
        double endingPrice = v.getPrice() * .5;
        double leaseFee = v.getPrice() *.07;

        return endingPrice + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double price = getTotalPrice();
        double rate = .04/12;
        int months = 36;

        return (price * rate) / (1-Math.pow(1+rate,-months));
    }
}
