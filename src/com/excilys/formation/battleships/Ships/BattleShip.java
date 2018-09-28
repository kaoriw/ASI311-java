package com.excilys.formation.battleships.Ships;

public class BattleShip extends AbstractShip{
    public BattleShip(){
        setName("Battleship");
        setLabel('B');
        setLength(4);
        setOrientation(null);
    }

    public BattleShip(Orientation or){
        super("Battleship", 'B', 4, or);
    }
}
