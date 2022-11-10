package game.role.item;

import game.RPGGame.RPGItem;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.item
 * @className: Spell
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/3 21:47
 * @version: 1.0
 */
public abstract class Spell extends RPGItem {

    private String type;

    private int damage;

    private int manaCost;

    public Spell(String name, int price, int level, String type, int damage, int manaCost) {
        super(name, price, level, "Spell");
        this.type = type;
        this.damage = damage;
        this.manaCost = manaCost;
    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    @Override
    public String toString() {
        return "Type: " + "Spell " +  "Name: " + super.getName() + ", damage: " + damage + ", mana cost: " + manaCost;
    }
}
