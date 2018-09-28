package com.excilys.formation.battleships;

import com.excilys.formation.battleships.Ships.*;

import java.util.ArrayList;
import java.util.List;

public class TestGame {

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Board b = new Board("b", 10);
        try{
            b.print(b.getHits());
        }
        catch (BoardException E){
            System.err.println(E.getMessage());
        }
        List<AbstractShip> ships = new ArrayList<AbstractShip>();
        ships.add(new Destroyer());
        ships.add(new Submarine());
        ships.add(new Submarine());
        ships.add(new BattleShip());
        ships.add(new Carrier());
        BattleShipsAI ai = new BattleShipsAI(b,b);

        AbstractShip[] shipsArr = ships.toArray(new AbstractShip[0]);

        ai.putShips(shipsArr);

        int coords[] = new int[2];

        Hit hit;

        int ctr = 0; //compte le nombre de navires coulés

        do {
            hit = ai.sendHit(coords);
            System.out.println("x : "+ coords[0] + ", y : " + coords[1]);
            if (hit == Hit.STRIKE || hit == Hit.MISS){
                System.out.println(hit.toString());
            }
            else{
                System.out.println(hit.toString() + " coulé");
                ctr++;
            }

            try{
                b.print(b.getHits());
            }
            catch (BoardException E){
                System.err.println(E.getMessage());
            }

            sleep(1000);
        }
        while(ctr != ships.size());
    }
}
