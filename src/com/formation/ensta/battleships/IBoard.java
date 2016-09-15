package com.formation.ensta.battleships;

public interface IBoard {
	void putShip(Ship ship, int x, int y);
	void hasShip(int x, int y);
}
