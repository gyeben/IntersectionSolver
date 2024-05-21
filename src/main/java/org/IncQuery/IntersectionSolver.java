package org.IncQuery;

import java.util.ArrayList;

public class IntersectionSolver {

    static void printVehicleChar(Vehicle vehicle) {
        System.out.print(vehicle.sourceDirection.name().charAt(0));
    }

    static ArrayList<Vehicle> createVehiclesFromInput(String[] args) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            // A _ character denotes that no vehicle is coming from that direction
            if (!args[i].equals("_")) {
                Vehicle newVehicle = new Vehicle(args[i], Vehicle.SourceDirection.values()[i]);
                vehicles.add(newVehicle);
            }
        }
        return vehicles;
    }

    static ArrayList<Vehicle> filterByVehicleType(ArrayList<Vehicle> vehicles, Vehicle.Type filterType) {
        ArrayList<Vehicle> filteredVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.type == filterType) {
                filteredVehicles.add(vehicle);
            }
        }
        return filteredVehicles;
    }

    static Intersection intersection;
    static ArrayList<Vehicle> waitingCars = new ArrayList<>();

    static void processCar(Vehicle car) {
        Vehicle carComingFromRight = intersection.getVehicleComingFromRight(car.sourceDirection);
        boolean shouldPass = false;

        switch (car.targetDirection) {
            case LEFT:
                Vehicle carComingOpposite = intersection.getVehicleComingOpposite(car.sourceDirection);
                shouldPass = carComingFromRight == null &&
                        (carComingOpposite == null || carComingOpposite.targetDirection == Vehicle.TargetDirection.LEFT);
                break;
            case STRAIGHT:
                shouldPass = carComingFromRight == null;
                break;
            case RIGHT:
                shouldPass = true;
                break;
        }

        if (shouldPass) {
            printVehicleChar(car);
            intersection.removeVehicle(car.sourceDirection);
        } else {
            waitingCars.add(car);
        }
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Failed to parse input: invalid number of input parameters");
            return;
        }

        ArrayList<Vehicle> vehicles;
        try {
            vehicles = createVehiclesFromInput(args);
        } catch (RuntimeException exception) {
            System.out.println("Failed to parse input: " + exception.getMessage());
            return;
        }

        // Ambulances shall cross the intersection first
        for (Vehicle ambulance : filterByVehicleType(vehicles, Vehicle.Type.AMBULANCE)) {
            printVehicleChar(ambulance);
        }
        ArrayList<Vehicle> cars = filterByVehicleType(vehicles, Vehicle.Type.CAR);
        intersection = new Intersection(cars);

        while (!cars.isEmpty()) {
            for (Vehicle car : cars) {
                processCar(car);
            }

            // If every car is waiting, let one of them pass
            if (cars.size() == waitingCars.size()) {
                printVehicleChar(waitingCars.get(0));
                waitingCars.remove(0);
            }

            cars = waitingCars;
            waitingCars = new ArrayList<>();
        }

        System.out.println(); // newline
    }
}