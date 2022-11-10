package game.role.item;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.item
 * @className: fireSpell
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/3 21:51
 * @version: 1.0
 */
public class fireSpell extends Spell{
    private final String type = "FIRE";

    private int damage;

    private int manaCost;

    public fireSpell(String name, int price, int level, int damage, int manaCost) {
        super(name, price, level, "FIRE", damage, manaCost);
        this.damage = damage;
        this.manaCost = manaCost;
    }

    public String getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }
}
