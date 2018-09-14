package com.excilys.formation.battleships;

public class BoardSizeException extends Exception {
    public BoardSizeException(){
        super();
    }
    public BoardSizeException(String message)
    {
        super(message);
    }
}
