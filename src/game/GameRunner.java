package game;

import game.RPGGame.Board;
import game.RPGGame.Round;
import game.role.heroes.Hero;
import game.role.heroes.Party;
import game.role.monsters.MonsterFactory;
import game.role.places.*;
import game.role.role;
import game.utils.instructions;

import java.io.PrintStream;
import java.util.Scanner;

import static game.utils.ConsoleColorsCodes.YELLOW_BOLD_BRIGHT;

/**
 * @projectName: monstersAndHerios
 * @package: game
 * @className: GameRunner
 * @author: Danny
 * @description: Running instance of the game which inherits from GameState
 * @date: 2022/11/3 20:45
 * @version: 1.0
 */
public class GameRunner implements GameState {

    private static PrintStream printStream;

    public static Scanner scanner;

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

    private Round round;

    public GameRunner(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
    }

    @Override
    public boolean isOver() {
        for (role hero : HeroParty.getParty()) {
            if (hero.getPos()[0] == 0) return true;
        }
        for (role monster : MonsterParty.getParty()) {
            if (monster.getPos()[0] == 7) return true;
        }
        return false;
    }

    @Override
    public void run() {
        numRounds = 1;
        while (!isOver()) {
            if (numRounds % 8 == 0) {
                printStream.println(YELLOW_BOLD_BRIGHT + "New monsters are generated!" + RESET);
                spawnMonsters(3);
            }
            printStream.println(RED + "========Round " + numRounds + " begins===========" + RESET);
            this.round.playRound(HeroParty, MonsterParty);
            numRounds++;
            heroReborn();
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
        for (int i = 0; i < 3; i++) {
            initHero(i, HeroParty);
        }
        map = new Map(8, HeroParty);
        // Initialize hero 1,2,3 on the map
        for (int i = 0; i < 3; i++) {
            map.getCell(7, i * 3).setHero(HeroParty.getParty().get(i));
            map.setContent(7, i * 3, map.getCell(7, i * 3).getHero().getCharacter() + "     ");
        }
        spawnMonsters(3);
        printStream.println("Your hero party of size " + HeroParty.getParty().size() + " is ready to battle! ");
        printStream.println("Here is your game map: ");
        map.printMap();
        this.round = new Round(map, MonsterParty, HeroParty, printStream);
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
     *
     * @param monsterNum number of monsters to create
     */
    private void spawnMonsters(int monsterNum) {
        int maxLevel = 0;
        int preNum = MonsterParty.getParty().size();
        for (int i = 0; i < HeroParty.getParty().size(); i++) {
            maxLevel = Math.max(maxLevel, HeroParty.getParty().get(i).getLevel());
        }
        for (int i = 0; i < monsterNum; i++) {
            role newMonster = (role) MonsterFactory.getInstance().creatProduct();
            while (newMonster.getLevel() > maxLevel) newMonster = (role) MonsterFactory.getInstance().creatProduct();
            newMonster.setPos(0, i * 3 + 1);
            newMonster.setCharacter("M" + ((numRounds / 8) * 3 + i + 1));
            MonsterParty.addMember(newMonster);
            printStream.println("Monster " + GREEN + newMonster.getName() + RESET + " was spawned.");
        }
        // Initialize Monsters on the map
        for (int i = preNum, j = 0; i < preNum + monsterNum; i++, j++) {
            map.getCell(0, j * 3 + 1).setMonster(MonsterParty.getParty().get(i));
            map.setContent(0, j * 3 + 1, "     " + map.getCell(0, j * 3 + 1).getMonster().getCharacter());
        }
    }

    /**
     * Hero reborn after each round
     */
    private void heroReborn() {
        for (role hero: HeroParty.getParty()) {
            if (((Hero) hero).isDead()) {
                hero.setDead(false);
                int[] birthPlace = ((Hero) hero).getBirthPlace();
                map.getCell(birthPlace[0], birthPlace[1]).setContent(hero.getCharacter() + "     ");
            }
        }
    }

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
