package com.ps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class DealershipFileManager {
    public Dealership getDealership(){
        try {
            BufferedReader buffReader = new BufferedReader(new FileReader("inventory.txt"));
            String header = buffReader.readLine();

            String vehicleInput;
            if (header == null || header.trim().isEmpty()) {
                System.out.println("The inventory file is empty. Exiting the program.");
                System.exit(0);
            }
            String[] dealershipList = header.split("\\|");

            String dealershipName = dealershipList[0];
            String dealershipAddress = dealershipList[1];
            String dealershipPhoneNumber = dealershipList[2];

            Dealership dealership = new Dealership(dealershipAddress, dealershipName, dealershipPhoneNumber);

            while((vehicleInput=buffReader.readLine()) != null){
                Vehicle vehicle = getVehicle(vehicleInput);
                dealership.addVehicle(vehicle);
            }
            return dealership;
        }
        catch(Exception e){
            System.out.println("FILE NOT READ");
        }
        return null;
    }

    //helper
    public Vehicle getVehicle(String input) {
        String[] vehicleList = input.split("\\|");
        int vin = Integer.parseInt(vehicleList[0]);
        int year = Integer.parseInt(vehicleList[1]);
        String make = vehicleList[2];
        String model = vehicleList[3];
        String vehicleType = vehicleList[4];
        String color = vehicleList[5];
        int odometer = Integer.parseInt(vehicleList[6]);
        double price = Double.parseDouble(vehicleList[7]);

        return new Vehicle(color, year, vin, price, vehicleType, odometer, make, model);
    }

    public void saveDealership(Dealership dealership){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.txt"))) {

            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            writer.newLine();

            for (Vehicle v : dealership.getAllVehicles()) {
                writer.write(v.getVin() + "|" + v.getYear() + "|" + v.getMake() + "|" + v.getModel() + "|" +
                        v.getVehicleType() + "|" + v.getColor() + "|" + v.getOdometer() + "|" + v.getPrice());
                writer.newLine();
            }


        } catch (Exception e) {
            System.out.println("FILE NOT SAVED.");
        }
    }
}
