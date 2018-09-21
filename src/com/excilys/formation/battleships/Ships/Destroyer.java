package com.excilys.formation.battleships.Ships;

import com.excilys.formation.battleships.Orientation;

public class Destroyer extends AbstractShip {
    public Destroyer(){
        setName("Destroyer");
        setLabel('D');
        setLength(2);
        setOrientation(null);
    }

    public Destroyer(Orientation or){
        super("Destroyer", 'D', 2, or);
    }
}
