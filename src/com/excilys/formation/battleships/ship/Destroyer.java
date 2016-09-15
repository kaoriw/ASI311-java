package com.excilys.formation.battleships.ship;

public class Destroyer extends AbstractShip {
	
	public Destroyer() {
		this(null);
	}

	public Destroyer(Orientation o) {
		super('D', "Frégate", 2, o);
	}
}
