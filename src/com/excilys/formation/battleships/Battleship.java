package com.excilys.formation.battleships;

public class Battleship extends AbstractShip{
    public Battleship(){
        name = "noname";
        label = 'B';
        length = 3;
        or = null;
    }

    public Battleship(Orientation shipOr){
        super("noname", 'B', 3, shipOr);
    }
}
