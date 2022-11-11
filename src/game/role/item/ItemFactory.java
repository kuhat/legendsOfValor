package game.role.item;

import game.Product;
import game.RPGGame.RPGItem;
import game.role.RoleFactory;
import game.utils.fileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.item
 * @className: itemFactory
 * @author: Danny
 * @description: Item factory to create different items
 * @date: 2022/11/7 10:50
 * @version: 1.0
 */
public class ItemFactory extends RoleFactory {

    private static ItemFactory itemFactory;

    private List<List<String>> spellInfo;

    private List<List<String>> weaponInfo;

    private List<List<String>> potionInfo;

    private List<List<String>> armorInfo;

    private final String armoryPath = "Armory.txt";

    private final String potionPath = "Potions.txt";

    private final String weaponryPath = "Weaponry.txt";

    private SpellFactory spellFactory;

    private ItemFactory() {
        spellFactory = SpellFactory.getInstance();
        readWeapon();
        readArmory();
        readPotion();
    }

    /**
     * Read the config file of Potion
     */
    private void readPotion() {
//        Name/cost/required level/attribute increase/attribute affected
        List<String> allPotionInfo = fileReader.readFile(potionPath);
        potionInfo = new ArrayList<>();
        for (int i = 1; i < allPotionInfo.size(); i++) {
            if (!allPotionInfo.get(i).equals("")) potionInfo.add(Arrays.asList(allPotionInfo.get(i).split(" ")));
        }
    }

    /**
     * Read the config file of weapon
     */
    private void readWeapon() {
        List<String> allWeaponInfo = fileReader.readFile(weaponryPath);
        weaponInfo = new ArrayList<>();
        for (int i = 1; i < allWeaponInfo.size(); i++) {
            if (!allWeaponInfo.get(i).equals("")) weaponInfo.add(Arrays.asList(allWeaponInfo.get(i).split(" ")));
        }
    }

    /**
     * Read the config Armory file
     */
    private void readArmory() {
//        Name/cost/required level/damage reduction
        List<String> allArmorInfo = fileReader.readFile(armoryPath);
        armorInfo = new ArrayList<>();
        for (int i = 1; i < allArmorInfo.size(); i++) {
            if (!allArmorInfo.get(i).equals("")) armorInfo.add(Arrays.asList(allArmorInfo.get(i).split(" ")));
        }
    }

    /**
     * create num of weapon randomly from weapon file
     *
     * @param num
     * @return
     */
    public List<RPGItem> createWeapon(int num) {
        //        Name/cost/level/damage/required hands
        Random random = new Random();
        List<RPGItem> weaponList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            int nextIdx = random.nextInt(num);
            List<String> info = weaponInfo.get(nextIdx);
//            System.out.println( i+ " num: " + num + " nextIDX: " + nextIdx +  "  " + Arrays.toString(new List[]{Arrays.asList(info)}));
            List<String> prop = new ArrayList<>();
            for (String s : info) if (!s.equals("")) prop.add(s);
            weaponList.add(new Weapon(prop.get(0), Integer.valueOf(prop.get(1).trim()), Integer.valueOf(prop.get(2)),
                    Integer.valueOf(prop.get(3)), Integer.valueOf(prop.get(4))));
        }
        return weaponList;
    }

    /**
     * Create random Potion
     * @param num number of potion to add
     * @return potionList of size num. in which the affected attributes are HP, Power, Mana, agility, Dexterity, defense
     */
    public List<RPGItem> createPotion(int num) {
        // Name/cost/required level/attribute increase/attribute affected
        // props: String name, int Hp, int power, int defense, int level, int dodge, int agility, int dexterity, int gold, int experience
        Random random = new Random();
        List<RPGItem> potionList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            int nextIdx = random.nextInt(num);
            List<String> info = potionInfo.get(nextIdx);
            List<String> prop = new ArrayList<>();
            for (String s : info) if (!s.equals("")) prop.add(s);
            int[] attAff = new int[6];
            if (prop.get(4).equals("Health")) attAff[0] = Integer.valueOf(prop.get(3));
            else if (prop.get(4).equals("Strength")) attAff[1] = Integer.valueOf(prop.get(3));
            else if (prop.get(4).equals("Mana")) attAff[2] = Integer.valueOf(prop.get(3));
            else if (prop.get(4).equals("Agility")) attAff[3] = Integer.valueOf(prop.get(3));
            else if (prop.get(4).length() == 28) attAff[0] = attAff[1] = attAff[2] = attAff[3] = Integer.valueOf(prop.get(3));
            else Arrays.fill(attAff, Integer.valueOf(prop.get(3)));
            potionList.add(new Potion(prop.get(0), Integer.valueOf(prop.get(1).trim()), Integer.valueOf(prop.get(2)), attAff));
        }
        return potionList;
    }

    /**
     * Create Armor
     * @return armorList of random armor obj generated from the config file
     */
    public List<RPGItem> createArmor(int num) {
        // Name/cost/required level/damage reduction
        Random random = new Random();
        List<RPGItem> armorList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            int nextIdx = random.nextInt(num);
            List<String> info = armorInfo.get(nextIdx);
            List<String> prop = new ArrayList<>();
            for (String s: info) if (!s.equals("")) prop.add(s);
            armorList.add(new Armor(prop.get(0), Integer.valueOf(prop.get(1).trim()), Integer.valueOf(prop.get(2)),
                    Integer.valueOf(prop.get(3))));
        }
        return armorList;
    }

    /**
     * Create Spell randomly using the three types: lightning, fire, ice
     * @param num number of spells to return
     * @return Spell list
     */
    public List<RPGItem> createSpell(int num) {
        List<RPGItem> spells = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            spells.add((RPGItem) spellFactory.creatProduct());
        }
        return spells;
    }


    @Override
    public Product creatProduct() {
        return null;
    }

    /**
     * getInstance of the ItemFactory
     */
    public static ItemFactory getInstance() {
        if (itemFactory == null) return new ItemFactory();
        else return itemFactory;
    }
}
