package com.excilys.formation.battleships.Ships;

public abstract class AbstractShip {

    private String name;
    private Character label;
    private int length;
    private Orientation or;
    private int strikeCount;

    public AbstractShip(String name, Character label, int length, Orientation or){
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

    public Character getLabel(){
        return label;
    }
    public void setLabel(Character label){
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

    public int getStrikeCount() { return this.strikeCount;}
    public void setStrikeCount(int strikeCount) { this.strikeCount = strikeCount;}

    public void addStrike(){
        this.strikeCount++;
    }

    public boolean isSunk(){ return this.strikeCount == this.length;}

}
