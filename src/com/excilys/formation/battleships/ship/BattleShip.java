package com.excilys.formation.battleships.ship;

public class BattleShip extends AbstractShip {
	
	public BattleShip() {
		this(null);
	}
	public BattleShip(Orientation o) {
		super('B', "Croiseur", 4, o);
	}
}
