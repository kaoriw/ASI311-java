package com.excilys.formation.battleships;

import com.excilys.formation.battleships.Ships.Battleship;
import com.excilys.formation.battleships.Ships.Destroyer;

public class TestBoard {
    public static void main(String[] args) {
        Board test = new Board("test", 20);
        try{
            Destroyer d = new Destroyer(Orientation.EAST);
            Battleship b = new Battleship(Orientation.SOUTH);
            System.out.println(test.getShip(1,2));

            test.putShip(d,4,5);
            test.putShip(b, 10, 5);
            System.out.println(test.getShip(10,4));
            test.print();
        }
        catch(BoardException E)
        {
            System.out.println(E.getMessage());
        }
    }

}
