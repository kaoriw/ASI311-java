package com.excilys.formation.battleships;

import com.excilys.formation.battleships.ship.AbstractShip;

import java.util.NoSuchElementException;

public interface IBoard {

	/**
	 *
	 * @return The size of the Board
	 */
	int getSize();

	/**
	 * Return true if the specified ship can be placed in the given coordinates
	 * @param ship The ship to place on the board
	 * @param x
	 * @param y
	 */
	boolean canPutShip(AbstractShip ship, int x, int y);

	/**
	 * Put the given ship at the given coordinates
	 * @param ship The ship to place on the board
	 * @param x
	 * @param y
	 */
	void putShip(AbstractShip ship, int x, int y);

	/**
	 * @param x
	 * @param y
	 * @return True if a ship is located at the given coords
	 */
	boolean hasShip(int x, int y);

	/**
	 * Set the state of the hit at a given position
	 * @param hit true if the hit must be set to successful
	 * @param x
	 * @param y
	 */
	void setHit(boolean hit, int x, int y);

	/**
	 * Get the state of a hit at the given position
	 * @param x
	 * @param y
	 * @return
	 */
	Boolean getHit(int x, int y);
}
