package game.role.places;

import game.RPGGame.RPGItem;
import game.RPGGame.normalCell;
import game.role.heroes.Hero;
import game.role.item.*;
import game.role.role;
import game.utils.instructions;

import java.util.List;

import static game.GameRunner.*;
import static game.utils.ConsoleColorsCodes.YELLOW_BOLD_BRIGHT;

/**
 * @projectName: monstersAndHeroes
 * @package: game.role.places
 * @className: Nexus
 * @author: Danny
 * @description: The nexus where heroes and monster spawn
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
                input = getInput("You have chance to buy or sell items on Nexus for Hero: " + role.getName() + "\n" +
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
        } else if (input.equals("2")) {
            instructions.printArmor();
            buyArmor(role);
        } else if (input.equals("3")) {
            instructions.printPotion();
            buyPotion(role);
        } else {
            instructions.printSpell();
            buySpell(role);
        }
        System.out.println("Hero's current money is " + ((Hero) role).getGold());
    }

    private void buyWeapon(role role) {
        List<RPGItem> weaponList = ItemFactory.getInstance().createWeapon();
        String input = "";
        boolean strRes = false;
        while (!strRes) {
            input = getInput("Please choose which weapon to buy:");
            strRes = (input != null && !input.equals("") && checkDigit(input) && (Integer.parseInt(input) >= 0
                    || Integer.parseInt(input) < weaponList.size()));
            if (!strRes) System.out.println("Please enter digit between 0 and " + (weaponList.size() - 1));
        }
        RPGItem item = weaponList.get(Integer.parseInt(input));
        if (((Hero) role).getGold() < item.getPrice()) {
            System.out.println("Hero do not have enough money to buy this weapon!");
        } else if ((role).getLevel() < item.getLevel()) {
            System.out.println("Hero's level is not enough to buy this weapon!");
        } else {
            ((Hero) role).getInventory().addItem(weaponList.get(Integer.parseInt(input)));
            ((Hero) role).setMoney(((Hero) role).getMoney() - item.getPrice());
            System.out.println("Transaction success! Current money: " + ((Hero) role).getGold());
        }
    }

    private void buyArmor(role role) {
        List<RPGItem> armorList = ItemFactory.getInstance().createArmor();
        String input = "";
        boolean strRes = false;
        while (!strRes) {
            input = getInput("Please choose which armor to buy:");
            strRes = (input != null && !input.equals("") && checkDigit(input) && (Integer.parseInt(input) >= 0
                    || Integer.parseInt(input) < armorList.size()));
            if (!strRes) System.out.println("Please enter digit between 0 and " + (armorList.size() - 1));
        }
        RPGItem item = armorList.get(Integer.parseInt(input));
        if (((Hero) role).getGold() < item.getPrice()) {
            System.out.println("Hero do not have enough money to buy this armor!");
        } else if ((role).getLevel() < item.getLevel()) {
            System.out.println("Hero's level is not enough to buy this armor!");
        } else {
            ((Hero) role).getInventory().addItem(armorList.get(Integer.parseInt(input)));
            ((Hero) role).setMoney(((Hero) role).getMoney() - item.getPrice());
            System.out.println("Transaction success! Current money: " + ((Hero) role).getGold());
        }
    }

    private void buyPotion(role role) {
        List<RPGItem> optionList = ItemFactory.getInstance().createPotion();
        String input = "";
        boolean strRes = false;
        while (!strRes) {
            input = getInput("Please choose which potion to buy:");
            strRes = (input != null && checkDigit(input) && (Integer.parseInt(input) >= 0
                    || Integer.parseInt(input) < optionList.size()));
            if (!strRes) System.out.println("Please enter digit between 0 and " + (optionList.size() - 1));
        }
        RPGItem item = optionList.get(Integer.parseInt(input));
        if (((Hero) role).getGold() < item.getPrice()) {
            System.out.println("Hero do not have enough money to buy this potion!");
        } else if ((role).getLevel() < item.getLevel()) {
            System.out.println("Hero's level is not enough to buy this potion!");
        } else {
            ((Hero) role).getInventory().addItem(optionList.get(Integer.parseInt(input)));
            ((Hero) role).setMoney(((Hero) role).getMoney() - item.getPrice());
            System.out.println("Transaction success! Current money: " + ((Hero) role).getGold());
        }
    }

    private void buySpell(role role) {
        List<RPGItem> spellList = ItemFactory.getInstance().createSpell();
        String input = "";
        boolean strRes = false;
        while (!strRes) {
            input = getInput("Please choose which spell to buy:");
            strRes = (input != null && checkDigit(input) && (Integer.parseInt(input) >= 0
                    || Integer.parseInt(input) < spellList.size()));
            if (!strRes) System.out.println("Please enter digit between 0 and " + (spellList.size() - 1));
        }
        RPGItem item = spellList.get(Integer.parseInt(input));
        if (((Hero) role).getGold() < item.getPrice()) {
            System.out.println("Hero do not have enough money to buy this spell!");
        } else if ((role).getLevel() < item.getLevel()) {
            System.out.println("Hero's level is not enough to buy this spell!");
        } else {
            ((Hero) role).getInventory().addItem(spellList.get(Integer.parseInt(input)));
            ((Hero) role).setMoney(((Hero) role).getMoney() - item.getPrice());
            System.out.println("Transaction success! Current money: " + ((Hero) role).getGold());
        }
    }

    private void sellItem(role role) {
        Inventory inventory = ((Hero) role).getInventory();
        if (inventory.getItems().size() == 0) {
            System.out.println("Hero's inventory is empty! No item can be sold.");
        } else {
            String input = "";
            boolean strResult = false;
            while (!strResult) {
                input = getInput("Please choose the kind of item to sell: \n" +
                        " 1: Weapon \n" +
                        " 2: Armor \n" +
                        " 3: Potion \n" +
                        " 4: Spell");
                strResult = (input != null && input.matches("[1-4]"));
                if (!strResult) System.out.println("Please enter valid choice!");
            }
            if (input.equals("1")) {
                sellWeapon(role, inventory);
            } else if (input.equals("2")) {
                sellArmor(role, inventory);
            } else if (input.equals("3")) {
                sellPotion(role, inventory);
            } else {
                sellSpell(role, inventory);
            }
            System.out.println("Hero's current money is " + ((Hero) role).getGold());
        }
    }

    private void sellWeapon(role role, Inventory inventory) {
        List<Weapon> weaponList = inventory.getWeapon();
        if (weaponList.size() == 0) {
            System.out.println("Hero " + RED + role.getName() + RESET + " does not have any weapons." );
        } else {
            instructions.printInventoryWeapon(inventory);
            String input = "";
            boolean strRes = false;
            while (!strRes) {
                input = getInput("Please choose which weapon to sell:");
                strRes = (input != null && checkDigit(input) && (Integer.parseInt(input) >= 0
                        || Integer.parseInt(input) < weaponList.size()));
                if (!strRes) System.out.println("Please enter digit between 0 and " + (weaponList.size() - 1));
            }
            RPGItem item = weaponList.get(Integer.parseInt(input));
            inventory.removeItem(item);
            ((Hero) role).setGold(((Hero)role).getGold() + item.getPrice() / 2);
            System.out.println("Transaction success! hero gains gold " + GREEN + item.getPrice() / 2 + RESET +  ", Now the hero has: " +
                    YELLOW_BOLD_BRIGHT + ((Hero) role).getGold() + RESET);
        }
    }

    private void sellArmor(role role, Inventory inventory) {
        List<Armor> armorList = inventory.getArmor();
        if (armorList.size() == 0) {
            System.out.println("Hero " + RED + role.getName() + RESET + " does not have any armors." );
        } else {
            instructions.printInventoryArmor(inventory);
            String input = "";
            boolean strRes = false;
            while (!strRes) {
                input = getInput("Please choose which armor to sell:");
                strRes = (input != null && checkDigit(input) && (Integer.parseInt(input) >= 0
                        || Integer.parseInt(input) < armorList.size()));
                if (!strRes) System.out.println("Please enter digit between 0 and " + (armorList.size() - 1));
            }
            RPGItem item = armorList.get(Integer.parseInt(input));
            inventory.removeItem(item);
            ((Hero) role).setGold(((Hero)role).getGold() + item.getPrice() / 2);
            System.out.println("Transaction success! hero gains gold " + GREEN + item.getPrice() / 2 + RESET +  ", Now the hero has: " +
                    YELLOW_BOLD_BRIGHT + ((Hero) role).getGold() + RESET);
        }
    }

    private void sellPotion(role role, Inventory inventory) {
        List<Potion> potionList = inventory.getPotion();
        if (potionList.size() == 0) {
            System.out.println("Hero " + RED + role.getName() + RESET + " does not have any potions." );
        } else {
            instructions.printInventoryPotion(inventory);
            String input = "";
            boolean strRes = false;
            while (!strRes) {
                input = getInput("Please choose which potion to sell:");
                strRes = (input != null && checkDigit(input) && (Integer.parseInt(input) >= 0
                        || Integer.parseInt(input) < potionList.size()));
                if (!strRes) System.out.println("Please enter digit between 0 and " + (potionList.size() - 1));
            }
            RPGItem item = potionList.get(Integer.parseInt(input));
            inventory.removeItem(item);
            ((Hero) role).setGold(((Hero)role).getGold() + item.getPrice() / 2);
            System.out.println("Transaction success! hero gains gold " + GREEN + item.getPrice() / 2 + RESET +  ", Now the hero has: " +
                    YELLOW_BOLD_BRIGHT + ((Hero) role).getGold() + RESET);
        }
    }

    private void sellSpell(role role, Inventory inventory) {
        List<Spell> spellList = inventory.getSpell();
        if (spellList.size() == 0) {
            System.out.println("Hero " + RED + role.getName() + RESET + " does not have any spells." );
        } else {
            instructions.printInventorySpell(inventory);
            String input = "";
            boolean strRes = false;
            while (!strRes) {
                input = getInput("Please choose which spell to sell:");
                strRes = (input != null && checkDigit(input) && (Integer.parseInt(input) >= 0
                        || Integer.parseInt(input) < spellList.size()));
                if (!strRes) System.out.println("Please enter digit between 0 and " + (spellList.size() - 1));
            }
            RPGItem item = spellList.get(Integer.parseInt(input));
            inventory.removeItem(item);
            ((Hero) role).setGold(((Hero)role).getGold() + item.getPrice() / 2);
            System.out.println("Transaction success! hero gains gold " + GREEN + item.getPrice() / 2 + RESET +  ", Now the hero has: " +
                    YELLOW_BOLD_BRIGHT + ((Hero) role).getGold() + RESET);
        }
    }

    private boolean checkDigit(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

}
