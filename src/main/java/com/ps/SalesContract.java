package com.ps;

public class SalesContract extends Contract{
    private static double salesTax = 5;
    private static int recordingFee = 100;
    private boolean isFinanced;

    public SalesContract( Vehicle vehicleSold, String date, String customerName, String customerEmail) {
        super(vehicleSold, date, customerName, customerEmail);
    }

    public SalesContract( Vehicle vehicleSold, String date, String customerName, String customerEmail, boolean finance) {
        super(vehicleSold, date, customerName, customerEmail);
        this.isFinanced = finance;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinance(boolean finance) {
        this.isFinanced = finance;
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice = getVehicleSold().getPrice();

        int processingFee = vehiclePrice < 10000 ? 295 : 495;
        double priceWithTax = vehiclePrice * (1+(salesTax/100));

        return recordingFee + processingFee + priceWithTax;
    }

    @Override
    public double getMonthlyPayment() {
        if(isFinanced) {
            double vehiclePrice = getVehicleSold().getPrice();
            int months;
            double monthlyAPR = vehiclePrice > 10000 ? (4.25 / 12) : (5.25 / 12);
            months = monthlyAPR == (4.25 / 12) ? 48 : 24;
            return (vehiclePrice * monthlyAPR) / (1 - Math.pow((1 + monthlyAPR), -1 * months));
        }
        return 0;
    }
}
