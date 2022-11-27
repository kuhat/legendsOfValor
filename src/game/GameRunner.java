package game;

import game.RPGGame.Board;
import game.RPGGame.Cell;
import game.RPGGame.RPGItem;
import game.role.heroes.Hero;
import game.role.heroes.Party;
import game.role.item.Armor;
import game.role.item.Potion;
import game.role.item.Spell;
import game.role.item.Weapon;
import game.role.monsters.Monster;
import game.role.monsters.MonsterFactory;
import game.role.mountable;
import game.role.places.Map;
import game.role.role;
import game.utils.ConsoleColorsCodes;
import game.utils.instructions;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static game.utils.ConsoleColorsCodes.GREEN_BOLD_BRIGHT;
import static game.utils.ConsoleColorsCodes.YELLOW_BOLD_BRIGHT;

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
                printStream.println(YELLOW_BOLD_BRIGHT + "New monsters are generated!" + RESET);
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
            map.getCell(7, i * 3).setHero(HeroParty.getParty().get(i));
            map.setContent(7, i * 3, map.getCell(7, i *3).getHero().getCharacter() + "     ");
        }
        spawnMonsters(3);
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
        int preNum = MonsterParty.getParty().size();
        for (int i = 0; i < HeroParty.getParty().size(); i++) {
            maxLevel = Math.max(maxLevel, HeroParty.getParty().get(i).getLevel());
        }
        for (int i = 0; i < monsterNum; i++) {
            role newMonster = (role) MonsterFactory.getInstance().creatProduct();
            while (newMonster.getLevel() > maxLevel) newMonster = (role) MonsterFactory.getInstance().creatProduct();
            newMonster.setPos(0, i * 3 + 1);
            newMonster.setCharacter("M" + ((numRounds / 8) + i + 1));
            MonsterParty.addMember(newMonster);
            printStream.println("Monster " + GREEN + newMonster.getName() + RESET + " was spawned.");
        }
        // Initialize Monsters on the map
        for (int i = preNum, j = 0; i < preNum + monsterNum; i++, j++) {
            map.getCell(0, j * 3 + 1).setMonster(MonsterParty.getParty().get(i));
            map.setContent(0, j * 3 + 1, "     " + map.getCell(0, j * 3 + 1).getMonster().getCharacter());
        }
    }

    private void playRound() {
        heroesAction();
        printStream.println(BLUE + "================= Heroes' tur end =================" + GREEN);
        printStream.println("================= Monster's turn begin =================" + RESET);
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
        map.printMap();
    }

    boolean finish;
    private void heroTakeAction(role hero) {
        while(true) {
            finish = false;
            // If the hero is at the Nexus, ask if the player wants to buy things
            int heroX = hero.getPos()[0];
            int heroY = hero.getPos()[1];
            if (heroX == 7) {
                // Hero enters Nexus
                map.getCell(heroX, heroY).enter(hero);
            }
            String input = "";
            boolean strRes = false;
            while (!strRes) {
                instructions.printHeroChoices(printStream, hero);
                input = getInput("");
                strRes = (input != null && input.matches("[1-8]"));
                if (!strRes) printStream.println("Please choose one number from 1 to 8!");
            }
            switch (input) {
                case "1":  // attack
                    Attack(hero);
                    break;
                case "2":  // cast spell
                    CastSpell(hero);
                    break;
                case "3":  // change armor/weapon
                    ChangeMountables(hero);
                    break;
                case "4": // use potion
                    UsePotion(hero);
                    break;
                case "5": // move
                    Move(hero);
                    map.printMap();
                    break;
                case "6": // Teleport
                    Teleport(hero);
                    map.printMap();
                    break;
                case "7":
                    recall(hero);
                    map.printMap();
                    break;
                case "8": // quit
                    System.out.println("Thanks for playing! ");
                    System.exit(0);
            }
            if (finish) break;
        }
    }

    private void Attack(role hero) {
        if (heroCanAttack(hero)){
            heroAttack(hero);
            finish = true;
        } else {
            System.out.println("No monster is within attack range!");
            finish = false;
        }
    }

    /**
     * Check if there is a monster within the attack range of the hero
     * @param hero hero to attack
     * @return true if the hero can attack
     */
    private boolean heroCanAttack(role hero) {
        int x = hero.getPos()[0], y = hero.getPos()[1];
        boolean res = map.getCell(Math.max(0, x - 1), Math.max(0, y - 1)).hasMonster() || map.getCell(Math.max(0, x - 1), y).hasMonster() || map.getCell(Math.max(0, x - 1), Math.min(7, y + 1)).hasMonster()
                || map.getCell(x, Math.max(0, y - 1)).hasMonster() || map.getCell(x,y).hasMonster() || map.getCell(x, Math.min(7, y + 1)).hasMonster() ||
                map.getCell(Math.min(7, x + 1), Math.max(0, y - 1)).hasMonster() || map.getCell(Math.min(7, x + 1), y).hasMonster() || map.getCell(Math.min(7, x + 1), Math.min(7, y + 1)).hasMonster();
        return res;
    }

    private void heroAttack(role hero) {
        Monster monster = getNeighborMonster(hero);
        hero.attack(monster);
    }

    private void CastSpell(role hero) {
        if (heroCanAttack(hero)) {
            heroCastSpell(hero);
        } else{
            System.out.println("No monster is within attack range!");
            finish = false;
        }
    }

    /**
     * Get the position of the neighboring Monster to attack
     * @param hero hero to attack
     * @return the first Monster that is within attack range
     */
    private Monster getNeighborMonster(role hero) {
        int x = hero.getPos()[0], y = hero.getPos()[1];
        if (map.getCell(Math.max(0, x - 1), Math.max(0, y - 1)).hasMonster()) {
            for (int i = 0; i < MonsterParty.getParty().size(); i++) {
                if (MonsterParty.getParty().get(i).getPos()[0] == Math.max(0, x - 1) && MonsterParty.getParty().get(i).getPos()[1] == Math.max(0, y  - 1)) {
                    return (Monster) MonsterParty.getParty().get(i);
                }
            }
        } else if (map.getCell(Math.max(0, x - 1), y).hasMonster()) {
            for (int i = 0; i < MonsterParty.getParty().size(); i++) {
                if (MonsterParty.getParty().get(i).getPos()[0] == Math.max(0, x - 1) && MonsterParty.getParty().get(i).getPos()[1] == y) {
                    return (Monster) MonsterParty.getParty().get(i);
                }
            }
        } else if (map.getCell(Math.max(0, x - 1), Math.min(7, y + 1)).hasMonster()) {
            for (int i = 0; i < MonsterParty.getParty().size(); i++) {
                if (MonsterParty.getParty().get(i).getPos()[0] == Math.max(0, x - 1) && MonsterParty.getParty().get(i).getPos()[1] == Math.min(7, y + 1)) {
                    return (Monster) MonsterParty.getParty().get(i);
                }
            }
        } else if (map.getCell(x, Math.max(0, y - 1)).hasMonster()) {
            for (int i = 0; i < MonsterParty.getParty().size(); i++) {
                if (MonsterParty.getParty().get(i).getPos()[0] == x && MonsterParty.getParty().get(i).getPos()[1] == Math.max(0, y - 1)) {
                    return (Monster) MonsterParty.getParty().get(i);
                }
            }
        } else if (map.getCell(x, y).hasMonster()) {
            for (int i = 0; i < MonsterParty.getParty().size(); i++) {
                if (MonsterParty.getParty().get(i).getPos()[0] == x && MonsterParty.getParty().get(i).getPos()[1] == y) {
                    return (Monster) MonsterParty.getParty().get(i);
                }
            }
        }  else if (map.getCell(x, Math.min(7, y + 1)).hasMonster()) {
            for (int i = 0; i < MonsterParty.getParty().size(); i++) {
                if (MonsterParty.getParty().get(i).getPos()[0] == x && MonsterParty.getParty().get(i).getPos()[1] == Math.min(7, i + 1)) {
                    return (Monster) MonsterParty.getParty().get(i);
                }
            }
        } else if (map.getCell(Math.min(x + 1, 7), Math.max(0, y - 1)).hasMonster()) {
            for (int i = 0; i < MonsterParty.getParty().size(); i++) {
                if (MonsterParty.getParty().get(i).getPos()[0] == Math.min(x + 1, 7) && MonsterParty.getParty().get(i).getPos()[1] == Math.max(0, y - 1)) {
                    return (Monster) MonsterParty.getParty().get(i);
                }
            }
        } else if (map.getCell(Math.min(x + 1, 7), y).hasMonster()) {
            for (int i = 0; i < MonsterParty.getParty().size(); i++) {
                if (MonsterParty.getParty().get(i).getPos()[0] == Math.min(x + 1, 7) && MonsterParty.getParty().get(i).getPos()[1] == y) {
                    return (Monster) MonsterParty.getParty().get(i);
                }
            }
        }else {
            for (int i = 0; i < MonsterParty.getParty().size(); i++) {
                if (MonsterParty.getParty().get(i).getPos()[0] == Math.min(x + 1, 7) && MonsterParty.getParty().get(i).getPos()[1] == Math.min(7, y + 1)) {
                    return (Monster) MonsterParty.getParty().get(i);
                }
            }
        }
        return null;
    }

    private void heroCastSpell(role hero) {
        Monster monster = getNeighborMonster(hero);
        String input = "";
        boolean strRes = false;
        List<Spell> spellList = ((Hero) hero).getInventory().getSpell();
        if (spellList.size() == 0) {
            System.out.println("This Hero doesn't have any spells.");
            finish = false;
        } else {
            finish = true;
            System.out.println("Hero " + hero.getName() + " has " + ((Hero) hero).getMP() + " left.");
            ((Hero) hero).getInventory().showSpell();
            while (!strRes) {
                input = getInput("Choose a spell to use:");
                strRes = (input != null) && isValid(input, spellList.size() - 1);
                if (!strRes) System.out.println("Invalid input, choose input from 0~" + (spellList.size() - 1));
            }
            if (!((Hero) hero).useSpellTo(Integer.valueOf(input), monster)) {
                System.out.println("Hero doesn't have enough MANA left to cast the spell!");
            }
        }
    }

    private void ChangeMountables(role hero) {
        boolean strRes = false;
        String input = "";
        List<RPGItem> weaponsAndArmors = new ArrayList<>();
        weaponsAndArmors.addAll(((Hero) hero).getInventory().getWeapon());
        weaponsAndArmors.addAll(((Hero) hero).getInventory().getArmor());
        if (weaponsAndArmors.size() == 0) {
            System.out.println("Currently there are no any Weapons or Armors in this hero's inventory.");
        } else {
            Weapon equippedWeapon = ((Hero) hero).getWeapon();
            Armor equippedArmor = ((Hero) hero).getArmor();
            if (equippedWeapon == null) System.out.println("Equipped Weapon is None");
            else
                System.out.println("Equipped Weapon: " + equippedWeapon.getName() + ", Damage: " + equippedWeapon.getDamage());
            if (equippedArmor == null) System.out.println("Equipped Armor is None");
            else
                System.out.println("Equipped Armor: " + equippedArmor.getName() + ", Reduction: " + equippedArmor.getReduction());
            System.out.println("You can choose Armors and Weapons in the current inventory to equip.");
            List<mountable> mountables = ((Hero) hero).getInventory().showMountable();
            while (!strRes) {
                input = getInput("Choose a Weapon or Armor to equip:");
                strRes = (input != null) && isValid(input, mountables.size() - 1);
                if (!strRes) System.out.println("Invalid input, choose input from 0~" + (mountables.size() - 1));
            }
            mountable equipment = mountables.get(Integer.valueOf(input));
            equipment.equip((Hero) hero);
            System.out.println("Hero successfully equipped " + equipment.getType() + ": " + equipment.getName());
        }
    }

    private void UsePotion(role hero) {
        boolean strRes = false;
        String input = "";
        List<Potion> potionList = ((Hero) hero).getInventory().getPotion();
        if (potionList.size() == 0) System.out.println("This hero doesn't have any potions!");
        else {
            ((Hero) hero).getInventory().showPotion();
            while (!strRes) {
                input = getInput("Choose a Potion to use:");
                strRes = (input != null) && isValid(input, potionList.size() - 1);
                if (!strRes) System.out.println("Invalid input, choose input from 0~" + (potionList.size() - 1));
            }
            ((Hero) hero).usePotion(Integer.valueOf(input));
            System.out.println("After using potion, the property of hero: " + hero.getName() + ": \n"
                    + ((Hero) hero).toString());
        }
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
            if (j == 2 || j == 5) {
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
                        if(i<heroPositionRow)
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
                        if(i<heroPositionRow)
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
        if(i>=0 && i<8 && j>=0 && j<8 && j!=2 && j!=5) {
            Cell cell = map.getCell(i,j);
            cellContent = map.getCell(i, j).getContent();
            if (cellContent.equalsIgnoreCase("       ") || cell.hasMonster()) {
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
        Cell newCell = map.getCell(i,j);
        if(newCell.hasMonster()){
            String content = hero.getCharacter() + "   " + map.getCell(i, j).getMonster().getCharacter();
            map.setContent(i, j, content);
        }
        else {
            map.setContent(i, j, ConsoleColorsCodes.BLUE_BOLD_BRIGHT + hero.getCharacter() + "     " + ConsoleColorsCodes.RESET);
        }
        newCell.setHero(hero);
        finish = true;
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
        Cell previousCell = map.getCell(currentRow,currentCol);
        previousCell.setHero(null);
        map.setContent(currentRow,currentCol, "       ");
    }

    private void recall(role hero) {

        int [] birthPlace =((Hero) hero).getBirthPlace();
        hero.setPos(birthPlace[0],birthPlace[1]);
        map.setContent(birthPlace[0], birthPlace[1], hero.getCharacter()+"     ");

    }

    private void Teleport(role hero) {
        int currentRow = hero.getPos()[0];
        int currentCol = hero.getPos()[1];
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
                {
                    System.out.println("Enter the row number to teleport(0-7): ");
                    //Scanner s = new Scanner(System.in);
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
                                    boolean adjacentToHeroCell = isHeroAdjacent(destinationRow,destinationCol);
                                    if(adjacentToHeroCell) {
                                        makeMove(destinationRow, destinationCol, hero);
                                        map.setContent(currentRow, currentCol, "       ");
                                        System.out.println(ConsoleColorsCodes.YELLOW_BOLD_BRIGHT + "Your destination lane is: " + destinationLaneName + ConsoleColorsCodes.RESET);
                                        System.out.println(ConsoleColorsCodes.YELLOW_BOLD_BRIGHT + "Your destination Row is: " + destinationRow + ConsoleColorsCodes.RESET);
                                        System.out.println(ConsoleColorsCodes.YELLOW_BOLD_BRIGHT + "Your destination Column is: " + destinationCol + ConsoleColorsCodes.RESET);
                                        break;
                                    }
                                    else{
                                        System.out.println(ConsoleColorsCodes.RED+"Your chosen cell should be adjacent to hero cell"+ConsoleColorsCodes.RESET);
                                    }
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
                            System.out.println(ConsoleColorsCodes.RED + "Invalid move!" + ConsoleColorsCodes.RESET);
                        }

                    }
                    else{
                        System.out.println(ConsoleColorsCodes.RED + "Invalid move!!" + ConsoleColorsCodes.RESET);
                    }
                }

            }

        }

    }

    boolean isHeroAdjacent(int destinationRow, int destinationCol){

        if(destinationCol==0){
            Cell topCell = map.getCell(destinationRow-1, destinationCol);
            Cell rightCell = map.getCell(destinationRow, destinationCol+1);
            if(topCell.hasHero()  || rightCell.hasHero())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(destinationCol==7){
            Cell topCell = map.getCell(destinationRow-1, destinationCol);
            Cell leftCell = map.getCell(destinationRow,destinationCol-1);
            if(topCell.hasHero() || leftCell.hasHero())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else {
            Cell topCell = map.getCell(destinationRow - 1, destinationCol);
            Cell leftCell = map.getCell(destinationRow, destinationCol - 1);
            Cell rightCell = map.getCell(destinationRow, destinationCol + 1);

        if(topCell.hasHero() || leftCell.hasHero() || rightCell.hasHero())
        {
            return true;
        }
        else
        {
            return false;
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
        printStream.println("Monster " + RED + monster.getName() + RESET + " is taking action.");
        int[] pos = monster.getPos();
        if (monsterCanAttack(monster)) {
            Hero hero = getNeighborHero(monster);
            monster.attack(hero);
            // If there is already a  monster in front of this monster, do not move
        } else if (!heroCanAttack(monster)) {
            printStream.println("No hero is within attack range, move forward");
            // Update the Content of the new Cell and old cell
            String prevContent = map.getCell(pos[0], pos[1]).getContent();
            prevContent = prevContent.substring(0, 5) + "  ";
            String newContent = map.getCell(pos[0] + 1, pos[1]).getContent();
            newContent = newContent.substring(0, 5) + map.getCell(pos[0], pos[1]).getMonster().getCharacter();
            monster.setPos(pos[0] + 1, pos[1]);
            map.getCell(pos[0], pos[1]).setMonster(null);
            map.getCell(pos[0], pos[1]).setContent(prevContent);
            map.getCell(pos[0] + 1, pos[1]).setMonster(monster);
            map.getCell(pos[0] + 1, pos[1]).setContent(newContent);
            // if the monster enters hero's nexus, monster wins, game ends
            if (pos[0] + 1 == 7) {
                monsterWin();
            }
        } else {
            printStream.println("Another monster is in front of this monster, stop moving");
        }
        getInput("Press any key to proceed.");
    }

    /**
     * Check if any heroes are within the attack range of the monster
     * @param monster monster to attack
     * @return true if a hero is within range
     */
    private boolean monsterCanAttack(role monster) {
        int x = monster.getPos()[0], y = monster.getPos()[1];
        boolean res = map.getCell(Math.max(0, x - 1), Math.max(0, y - 1)).hasHero() || map.getCell(Math.max(0, x - 1), y).hasHero() || map.getCell(Math.max(0, x - 1), Math.min(7, y + 1)).hasHero()
                || map.getCell(x, Math.max(0, y - 1)).hasHero() || map.getCell(x,y).hasHero() || map.getCell(x, Math.min(7, y + 1)).hasHero() ||
                map.getCell(Math.min(7, x + 1), Math.max(0, y - 1)).hasHero() || map.getCell(Math.min(7, x + 1), y).hasHero() || map.getCell(Math.min(7, x + 1), Math.min(7, y + 1)).hasHero();
        return res;
    }

    /**
     * Get the position of the neighboring Hero to attack
     * @param monster Monster to attack
     * @return the first Hero that is within attack range
     */
    private Hero getNeighborHero(role monster) {
        int x = monster.getPos()[0], y = monster.getPos()[1];
        if (map.getCell(x, Math.max(0, y - 1)).hasHero()) {
            for (int i = 0; i < HeroParty.getParty().size(); i++) {
                if (HeroParty.getParty().get(i).getPos()[0] == x && HeroParty.getParty().get(i).getPos()[1] == Math.max(0, y - 1)) {
                    return (Hero) HeroParty.getParty().get(i);
                }
            }
        } else if (map.getCell(x, y).hasHero()) {
            for (int i = 0; i < HeroParty.getParty().size(); i++) {
                if (HeroParty.getParty().get(i).getPos()[0] == x && HeroParty.getParty().get(i).getPos()[1] == y) {
                    return (Hero) HeroParty.getParty().get(i);
                }
            }
        }  else if (map.getCell(x, Math.min(7, y + 1)).hasHero()) {
            for (int i = 0; i < HeroParty.getParty().size(); i++) {
                if (HeroParty.getParty().get(i).getPos()[0] == x && HeroParty.getParty().get(i).getPos()[1] == Math.min(7, i + 1)) {
                    return (Hero) HeroParty.getParty().get(i);
                }
            }
        } else if (map.getCell(Math.min(x + 1, 7), Math.max(0, y - 1)).hasHero()) {
            for (int i = 0; i < HeroParty.getParty().size(); i++) {
                if (HeroParty.getParty().get(i).getPos()[0] == Math.min(x + 1, 7) && HeroParty.getParty().get(i).getPos()[1] == Math.max(0, y - 1)) {
                    return (Hero) HeroParty.getParty().get(i);
                }
            }
        } else if (map.getCell(Math.min(x + 1, 7), y).hasHero()) {
            for (int i = 0; i < HeroParty.getParty().size(); i++) {
                if (HeroParty.getParty().get(i).getPos()[0] == Math.min(x + 1, 7) && HeroParty.getParty().get(i).getPos()[1] == y) {
                    return (Hero) HeroParty.getParty().get(i);
                }
            }
        }else {
            for (int i = 0; i < HeroParty.getParty().size(); i++) {
                if (HeroParty.getParty().get(i).getPos()[0] == Math.min(x + 1, 7) && HeroParty.getParty().get(i).getPos()[1] == Math.min(7, y + 1)) {
                    return (Hero) HeroParty.getParty().get(i);
                }
            }
        }
        return null;
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

    /**
     * Check if the input of the user is value and within heroIdx range
     *
     * @param input user input
     * @return true if the input is an Integer and within hero idx range
     */
    private boolean isValid(String input, int upperValue) {
        for (char s : input.toCharArray()) if (!Character.isDigit(s)) return false;
        int value = Integer.valueOf(input);
        if (value < 0 && value > upperValue) return false;
        return true;
    }
}
