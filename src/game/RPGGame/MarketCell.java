package game.RPGGame;

import game.GameRunner;
import game.role.Buyable;
import game.role.heroes.Hero;
import game.role.heroes.Party;
import game.role.item.*;
import game.role.role;
import game.utils.instructions;

import java.util.ArrayList;
import java.util.List;

import static game.GameRunner.*;

/**
 * @projectName: monstersAndHerios
 * @package: game.RPGGame
 * @className: MarketCell
 * @author: Danny
 * @description: the cell where heroes can sell or buy items
 * @date: 2022/11/3 22:03
 * @version: 1.0
 */
public class MarketCell extends Cell {

    List<RPGItem> armorList;

    List<RPGItem> spellList;

    List<RPGItem> potionList;

    List<RPGItem> weaponList;

    private ItemFactory itemFactory;

    List<RPGItem> allItem;

    public MarketCell(String content) {
        super(content);
        generateItem();
    }

    /**
     * Generate Item for this market Cell
     */
    private void generateItem() {
        itemFactory = ItemFactory.getInstance();
        generateWeapon();
        generatePotion();
        generateSpell();
        generateArmor();
        allItem = new ArrayList<>();
        allItem.addAll(armorList);
        allItem.addAll(potionList);
        allItem.addAll(weaponList);
        allItem.addAll(spellList);
    }

    /**
     * Generate Armor randomly
     */
    private void generateArmor() {
        int num = (int) (Math.random() * 5);
        armorList = itemFactory.createArmor(num);
    }

    /**
     * Generate spells randomly
     */
    private void generateSpell() {
        int num = (int) (Math.random() * 5);
        spellList = itemFactory.createSpell(num);
    }

    /**
     * Generate random potion, the number of which is in the range 0 to 6
     */
    private void generatePotion() {
        int num = (int) (Math.random() * 6);
        potionList = itemFactory.createPotion(num);
    }

    /**
     * Generate random weapon, the number of which is in the range 0 to 6
     */
    private void generateWeapon() {
        int num = (int) (Math.random() * 6);
        weaponList = itemFactory.createWeapon(num);
    }

    /**
     * When the party enters this market cell, the system asks player if he wants to go into the market to buy
     *
     * @param party party to enter this cell
     */
    @Override
    public void enter(Party party) {
        instructions.printMarketMsg(party);
        super.setContent("T");
        act(party);
    }

    /**
     * The actions when party enters the market
     * @param party party to perform actions
     */

    private void act(Party party){
        String input = "";
        boolean strResult = false;
        while (!strResult) {
            input = GameRunner.getInput("Enter m/M to buy goods for your heroes, enter q/Q to do nothing:");
            strResult = (input != null && input.matches("[QqMm]"));
            if (!strResult) System.out.println("Please enter valid choice!");
        }
        if (input.equalsIgnoreCase("m")) {
            System.out.println("You chose to enter market to make transactions for your heroes! \n" +
                                "-------------------------------------------------------------------\n" +
                                "Firstly, you have chance to sell items for your heroes to earn money.");
            sellItem(party);
            System.out.println("--------------------------------------------------------------------\n" +
                               "Then, you can buy items for each hero, This is the items this market currently have: ");
            System.out.println(toString());
            System.out.println("Now, you can buy things from the item set. ");
            buyItem(party);
            System.out.println("Thanks for entering Market! Good luck to the rest of your journey!");
        } else {
            System.out.println("You chose to do nothing, good luck!");
        }
    }

    /**
     * Actions to do when user choose to buy items
     * heroIdx: number of item this hero has
     * @param party 
     */
    int heroIdx = 1;
    private void sellItem(Party party) {
        List<role> heroes = party.getParty();
        for (int i = 0; i < heroes.size(); i++) {
            heroSellItem(heroes.get(i));
        }
    }

    public void buyItem(Party party) {
        List<role> heroes = party.getParty();
        for (int i = 0; i < heroes.size(); i++) {
            heroBuyItem(heroes.get(i));
        }
    }

