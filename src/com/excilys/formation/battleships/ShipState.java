package com.excilys.formation.battleships;

import com.excilys.formation.battleships.Ships.AbstractShip;

public class ShipState {
    private AbstractShip ship;
    private boolean struck;

    public ShipState(){
        this.ship = null;
        this.struck = false;
    }

    public ShipState(AbstractShip ship){
        this.ship = ship;
        this.struck = false;
    }

    public AbstractShip getShip(){ return this.ship;}
    public void setShip(AbstractShip ship) { this.ship = ship;}

    public boolean getStruck(){return this.struck;}
    public void setStruck(boolean struck) { this.struck = struck;}

    public void addStrike() throws BoardException{
        if(!this.struck){
            this.struck = true;
            this.ship.addStrike();
        }
        else{
            throw new BoardException("Navire déjà touché !");
        }
    }
    public boolean isStruck(){ return this.struck;}
    public String toString(){
        return ColorUtil.colorize(String.valueOf(this.ship.getLabel()), ColorUtil.Color.RED);
    }
    public boolean isSunk(){ return this.ship.isSunk(); }
}
