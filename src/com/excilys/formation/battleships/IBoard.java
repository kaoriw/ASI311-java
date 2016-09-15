package com.excilys.formation.battleships;

import com.excilys.formation.battleships.ship.AbstractShip;

public interface IBoard {
	void putShip(AbstractShip ship, int x, int y);
	void hasShip(int x, int y);
}
