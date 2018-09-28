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
        boolean success = false;
        int i = 0;

        do {
            AbstractShip s = ships[i];
            success = false;
            while(!success) {
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
                try{
                    this.board.putShip(s, res.x, res.y);
                    success = true;
                    System.out.println(success);
                }
                catch (BoardException E){
                    System.err.println(E.getMessage());
                    continue;
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

    public Hit sendHit(int[] coords) {
        boolean done;
        Hit hit = null;
        int x,y;
        do {
            done = false;
            while(!done) {
                System.out.println("où frapper?");
                InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
                x = hitInput.x;
                y = hitInput.y;
                if(this.opponentBoard.hasShip(x, y)){ //si le navire est touché
                    this.opponentBoard.setHit(true, x, y);
                    try{       //ajouter une frappe
                        this.opponentBoard.getShip(x, y).addStrike();
                    }
                    catch(BoardException E){  //si le navire était déjà touché à ce point
                        System.err.println(E.getMessage());
                        done = false;
                        continue;
                    }

                    if(this.opponentBoard.getShip(x, y).isSunk()){ //si le navire est touché et coule
                        hit = Hit.fromInt(this.opponentBoard.getShip(x, y).getShip().getLength()); //donner le type du navire
                        done = true;
                    }
                    else{  //si le navire est touché
                        hit = Hit.STRIKE;
                        done = true;
                    }
                }
                else{ //si la frappe est ratée
                    this.opponentBoard.setHit(false, x, y);
                    hit = Hit.MISS;
                    done = true;
                }
                coords[0] = x;
                coords[1] = y;
            }

            // TODO call sendHit on this.opponentBoard

            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            // return hit is obvious. But how to return coords at the same time ? --> use pointer/array in arguments

        } while (!done);
        return hit;
    }

    public AbstractShip[] getShips() {
        return this.ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}