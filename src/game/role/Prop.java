package game.role;

/**
 * @projectName: monstersAndHeroes
 * @package: game.role
 * @className: Prop
 * @author: Danny
 * @description: This is the general properties of a role to have: Heroes and Monsters
 * @date: 2022/11/5 1:49
 * @version: 1.0
 */
public class Prop {

    /**
     * Level of the character
     */
    private int level;

    /**
     * power damage level of the character
     */
    private int power;

    /**
     * Defense level of the character
     */
    private int defense;

    /**
     * Dodge ratio of the character
     */
    private int dodge;

    /**
     * Hp of the Character
     */
    private int Hp;


    public Prop(int level, int power, int defense, int dodge, int Hp) {
        this.level = level;
        this.power = power;
        this.defense = defense;
        this.dodge = dodge;
        this.Hp = Hp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDodge() {
        return dodge;
    }

    public void setDodge(int dodge) {
        this.dodge = dodge;
    }

    public int getHP() {
        return Hp;
    }

    public void setHP(int Hp) {
        this.Hp = Hp;
    }
}
