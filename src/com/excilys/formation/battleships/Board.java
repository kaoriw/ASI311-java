package com.excilys.formation.battleships;

public class Board {
    private String name;
    private char[][] ships;
    private boolean[][] hits;

    public Board(String boardName, int size){
        name = boardName;
        ships = new char[size][size];
        hits = new boolean[size][size];
    }

    public Board(String boardName){
        name = boardName;
        ships = new char[10][10];
        hits = new boolean[10][10];
    }

    //créer exception pour taille tableau (class extend exception)
    public void print() throws BoardSizeException{
        if(ships[0].length > 27) throw new BoardSizeException("Board size exceeds limit.");
        char letter = 'A';
        String letters = "";
        System.out.println("Navires :               Frappes :");
        for (int j=0; j< this.ships[0].length; j++){
            letters += (char)(letter+j);
            letters += " ";
        }

        letters += "     ";
        letters += letters;
        System.out.println(letters);

        String numbers = "";

        for (int i=1; i<= this.ships[0].length; i++){
            numbers += String.valueOf(i);
            if(i<10) numbers += " ";
            numbers += ". . . . . . . . . .    ";
            numbers += numbers;
            System.out.println(numbers);
            numbers = "";
        }
    }
}
