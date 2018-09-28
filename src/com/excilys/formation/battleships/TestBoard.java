package com.excilys.formation.battleships;

import com.excilys.formation.battleships.Ships.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

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
//            b1.setHit(true, 5, 8);
//            b1.setHit(false ,2, 7);
//
//
//
//            b1.print();
            //Ex 4
//            List<AbstractShip> shipList = new ArrayList<AbstractShip>();
//            shipList.add(new Destroyer());
//            shipList.add(new Submarine());
//            shipList.add(new Submarine());
//            shipList.add(new BattleShip());
//            shipList.add(new Carrier());
//
//            Player p1 = new Player(b1, b2, shipList);
//            Player p2 = new Player(b2, b1, shipList);
//
//            p1.putShips();
//
//            int coords[] = {0,0};
//
//            p2.sendHit(coords);
//            p2.sendHit(coords);
//            b1.print();

            //Ex 6
            Destroyer d = new Destroyer(Orientation.EAST);
            b1.putShip(d, 5, 8);
            b1.print();
            b1.sendHit(5,8);
            //b1.sendHit(5,8);
            //System.out.println(b1.sendHit(4,8));
            if(b1.sendHit(4,8) == Hit.DESTROYER){
                b1.print();
                System.out.println("Le dernier sendHit renvoie Hit.DESTROYER et le navire a coulé !");
            }
            System.out.println("Navire coulé : "+d.isSunk());

        }
        catch(BoardException E)
        {
            System.err.println(E.getMessage());
        }

    }

}
