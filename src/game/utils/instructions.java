package game.utils;

import game.RPGGame.RPGItem;
import game.role.heroes.*;
import game.role.item.*;
import game.role.places.Map;
import game.role.role;

import java.io.PrintStream;
import java.util.List;

import static game.GameRunner.*;
import static game.utils.ConsoleColorsCodes.*;
import static game.utils.ConsoleColorsCodes.BLUE;
import static game.utils.ConsoleColorsCodes.GREEN;
import static game.utils.ConsoleColorsCodes.RED;
import static game.utils.ConsoleColorsCodes.RESET;

/**
 * @projectName: monstersAndHeroes
 * @package: game.utils
 * @className: instructions
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/4 17:53
 * @version: 1.0
 */
public class instructions {

    static PrintStream printStream = new PrintStream(System.out);

    /**
     * First notification shown on the welcome stage
     *
     * @param printStream printStream to print message
     */
    public static void printInitInstruction(PrintStream printStream) {
        printStream.println();
        printStream.println(" This is a terminal based RPG game, pay attention to the output of terminal! \n" +
                "You will be given a team of Heroes to fight with a variety of Monsters. \n Good Luck!");
        printStream.println("*++++++++++++++++++++++*++  Now Let's begin  +++++++++++++++++++*********");
    }

    /**
     * Print the choice of choosing heroes at the beginning of the game
     *
     * @param printStream printStream to print the message
     */
    public static void printHeroChoice(PrintStream printStream) {
        printStream.println(
                "Please choose the type of Hero, Here is the choice of heroes: \n" +
                        "1: Warriors: favored on strength and agility. \n" +
                        "2: Sorcerers: favored on dexterity and agility. \n" +
                        "3: Paladins: favored on strength and dexterity. \n"
        );
    }

    /**
     * Print the instruction for each step
     *
     * @param printStream PrintStream to print out information
     */
    public static void printStepInstruction(PrintStream printStream) {
        printStream.println("Use Key WSAD to move around in the board. \n" +
                "+ W/w: move up\n" +
                "+ A/a: move left\n" +
                "+ S/s: move down\n" +
                "+ D/d: move right\n" +
                "+ Q/q: quit game\n" +
                "+ I/i: show information\n" +
                "A Monster will appear by chance when you enter a empty cell\n" +
                "You can buy items when entering a market cell");
    }

    /**
     * Print the user hero choice outcome
     *
     * @param kind        kind of the hero, 1 represent Warrior, 2 represent Sorcerer, 3 represent Paladin
     * @param printStream printStream to print content
     * @param party       Party to add the heroes
     */
    public static void printHeroChoiceOutcome(int kind, PrintStream printStream, Party party, int lane, Map map) {
        if (kind == 1) {
            printStream.println("You chose Warrior! ");
            Warrior warrior = (Warrior) party.addWarrior();
            warrior.setPos(7, lane * 3);
            warrior.setBirthPlace(7, lane * 3);
//            map.getCell(7, lane * 3).setRole(warrior);
            warrior.setCharacter("H" + (lane + 1));
            printStream.println("Warrior: " + BLUE + warrior.getName() + RESET + " entered lane " + lane + "!");
        } else if (kind == 2) {
            printStream.println("You chose Sorcerer! ");
            Sorceror sorceror = (Sorceror) party.addSorcerer();
            sorceror.setPos(7, lane * 3);
            sorceror.setCharacter("H" + (lane + 1));
            sorceror.setBirthPlace(7, lane * 3);
//            map.getCell(7, lane * 3).setRole(sorceror);
            printStream.println("Sorcerer: " + GREEN + sorceror.getName() + RESET + " entered lane " + lane + "!");
        } else if (kind == 3) {
            printStream.println("You chose Paladin! ");
            Paladin paladin = (Paladin) party.addPaladin();
            paladin.setPos(7, lane * 3);
            paladin.setCharacter("H" + (lane + 1));
            paladin.setBirthPlace(7, lane * 3);
//            map.getCell(7, lane * 3).setRole(paladin);
            printStream.println("Paladin: " + RED + paladin.getName() + RESET + " entered lane " + lane + "!");
        }
    }

