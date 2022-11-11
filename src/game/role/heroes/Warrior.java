package game.role.heroes;

import game.role.role;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.heroes
 * @className: Warrior
 * @author: Danny
 * @description: Warrior hero type
 * @date: 2022/11/3 20:59
 * @version: 1.0
 */
public class Warrior extends Hero {


    public Warrior(String name, int Mp, int Hp, int power, int defense, int level, int dodge, int agility, int dexterity,
                   int gold, int experience) {
        super(name, Mp, Hp, power, defense, level, dodge, agility, dexterity, gold, experience, "Warrior");
    }



}
