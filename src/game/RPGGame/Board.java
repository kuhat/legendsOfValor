package game.RPGGame;

import game.role.heroes.Party;

import java.util.Random;

/**
 * @projectName: monstersAndHerios
 * @package: game.boardGame
 * @className: Board
 * @author: Danny
 * @description: Board class, containing the cells of inaccessible, normal cells and market cells
 * @date: 2022/11/3 18:22
 * @version: 1.0
 */
public class Board {

    private Cell[][] grid;

    private int size;

    private Party party;

    private static final String RED = "\033[31m";

    private static final String RESET = "\033[0m";

    public static final String GREEN = "\u001B[32m";

    public static final String BLUE = "\u001B[34m";

    public Board(int size, Party party) {
        this.grid = new Cell[size][size];
        this.size = size;
        this.party = party;
        int[] teamPos = generateTeamPos();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == teamPos[0] && j == teamPos[1]) {
                    grid[i][j] = new normalCell("T");
                } else {
                    grid[i][j] = generateCell();
                }
            }
        }
    }

    public int getSize() {
        return size;
    }

    public Cell getCell(int i, int j) {
        return grid[i][j];
    }

    /**
     * initialize the position of team
     */
    private int[] generateTeamPos() {
        Random random = new Random();
        int col = random.nextInt(size);
        int row = random.nextInt(size);
        party.setPosition(row, col);
        return new int[]{row, col};
    }

    /**
     * 0.2 chance of Inaccessible cell
     * 0.3 chance of Market Cell
     * 0.5 chance of common cell
     */
    private Cell generateCell() {
        double random = Math.random();
        if (random < 0.2) return new InaccessibleCell("X");
        else if (random < 0.5) return new MarketCell("M");
        else return new normalCell(" ");
    }

    /**
     * Print Board, RED color indicate Inaccessible Cell, GREEN indicate Market Cell
     */
    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j].getContent().equals("M"))
                    System.out.print(" " + GREEN + grid[i][j].getContent() + RESET);
                else if (grid[i][j].getContent().equals("X"))
                    System.out.print(" " + RED + grid[i][j].getContent() + RESET);
                else if (grid[i][j].getContent().equals("T"))
                    System.out.print(" " + BLUE + grid[i][j].getContent() + RESET);
                else System.out.print(" " + grid[i][j].getContent());
                if (j != size - 1) System.out.print(" |");
            }
            System.out.println();
            if (i != size - 1) {
                for (int j = 0; j < size - 1; j++) System.out.print("-+-+");
                System.out.print("-+-");
            }
            System.out.println();
        }
        System.out.println(GREEN + " M " + RESET + "represent Market cell where you can buy item for your heroes \n"
                + RED + " X " + RESET + "represent inaccessible cell where your heroes cannot enter \n"
                + BLUE + " T " + RESET + "represent your current team position. \n"
                + " Empty cell represent a common cell where a " + BLUE + "MONSTER" + RESET + " may occur! Your heroes must fight.");
    }
}
