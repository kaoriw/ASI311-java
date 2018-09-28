package com.excilys.formation.battleships.Ships;

public class Carrier extends AbstractShip {
    public Carrier(){
        setName("Carrier");
        setLabel('C');
        setLength(5);
        setOrientation(null);
    }

    public Carrier(Orientation or){
        super("Carrier", 'C', 5, or);
    }
}
