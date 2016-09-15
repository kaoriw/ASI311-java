package com.excilys.formation.battleships.ship;

public class Submarine extends AbstractShip {
	
	public Submarine() {
		this(null);
	}
	
	public Submarine(Orientation o) {
		super('S', "Sous-marin", 3, o);
	}
}
