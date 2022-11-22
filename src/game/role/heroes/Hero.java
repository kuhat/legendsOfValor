package game.role.heroes;

import game.role.Buyable;
import game.role.attackable;
import game.role.item.*;
import game.role.role;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.heroes
 * @className: Hero
 * @author: Danny
 * @description: Hero class inherited by all the heroes, which can attack and buy items
 * @date: 2022/11/3 18:33
 * @version: 1.0
 */
public abstract class Hero extends role implements attackable, Buyable {

    private int MP;

    private int maxHP;

    private int dexterity;

    private int agility;

    private int gold;

    private int experience;

    private Weapon weapon;

    private Armor armor;

    private Inventory inventory;
    private String heroType;

    private int[] birthPlace;

    public Hero(String name,int mp, int Hp, int power, int defense, int level, int dodge, int agility, int dexterity,
                int gold, int experience, String type) {
        super(name, Hp, power, defense, level, dodge, "Hero");
        this.agility = agility;
        this.gold = gold;
        this.MP = mp;
        this.dexterity = dexterity;
        this.experience = experience;
        this.inventory = new Inventory();
        this.heroType = type;
        this.maxHP = Hp;
    }

    public int[] getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(int i, int j) {
        this.birthPlace = new int[]{i, j};
    }

    public String getHeroType() {
        return heroType;
    }

    public void setExperience(int num) {
        this.experience = num;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int mp) {
        this.MP = mp;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int newAgility) {
        this.agility = newAgility;
    }

