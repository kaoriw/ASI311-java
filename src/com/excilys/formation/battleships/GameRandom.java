package com.excilys.formation.battleships;

import com.excilys.formation.battleships.Ships.*;

import java.io.*;
import java.util.*;

public class GameRandom {

    /* ***
     * Constants
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    /* ***
     * Attributes
     */
    private Player player1;
    private Player player2;
    private Scanner sin;

    /* ***
     * Methods
     */
    public GameRandom() {

    }

    public AbstractShip randomShip(Random rnd){
        int n = rnd.nextInt(5);
        switch(n) {
            case 1:
                return new BattleShip();
            case 2:
                return new Carrier();
            case 3:
                return new Destroyer();
            case 4:
                return new Submarine();
        }
        return null;
    }

    public GameRandom init() {

//        if (!loadSave()) {
        // init attributes
        System.out.println("entre ton nom:");

        // TODO use a scanner to read player name
        sin = new Scanner(System.in);

        // TODO init boards
        Random rnd = new Random();
        int nbShips = 0;
        while(nbShips == 0 ){
            nbShips = rnd.nextInt(3);
        }
        System.out.println("nbShips = " + nbShips);
        Board b1, b2;
        b1 = new Board(sin.nextLine());
        b2 = new Board("AI");

        // TODO init this.player1 & this.player2
        List<AbstractShip> ships1 = new ArrayList<AbstractShip>();
        List<AbstractShip> ships2 = new ArrayList<AbstractShip>();

            for (int i=0; i<nbShips; i++){
            ships1.add(this.randomShip(rnd));
            ships2.add(this.randomShip(rnd));
        }

        this.player1 = new Player(b1, b2, ships1);
        this.player2 = new AIPlayer(b2, b1, ships2);

        try{
            b1.print(b2.getHits());
        }
        catch(BoardException E){
            System.err.println(E.getMessage());
        }

        // place player ships
        player1.putShips(nbShips);
        player2.putShips(nbShips);
        //}
        return this;
    }

    public void run() {
        int[] coords = new int[2];
        Board b1 = player1.board;
        Hit hit;

        // main loop
        try{
            b1.print(this.player1.opponentBoard.getHits());
        }
        catch (BoardException E){
            System.err.println(E.getMessage());
        }
        boolean done;
        do {
            hit = player1.sendHit(coords); // TODO player1 send a hit
            boolean strike = hit != Hit.MISS; // TODO set this hit on his board (b1)

            done = updateScore();
            try{
                b1.print(this.player1.opponentBoard.getHits());
            }
            catch (BoardException E){
                System.err.println(E.getMessage());
            }

            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

//            save();

            if (!done && !strike) {
                do {
                    hit = player2.sendHit(coords); // TODO player2 send a hit.

                    strike = hit != Hit.MISS;
                    if (strike) {
                        try{
                            b1.print(this.player1.opponentBoard.getHits());
                        }
                        catch (BoardException E){
                            System.err.println(E.getMessage());
                        }
                    }
                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                    done = updateScore();

                    if (!done) {
                        //save();
                    }
                } while(strike && !done);
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        sin.close();
    }


//    private void save() {
//        try {
//            // TODO bonus 10 : uncomment
////            if (!SAVE_FILE.exists()) {
////                SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
////            }
//
//            // TODO bonus 10 : serialize players
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private boolean loadSave() {
//        if (SAVE_FILE.exists()) {
//            try {
//                // TODO bonus 10 : deserialize players
//
//                return true;
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return false;
//    }

    private boolean updateScore() {
        for (Player player : new Player[]{player1, player2}) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips())
                if (ship.isSunk()) {
                    destroyed++;
                }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
            case MISS:
                msg = hit.toString();
                break;
            case STRIKE:
                msg = hit.toString();
                color = ColorUtil.Color.RED;
                break;
            default:
                msg = hit.toString() + " coulé";
                color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>",
                ((char) ('A' + coords[0])),
                (coords[1] + 1), msg);
        return ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new BattleShip(), new Carrier()});
    }

    public static void main(String args[]) {
        new GameRandom().init().run();
    }

}
