package org.IncQuery;

import java.util.ArrayList;

import org.IncQuery.Vehicle.SourceDirection;

public class Intersection {

    Vehicle topVehicle, rightVehicle, bottomVehicle, leftVehicle;

    public Vehicle getVehicleComingFromRight(SourceDirection sourceDirection) {
        Vehicle vehicle = null;
        switch (sourceDirection) {
            case TOP:
                vehicle = this.leftVehicle; break;
            case RIGHT:
                vehicle = this.topVehicle; break;
            case BOTTOM:
                vehicle = this.rightVehicle; break;
            case LEFT:
                vehicle = this.bottomVehicle; break;
        }
        return vehicle;
    }

    public Vehicle getVehicleComingOpposite(SourceDirection sourceDirection) {
        Vehicle vehicle = null;
        switch (sourceDirection) {
            case TOP:
                vehicle = this.bottomVehicle; break;
            case RIGHT:
                vehicle = this.leftVehicle; break;
            case BOTTOM:
                vehicle = this.topVehicle; break;
            case LEFT:
                vehicle = this.rightVehicle; break;
        }
        return vehicle;
    }

    public void removeVehicle(Vehicle.SourceDirection sourceDirection) {
        switch (sourceDirection) {
            case TOP:
                this.topVehicle = null; break;
            case RIGHT:
                this.rightVehicle = null; break;
            case BOTTOM:
                this.bottomVehicle = null; break;
            case LEFT:
                this.leftVehicle = null; break;
        }
    }

    public Intersection(ArrayList<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            switch (vehicle.sourceDirection) {
                case TOP:
                    this.topVehicle = vehicle; break;
                case RIGHT:
                    this.rightVehicle = vehicle; break;
                case BOTTOM:
                    this.bottomVehicle = vehicle; break;
                case LEFT:
                    this.leftVehicle = vehicle; break;
            }
        }
    }
}