    public void setDexterity(int newDexterity) {
        this.dexterity = newDexterity;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getExperience() {
        return experience;
    }

    public double getDodge() {
        return this.agility * 0.002;
    }

    public void revive() {
        setDead(false);
        super.setHP(maxHP / 2);
        System.out.println("Hero " + this.getName() + " has reborn with the Hp of " + getHP() + " out of " + maxHP);
    }

    public void regain() {
        this.setMP(this.getMP() + this.getMP() / 10);
        this.setHP((int) (this.getHP() +  maxHP * 0.1));
        System.out.println("Hero " + this.getName() + " has regained his HP for " + 0.1 * maxHP + " and Mana for " + this.getMP() / 11);
    }

    public void levelUp() {
        this.setLevel(this.getLevel() + 1);
        this.setHP(getLevel() * 100);
        this.setExperience(this.getExperience() + this.getLevel() * 10);
        System.out.println(" - get experience: " + this.getLevel() * 10);
        this.setExperience(this.getExperience() + getLevel() * 10);
        if (this.getHeroType().equals("Paladin")) {
            this.setPower(this.getPower() + this.getPower()/ 20);
            this.setDexterity(this.getDexterity() + this.getDexterity() / 20);
            System.out.println(" - " + " Get power points: "+ this.getPower() / 20 +", Current power points: " + this.getPower());
            System.out.println(" - "+ " Get Dexterity Points: "+ this.getDexterity() / 20  +", Current Dexterity points: " + this.getDexterity());
        } else if (this.getHeroType().equals("Warrior")) {
            this.setPower(this.getPower() + this.getPower() / 20);
            this.setAgility(this.getAgility() + this.getAgility() / 20);
            System.out.println(" - " + " Get power points: "+ this.getPower() / 20 +", Current power points: " + this.getPower());
            System.out.println(" - "+ " Get Agility Points: "+ this.getAgility() / 20  +", Current Agility points: " + this.getAgility());
        } else {
            this.setDexterity(this.getDexterity() + this.getDexterity() / 20);
            this.setAgility(this.getAgility() + this.getAgility() / 20);
            System.out.println(" - "+ " Get Agility Points: "+ this.getAgility() / 20  +", Current Agility points: " + this.getAgility());
            System.out.println(" - "+ " Get Dexterity Points: "+ this.getDexterity() / 20  +", Current Dexterity points: " + this.getDexterity());

        }
    }

    public void gainExp(int num) {
        this.setExperience(experience + num);
    }

    /**
     * Equip on the new Weapon, add the buff to the power
     * @param weapon new weapon
     */
    public void equipWeapon(Weapon weapon) {
        if (this.weapon != null) unmountWeapon();
        this.setPower(this.getPower() + weapon.getDamage());
        this.weapon = weapon;
    }

    /**
     * unmount the current weapon
     */
    public void unmountWeapon() {
        this.setPower(this.getPower() - this.weapon.getDamage());
    }

    /**
     * Equip a new armor
     * @param armor new armor to be equipped
     */
    public void equipArmor(Armor armor) {
        if (this.armor != null) unmountArmor();
        this.setDefense(this.getDefense() + armor.getReduction());
        this.armor = armor;
    }

    /**
     * unmount the currentArmor
     */
    public void unmountArmor() {
        this.setDefense(this.getDefense() - this.armor.getReduction());
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    /**
     * Hero uses spell at index idx to the monster
     * @param idx use spell at index idx of the spell list
     * @param monster the spell to spell on
     * @return true if succeed in casting the spell, false if the hero's mana is not enough
     */
    public boolean useSpellTo(int idx, role monster) {
        Spell spell = this.getInventory().getSpell().get(idx);
        int MPNeeded = spell.getManaCost();
        if (this.MP < MPNeeded) return false;
        else {
            setMP(this.MP - MPNeeded);
            this.getInventory().getSpell().remove(idx);  // remove the used spell
            this.inventory.removeItem(spell);
            int damage = spell.getDamage() + (getDexterity() / 10000) * spell.getDamage();
            // ICE spell reduce the damage level of a monster
            if (spell.getType().equals("ICE")) {
                monster.setPower(monster.getPower() - this.getDexterity());
                monster.setHp(monster.getHP() - damage - monster.getDefense());
                System.out.format("Hero %s cast a ICE spell to %s, reduced the damage level of %d (the damage " +
                                  "level of the enemy is now %d), costed %d HP loss to %s!", this.getName(),
                                  monster.getName(), this.getDexterity(), monster.getPower(), damage - monster.getDefense(),
                        monster.getName());
            } else if (spell.getType().equals("FIRE")) {
                // FIRE spell reduce the defense level of a monster
                monster.setDefense(monster.getDefense() - this.getDexterity());
                monster.setHp(monster.getHP() - damage - monster.getDefense());
                System.out.format("Hero %s cast a FIRE spell to %s, reduced the defense level of %d (the defense " +
                                "level of the enemy is now %d), costed %d HP loss to %s!", this.getName(),
                        monster.getName(), this.getDexterity(), monster.getDefense(),
                        damage - monster.getDefense(), monster.getName());
            } else {
                // lightning spell reduce the dodge chance of a monster
                monster.setDodge((int) (monster.getDodge() - this.getDexterity()));
                monster.setHp(monster.getHP() - damage - monster.getDefense());
                System.out.format("Hero %s cast a FIRE spell to %s, reduced the dodge chance of %.2f (the dodge " +
                                "chance of the enemy is now %.2f), costed %d HP loss to %s!", this.getName(),
                        monster.getName(), (double)1 / this.getDexterity(), monster.getDodge(),
                        damage - monster.getDefense(), monster.getName());
            }
            if (monster.getHP() <= 0) {
                monster.setDead(true);
                this.setLevel(this.getLevel() + 1);
                System.out.println("Congratulations, hero " + this.getName() + " used this spell to destroy this monster.");
                System.out.println(this.getHeroType() + " " + this.getName() + " has leveled up. Currently level: " + getLevel());
            }
        }
        return true;
    }

    public void usePotion(int idx) {
        Potion potion = getInventory().getPotion().get(idx);
        int[] attrInc = potion.getAffectedAttr();
        this.setHP(this.getHP() + attrInc[0]);
        this.setPower(this.getPower() + attrInc[1]);
        this.setMP(this.getMP() + attrInc[2]);
        this.setAgility(this.getAgility() + attrInc[3]);
        this.setDefense(this.getDefense() + attrInc[4]);
        this.setDexterity(this.getDefense() + attrInc[5]);
        getInventory().getPotion().remove(potion);
        this.getInventory().removeItem(potion);
    }


    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public int getMoney() {
        return gold;
    }

    @Override
    public void setMoney(int money) {
        this.gold = money;
    }

    @Override
    public String toString() {
        return "HP: " + getHP() + " MP: " + getMP() + " Dexterity: " + getDexterity() + " Defense: " + getDefense() +
                " Agility: " + getAgility() + " Power: " + getPower();
    }
}
