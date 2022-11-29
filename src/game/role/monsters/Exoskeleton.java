package game.role.monsters;

import game.role.Prop;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.monsters
 * @className: Exoskeleton
 * @author: Danny
 * @description: Exoskeleton monster type
 * @date: 2022/11/3 21:24
 * @version: 1.0
 */
public class Exoskeleton extends Monster{

    public Exoskeleton(String name, int Hp, int power, int defense, int level, int dodge) {
        super(name, Hp, power, defense, level, dodge, "Exoskeleton");
    }
}