    private void heroBuyItem(role hero) {
        String input = "";
        boolean strRes = false;
        List<RPGItem> soldItem = new ArrayList<>();
        while (true) {
            System.out.println("Hero: " + GREEN + RESET + hero.getName() + " has gold: " + RED + ((Hero) hero).getGold() +
                    RESET +  ", current level: " + BLUE +hero.getLevel() + RESET);
            while (!strRes) {
                input = getInput("Please choose:\n - 1~"+ (marketIdx - 1) + " to buy item. \n - 0 to buy nothing \nfor this hero.");
                strRes = (input != null && isValid(input, marketIdx - 1));
                if (!strRes) System.out.println("Invalid input!");
            }
            strRes = false;
            int buyIdx = Integer.valueOf(input);
            if (buyIdx != 0) {
                RPGItem item = allItem.get(buyIdx - 1);
                if (soldItem.contains(item)) {  // if the sold item list contains the item, it has already been sold
                    System.out.println("This item has been sold, please choose another one!");
                } else {
                    if (item.sellTo((Buyable) hero)) {
                        System.out.println("Transaction success! ");
                        soldItem.add(item);
                        dealItem(item, true);
                    } else {
                        System.out.println("Transaction fail! Hero do not have enough gold or the level is too lower ro buy this item!");
                    }
                }
            } else {
                System.out.println("Thanks for your shopping! ");
                System.out.println("currently, Hero " + GREEN + hero.getName() + RESET + " has gold: " + RED + ((Hero) hero).getGold() +
                        RESET +  ", current level: " + BLUE +hero.getLevel() + "\n" + RESET +
                        "Current inventory: ");
                for (RPGItem item: ((Hero) hero).getInventory().getItems()) {
                    System.out.println("Type: " + item.getType() + ", Name: " + item.getName() + ", Sale price: " + item.getPrice());
                }
                break;
            }
        }
        allItem.removeAll(soldItem);
    }

    private void heroSellItem(role hero) {
        String input = "";
        boolean strRes = false;
        List<RPGItem> heroSoldItems = new ArrayList<>();
        System.out.println("Hero: " + BLUE + RESET +  hero.getName() + " has gold: " + RED + ((Hero) hero).getGold() +
                RESET +  ", current level: " + BLUE +hero.getLevel() + RESET);
        System.out.println("current inventory: ");

        // If the length of this hero's inventory is not zero, ask the player to sell them or not.
        if (((Hero) hero).getInventory().getItems().size() != 0) {
            while (((Hero) hero).getInventory().getItems().size() > 0) {
                for (RPGItem item: ((Hero) hero).getInventory().getItems()) {
                    System.out.println(heroIdx + ": " + "Type: " + item.getType() + ", Name: " + item.getName() + "," +
                            " Sale price: " + item.getPrice());
                    heroIdx++;
                }
                while (!strRes) {
                    input = getInput("Do you want to sell the items of hero " + hero.getName() + "? \n" +
                            "Select 1~" + heroIdx + " to sell item and obtain money.\n" +
                            "Select 0 to continue.");
                    strRes = (input != null && isValid(input, heroIdx));
                    if (!strRes) System.out.println("Invalid input!");
                }
                strRes = false;
                // If the user did not choose to sell item, jump, otherwise sell the input one
                if (Integer.valueOf(input) != 0){
                    RPGItem heroSellingItem = ((Hero) hero).getInventory().getItems().get(Integer.valueOf(input) - 1);
                    if (!heroSellItem(hero, heroSellingItem, heroSoldItems)){
                        System.out.println("Transaction failed, this item has been sold to the market, choose another one");
                    } else {
                        allItem.add(heroSellingItem);  // add hero's selling item to this market's item list
                        System.out.println("Transaction success! Hero " + hero.getName() + " now have money: " + ((Hero) hero).getMoney());
                    }
                } else {
                    System.out.println("You choose not to sell anything for this hero!");
                    break;
                }
            }
            System.out.println("Selling items for hero " + hero.getName() + " finish.");
            heroIdx = 1;
        } else {
            System.out.println("    This hero has no items in the inventory.");
        }
    }

    /**
     * Make deals for item, remove item from the individual item lists. When sell is true, this market sells item,
     * otherwise, it receives items from hero
     * @param itemToDeal item to be removed
     * @param sell true if this market cell sells, false if hero sells
     */
    private void dealItem(RPGItem itemToDeal, boolean sell) {
        if (itemToDeal.getType().equals("Potion")) {
            if (sell) potionList.remove(itemToDeal);
            else potionList.add(itemToDeal);
        }
        else if (itemToDeal.getType().equals("Armor")) {
            if (sell) armorList.remove(itemToDeal);
            else armorList.add(itemToDeal);
        }
        else if (itemToDeal.getType().equals("Weapon")) {
            if (sell) weaponList.remove(itemToDeal);
            else weaponList.add(itemToDeal);
        }
        else  {
            if (sell) spellList.remove(itemToDeal);
            else spellList.add(itemToDeal);
        }
    }

    /**
     * Hero sell item to this marketCell
     * @param hero hero to sell things
     * @param soldItem the item hero want to sell
     * @param heroSoldItems the item list hero already sold
     * @return true if the inventory of hero successfully removes the item
     */
    private boolean heroSellItem(role hero, RPGItem soldItem, List<RPGItem> heroSoldItems) {
        if (!((Hero) hero).getInventory().removeItem(soldItem)) return false;
        dealItem(soldItem, false);
        heroSoldItems.add(soldItem);
        ((Hero) hero).setMoney(((Hero) hero).getMoney() + soldItem.getPrice() / 2);
        return true;
    }

