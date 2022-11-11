package game.role.heroes;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.heroes
 * @className: Sorceror
 * @author: Danny
 * @description: Sorceror hero type
 * @date: 2022/11/3 21:00
 * @version: 1.0
 */
public class Sorceror extends Hero{


    public Sorceror(String name, int Mp, int Hp, int power, int defense, int level, int dodge, int agility, int dexterity,
                    int gold, int experience) {
        super(name, Mp, Hp, power, defense, level, dodge, agility, dexterity, gold, experience, "Sorceror");
    }
}
