package com.excilys.formation.battleships;

public class Destroyer extends AbstractShip {
    public Destroyer(){
        name = "noname";
        label = 'D';
        length = 2;
        or = null;
    }

    public Destroyer(Orientation shipOr){
        super("noname", 'D', 2, shipOr);
    }
}
