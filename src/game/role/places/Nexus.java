package game.role.places;

import game.GameRunner;
import game.RPGGame.normalCell;
import game.role.role;
import game.utils.instructions;

/**
 * @projectName: monstersAndHeroes
 * @package: game.role.places
 * @className: Nexus
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/20 16:49
 * @version: 1.0
 */
public class Nexus extends normalCell {
    public Nexus(String content) {
        super(content);
    }

    public Nexus(String content, String type) {
        super(content, type);
    }

    @Override
    public void enter(role role) {
        if (role.getType().equals("Hero")) {
            instructions.printMarketMsg(role);
            super.setContent(role.getCharacter() + "     ");
            instructions.printMarketMsg(role);
            act(role);
        } else {
            // if Monster enters a Nexus, then Heroes lose
            super.setContent("     " + role.getCharacter());
        }
    }

    private void act(role role) {
        while (true) {
            String input = "";
            boolean strResult = false;
            while (!strResult) {
                input = GameRunner.getInput("You have chance to buy or sell items on Nexus for Hero: "+ role.getName()+ "\n" +
                        "1: Buy \n" +
                        "2: Sell \n" +
                        "0: Quit");
                strResult = (input != null && input.matches("[0-2]"));
                if (!strResult) System.out.println("Please enter valid choice!");
            }
            if (input.equals("1")) {
                System.out.println("You chose to buy items");
                buyItem();
            } else if (input.equals("2")) {
                System.out.println("You chose to sell items");
                sellItem();
            } else {
                System.out.println("You chose to quit, good luck!");
                break;
            }
        }
    }

    private void buyItem() {
        String input = "";
        boolean strResult = false;
        while (!strResult) {
            input = GameRunner.getInput("Please choose the kind of item to buy: \n" +
                    " 1: Weapon \n" +
                    " 2: Armor \n" +
                    " 3: Potion \n" +
                    " 4: Spell");
            strResult = (input != null && input.matches("[1-4]"));
            if (!strResult) System.out.println("Please enter valid choice!");
        }
        if (input.equals("1")) instructions.printWeapon();
        else if (input.equals("2")) instructions.printArmor();
        else if (input.equals("3")) instructions.printPotion();
        else instructions.printSpell();
        // Player enter choice
    }

    private void sellItem() {

    }

}