    public static void printAllHeroesInfo(PrintStream printStream, Party party) {
        printStream.println("Here are your Heroes information: ");
        printStream.format("%4s%24s%10s%10s%10s%15s%15s%15s%15s%16s%15s", "ID", "Name", "Level", "HP", "Mana",
                "Strength", "Agility", "Dexterity", "Money", "Experience", "Current lane");
        printStream.println();
        printStream.println(PURPLE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" +
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" + RESET);
        List<role> Hparty = party.getParty();
        for (int i = 0; i < Hparty.size(); i++) {
            role Hero = Hparty.get(i);
            printStream.format("%4s%24s%10s%10s%10s%15s%15s%15s%15s%16s%15s", i, Hero.getName(), Hero.getLevel(), Hero.getHP()
            ,((Hero)Hero).getMP(), ((Hero)Hero).getPower(), ((Hero)Hero).getAgility(), ((Hero)Hero).getDexterity(), ((Hero)Hero).getMoney()
            , ((Hero)Hero).getExperience(), Hero.getLane());
            printStream.println();
        }
        printStream.println(PURPLE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" +
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" + RESET);
    }

    public static void printHeroChoices(PrintStream printStream, role hero) {
        printStream.println("Please choose one action for hero " + BLUE_BOLD_BRIGHT + hero.getName() + RESET + ":");
        printStream.println(" 1: Attack\n 2: Use a spell\n 3: Change Weapon or Armor\n 4: Use a potion\n " +
                "5: Move\n 6: Teleport\n 7: Recall\n 8: Quit game\n");
    }

    /**
     * Print the message when the player enter a market cell
     */
    public static void printMarketMsg(Party party) {
        System.out.println("Welcome to the market, do you want to enter to buy things? \n" +
                " the amount of gold each hero has is listed below:");
        for (int i = 0; i < party.getParty().size(); i++) {
            System.out.println("Hero " + party.getParty().get(i).getName() + " has " + ((Hero) party.getParty().get(i)).getGold());
        }
    }

    public static void printMarketMsg(role role) {
        System.out.println("Welcome to Nexus, do you want to buy or sell items? \n " +
                "The hero's gold is " + ((Hero) role).getGold());
    }

    /**
     * Print Spell list for the market
     */
    public static void printSpell() {
        //        Name/cost/required level/damage/mana cost
        List<RPGItem> spellList = ItemFactory.getInstance().createSpell();
        System.out.println("Here is all the spells that are on sale: ");
        printStream.format("%4s%20s%10s%10s%10s%15s", "ID", "Name", "cost", "required level", "damage", "mana cost");
        printStream.println();
        printStream.println(GREEN + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + RESET);
        for (int i = 0 ; i < spellList.size(); i ++) {
            RPGItem spell = spellList.get(i);
            printStream.format("%4s%20s%10s%10s%10s%15s", i, spell.getName(), spell.getPrice(), spell.getLevel(),
                    ((Spell) spell).getDamage(), ((Spell) spell).getManaCost());
            printStream.println();
        }
        printStream.println(GREEN + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + RESET);
    }

    /**
     * Print Armor list for the market
     */
    public static void printArmor() {
        List<RPGItem> armorList = ItemFactory.getInstance().createArmor();
        // Name/cost/required level/damage reduction
        System.out.println("Here is all the armors that are on sale: ");
        printStream.format("%4s%20s%10s%20s%20s", "ID", "Name", "cost", "required level", "damage reduction");
        printStream.println();
        printStream.println(PURPLE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + RESET);
        for (int i = 0 ; i < armorList.size(); i ++) {
            RPGItem armor = armorList.get(i);
            printStream.format("%4s%20s%10s%20s%20s", i, armor.getName(), armor.getPrice(), armor.getLevel(),
                    ((Armor) armor).getReduction());
            printStream.println();
        }
        printStream.println(PURPLE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + RESET);

    }

    /**
     * Print weapon list for the market
     */
    public static void printWeapon() {
        //        Name/cost/level/damage/required hands
        List<RPGItem> weaponList = ItemFactory.getInstance().createWeapon();
        System.out.println("Here are all the weapons that are on sale: ");
        printStream.format("%4s%20s%10s%10s%10s%20s", "ID", "Name", "cost", "required level", "damage", "required hands");
        printStream.println();
        printStream.println(BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" + RESET);
        for (int i = 0 ; i < weaponList.size(); i ++) {
            RPGItem weapon = weaponList.get(i);
            printStream.format("%4s%20s%10s%10s%10s%20s", i, weapon.getName(), weapon.getPrice(), weapon.getLevel(),
                    ((Weapon) weapon).getDamage(), ((Weapon) weapon).getHands());
            printStream.println();
        }
        printStream.println(BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" + RESET);
    }

    /**
     * print potion list for the market
     */
    public static void printPotion() {
        // Name/cost/required level/attribute increase/attribute affected
        // props: String name, int Hp, int power, int defense, int level, int dodge, int agility, int dexterity, int gold, int experience
        List<RPGItem> potionList = ItemFactory.getInstance().createPotion();
        System.out.println("Here are all the weapons that are on sale: ");
        printStream.format("%4s%20s%10s%20s%20s%55s", "ID", "Name", "cost", "required level", "attribute increase", "attribute affected");
        printStream.println();
        printStream.println(YELLOW_BOLD_BRIGHT + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" +
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" + RESET);
        for (int i = 0 ; i < potionList.size(); i ++) {
            RPGItem potion = potionList.get(i);
            printStream.format("%4s%20s%10s%20s%20s%55s", i, potion.getName(), potion.getPrice(), potion.getLevel(),
                     ((Potion) potion).getInc(), ((Potion) potion).getAttr());
            printStream.println();
        }
        printStream.println(YELLOW_BOLD_BRIGHT + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" +
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" + RESET);
    }

    /**
     * Print the weapon in hero's inventory
     * @param inventory hero's inventory
     */
    public static void printInventoryWeapon(Inventory inventory) {
            List<Weapon> weaponList = inventory.getWeapon();
            System.out.println("Here are all the weapons that are in the hero's inventory: ");
            printStream.format("%4s%20s%10s%10s%10s%20s", "ID", "Name", "cost", "required level", "damage", "required hands");
            printStream.println();
            printStream.println(BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" + RESET);
            for (int i = 0 ; i < weaponList.size(); i ++) {
                RPGItem weapon = weaponList.get(i);
                printStream.format("%4s%20s%10s%10s%10s%20s", i, weapon.getName(), weapon.getPrice(), weapon.getLevel(),
                        ((Weapon) weapon).getDamage(), ((Weapon) weapon).getHands());
                printStream.println();
            }
            printStream.println(BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" + RESET);
    }

    /**
     * print the armors in hero's inventory
     * @param inventory hero's inventory
     */
    public static void printInventoryArmor(Inventory inventory) {
        List<Armor> armorList = inventory.getArmor();
        // Name/cost/required level/damage reduction
        System.out.println("Here is all the armors that are in the hero's inventory: ");
        printStream.format("%4s%20s%10s%20s%20s", "ID", "Name", "cost", "required level", "damage reduction");
        printStream.println();
        printStream.println(PURPLE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + RESET);
        for (int i = 0 ; i < armorList.size(); i ++) {
            Armor armor = armorList.get(i);
            printStream.format("%4s%20s%10s%20s%20s", i, armor.getName(), armor.getPrice(), armor.getLevel(),
                    armor.getReduction());
            printStream.println();
        }
        printStream.println(PURPLE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + RESET);
    }

    /**
     * print the Spells in hero's inventory
     * @param inventory hero's inventory
     */
    public static void printInventorySpell(Inventory inventory) {
        List<Spell> spellList = inventory.getSpell();
        System.out.println("Here is all the spells that are on sale: ");
        printStream.format("%4s%20s%10s%10s%10s%15s", "ID", "Name", "cost", "required level", "damage", "mana cost");
        printStream.println();
        printStream.println(GREEN + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + RESET);
        for (int i = 0 ; i < spellList.size(); i ++) {
            Spell spell = spellList.get(i);
            printStream.format("%4s%20s%10s%10s%10s%15s", i, spell.getName(), spell.getPrice(), spell.getLevel(),
                    spell.getDamage(), spell.getManaCost());
            printStream.println();
        }
        printStream.println(GREEN + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + RESET);
    }

    /**
     * print the Potions in hero's inventory
     * @param inventory hero's inventory
     */
    public static void printInventoryPotion(Inventory inventory) {
        List<Potion> potionList = inventory.getPotion();
        System.out.println("Here are all the weapons that are in the hero's inventory: ");
        printStream.format("%4s%20s%10s%20s%20s%55s", "ID", "Name", "cost", "required level", "attribute increase", "attribute affected");
        printStream.println();
        printStream.println(YELLOW_BOLD_BRIGHT + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" +
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" + RESET);
        for (int i = 0 ; i < potionList.size(); i ++) {
            Potion potion = potionList.get(i);
            printStream.format("%4s%20s%10s%20s%20s%55s", i, potion.getName(), potion.getPrice(), potion.getLevel(),
                    potion.getInc(), potion.getAttr());
            printStream.println();
        }
        printStream.println(YELLOW_BOLD_BRIGHT + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" +
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" + RESET);
    }



}
