package game.role.item;

import game.RPGGame.RPGItem;
import game.role.heroes.Hero;
import game.role.mountable;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.item
 * @className: Shield
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/3 21:45
 * @version: 1.0
 */
public class Weapon extends RPGItem implements mountable {

    private int damage;

    private int hands;

    public Weapon(String name, int price, int level, int damage, int hands) {
        super(name, price, level, "Weapon");
        this.damage = damage;
        this.hands = hands;
    }

    public int getDamage() {
        return damage;
    }

    public int getHands() {
        return hands;
    }

    @Override
    public String toString() {
        return "Type: " + "Weapon, " + "Name: " + getName() + ", Damage: " + getDamage() + ", required hands: " + hands;
    }

    @Override
    public void equip(Hero hero) {
        hero.equipWeapon(this);
    }

    @Override
    public String getType() {
        return super.getType();
    }
}
