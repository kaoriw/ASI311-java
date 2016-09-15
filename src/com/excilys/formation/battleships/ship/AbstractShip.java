package com.excilys.formation.battleships.ship;

public abstract class AbstractShip {
	// On choisit d'utiliser un énum pour l'orientation.
	public enum Orientation {
		NORTH, SOUTH, EAST, WEST
	}
	
	private Character label;
	private String name;
	private int length;
	private Orientation orientation;

	public AbstractShip(Character label, String name, int length, Orientation orientation) {
		this.label = label; 
		this.name = name;
		this.length = length;
		this.orientation = orientation;
	}
	
	public Character getLabel() {
		return this.label;
	}

	public String getName() {
		return this.name;
	}
	
	public int getLength() {
		return this.length;
	}

	public Orientation getOrientation() {
		return this.orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
}
