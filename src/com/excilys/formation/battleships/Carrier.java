package com.excilys.formation.battleships;

public class Carrier extends AbstractShip {
    public Carrier(){
        name = "noname";
        label = 'C';
        length = 4;
        or = null;
    }

    public Carrier(Orientation shipOr){
        super("noname", 'C', 4, shipOr);
    }
}
