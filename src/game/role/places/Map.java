package game.role.places;

import game.RPGGame.Board;
import game.RPGGame.Cell;
import game.RPGGame.InaccessibleCell;
import game.role.heroes.Party;

/**
 * @projectName: monstersAndHeroes
 * @package: game.RPGGame
 * @className: Map
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/20 17:06
 * @version: 1.0
 */
public class Map extends Board {

    public Map(int size, Party party) {
        super(size, party);
        init();
    }

    /**
     * Initialize the map
     */
    private void init() {
        // The first row and the last row are Nexuses and Inaccessibles
        for (int i = 0; i < 8; i++) {
            if (i == 2 || i == 5) {
                setCell(0, i, new InaccessibleCell(" X X X ", "I"));
                setCell(7, i, new InaccessibleCell(" X X X ", "I"));
            }
            else {
                setCell(0, i, new Nexus("       ", "N"));
                setCell(7, i, new Nexus("       ", "N"));
            }
        }
        // The Cell in the middle
        for (int i = 1; i < 7; i ++) {
            for (int j = 0; j < 8; j++) {
                if (j == 2 || j == 5) setCell(i, j, new InaccessibleCell(" X X X ", "I"));
                else setCell(i, j, generateRandomCell());
            }
        }
    }

    public void printMap() {
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {

            }
        }
    }


    /**
     * Generate Random places
     * @return random 4 kinds of Cell of the same probability
     */
    private Cell generateRandomCell() {
        final double Plain_Pro = 0.25;
        final double Cave_Pro = 0.50;
        final double Bush_Pro = 0.75;
        double rand  = Math.random();
        if (rand < Plain_Pro) return new Plain("       ", "P");
         else if (rand < Cave_Pro) return new Cave("       ", "C");
         else if (rand < Bush_Pro) return new Bush("       ", "B");
         else return new Koulou("       ", "K");
    }
}
