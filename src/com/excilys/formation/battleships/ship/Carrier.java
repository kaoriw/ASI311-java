package com.excilys.formation.battleships.ship;

public class Carrier extends AbstractShip {
	
	public Carrier() {
		this(null);
	}
	
	public Carrier(Orientation o) {
		super('C', "Porte avions", 5, o);
	}
}
