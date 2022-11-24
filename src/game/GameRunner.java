package game;

import game.RPGGame.Board;
import game.RPGGame.Cell;
import game.RPGGame.RPGItem;
import game.role.heroes.Hero;
import game.role.heroes.Party;
import game.role.monsters.MonsterFactory;
import game.role.places.Map;
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

    private Map map;

    private Party HeroParty;

    private Party MonsterParty;

    private int numRounds;

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
        numRounds = 1;
        while (true) {
            if(numRounds % 8 == 0) {
                printStream.println("New monsters are generated!");
                spawnMonsters(3);
            }
            printStream.println(RED + "========Round " + numRounds + " begins===========" + RESET);
            playRound();
            numRounds++;
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
        instructions.printAllHeroesInfo(this.printStream, HeroParty);
    }

    /**
     * Initialize the roles and board
     */
    private void initRoles() {
        this.HeroParty = new Party();
        this.MonsterParty = new Party();
        // Initialize three hero on the three lanes
        for (int i = 0; i <3; i++) {
            initHero(i, HeroParty);
        }
        map = new Map(8, HeroParty);
        // Initialize hero 1,2,3 on the map
        for (int i = 0; i < 3; i++) {
            map.getCell(7, i * 3).setRole(HeroParty.getParty().get(i));
            map.setContent(7, i * 3, map.getCell(7, i *3).getRole().getCharacter() + "     ");
        }
        spawnMonsters(3);
        // Initialize Monster 1,2,3 on the map
        for (int i = 0; i < 3; i++) {
            map.getCell(0, i * 3 + 1).setRole(MonsterParty.getParty().get(i));
            map.setContent(0, i * 3 + 1, "     " + map.getCell(0, i * 3 + 1).getRole().getCharacter());
        }
        printStream.println("Your hero party of size " + HeroParty.getParty().size() + " is ready to battle! ");
        printStream.println("Here is your game map: ");
        map.printMap();
    }

    private void initHero(int lane, Party party) {
        printStream.println("Please choose your hero on the lane " + lane);
        String input = "";
        boolean strResult = false;
        while (!strResult) {
            instructions.printHeroChoice(printStream);
            input = getInput("");
            strResult = (input != null & input.matches("[1-3]"));
        }
        int kind = Integer.valueOf(input);
        instructions.printHeroChoiceOutcome(kind, printStream, party, lane, map);
    }

    /**
     * Create new monsters on each lane
     * @param monsterNum number of monsters to create
     */
    private void spawnMonsters(int monsterNum) {
        int maxLevel = 0;
        for (int i = 0; i < HeroParty.getParty().size(); i++) {
            maxLevel = Math.max(maxLevel, HeroParty.getParty().get(i).getLevel());
        }
        for (int i = 0; i < monsterNum; i++) {
            role newMonster = (role) MonsterFactory.getInstance().creatProduct();
            while (newMonster.getLevel() > maxLevel) newMonster = (role) MonsterFactory.getInstance().creatProduct();
            newMonster.setPos(0, i * 3 + 1);
            newMonster.setCharacter("M" + (i + 1));
            MonsterParty.addMember(newMonster);
        }
    }

    private void playRound() {
        heroesAction();
        monsterAction();
    }

    private void heroesAction() {
        for (int i = 0; i < HeroParty.getParty().size(); i++) {
            heroTakeAction(HeroParty.getParty().get(i));
        }
    }

    private void monsterAction() {

    }

    private void heroTakeAction(role hero) {
        // If the hero is at the Nexus, ask if the player wants to buy things
        int heroX = hero.getPos()[0];
        int heroY = hero.getPos()[1];
        if (heroX == 7) {
            // Hero enters Nexus
            map.getCell(heroX, heroY).enter(hero);
        }
        String input = "";
        Boolean strRes = false;
        while (!strRes) {
            instructions.printHeroChoices(printStream, hero);
            input = getInput("");
            strRes = (input != null && input.matches("[1-7]"));
            if (!strRes) printStream.println("Please choose one number from 1 to 7!");
        }
        switch (input) {
            case "1":  // attack
               Attack();
            case "2":  // cast spell
                CastSpell();
            case "3":  // change armor/weapon
                ChangeMountables();
            case "4": // use potion
                UsePotion();
            case "5": // move
                Move();
            case "6": // Teleport
                Teleport();
            case "7": // quit
                System.out.println("Thanks for playing! ");
                System.exit(0);
        }
    }

    private void Attack() {
        if (canAttack()){
            heroAttack();
        } else {
            System.out.println("No monster is within attack range!");
        }
    }

    private boolean canAttack() {
        return true;
    }

    private void heroAttack() {

    }

    private void CastSpell() {
        if (canAttack()) {
            heroCastSpell();
        } else{
            System.out.println("No monster is within attack range!");
        }
    }

    private void heroCastSpell() {

    }

    private void ChangeMountables() {

    }

    private void UsePotion() {

    }

    private void Move() {

    }

    private void Teleport() {

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
        int[] pos = this.HeroParty.getPosition();
        int newX = pos[0], newY = pos[1];
        Cell oldCell = board.getCell(pos[0], pos[1]);
        String type = oldCell.getType();
        if (input.equalsIgnoreCase("s")) newX +=1;  // Move down
        else if (input.equalsIgnoreCase("w")) newX -= 1;  // move up
        else if (input.equalsIgnoreCase("a")) newY -= 1;  // move left
        else newY += 1;  // move right
        if (newX >= 0 && newX < board.getSize() && newY >= 0 && newY < board.getSize()) {
            Cell newCell = board.getCell(newX, newY);
            newCell.enter(HeroParty);  // Party go to the new position
            if (!newCell.getType().equals("X")) {
                newCell.setContent("T");
                HeroParty.setPosition(newX, newY);  // set the position of party
                oldCell.setContent(type);  // set the original type of the cell to the content
                board.printBoard();
            }
        } else {
            printStream.println("Invalid input! Please choose your destination again!");
        }
    }

    private void showInventory() {
        for (role hero: HeroParty.getParty()) {
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
