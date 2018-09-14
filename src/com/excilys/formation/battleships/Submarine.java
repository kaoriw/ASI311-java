package com.excilys.formation.battleships;

public class Submarine extends AbstractShip{
    public Submarine(){
        name = "noname";
        label = 'S';
        length = 1;
        or = null;
    }

    public Submarine(Orientation shipOr){
        super("noname", 'S', 1, shipOr);
    }
}
