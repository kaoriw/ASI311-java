package com.excilys.formation.battleships;

import com.excilys.formation.battleships.Ships.*;

import java.util.ArrayList;
import java.util.List;

public class TestBoard {
    public static void main(String[] args) {
        Board b1 = new Board("b1", 10);
        Board b2 = new Board("b2", 10);
        try{
              //Ex1-3
//            Destroyer d = new Destroyer(Orientation.EAST);
//            BattleShip b = new BattleShip(Orientation.SOUTH);
//
//            b1.putShip(d,5,8);
//            b1.putShip(b, 6, 5);
//
//            b1.print();
            //Ex 4
            List<AbstractShip> shipList = new ArrayList<AbstractShip>();
            shipList.add(new Destroyer());
            shipList.add(new Submarine());
            shipList.add(new Submarine());
            shipList.add(new BattleShip());
            shipList.add(new Carrier());

            Player p1 = new Player(b1, b2, shipList);

            p1.putShips();
            b1.print();
        }
        catch(BoardException E)
        {
            System.err.println(E.getMessage());
        }

    }

}
