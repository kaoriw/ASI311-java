package com.excilys.formation.battleships;

import java.util.Arrays;
import java.util.Scanner;


public final class ShipInputHelper {
	
	private ShipInputHelper() {}
	
	public static class ShipInput {
		public String orientation;
		public int x;
		public int y;
	}

	public static ShipInput readShipInput() {
		@SuppressWarnings("resource")
		Scanner sin = new Scanner(System.in);
		ShipInput res = new ShipInput();
		String[] validOrientations = {"n", "s", "e", "o"};
		boolean done = false;
		do {
			try {
				String[] in = sin.nextLine().toLowerCase().split(" ");
				if (in.length == 2) {
					String coord = in[0];
					if (Arrays.asList(validOrientations).contains(in[1])) {
						res.orientation = in[1];
						res.x = coord.charAt(0) - 'a';
						res.y = Integer.parseInt(coord.substring(1, coord.length())) - 1;
						done = true;
					}
				}
			} catch (Exception e) {
				// nop
			}

			if (!done) {
				System.err.println("Format incorrect! Entrez la position sous forme 'A0 n'");
			}

		} while (!done && sin.hasNextLine());

		return res;
	}

}
