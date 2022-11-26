package game.role.item;

import game.RPGGame.RPGItem;
import game.role.mountable;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.item
 * @className: Inventory
 * @author: Danny
 * @description: inventory owned by hero
 * @date: 2022/11/3 21:55
 * @version: 1.0
 */
public class Inventory {

    List<RPGItem> items;

    private List<Spell> spellList;

    private List<Potion> potionList;

    private List<Armor> armorList;

    private List<Weapon> weaponList;

    public Inventory() {
        items = new ArrayList<>();
        potionList = new ArrayList<>();
        armorList = new ArrayList<>();
        weaponList = new ArrayList<>();
        spellList = new ArrayList<>();
    }

    public void removeItem(int idx) {
        items.remove(idx);
    }

    public boolean removeItem(RPGItem item) {
        if (item.getType().equals("Weapon")) {
            return weaponList.remove(item);
        } else if (item.getType().equals("Armor")) {
            return armorList.remove(item);
        } else if (item.getType().equals("Potion")) {
            return potionList.remove(item);
        } else if (item.getType().equals("Spell")) {
            return spellList.remove(item);
        }
        return items.remove(item);
    }

    public List<RPGItem> getItems() {
        return items;
    }

    public void addItem(RPGItem item) {
        items.add(item);
        if (item.getType().equals("Spell")) {
            spellList.add((Spell)item);
        } else if (item.getType().equals("Weapon")) {
            weaponList.add((Weapon) item);
        } else if (item.getType().equals("Armor")) {
            armorList.add((Armor) item);
        } else if (item.getType().equals("Potion")) {
            potionList.add((Potion) item);
        }
    }

    public List<Spell> getSpell() {
//        List<Spell> spellList = new ArrayList<>();
        return spellList;
    }

    public void showSpell() {
        List<Spell> spellList = new ArrayList<>();
        for (RPGItem item : this.getItems()) {
            if (item.getType().equals("Spell")) spellList.add((Spell) item);
        }
        for (int i = 0; i < spellList.size(); i++) {
            System.out.println(" -" + i + ": " + spellList.get(i).toString());
        }
    }

    public List<Potion> getPotion() {
//        List<Potion> potionList = new ArrayList<>();
        return potionList;
    }

    public void showPotion() {
        List<Potion> potionList = new ArrayList<>();
        for (RPGItem item : this.getItems()) {
            if (item.getType().equals("Potion")) potionList.add((Potion) item);
        }
        for (int i = 0; i < potionList.size(); i++) {
            System.out.println(" -" + i + ": " + potionList.get(i).toString());
        }
    }

    public List<Weapon> getWeapon() {
//        List<Weapon> weaponList = new ArrayList<>();
        return weaponList;
    }

    public List<Armor> getArmor() {
//        List<Armor> armorList = new ArrayList<>();
        return armorList;
    }

    /**
     * Shou the info of Weapons and Armors (Mountable)
     */
    public List<mountable> showMountable() {
        List<mountable> mountables = new ArrayList<>();
        for (RPGItem item : this.getItems()) {
            if (item.getType().equals("Weapon")) mountables.add((Weapon) item);
        }
        for (RPGItem item: this.getItems()) {
            if (item.getType().equals("Armor")) mountables.add((Armor) item);
        }
        for (int i = 0; i < mountables.size(); i++) {
            System.out.println(" -" + i + ": " + mountables.get(i).toString());
        }
        return mountables;
    }

}
