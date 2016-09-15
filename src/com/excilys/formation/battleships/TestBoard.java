package com.excilys.formation.battleships;

import com.excilys.formation.battleships.ship.AbstractShip.Orientation;
import com.excilys.formation.battleships.ship.Carrier;
import com.excilys.formation.battleships.ship.Destroyer;
import com.excilys.formation.battleships.ship.Submarine;

public class TestBoard {
	
	public static void main(String args[]) {
		Board b = new Board("test");
		b.putHit(1, 2);
		b.putShip(new Carrier(Orientation.NORTH), 4, 4);
		b.putShip(new Destroyer(Orientation.EAST), 5, 4);
		b.print();
	}
}
