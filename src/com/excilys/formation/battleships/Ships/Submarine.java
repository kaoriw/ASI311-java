package com.excilys.formation.battleships.Ships;

public class Submarine extends AbstractShip{
    public Submarine(){
        setName("Submarine");
        setLabel('S');
        setLength(3);
        setOrientation(null);
    }

    public Submarine(Orientation or){
        super("Submarine", 'S', 3, or);
    }
}
