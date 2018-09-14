package com.excilys.formation.battleships;

public class TestBoard {
    public static void main(String[] args) {
        Board test = new Board("test", 30);
        try{
            test.print();
        }
        catch(BoardSizeException E)
        {
            System.out.println(E.getMessage());
        }
    }

}
