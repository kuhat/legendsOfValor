package game.role.item;

import game.RPGGame.RPGItem;
import game.role.heroes.Hero;
import game.role.mountable;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.item
 * @className: Armor
 * @author: Danny
 * @description: armor item
 * @date: 2022/11/3 21:44
 * @version: 1.0
 */
public class Armor extends RPGItem implements mountable {

    private int reduction;

    public Armor(String name, int price, int level, int reduction) {
        super(name, price, level, "Armor");
        this.reduction = reduction;
    }

    public int getReduction() {
        return reduction;
    }

    @Override
    public void equip(Hero hero) {
        hero.equipArmor(this);
    }

    @Override
    public String toString() {
        return "Type: " + "Armor" + ", Name: "  + super.getName() + ", Reduction: " + getReduction();
    }

    @Override
    public String getType() {
        return super.getType();
    }
}
