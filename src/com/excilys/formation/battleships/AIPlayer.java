package com.excilys.formation.battleships;

import com.excilys.formation.battleships.Ships.AbstractShip;

import java.io.Serializable;
import java.util.List;

public class AIPlayer extends Player {
    private BattleShipsAI ai;
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    // TODO AIPlayer must not inherit "keyboard behavior" from player. Call ai instead.

    public void putShips(int nbShips){
        ai.putShips(this.ships);
    }

    public Hit sendHit(int[] coords){
        Hit hit;
        hit = ai.sendHit(coords);
        return hit;
    }
}