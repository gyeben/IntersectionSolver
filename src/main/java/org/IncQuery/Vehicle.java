package org.IncQuery;

public class Vehicle {
    enum Type {
        CAR,
        AMBULANCE
    }

    public enum SourceDirection {
        TOP,
        RIGHT,
        BOTTOM,
        LEFT
    }

    enum TargetDirection {
        LEFT,
        STRAIGHT,
        RIGHT
    }

    Type type;
    SourceDirection sourceDirection;
    TargetDirection targetDirection;

    public Vehicle(String vehicleString, SourceDirection sourceDirection) {
        if (vehicleString.length() != 2) {
            throw new RuntimeException("invalid vehicle string");
        }

        this.type = getTypeFromString(vehicleString);
        this.sourceDirection = sourceDirection;
        this.targetDirection = getTargetDirectionFromString(vehicleString);
    }

    Type getTypeFromString(String vehicleString) {
        switch (vehicleString.charAt(0)) {
            case 'C':
                return Type.CAR;
            case 'A':
                return Type.AMBULANCE;
            default:
                throw new RuntimeException("invalid vehicle type");
        }
    }

    TargetDirection getTargetDirectionFromString(String vehicleString) {
        switch (vehicleString.charAt(1)) {
            case 'L':
                return TargetDirection.LEFT;
            case 'S':
                return TargetDirection.STRAIGHT;
            case 'R':
                return TargetDirection.RIGHT;
            default:
                throw new RuntimeException("invalid target direction");
        }
    }
}
