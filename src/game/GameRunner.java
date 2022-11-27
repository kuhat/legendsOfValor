package game;

import game.RPGGame.Board;
import game.RPGGame.Cell;
import game.RPGGame.InaccessibleCell;
import game.RPGGame.RPGItem;
import game.role.heroes.Hero;
import game.role.heroes.Party;
import game.role.monsters.Monster;
import game.role.monsters.MonsterFactory;
import game.role.places.Map;
import game.role.role;
import game.utils.ConsoleColorsCodes;
import game.utils.instructions;

import java.io.PrintStream;
import java.util.InputMismatchException;
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
        for (role hero : HeroParty.getParty()) {
            if (hero.getPos()[0] == 0) return true;
        }
        for (role monster: MonsterParty.getParty()) {
            if (monster.getPos()[0] == 7) return true;
        }
        return false;
    }

    @Override
    public void run() {
        numRounds = 1;
        while (!isOver()) {
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
            newMonster.setCharacter("M" + ((numRounds / 8) + i + 1));
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
        for (int i = 0; i < MonsterParty.getParty().size(); i ++) {
            MonsterTakeAction(MonsterParty.getParty().get(i));
        }
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
            strRes = (input != null && input.matches("[1-8]"));
            if (!strRes) printStream.println("Please choose one number from 1 to 8!");
        }
        switch (input) {
            case "1":  // attack
               Attack(hero);
            case "2":  // cast spell
                CastSpell(hero);
            case "3":  // change armor/weapon
                ChangeMountables();
            case "4": // use potion
                UsePotion();
            case "5": // move
                Move(hero);
            case "6": // Teleport
                Teleport(hero);
            case "7":
                recall(hero);
            case "8": // quit
                System.out.println("Thanks for playing! ");
                System.exit(0);
        }
    }

    private void Attack(role hero) {
        if (canAttack(hero)){
            heroAttack();
        } else {
            System.out.println("No monster is within attack range!");
        }
    }

    private boolean canAttack(role hero) {
        int x = hero.getPos()[0], y = hero.getPos()[1];
        boolean res = map.getCell(Math.max(0, x - 1), Math.max(0, y - 1)).hasMonster() || map.getCell(Math.max(0, x - 1), y).hasMonster() || map.getCell(Math.max(0, x - 1), Math.min(7, y + 1)).hasMonster()
                || map.getCell(x, Math.max(0, y - 1)).hasMonster() || map.getCell(x,y).hasMonster() || map.getCell(x, Math.min(7, y + 1)).hasMonster() ||
                map.getCell(Math.min(7, x + 1), Math.max(0, y - 1)).hasMonster() || map.getCell(Math.min(7, x + 1), y).hasMonster() || map.getCell(Math.min(7, x + 1), Math.min(7, y + 1)).hasMonster();
        return res;
    }

    private void heroAttack() {

    }

    private void CastSpell(role hero) {
        if (canAttack(hero)) {
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

    private void Move(role hero) {
        getCoordinates(hero);
    }

    private int selectDirection() {

        System.out.println("1. To Move Up");
        System.out.println("2. To Move Down");
        System.out.println("3. To Move Left");
        System.out.println("4. To Move Right");

        int direction;

        do{
            System.out.println("Please enter your move as below:");
            Scanner s = new Scanner(System.in);
            try{
                direction = s.nextInt();
            }
            catch(ArrayIndexOutOfBoundsException a){
                System.out.println(ConsoleColorsCodes.RED+"Please select the valid move"+ConsoleColorsCodes.RESET);
                direction = 0;
            }
        }while(direction<1 || direction>4);
        return direction;
    }

    private int[] getDirectionCoordinates(int direction, role hero){
        int newRow=0;
        int newCol=0;
        int[] newTile = new int[2];
        //Move Up
        if (direction==1){
            newRow= hero.getPos()[0]-1;
            newCol=hero.getPos()[1];
        }
        //Move Down
        if(direction==2){
            newRow=hero.getPos()[0]+1;
            newCol=hero.getPos()[1];
        }
        //Move Left
        if(direction==3){
            newRow=hero.getPos()[0];
            newCol=hero.getPos()[1]-1;
        }
        //Move Right
        if(direction==4){
            newRow=hero.getPos()[0];
            newCol=hero.getPos()[1]+1;
        }

        newTile[0]= newRow;
        newTile[1]= newCol;

        return newTile;
    }

    private boolean isValidateMove(int i, int j){

        if(i>=0 && i<8 && j>=0 && j<8){
            if (i == 2 || i == 5) {
                System.out.println(ConsoleColorsCodes.RED+"You are trying to access Inaccessible area!!"+ConsoleColorsCodes.RESET);
                return false;
            }
            else
            {
                return true;
            }
        }
        else {
            System.out.println(ConsoleColorsCodes.RED+"Invalid move"+ConsoleColorsCodes.RESET);
            return false;
        }
    }

    private int getMonsterPosition(role hero){
        int heroCol;
        int monsterPositionRow = 0;
        heroCol = hero.getPos()[1];
        if(heroCol==0 || heroCol==3 || heroCol==6){
            for (int i=0;i<8;i++){
                for(int j=heroCol;j<=(heroCol+1);j++){
                    Cell cell = map.getCell(i,j);
                    if(cell.hasMonster()){
                        monsterPositionRow=i;
                    }
                }
            }
        }
        else{
            for (int i=0;i<8;i++){
                for(int j=(heroCol-1);j<=heroCol;j++){
                    Cell cell = map.getCell(i,j);
                    if(cell.hasMonster()){
                        monsterPositionRow=i;
                    }
                }
            }
        }
        return monsterPositionRow;
    }

    private int getHeroPosition(role hero){
        int heroCol;
        int heroPositionRow = 0;
        heroCol = hero.getPos()[1];
        if(heroCol==0 || heroCol==3 || heroCol==6){
            for (int i=0;i<8;i++){
                for(int j=heroCol;j<=(heroCol+1);j++){
                    Cell cell = map.getCell(i,j);
                    if(cell.hasHero()){
                        if(i>heroPositionRow)
                        {
                        heroPositionRow=i;
                        }
                    }
                }
            }
        }
        else{
            for (int i=0;i<8;i++){
                for(int j=(heroCol-1);j<=heroCol;j++){
                    Cell cell = map.getCell(i,j);
                    if(cell.hasHero()){
                        if(i>heroPositionRow)
                        {
                        heroPositionRow=i;
                        }
                    }
                }
            }
        }
        return heroPositionRow;
    }

    private boolean isCellContentEmpty(int i, int j){
        String cellContent;
        if(i>=0 && i<8 && j>=0 && j<8 && i!=2 && i!=5) {
            cellContent = map.getCell(i, j).getContent();
            if (cellContent.equalsIgnoreCase("       ")) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            System.out.println(ConsoleColorsCodes.RED + "You are trying wrong move." + ConsoleColorsCodes.RESET);
            return false;
        }
    }

    private void makeMove(int i, int j, role hero){
        hero.setPos(i,j);
        System.out.println("New Positions: " + hero.getPos()[0]+ " " + hero.getPos()[1]);
    }

    private void getCoordinates(role hero){
        int currentRow = hero.getPos()[0];
        int currentCol = hero.getPos()[1];
        int newTile[];
        int monsterExploredRow = getMonsterPosition(hero);
        boolean checkValidity;
        boolean checkValidity2;
        boolean checkMonsterSurpassed = true;
        boolean checkCellValidity;
        do {
            System.out.println("HeroName"+hero.getName());
            int direction = selectDirection();
            newTile=getDirectionCoordinates(direction, hero);
            checkValidity = isValidateMove(currentRow, currentCol);
            checkValidity2 = isValidateMove(newTile[0], newTile[1]);
            checkCellValidity = isCellContentEmpty(newTile[0], newTile[1]);

            if(monsterExploredRow<=newTile[0]){
                checkMonsterSurpassed = false;
            }
            else {
                System.out.println(ConsoleColorsCodes.RED+"You cannot surpass the monster."+ConsoleColorsCodes.RESET);
            }
        }
        while(!checkValidity || !checkValidity2 || !checkCellValidity || checkMonsterSurpassed); //All condition should be false to break the loop
        makeMove(newTile[0], newTile[1], hero);
    }

    private void recall(role hero) {

        int [] birthPlace =((Hero) hero).getBirthPlace();
        hero.setPos(birthPlace[0],birthPlace[1]);
        map.setContent(birthPlace[0], birthPlace[1], hero.getCharacter()+"     ");

    }

    private void Teleport(role hero) {
        int destinationLane;
        String destinationLaneName;
        while(true){
            System.out.println("Enter the lane number to teleport: ");
            System.out.println("1. Top Lane");
            System.out.println("2. Mid Lane");
            System.out.println("3. Bot Lane");
            Scanner s = new Scanner(System.in);
            int lane = s.nextInt();
            int currentLane = getHeroCurrentLane(hero);
            if(lane == currentLane){
                System.out.println(ConsoleColorsCodes.RED + "Teleport is possible between different lanes" + ConsoleColorsCodes.RESET);
            }
            else if(lane>=1 && lane<=3 && (lane != currentLane)){
                destinationLane = lane;
                if(destinationLane == 1)
                    destinationLaneName = "Top Lane";
                else if(destinationLane == 2)
                    destinationLaneName = "Middle Lane";
                else if(destinationLane==3)
                    destinationLaneName = "Bot Lane";
                else destinationLaneName = "Null Lane";
                System.out.println(ConsoleColorsCodes.YELLOW_BOLD_BRIGHT + "Your destination lane is: " + destinationLaneName + ConsoleColorsCodes.RESET);
                break;
            }

        }
        System.out.println("Out of loop");
        while (true){
            System.out.println("Enter the row number to teleport(0-7): ");
            Scanner s = new Scanner(System.in);
            int destinationRow = s.nextInt();
            int destinationCol;
            if(destinationRow>=0 && destinationRow<8){
                System.out.println("Enter the column number to teleport(0-7):");
                destinationCol = s.nextInt();
                boolean validateCell = isCellContentEmpty(destinationRow, destinationCol);
                if(validateCell){
                    int monsterExploredRow = getMonsterPosition(hero);
                    if(monsterExploredRow<=destinationRow){
                        int heroExploredRow = getHeroPosition(hero);
                        if(heroExploredRow<=destinationRow)
                        {
                            makeMove(destinationRow,destinationCol,hero);
                            System.out.println(ConsoleColorsCodes.YELLOW_BOLD_BRIGHT + "Your destination lane is: " + destinationLaneName + ConsoleColorsCodes.RESET);
                            System.out.println(ConsoleColorsCodes.YELLOW_BOLD_BRIGHT + "Your destination Row is: " + destinationRow + ConsoleColorsCodes.RESET);
                            System.out.println(ConsoleColorsCodes.YELLOW_BOLD_BRIGHT + "Your destination Column is: " + destinationCol + ConsoleColorsCodes.RESET);
                            break;
                        }
                        else{
                            System.out.println(ConsoleColorsCodes.RED+"You cannot surpass the hero."+ConsoleColorsCodes.RESET);
                        }
                    }
                    else {
                        System.out.println(ConsoleColorsCodes.RED+"You cannot surpass the monster."+ConsoleColorsCodes.RESET);
                    }
                }
                else{
                    System.out.println(ConsoleColorsCodes.RED + "Please enter the column in the range of 0-7" + ConsoleColorsCodes.RESET);
                }

            }
            else{
                System.out.println(ConsoleColorsCodes.RED + "Please enter the row in the range of 0-7" + ConsoleColorsCodes.RESET);
            }
        }

    }

    private int getHeroCurrentLane(role hero){
        int currentLane=0;
        int heroCol = hero.getPos()[1];
        if(heroCol==0 || heroCol==1){
            currentLane = 1;
        }
        else if(heroCol==3 || heroCol==4){
            currentLane = 2;
        }
        else if(heroCol==6 || heroCol==7){
            currentLane = 3;
        }
        else {
            currentLane = 0;
        }
        return currentLane;
    }

    private void MonsterTakeAction(role monster) {

    }

    private void heroWin() {
        System.out.println("A hero has successfully entered the Nexus of Monster! \n" +
                "You won the game!");
        getInput("Press any key to end the game.");
        System.exit(0);
    }

    private void monsterWin() {
        System.out.println("A Monster has successfully entered the Nexus of Hero! \n" +
                "You lose the game!");
        getInput("Press any key to end the game.");
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
