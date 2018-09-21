package com.excilys.formation.battleships.Ships;

import com.excilys.formation.battleships.Orientation;

public class Carrier extends AbstractShip {
    public Carrier(){
        setName("Carrier");
        setLabel('C');
        setLength(5);
        setOrientation(null);
    }

    public Carrier(Orientation shipOr){
        super("Carrier", 'C', 5, shipOr);
    }
}
