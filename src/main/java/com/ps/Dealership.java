package com.ps;
import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;



    public Dealership(String address, String name, String phone) {
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max){
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle v : this.inventory){
            if(v.getPrice() >= min && v.getPrice() <= max){
                result.add(v);
            }
        }
        return result;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model){
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle v : this.inventory){
            if(v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model)){
                result.add(v);
            }
        }
        return result;

    }

    public List<Vehicle> getVehiclesByColor(String color){
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle v : this.inventory){
            if(v.getColor().equalsIgnoreCase(color)){
                result.add(v);
            }
        }
        return result;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max){
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle v : this.inventory){
            if(v.getOdometer() >= min && v.getOdometer() <= max){
                result.add(v);
            }
        }
        return result;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType){
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle v : this.inventory){
            if(v.getVehicleType().equalsIgnoreCase(vehicleType)){
                result.add(v);
            }
        }
        return result;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max){
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle v : this.inventory){
            if(v.getYear() >= min && v.getYear() <= max){
                result.add(v);
            }
        }
        return result;
    }


    public List<Vehicle> getAllVehicles(){
        return this.inventory;
    }

    public void addVehicle(Vehicle vehicle){
        this.inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle){
        this.inventory.remove(vehicle);
    }


//  Getters and Setters
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Vehicle> getInventory() {
        return this.inventory;
    }

    public void setInventory(ArrayList<Vehicle> inventory) {
        this.inventory = inventory;
    }
}
