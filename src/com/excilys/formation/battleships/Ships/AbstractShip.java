package com.excilys.formation.battleships.Ships;

import com.excilys.formation.battleships.Orientation;

public abstract class AbstractShip {

    private String name;
    private char label;
    private int length;
    private Orientation or;

    public AbstractShip(String name, char label, int length, Orientation or){
        this.name = name;
        this.label = label;
        this.length = length;
        this.or = or;
    }

    public AbstractShip(){}

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public char getLabel(){
        return label;
    }
    public void setLabel(char label){
        this.label = label;
    }

    public int getLength(){
        return length;
    }
    public void setLength(int length){
        this.length = length;
    }

    public Orientation getOrientation(){
        return this.or;
    }
    public void setOrientation(Orientation or){
        this.or = or;
    }


}
