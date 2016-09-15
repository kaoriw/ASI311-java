package com.excilys.formation.battleships;

import com.excilys.formation.battleships.ship.AbstractShip;

public interface IBoard {
	void putShip(AbstractShip ship, int x, int y);
	boolean hasShip(int x, int y);
	
	void putHit(int x, int y);
	boolean hasHit(int x, int y);
}
