package com.excilys.formation.battleships;

public abstract class AbstractShip {

    String name;
    char label;
    int length;
    Orientation or;

    public AbstractShip(String shipName, char shipLabel, int shipLength, Orientation shipOr){
        name = shipName;
        label = shipLabel;
        length = shipLength;
        or = shipOr;
    }

    public AbstractShip(){};

    public char getLabel(){
        return label;
    }

    public int getLength(){
        return length;
    }

    public Orientation getOrientation(){
        return or;
    }

    public void setOrientation(Orientation shipOr){
        or = shipOr;
    }


}
