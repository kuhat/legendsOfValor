package game.role.item;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.item
 * @className: iceSpell
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/3 21:50
 * @version: 1.0
 */
public class iceSpell extends Spell{

    private final String type = "ICE";

    private int damage;

    private int manaCost;

    public iceSpell(String name, int price, int level, int damage, int manaCost) {
        super(name, price, level, "ICE", damage, manaCost);
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
