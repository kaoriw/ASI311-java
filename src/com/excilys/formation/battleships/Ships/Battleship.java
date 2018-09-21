package com.excilys.formation.battleships.Ships;

import com.excilys.formation.battleships.Orientation;

public class Battleship extends AbstractShip{
    public Battleship(){
        setName("Battleship");
        setLabel('B');
        setLength(4);
        setOrientation(null);
    }

    public Battleship(Orientation shipOr){
        super("Battleship", 'B', 4, shipOr);
    }
}
