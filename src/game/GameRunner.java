package game;

import game.RPGGame.Board;
import game.RPGGame.Cell;
import game.RPGGame.RPGItem;
import game.role.heroes.Hero;
import game.role.heroes.Party;
import game.role.role;
import game.utils.instructions;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @projectName: monstersAndHerios
 * @package: game
 * @className: GameRunner
 * @author: Danny
 * @description: Running instance of the game
 * @date: 2022/11/3 20:45
 * @version: 1.0
 */
public class GameRunner implements GameState {

    private static PrintStream printStream;

    private static Scanner scanner;

    /**
     * Color definitions
     */
    public static final String RED = "\033[31m";

    public static final String RESET = "\033[0m";

    public static final String GREEN = "\u001B[32m";

    public static final String BLUE = "\u001B[34m";

    private Board board;

    private Party party;

    public GameRunner(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public void run() {
        while (true) {
            handleUserInput();
        }
    }

    @Override
    public void start() {
        init();
        run();
    }

    @Override
    public void init() {
        instructions.printInitInstruction(this.printStream);
        initRoles();
    }

    /**
     * Initialize the roles and board
     */
    private void initRoles() {
        printStream.println("Here is your game board: ");
        board.printBoard();
        this.party = new Party();
        // Initialize three hero on the three lanes
        for (int i = 0; i <3; i++) {
            initHero(i, party);
        }
        String input = "";
        boolean strResult = false;
        printStream.println("Your hero party of size " + party.getParty().size() + " is ready to battle! ");

        this.board = new Board(8, party);
        board.printBoard();
    }

    private void initHero(int lane, Party party) {
        printStream.println("Please choose your hero on the lane " + lane);
        String input = "";
        boolean strResult = false;
        while (!strResult) {
            instructions.printHeroChoice(printStream);
            input = getInput("");
            strResult = (input != null & input.matches("[1-3]]"));
        }
        int kind = Integer.valueOf(input);
        instructions.printHeroChoiceOutcome(kind, printStream, party, lane);
    }


    private void handleUserInput() {
        instructions.printStepInstruction(printStream);
        boolean strResult = false;
        String input = "";
        while (!strResult) {
            input = getInput("Please enter your choice: ");
            strResult = (input != null && input.matches("[WwAaSsDdQqIi]"));
            if (!strResult) printStream.println("Please input valid choice!");
        }
        if (input.equalsIgnoreCase("i")) showInventory();
        else if (input.equalsIgnoreCase("q")) {
            printStream.println("Thanks for Playing!");
            System.exit(0);
        } else TeamMove(input);
    }

    /**
     * Handle team move movement. If the destination os valid, enter the new Cell else do not enter
     * @param input User input to judge: W, A, S, D
     */
    private void TeamMove(String input) {
        int[] pos = this.party.getPosition();
        int newX = pos[0], newY = pos[1];
        Cell oldCell = board.getCell(pos[0], pos[1]);
        String type = oldCell.getType();
        if (input.equalsIgnoreCase("s")) newX +=1;  // Move down
        else if (input.equalsIgnoreCase("w")) newX -= 1;  // move up
        else if (input.equalsIgnoreCase("a")) newY -= 1;  // move left
        else newY += 1;  // move right
        if (newX >= 0 && newX < board.getSize() && newY >= 0 && newY < board.getSize()) {
            Cell newCell = board.getCell(newX, newY);
            newCell.enter(party);  // Party go to the new position
            if (!newCell.getType().equals("X")) {
                newCell.setContent("T");
                party.setPosition(newX, newY);  // set the position of party
                oldCell.setContent(type);  // set the original type of the cell to the content
                board.printBoard();
            }
        } else {
            printStream.println("Invalid input! Please choose your destination again!");
        }
    }

    private void showInventory() {
        for (role hero: party.getParty()) {
            System.out.println("Hero: " + BLUE  + hero.getName() + RESET+ ": ");
            System.out.println(((Hero)hero).toString());
            for (RPGItem item : ((Hero)hero).getInventory().getItems()) {
                System.out.println( " - "+  item.toString());
            }
        }
    }


    /**
     * Get user input and give them a prompt
     *
     * @param prompt Prompt to be displayed to user
     * @return user input if user input the right format
     * @throws Exception if user input wrong
     */
    public static String getInput(String prompt) {
        if (!prompt.equals("")) printStream.println(prompt);
        try {
            String input = scanner.nextLine();
            return input;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
