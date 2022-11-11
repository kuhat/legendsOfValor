package game.role;

import game.Product;
import game.role.heroes.Hero;

/**
 * @projectName: monstersAndHerios
 * @package: game
 * @className: role
 * @author: Danny
 * @description: base role instance inherited by monsters and heroes
 * @date: 2022/11/3 18:27
 * @version: 1.0
 */
public abstract class role implements attackable, Product {
    private String name;
    private Prop prop;

    private String type;

    private boolean dead;

    public role(String name, Prop prop) {
        this.name = name;
        this.prop =prop;
    }

    public role(String name, int Hp, int power, int defense, int level, int dodge, String type) {
        this.prop = new Prop(level, power, defense, dodge, Hp);
        this.name = name;
        this.type = type;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return prop.getLevel();
    }

    public void setLevel(int level) {
        prop.setLevel(level);
    }

    public int getPower() {
        return prop.getPower();
    }

    public void setPower(int power) {
        prop.setPower(power);
    }

    public int getDefense() {
        return prop.getDefense();
    }

    public void setDefense(int defense) {
        prop.setDefense(defense);
    }

    public double getDodge() {
        return prop.getDodge();
    }

    public void setDodge(int dodge) {
        prop.setDodge(dodge);
    }

    public int getHP() {
        return prop.getHP();
    }

    public void setHP(int Hp) {
        prop.setHP(Hp);
    }

    /**
     * Get the type of this role, Hero or Monster
     * @return type
     */
    public String getType() {
        return type;
    }

    @Override
    public void setHp(int hp) {
        prop.setHP(hp);
    }

    /**
     * this role attack another role
     * @param other other role to attack
     */
    @Override
    public void attack(role other) {
        // Monster's dodge chance: 0.1 * dodge
        double dodgeChance = other.getDodge() * 0.1;
        if (other.getType().equals("Hero")) {
            dodgeChance = ((Hero)other).getAgility() * 0.0005;
        }
        double rand = Math.random();
        if (rand > dodgeChance) {
            System.out.println(other.getName() + " did not dodge this attack!");
            int damage = Math.max((int)(this.getPower() - other.getDefense()) / 20, 0);
            if (damage != 0) {
                other.setHp(other.getHP() - damage);
                System.out.format("%s caused %d hp loss to %s \n", this.getName(), damage, other.getName());
                System.out.println(other.getName() + " has HP: " + other.getHP());
                // If the HP is less than 0, the other target is dead
                if (other.getHP() <= 0) {
                    System.out.println(other.getType() + ": " + other.getName() + " has dead after the attack of "
                            + this.getType() + ": " + this.getName());
                    other.setDead(true);
                    // If the hero successfully defeated a monster, level it up, gain money
                    if (other.getType().equals("Monster")) {
                        System.out.println("Your hero has slayed a monster. Leveled up by one. Current level: " + this.getLevel() + 1);
                        ((Hero)this).levelUp();
                        ((Hero)this).setMoney(((Hero)this).getMoney() + 100 * other.getLevel());
                    }
                }
            }
            else System.out.println("But " + other.getName() + " absorbed all the damage. ");
        } else {
            System.out.println(other.getName() + " dodged this attack.");
        }

    }

}
