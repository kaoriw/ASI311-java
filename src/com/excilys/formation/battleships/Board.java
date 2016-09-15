package com.excilys.formation.battleships;

import java.io.IOException;

import com.excilys.formation.battleships.ship.AbstractShip;
import com.excilys.formation.battleships.ship.AbstractShip.Orientation;

public class Board implements IBoard {

	private int w;
	private int h;
	private String name;
	private Character[][] ships;
	private boolean[][] hits;
	
	public Board(String name, int w, int h) {
		this.w = w;
		this.h = h;
		this.name = name;
		
		this.ships = new Character[w][h];
		this.hits= new boolean[w][h];
	}
	
	public Board(String name) {
		this(name, 10, 10);
	}
	
	public void print() {
		Character currentLetter = 'A';
		Character currentShipLabel;
		Character currentHitLabel;
		try {Runtime.getRuntime().exec("clear");} catch (IOException e) {}
		System.out.println(this.name);

		System.out.print("   ");
		for (int i = 0; i < this.w; ++i) {
			System.out.print(currentLetter++ + " ");
		}
		
		currentLetter = 'A';
		System.out.print("      ");
		for (int i = 0; i < this.w; ++i) {
			System.out.print(currentLetter++ + " ");
		}
		
		System.out.println();

		for (int j = 0; j < this.h; ++j) {
			System.out.print(String.format("%2d ", j + 1));

			for (int i = 0; i < this.h; ++i) {
				currentShipLabel = this.ships[i][j] != null ? this.ships[i][j] : '.'; 
				System.out.print(currentShipLabel + " ");
			}
			System.out.print(" ");
			System.out.print(String.format("  %2d ", j + 1));
			for (int i = 0; i < this.h; ++i) {
				currentHitLabel = this.hits[i][j] ? 'X' : '.'; 
				System.out.print(currentHitLabel + " ");
			}
			System.out.println();
		}
		
		currentLetter++;
	}

	@Override
	public void putShip(AbstractShip ship, int x, int y) {
		Orientation o = ship.getOrientation();
		int dx = 0, dy = 0;
		if (o == Orientation.EAST) {
			if (x + ship.getLength() >= this.w) {
				throw new IllegalArgumentException("ship is out of the grid.");	
			}
			dx = 1;
		} else if (o == Orientation.SOUTH) {
			if (y + ship.getLength() >= this.h) {
				throw new IllegalArgumentException("ship is out of the grid.");	
			}
			dy = 1;
		} else if (o == Orientation.NORTH) {
			if (y + 1 - ship.getLength() < 0) {
				throw new IllegalArgumentException("ship is out of the grid.");	
			}
			dy = -1;
		}
		if (o == Orientation.WEST) {
			if (x + 1 - ship.getLength() < 0) {
				throw new IllegalArgumentException("ship is out of the grid.");	
			}
			dx = -1;
		}

		int ix = x;
		int iy = y;
		
		for (int i = 0; i < ship.getLength(); ++i) {
			if (hasShip(ix, iy)) {
				throw new IllegalArgumentException("Ship overlays.");
			}
			ix += dx;
			iy += dy;
		}
		
		ix = x;
		iy = y;
		
		for (int i = 0; i < ship.getLength(); ++i) {
			this.ships[ix][iy] = ship.getLabel();
			ix += dx;
			iy += dy;
		}
	}

	@Override
	public boolean hasShip(int x, int y) {
		if (x > this.w || y > this.h) {
			throw new IllegalArgumentException("out of the grid.");
		}
		return this.ships[x][y] != null;
	}

	@Override
	public void putHit(int x, int y) {
		if (x > this.w || y > this.h) {
			throw new IllegalArgumentException("out of the grid.");
		}
		this.hits[x][y] = true;
	}

	@Override
	public boolean hasHit(int x, int y) {
		if (x > this.w || y > this.h) {
			throw new IllegalArgumentException("out of the grid.");
		}
		return this.hits[x][y];		
	}
}
