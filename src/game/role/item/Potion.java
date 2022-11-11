package game.role.item;

import game.RPGGame.RPGItem;

import java.util.HashMap;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.item
 * @className: Potion
 * @author: Danny
 * @description: Potion instance
 * @date: 2022/11/3 21:47
 * @version: 1.0
 */
public class Potion extends RPGItem {

    /**
     * affectedAttr is an int array with length six in which each attribute corresponds
     * to HP, Power, Mana, agility, Dexterity, defense
     */
    private int[] affectedAttr;

    public Potion(String name, int price, int level, int[] attAff) {
        super(name, price, level, "Potion");
        affectedAttr = attAff;
    }

    public int[] getAffectedAttr() {
        return affectedAttr;
    }

    @Override
    public String toString() {
        return "Type: "+ "Potion, " + "name: " + super.getName() + " Increase amount: " + getInc() + " Affect Attribute: " + getAttr();
    }

    private int getInc() {
        for (int i = 0; i < 6; i++) {
            if(affectedAttr[i] != 0) return affectedAttr[i];
        }
        return 0;
    }

    private String getAttr() {
        for (int i = 1 ; i < affectedAttr.length; i ++) {
            if (affectedAttr[i] != affectedAttr[i - 1]) break;
            if (i == 5) return "All Health/Mana/Strength/Dexterity/Defense/Agility";
        }
        if (affectedAttr[0] == affectedAttr[1] && affectedAttr[1] == affectedAttr[2] && affectedAttr[2] == affectedAttr[3]) {
            return "Health/Mana/Strength/Agility";
        }
        else if (affectedAttr[0] != 0) return "Health";
        else if (affectedAttr[1] != 0) return "Strength";
        else if (affectedAttr[2] != 0) return "Mana";
        else return "Agility";
    }
}
