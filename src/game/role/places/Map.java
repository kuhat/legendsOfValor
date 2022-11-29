package game.role.places;

import game.RPGGame.Board;
import game.RPGGame.Cell;
import game.RPGGame.InaccessibleCell;
import game.role.heroes.Party;
import game.utils.ConsoleColorsCodes;

import static game.utils.ConsoleColorsCodes.CYAN;

/**
 * @projectName: monstersAndHeroes
 * @package: game.RPGGame
 * @className: Map
 * @author: Danny
 * @description: Map instance where game happens on
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
                setCell(0, i, new InaccessibleCell(ConsoleColorsCodes.BLACK_BACKGROUND+" X X X "+ConsoleColorsCodes.RESET, "I"));
                setCell(7, i, new InaccessibleCell(ConsoleColorsCodes.BLACK_BACKGROUND+" X X X "+ConsoleColorsCodes.RESET, "I"));
            }
            else {
                setCell(0, i, new Nexus("       ", "N"));
                setCell(7, i, new Nexus("       ", "N"));
            }
        }
        // The Cell in the middle
        for (int i = 1; i < 7; i ++) {
            for (int j = 0; j < 8; j++) {
                if (j == 2 || j == 5) setCell(i, j, new InaccessibleCell(ConsoleColorsCodes.BLACK_BACKGROUND+" X X X "+ConsoleColorsCodes.RESET, "I"));
                else setCell(i, j, generateRandomCell());
            }
        }
    }

    public void setContent(int i, int j, String content) {
        super.getCell(i,j).setContent(content);
    }

    public void printMap() {
        // Each row of the map consists of eight Cells
        for (int j = 0; j < 8; j++) {
            // Print the first line of the cell
            for (int i = 0; i < 8; i ++) {
                super.getCell(j, i).printFirstAndLast();
                System.out.print("  ");
            }
            System.out.println();
            // print the content of the cell
            for (int i = 0; i < 8; i++) {
                super.getCell(j, i).printContent();
                System.out.print("  ");
            }
            System.out.println();
            // print the last line of the cell
            for (int i = 0; i < 8; i++) {
                super.getCell(j, i).printFirstAndLast();
                System.out.print("  ");
            }
            System.out.println();
            System.out.println();
        }
        System.out.println(CYAN + "    Lane 0                            Lane 1                            Lane 2" + ConsoleColorsCodes.RESET);
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
