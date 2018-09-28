package com.excilys.formation.battleships.Ships;

import com.excilys.formation.battleships.Hit;

import java.util.NoSuchElementException;

public enum Orientation {
    NORTH(0),
    EAST(1),
    WEST(2),
    SOUTH(3)
    ;
    private int value;

    Orientation(int value){
        this.value = value;
    }

    public static Orientation fromInt(int value){
        for (Orientation or : Orientation.values()) {
            if (or.value == value) {
                return or;
            }
        }
        throw new NoSuchElementException("no enum for value " + value);
    }

}

