package game.role.monsters;

import game.role.attackable;
import game.role.role;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.monsters
 * @className: Monster
 * @author: Danny
 * @description: Monster base class which extends role can attack
 * @date: 2022/11/3 18:33
 * @version: 1.0
 */
public abstract class Monster extends role implements attackable {

    private String monsterType;
    public Monster(String name, int Hp, int power, int defense, int level, int dodge, String type) {
        super(name, Hp, power, defense, level, dodge, "Monster");
        this.monsterType = type;
    }

    /**
     * Get the monster's type: Spirit or Exoskeleton or Dragon
     * @return Monster's type
     */
    public String getMonsterType() {
        return monsterType;
    }

    @Override
    public int getDefense() {
        return super.getDefense();
    }

    @Override
    public double getDodge() {
        return super.getDodge() * 0.1;
    }

    @Override
    public int getHP() {
        return super.getHP();
    }

    @Override
    public int getPower() {
        return super.getPower();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getLevel() {
        return super.getLevel();
    }
}