    /**
     * Check if the input of the user is value and within heroIdx range
     * @param input user input
     * @return true if the input is an Integer and within hero idx range
     */
    private boolean isValid(String input, int upperValue) {
        for (char s : input.toCharArray()) if (!Character.isDigit(s)) return false;
        int value = Integer.valueOf(input);
        if (value < 0 && value > upperValue) return false;
        return true;
    }

    /**
     * Display the items for sale in this Market cell
     *
     * @return
     */
    int marketIdx = 1;
    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        if (armorList.size() == 0) res.append("This market has no armor for sale!\n");
        else {
            res.append("Items in this Market Cell:\n" +
                    "-------------------------Armors-------------------\n" +
                    "Name    cost    required level    damage reduction\n");
            res.append(printArmor());
            res.append("--------------------------------------------------\n");
        }
        if (potionList.size() == 0) res.append("This market has no Potion for sale!\n");
        else {
            res.append("--------------------------------------------Potion-------------------------------------\n" +
                       "Name                cost    required level    attribute increase    attribute affected \n");
            res.append(printPotion());
            res.append("---------------------------------------------------------------------------------------\n");
        }
        if (weaponList.size() == 0) res.append("This market has no Weapon for sale!\n");
        else {
            res.append("----------------------Weapon---------------------\n" +
                       "Name    cost    level    damage    required hands\n");
            res.append(printWeapon());
            res.append("-------------------------------------------------\n");
        }
        if (spellList.size() == 0) res.append("This market has no Spell for sale! \n");
        else {
            res.append("---------------------------Spell-----------------------------\n" +
                       "Type        Name        required level    damage    Mana cost\n");
            res.append(printSpell());
            res.append("-------------------------------------------------------------\n");
        }
        return res.toString();
    }

    private String printSpell() {
        StringBuilder sb = new StringBuilder();
        for (RPGItem spell : spellList) {
            sb.append(marketIdx + ": " + ((Spell)spell).getType() + "    " + spell.getName() + "    " + spell.getLevel() + "        " +
                    "    " + ((Spell)spell).getDamage() + "        " + ((Spell)spell).getManaCost() + "\n");
            marketIdx++;
        }

        return sb.toString();
    }

    private String printWeapon() {
        StringBuilder sb = new StringBuilder();
        for (RPGItem weapon: weaponList) {
            sb.append(marketIdx + ": " + weapon.getName() + "     " + weapon.getPrice() + "      " + weapon.getLevel() + "          " +
                    ((Weapon)weapon).getDamage() + "         " + ((Weapon)weapon).getHands() + "\n");
            marketIdx++;
        }

        return sb.toString();
    }

    private String printArmor() {
        StringBuilder sb = new StringBuilder();
        for (RPGItem armor : armorList) {
            sb.append(marketIdx + ": " + armor.getName() + "    " + armor.getPrice() + "    " +
                    armor.getLevel() + "                   " + ((Armor) armor).getReduction() + "\n");
            marketIdx++;
        }
        return sb.toString();
    }

    private String printPotion() {
        StringBuilder sb = new StringBuilder();
        for (RPGItem potion: potionList) {
            String name = potion.getName();
            sb.append(marketIdx + ": ");
            if (name.equals("Healing_Potion")) sb.append(potion.getName() + "    " + potion.getPrice() +
                    "            " + potion.getLevel() + "                 " + ((Potion) potion).getAffectedAttr()[0] +
                    "                    " +"Health" +"\n");
            else if (name.equals("Strength_Potion")) sb.append(potion.getName() + "    " + potion.getPrice() +
                    "            " + potion.getLevel() + "                 " + ((Potion) potion).getAffectedAttr()[1] +
                    "                    " +  "Strength"   +"\n");
            else if (name.equals("Magic_Potion")) sb.append(potion.getName() + "    " + potion.getPrice() +
                    "             " + potion.getLevel() + "                 " + ((Potion) potion).getAffectedAttr()[2] +
                    "                    " + "Mana"  +"\n");
            else if (name.equals("Luck_Elixir")) sb.append(potion.getName() + "    " + potion.getPrice() +
                    "            " + potion.getLevel() + "                     " + ((Potion) potion).getAffectedAttr()[3] +
                    "                    " + "Agility" + "\n");
            else if (name.equals("Mermaid_Tears")) sb.append(potion.getName() + "    " + potion.getPrice() +
                    "            " + potion.getLevel() + "                 " + ((Potion) potion).getAffectedAttr()[3] +
                    "                " +  "Health/Mana/Strength/Agility" +"\n");
            else sb.append(sb.append(potion.getName() + "    " + potion.getPrice() +
                        "            " + potion.getLevel() + "             " + ((Potion) potion).getAffectedAttr()[3] +
                        "                " +  "Health/Mana/Strength/Agility/Dexterity/Defense" +"\n"));
            marketIdx++;
        }
        return sb.toString();
    }
}
