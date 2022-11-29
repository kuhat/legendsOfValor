package game.role.monsters;

import game.role.Prop;
import game.role.role;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.monsters
 * @className: Spirits
 * @author: Danny
 * @description: Spirit monster type
 * @date: 2022/11/3 21:24
 * @version: 1.0
 */
public class Spirit extends Monster {

    public Spirit(String name, int Hp, int power, int defense, int level, int dodge) {
        super(name, Hp, power, defense, level, dodge, "Spirit");
    }
}
