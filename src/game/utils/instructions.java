package game.utils;

import game.Product;
import game.role.heroes.*;

import java.io.PrintStream;

import static game.GameRunner.BLUE;
import static game.GameRunner.GREEN;
import static game.GameRunner.RED;
import static game.GameRunner.RESET;

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

    /**
     * First notification shown on the welcome stage
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
     * @param kind kind of the hero, 1 represent Warrior, 2 represent Sorcerer, 3 represent Paladin
     * @param printStream printStream to print content
     * @param party Party to add the heroes
     */
    public static void printHeroChoiceOutcome(int kind, PrintStream printStream, Party party, int lane) {
        if (kind == 1) {
            printStream.println("You chose Warrior! ");
            Warrior warrior = (Warrior) party.addWarrior();
            printStream.println("Warrior: " + BLUE + warrior.getName() + RESET +  " entered lane" +  lane + "!");
        } else if (kind == 2) {
            printStream.println("You chose Sorcerer! ");
            Sorceror sorceror = (Sorceror) party.addSorcerer();
            printStream.println("Sorcerer: " + GREEN + sorceror.getName() + RESET + " entered lane" +  lane + "!");
        } else if (kind == 3) {
            printStream.println("You chose Paladin! ");
            Paladin paladin = (Paladin) party.addPaladin();
            printStream.println("Paladin: " + RED + paladin.getName() + RESET + " entered lane" +  lane + "!");
        }
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
}
