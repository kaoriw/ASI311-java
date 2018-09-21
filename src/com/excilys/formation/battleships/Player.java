package com.excilys.formation.battleships;

import com.excilys.formation.battleships.Ships.AbstractShip;
import java.io.Serializable;
import java.util.List;

public class Player {
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coordinates.
     */
    public void putShips() {
        boolean done = false;
        int i = 0;

        do {
            boolean success = false;
            while(!success) {
                AbstractShip s = ships[i];
                String msg = String.format("placer %d : %s(%d)", i + 1, s.getName(), s.getLength());
                System.out.println(msg);
                InputHelper.ShipInput res = InputHelper.readShipInput();
                // TODO set ship orientation
                switch (res.orientation){
                    case "n":
                        s.setOrientation(Orientation.NORTH);
                        break;
                    case "s":
                        s.setOrientation(Orientation.SOUTH);
                        break;
                    case "e":
                        s.setOrientation(Orientation.EAST);
                        break;
                    case "o":
                        s.setOrientation(Orientation.WEST);
                        break;
                    default:
                        s.setOrientation(Orientation.WEST);
                        break;
                }
                // TODO put ship at given position


                try{
                    this.board.putShip(s, res.x, res.y);
                    success = true;
                }
                catch (BoardException E){
                    System.err.println(E.getMessage());

                }
            }

            // TODO when ship placement successful
            ++i;
            done = i == 5;
            try {
                board.print();
            }
            catch (BoardException E){
                System.err.println(E.getMessage());
            }

        } while (!done);
    }

//    public Hit sendHit(int[] coords) {
//        boolean done;
//        Hit hit = null;
//        do {
//
//            System.out.println("où frapper?");
//            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
//            // TODO call sendHit on this.opponentBoard
//
//            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
//            // return hit is obvious. But how to return coords at the same time ?
//        } while (!done);
//        return hit;
//    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}