package com.epam.rd.autotasks;

import java.util.Optional;

public enum Direction {
    N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315);

    Direction(final int degrees) {
        this.degrees = degrees;
    }

    private int degrees;

    public static Direction ofDegrees(int degrees) {
        if (degrees % 45 != 0) {
            return null;
        }
            return closestToDegrees(degrees);
        }

    private static int degreesClockwise(int givenDegrees) {
            boolean baseDegreesFound = false;
            while (!baseDegreesFound) {
                givenDegrees -= 8;
                if (givenDegrees < 8) {
                    baseDegreesFound = true;
                }
            }
        return givenDegrees;
    }

    private static int degreesAnticlockwise(int givenDegrees){
        boolean baseDegreesFound = false;
        while(!baseDegreesFound){
            givenDegrees += 8;
            if(givenDegrees >= 0 ) {
                baseDegreesFound = true;
            }
        }
        return givenDegrees;
    }
    public static Direction closestToDegrees(int degrees) {
        Direction closestDirection = null;
        int baseDegrees = (int) Math.round((double)degrees/45);
        boolean directionFound = false;

        if(baseDegrees >= 0){
            if(baseDegrees >= 8){
                baseDegrees = degreesClockwise(baseDegrees);
            }
        }
        else{
            baseDegrees = degreesAnticlockwise(baseDegrees);
        }
        for (Direction directionOption : Direction.values()) {
            if (directionOption.ordinal() == baseDegrees) {
                directionFound = true;
                closestDirection = directionOption;
                break;
            }
        }
        return closestDirection;
    }

    public Direction opposite() {
        Direction oppositeDirection = null;
        int degrees = this.degrees >= 180? this.degrees - 180 : this.degrees + 180;
        for(Direction direction : Direction.values()){
            if(direction.degrees == degrees){
                oppositeDirection = direction;
                break;
            }
        }
      return oppositeDirection;
    }

    public int differenceDegreesTo(Direction direction) {
        return this.degrees > direction.degrees ? this.degrees - direction.degrees : this.degrees==0 && direction.degrees > 180 ? 360 - direction.degrees : direction.degrees - this.degrees;
    }
}
