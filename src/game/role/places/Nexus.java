package game.role.places;

import game.GameRunner;
import game.RPGGame.InaccessibleCell;
import game.RPGGame.RPGItem;
import game.RPGGame.normalCell;
import game.role.heroes.Hero;
import game.role.item.ItemFactory;
import game.role.role;
import game.utils.instructions;

import java.util.List;

import static game.GameRunner.getInput;

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
                input = getInput("You have chance to buy or sell items on Nexus for Hero: "+ role.getName()+ "\n" +
                        "1: Buy \n" +
                        "2: Sell \n" +
                        "0: Quit");
                strResult = (input != null && input.matches("[0-2]"));
                if (!strResult) System.out.println("Please enter valid choice!");
            }
            if (input.equals("1")) {
                System.out.println("You chose to buy items");
                buyItem(role);
            } else if (input.equals("2")) {
                System.out.println("You chose to sell items");
                sellItem(role);
            } else {
                System.out.println("You chose to quit, good luck!");
                break;
            }
        }
    }

    private void buyItem(role role) {
        String input = "";
        boolean strResult = false;
        while (!strResult) {
            input = getInput("Please choose the kind of item to buy: \n" +
                    " 1: Weapon \n" +
                    " 2: Armor \n" +
                    " 3: Potion \n" +
                    " 4: Spell");
            strResult = (input != null && input.matches("[1-4]"));
            if (!strResult) System.out.println("Please enter valid choice!");
        }
        if (input.equals("1")) {
            instructions.printWeapon();
            buyWeapon(role);
        }
        else if (input.equals("2")) {
            instructions.printArmor();
            buyArmor();
        }
        else if (input.equals("3")) {
            instructions.printPotion();
            buyPotion();
        }
        else {
            instructions.printSpell();
            buySpell();
        }
    }

    private void buyWeapon(role role){
        List<RPGItem> weaponList = ItemFactory.getInstance().createWeapon();
        String input = "";
        boolean strRes = false;
        while (!strRes) {
            input = getInput("Please choose which weapon to buy:");
            strRes = (input != null && checkDigit(input) && (Integer.parseInt(input) >= 0
                    || Integer.parseInt(input) < weaponList.size()));
            if (!strRes) System.out.println("Please enter digit between 0 and " + (weaponList.size() - 1));
        }
        RPGItem item = weaponList.get(Integer.parseInt(input));
        if (((Hero)role).getGold() < item.getPrice()) {
            System.out.println("Hero do not have enough money to buy this weapon!");
        } else {
            ((Hero) role).getInventory().addItem(weaponList.get(Integer.parseInt(input)));
            ((Hero) role).setMoney(((Hero)role).getMoney() - item.getPrice());
            System.out.println("Transaction success! Current money: " + ((Hero) role).getGold());
        }

    }

    private void buyArmor(){

    }

    private void buyPotion() {

    }

    private void buySpell() {

    }

    private void sellItem(role role) {

    }

    private boolean checkDigit(String input){
        for (char c: input.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

}
