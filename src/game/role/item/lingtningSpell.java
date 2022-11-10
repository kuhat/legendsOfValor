package game.role.item;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.item
 * @className: lingteningSpell
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/3 21:51
 * @version: 1.0
 */
public class lingtningSpell extends Spell{
    private final String type = "LIGHTNING";

    private int damage;

    private int manaCost;

    public lingtningSpell(String name, int price, int level, int damage, int manaCost) {
        super(name, price, level, "LIGHTNING", damage, manaCost);
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
