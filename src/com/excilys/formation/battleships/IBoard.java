package com.excilys.formation.battleships;

import com.excilys.formation.battleships.ship.AbstractShip;

import java.util.NoSuchElementException;

public interface IBoard {

	int getSize();

	void putShip(AbstractShip ship, int x, int y);
	boolean hasShip(int x, int y);

	void setHit(boolean hit, int x, int y);
	Boolean getHit(int x, int y);
}
