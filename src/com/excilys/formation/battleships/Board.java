package com.excilys.formation.battleships;

import com.excilys.formation.battleships.Ships.AbstractShip;

import java.util.Arrays;

public class Board implements IBoard{
    private String name;  //String, Boolean = wrapper, peut être null
    private char[][] ships; //encapsulation : attribut privé + getters + setters
    private boolean[][] hits;
    private int size;

    public Board(String name, int size){
        this.name = name;
        this.size = size;
        this.ships = new char[size][size];
        this.hits = new boolean[size][size];
        for(int i=0; i<this.size; i++) {
            Arrays.fill(this.ships[i], '.');
            }
        }

    public Board(String name){
        this.name = name;
        this.size = 10;
        this.ships = new char[10][10];
        this.hits = new boolean[10][10];
        for(int i=0; i<this.size; i++) {
//            Arrays.fill(this.ships[i], ' ');
            for(int j=0; j<this.size; i++){
                this.ships[i][j] = '.';
            }
        }
    }

    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}

    public int getSize(){
        return size;
    }
    public void setSize(int size){this.size = size;}

    public char getShip(int x, int y){return this.ships[x][y];}


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

        String numbers = "";

        for (int i=0; i< this.size; i++){
            numbers += String.valueOf(i+1);
            if(i<9) numbers += " ";
            for(int j=0; j< this.size; j++){
                numbers += this.ships[i][j];
                numbers += " ";
            }
            numbers += "   ";
            System.out.print(numbers);
            numbers = "";
            numbers += String.valueOf(i+1);
            if(i<9) numbers += " ";
            for(int j=0; j< this.size; j++){
                if(hits[i][j]) numbers += "x "; //à changer : signe différent selon true ou false, . pour les cases non touchées
                else numbers += ". ";
            }
            System.out.println(numbers);
            numbers = "";
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
                            if(ships[y+j][x] != '.') flag = true;
                            j++;
                        }
                        if(!flag){
                            for (int i=0; i<ship.getLength(); i++){

                                ships[y+i][x] = ship.getLabel();
                            }
                        }
                        else
                            throw new BoardException("Erreur de placement. Changez de position");
                    break;
                case EAST:
                        while(!flag && j<ship.getLength()){
                            if(ships[y][x-j] != '.') flag = true;
                            j++;
                        }
                        if(!flag){
                            for(int i=0; i<ship.getLength(); i++){
                                ships[y][x-i] = ship.getLabel();
                            }
                        }
                        else
                            throw new BoardException("Erreur de placement. Changez de position");
                    break;
                case WEST:
                        while(!flag && j<ship.getLength()){
                            if(ships[y][x+j] != '.') flag = true;
                            j++;
                        }
                        if(!flag){
                            for(int i=0; i<ship.getLength(); i++){
                                ships[y][x+i] = ship.getLabel();
                            }
                        }
                        else
                            throw new BoardException("Erreur de placement. Changez de position");
                    break;
                case SOUTH:
                        while(!flag && j<ship.getLength()){
                            if(ships[y-j][x] != '.') flag = true;
                            j++;
                        }
                        if(!flag){
                            for(int i=0; i<ship.getLength(); i++){
                                ships[y-i][x] = ship.getLabel();
                            }
                        }
                        else
                            throw new BoardException("Erreur de placement. Changez de position");
                    break;
            }

        }catch(IndexOutOfBoundsException e){
            System.err.println(e.getMessage());
            System.out.println("Erreur de placement. Essayez une autre position !");
        }

    }

    public boolean hasShip(int x, int y){
        return ships[x][y] != '.';
    }

    public void setHit(boolean hit, int x, int y){
        hits[x][y] = hit;
    }

    public boolean getHit(int x, int y){
        return hits[x][y];
    }
}
