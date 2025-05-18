package com.ps;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    public static final Scanner scanner = new Scanner(System.in);
    public Dealership dealership;
    public final DealershipFileManager fileManager = new DealershipFileManager();
    public ContractDataManager contractDataManager = new ContractDataManager();
    public ArrayList<Contract> contracts = new ArrayList<>();

    public void init(){
      this.dealership = fileManager.getDealership();
    }





    public UserInterface() {
        init();
    }





    public void display(){
        int choice;
        do {
            displayMenu();
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    while (choice > 10 || choice < 0) {
                        System.out.println("Enter a valid choice: ");
                        choice = Integer.parseInt(scanner.nextLine());
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Enter a valid number: ");
                }
            }

            switch (choice) {
                    case 1:
                        processGetByPriceRequest();
                        break;
                    case 2:
                        processGetByMakeModelRequest();
                        break;
                    case 3:
                        processGetByYearRequest();
                        break;
                    case 4:
                        processGetByColorRequest();
                        break;
                    case 5:
                        processGetByMileageRequest();
                        break;
                    case 6:
                        processGetByVehicleTypeRequest();
                        break;
                    case 7:
                        processGetAllVehiclesRequest();
                        break;
                    case 8:
                        processAddVehicleRequest();
                        break;
                    case 9:
                        processRemoveVehicleRequest();
                        break;
                    case 10:
                        processSalesLeaseRequest();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Command not found. Try again");
            }
        }while(choice != 0);
    }

    private void processSalesLeaseRequest() {
        System.out.println("Enter the vin of the vehicle you are interested in: ");
        int vin = Integer.parseInt(scanner.nextLine());

        Vehicle vehicle = getVehicleByVIN(vin);
        System.out.println("Enter your first name: ");
        String name = scanner.nextLine();

        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        String date = LocalDate.now().toString();

        System.out.println("1)Sale");
        System.out.println("2)Lease");
        int saleOrLease = Integer.parseInt(scanner.nextLine());

        Contract contract;
        switch (saleOrLease){
            case 1:
                System.out.println("Will you be financing the vehicle? ");
                boolean isFinanced = scanner.nextLine().equals("yes");
                Contract salesContract = new SalesContract(vehicle, date, name, email, isFinanced);
                contracts.add(salesContract);
                contractDataManager.saveContract(salesContract);
                dealership.removeVehicle(vehicle);
                break;
            case 2:
                Contract leaseContract = new LeaseContract(vehicle, date, name, email);
                contracts.add(leaseContract);
                contractDataManager.saveContract(leaseContract);
                dealership.removeVehicle(vehicle);
                break;
        }
    }



    public Vehicle getVehicleByVIN(int vin){
        for(Vehicle v : dealership.getAllVehicles()){
            if(v.getVin() == vin){
                return v;
            }
        }
        return null;
    }

    private void displayMenu(){
        System.out.println("""
                              DEALERSHIP MENU
                ===============================================
                1)  Find vehicles within a price range
                2)  Find vehicles by make / model
                3)  Find vehicles by year range
                4)  Find vehicles by color
                5)  Find vehicles by mileage range
                6)  Find vehicles by type (car, truck, SUV, van)
                7)  List ALL vehicles
                8)  Add a vehicle
                9)  Remove a vehicle
                10) Sale/Lease a vehicle
                ================================================
                """);
    }




//  Helper methods
    private void displayVehicles(List<Vehicle> vehicles){
        for(Vehicle v : vehicles){
            System.out.println(v);
        }
    }




    private double getValidatedDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }



    private void processGetByPriceRequest(){
        double priceMin = getValidatedDouble("Vehicles must be over (Enter price): ");
        double priceMax = getValidatedDouble("Vehicles must be under (Enter price): ");

        List<Vehicle> vehicles = dealership.getVehiclesByPrice(priceMin, priceMax);
        if(!vehicles.isEmpty()) {
            displayVehicles(vehicles);
        }
        else{
            System.out.println("Double check spelling and if there is no vehicles listed, the vehicle is not in store");
        }
    }





    private void processGetByMakeModelRequest(){
        System.out.println("Enter vehicle make: ");
        String make= scanner.nextLine();

        System.out.println("Enter vehicle model: ");
        String model = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make,model);
        if(!vehicles.isEmpty()) {
            displayVehicles(vehicles);
        }
        else{
            System.out.println("Double check spelling and if there is no vehicles listed, the vehicle is not in store");
        }
    }





    private void processGetByYearRequest(){
        int minYear = (int)getValidatedDouble("Enter minimum year: ");
        int maxYear = (int)getValidatedDouble("Enter maximum year: ");

        List<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);
        if(!vehicles.isEmpty()) {
            displayVehicles(vehicles);
        }
        else{
            System.out.println("Double check spelling and if there is no vehicles listed, the vehicle is not in store");
        }
    }





    private void processGetByColorRequest(){
        System.out.println("Enter vehicle color: ");
        String color = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        if(!vehicles.isEmpty()) {
            displayVehicles(vehicles);
        }
        else{
            System.out.println("Double check spelling and if there is no vehicles listed, the vehicle is not in store");
        }
    }





    private void processGetByMileageRequest(){
        int minMileage = (int)getValidatedDouble("Enter minimum mileage: ");
        int maxMileage = (int)getValidatedDouble("Enter maximum mileage: ");

        List<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);
        if(!vehicles.isEmpty()) {
            displayVehicles(vehicles);
        }
        else{
            System.out.println("Double check spelling and if there is no vehicles listed, the vehicle is not in store");
        }
    }





    private void processGetByVehicleTypeRequest(){
        System.out.println("Enter vehicle type (car, truck, SUV, van): ");
        String type = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByType(type);
        if(!vehicles.isEmpty()) {
            displayVehicles(vehicles);
        }
        else{
            System.out.println("Double check spelling and if there is no vehicles listed, the vehicle is not in store");
        }
    }





    private void processGetAllVehiclesRequest(){
        List<Vehicle> vehicles = dealership.getInventory();
        if(vehicles.isEmpty()){
            System.out.println("There are currently no vehicles available");
        }
        else{
            for(Vehicle v: vehicles){
                System.out.println(v);
            }
        }
    }





    private void processAddVehicleRequest(){
        int vin = (int) getValidatedDouble("Enter VIN: ");
        int year = (int) getValidatedDouble("Enter year: ");

        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        System.out.print("Enter vehicle type (car, truck, SUV, van): ");
        String vehicleType = scanner.nextLine();

        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        int odometer = (int) getValidatedDouble("Enter odometer reading: ");
        double price = getValidatedDouble("Enter price: ");

        Vehicle vehicle = new Vehicle(color, year, vin, price, vehicleType, odometer, model, make);
        dealership.addVehicle(vehicle);

        fileManager.saveDealership(dealership);
        System.out.println("Vehicle added successfully.");
    }





    private void processRemoveVehicleRequest(){
        int vin = (int)getValidatedDouble("Enter VIN of vehicle to remove: ");

        List<Vehicle> inventory = dealership.getAllVehicles();
        Vehicle removeVehicle = null;

        for (Vehicle v : inventory) {
            if (v.getVin() == vin) {
                removeVehicle = v;
                break;
            }
        }

        if (removeVehicle != null) {
            dealership.removeVehicle(removeVehicle);
            fileManager.saveDealership(dealership);
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }
}
