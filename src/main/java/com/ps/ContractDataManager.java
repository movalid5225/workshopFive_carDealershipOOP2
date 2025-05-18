package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class ContractDataManager {
    public void saveContract(Contract contract){
        try(BufferedWriter buffWriter = new BufferedWriter(new FileWriter("contracts.txt"))){
            if(contract instanceof SalesContract sale){
                buffWriter.write(String.format(
                        "SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|100.00|%.2f|%.2f|%s|%.2f",
                        sale.getDate(), sale.getCustomerName(), sale.getCustomerEmail(),
                        sale.getVehicleSold().getVin(), sale.getVehicleSold().getYear(),
                        sale.getVehicleSold().getMake(), sale.getVehicleSold().getModel(),
                        sale.getVehicleSold().getVehicleType(), sale.getVehicleSold().getColor(),
                        sale.getVehicleSold().getOdometer(), sale.getVehicleSold().getPrice(),
                        sale.getVehicleSold().getPrice() * 0.05,
                        sale.getVehicleSold().getPrice() < 10000 ? 295.00 : 495.00,
                        sale.getTotalPrice(), sale.isFinanced() ? "YES" : "NO",
                        sale.getMonthlyPayment()));
            }
            else if (contract instanceof LeaseContract lease) {
                double price = lease.getVehicleSold().getPrice();
                double endValue = price * 0.5;
                double leaseFee = price * 0.07;

                buffWriter.write(String.format(
                        "LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f",
                        lease.getDate(), lease.getCustomerName(), lease.getCustomerEmail(),
                        lease.getVehicleSold().getVin(), lease.getVehicleSold().getYear(),
                        lease.getVehicleSold().getMake(), lease.getVehicleSold().getModel(),
                        lease.getVehicleSold().getVehicleType(), lease.getVehicleSold().getColor(),
                        lease.getVehicleSold().getOdometer(), lease.getVehicleSold().getPrice(),
                        endValue, leaseFee, lease.getTotalPrice(), lease.getMonthlyPayment()
                ));
            }
        }
        catch(Exception e){
            System.out.println("File writing failed");
        }
    }
}
