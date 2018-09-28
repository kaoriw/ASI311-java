package com.excilys.formation.battleships;

import com.excilys.formation.battleships.Ships.AbstractShip;

import java.util.Arrays;

public class Board implements IBoard{
    private String name;  //String, Boolean = wrapper, peut être null
    private ShipState[][] ships; //encapsulation : attribut privé + getters + setters
    private Boolean[][] hits;
    private int size;

    public Board(String name, int size){
        this.name = name;
        this.size = size;
        this.ships = new ShipState[size][size];
        this.hits = new Boolean[size][size];
        for(int i=0; i<this.size; i++) {
            for(int j=0; j<this.size; j++){
               this.ships[i][j] = new ShipState();
            }
        }
    }

    public Board(String name){
        this.name = name;
        this.size = 10;
        this.ships = new ShipState[10][10];
        this.hits = new Boolean[10][10];
        for(int i=0; i<this.size; i++) {
            for(int j=0; j<this.size; j++){
               this.ships[i][j] = new ShipState();
            }
        }
    }

    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}

    public int getSize(){
        return size;
    }
    public void setSize(int size){this.size = size;}

    public ShipState getShip(int x, int y){return this.ships[y][x];}

    public ShipState[][] getShips(){ return this.ships; }
    public Boolean[][] getHits(){ return this.hits; }
    //créer exception pour taille tableau (class extends exception)
    public void print() throws BoardException{
        if(size > 100) throw new BoardException("La grille est trop grande.");
        char first = 'A';
        String boards = "Navires :";
        String letters = "  ";
        for (int j=0; j< size; j++){
            letters += (char)(first+j);
            letters += " ";
            boards += "  ";
        }
        boards += "Frappes :";
        letters += "   ";
        letters += letters;
        System.out.println(boards);
        System.out.println(letters);

        String rows = "";

        for (int i=0; i< this.size; i++){
            rows += String.valueOf(i+1);
            if(i<9) rows += " ";
            for(int j=0; j< this.size; j++){
                if(this.ships[i][j].getShip() == null){
                    rows += ".";
                }
                else
                {
                    if(this.ships[i][j].isSunk()){
                        rows += this.ships[i][j].toString(); //label en rouge
                    }
                    else {
                        rows += this.ships[i][j].getShip().getLabel();
                    }
                }
                rows += " ";
            }
            rows += "   ";
            System.out.print(rows);
            rows = "";
            rows += String.valueOf(i+1);
            if(i<9) rows += " ";
            for(int j=0; j< this.size; j++){
                if (this.hits[i][j] == null){
                    rows += '.';
                }
                else if (!this.hits[i][j]){
                    rows += ColorUtil.colorize("X", ColorUtil.Color.WHITE);
                }
                else
                {
                    rows += ColorUtil.colorize("X", ColorUtil.Color.RED);
                }
                rows += " ";
            }
            System.out.println(rows);
            rows = "";
        }
    }


    //(x,y) front of ship
    public void putShip(AbstractShip ship, int x, int y) throws BoardException{
        boolean flag = false;
        int j=0;
        try{
            switch (ship.getOrientation()) {
                case NORTH:
                        while(!flag && j<ship.getLength()){
                            if(this.hasShip(x,y+j)) flag = true;
                            j++;
                        }
                        if(!flag){
                            for (int i=0; i<ship.getLength(); i++){

                                this.ships[y+i][x].setShip(ship);
                            }
                        }
                        else
                            throw new BoardException("Erreur de placement. Changez de position");
                    break;
                case EAST:
                        while(flag && j<ship.getLength()){
                            if(this.hasShip(x-j, y)) {
                                flag = true;
                            }
                            j++;
                        }
                        if(!flag){
                            for(int i=0; i<ship.getLength(); i++){
                                this.ships[y][x-i].setShip(ship);
                            }
                        }
                        else
                            throw new BoardException("Erreur de placement. Changez de position");
                    break;
                case WEST:
                        while(!flag && j<ship.getLength()){
                            if(this.hasShip(x+j, y)) flag = true;
                            j++;
                        }
                        if(!flag){
                            for(int i=0; i<ship.getLength(); i++){
                                this.ships[y][x+i].setShip(ship);
                            }
                        }
                        else
                            throw new BoardException("Erreur de placement. Changez de position");
                    break;
                case SOUTH:
                        while(!flag && j<ship.getLength()){
                            if(this.hasShip(x, y-j)) flag = true;
                            j++;
                        }
                        if(!flag){
                            for(int i=0; i<ship.getLength(); i++){
                                this.ships[y-i][x].setShip(ship);
                            }
                        }
                        else
                            throw new BoardException("Erreur de placement. Changez de position");
                    break;
            }

        }catch(IndexOutOfBoundsException e){
            throw new BoardException("Erreur de placement. Essayez une autre position !");
        }

    }

    public boolean hasShip(int x, int y){
        return this.ships[y][x].getShip() != null;
    }

    public void setHit(Boolean hit, int x, int y){
        hits[y][x] = hit;
    }

    public boolean getHit(int x, int y){
        return hits[y][x];
    }

    public Hit sendHit(int x, int y) {
        boolean done = false;
        Hit hit;
        do {

                if(this.hasShip(x, y)){ //si le navire est touché
                    this.setHit(true, x, y);
                    try{       //ajouter une frappe
                        this.getShip(x, y).addStrike();
                    }
                    catch(BoardException E){  //si le navire était déjà touché à ce point
                        System.err.println(E.getMessage());

                    }

                    if(this.getShip(x, y).isSunk()){ //si le navire est touché et coule
                        hit = Hit.fromInt(this.getShip(x, y).getShip().getLength()); //donner le type du navire
                        done = true;
                    }
                    else{  //si le navire est touché
                        hit = Hit.STRIKE;
                        done = true;
                    }
                }
                else{ //si la frappe est ratée
                    this.setHit(false, x, y);
                    hit = Hit.MISS;
                    done = true;
                }



            // TODO call sendHit on this.opponentBoard

            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            // return hit is obvious. But how to return coords at the same time ? --> use pointer/array in arguments

        } while (!done);
        return hit;
    }
}
