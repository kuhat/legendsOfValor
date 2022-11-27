package game.RPGGame;

import game.role.heroes.Hero;
import game.role.heroes.Party;
import game.role.item.Armor;
import game.role.item.Potion;
import game.role.item.Spell;
import game.role.item.Weapon;
import game.role.monsters.Monster;
import game.role.monsters.MonsterFactory;
import game.role.mountable;
import game.role.role;

import java.util.ArrayList;
import java.util.List;

import static game.GameRunner.*;
import static game.RPGGame.Board.BLUE;

/**
 * @projectName: monstersAndHerios
 * @package: game.RPGGame
 * @className: normalCell
 * @author: Danny
 * @description: a Cell where battle may happen
 * @date: 2022/11/3 22:02
 * @version: 1.0
 */
public class normalCell extends Cell {

    private Party monsterParty;

    private Party heroParty;
    protected boolean hasHero;
    protected boolean hasMonster;

    public normalCell() {
        super();
    }

    public void heroEntersSpace(Hero hero){}
    public void heroExitsSpace(Hero hero){}
    public void monsterEntersSpace(Monster monster){}
    public void monsterExitsSpace(Monster monster){}

    public boolean isHasHero() {
        return hasHero;
    }

    public void setHasHero(boolean hasHero) {
        this.hasHero = hasHero;
    }

    public boolean isHasMonster() {
        return hasMonster;
    }

    public void setHasMonster(boolean hasMonster) {
        this.hasMonster = hasMonster;
    }

    public normalCell(String content) {
        super(content);
        hasHero = false;
        hasMonster = false;
    }

    public normalCell(String content, String type) {
        super(content, type);
        hasHero = false;
        hasMonster = false;
    }

    @Override
    public void enter(Party party) {
        if (Math.random() < 0.7) {
            heroParty = party;
            monsterParty = createMonsters(party.getParty().size());
            System.out.println("Unfortunately, monster appears, your heroes must fight!");
            beginBattle();
        } else {
            System.out.println("Luckily, this time no monster occurs. Keep on moving!");
        }
    }

    @Override
    public void enter(role role) {

    }

    /**
     * Create monster Num of random monsters
     *
     * @param monsterNum number of monster to create
     * @return mParty, a party of Monster
     */
    private Party createMonsters(int monsterNum) {
        Party mParty = new Party();
        int maxLevel = 0;
        for (int j = 0; j < heroParty.getParty().size(); j++ ) {
            maxLevel = Math.max(maxLevel, heroParty.getParty().get(j).getLevel());
        }
        for (int i = 0; i < monsterNum; i++) {
            // Only generate the monster whose level is less than that of hero
           role newMonster = (role) MonsterFactory.getInstance().creatProduct();
           while (newMonster.getLevel() > maxLevel) newMonster = (role) MonsterFactory.getInstance().creatProduct();
           mParty.addMember(newMonster);
        }
        return mParty;
    }

    private void beginBattle() {
        printBattleField();
        int round = 1;
        while (true) {
            role heroToFight = null;
            role monsterToFight = null;
            // Find the next fighting pairs
            for (role hero : heroParty.getParty()) {
                if (!hero.isDead()) heroToFight = hero;
            }
            for (role monster : monsterParty.getParty()) {
                if (!monster.isDead()) monsterToFight = monster;
            }
            // if heroes are all dead, game over
            if (heroToFight == null) {
                System.out.println("Heroes are all fainted, game over.");
                System.out.println("thanks for playing!");
                System.exit(0);
            }
            if (monsterToFight == null) {
                System.out.println("Monsters are all defeated! Heroes win! Good job!");
                break;
            }
            round(heroToFight, monsterToFight, round);
            round++;
        }
        // revive all the fainted heroes

        for (role hero: heroParty.getParty()) {
            if (hero.isDead()) {
                ((Hero)hero).revive();
            } else {
                ((Hero)hero).regain();
                ((Hero)hero).setExperience(((Hero) hero).getExperience() + 2 * monsterParty.getParty().size());
            }
        }
    }

    private void round(role hero, role monster, int idx) {
        System.out.println("************************   Round " + idx + "    *************************");
        try {
            System.out.format(" " + BLUE + " %s : %s " + RESET + " VS " + RED + "  %s : %s  " + RESET + "\n",
                    ((Hero) hero).getHeroType(), hero.getName(), ((Monster) monster).getMonsterType(), monster.getName());
            System.out.println("*************************************************************************");
            Thread.sleep(1000);
            System.out.println("-------------------Hero " + hero.getName() + "'s turn--------------------");
            chooseAction(hero, monster);
            System.out.println("-------------------------Hero's turn end---------------------------------\n" +
                               "----------------Monster " + monster.getName() + " begin------------------\n");
            Thread.sleep(1000);
            if (monster.isDead()) {
                System.out.println("Monster has been slayed");
            } else {
                monster.attack(hero);
            }
            System.out.println("-------------------------Monster's turn end-------------------------------");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void chooseAction(role hero, role monster) {
        boolean strRes = false;
        String input = "";
        while (!strRes) {
            input = getInput("Please choose operation for hero: " + BLUE + hero.getName() + RESET + ": \n" +
                    " - 0 Regular Attack \n" +
                    " - 1 use a Spell \n" +
                    " - 2 use a Potion \n" +
                    " - 3 Change Weapon/armor \n");
            strRes = ((input != null && input.matches("[0123]")));
            if (!strRes) System.out.println("Please choose valid option!");
        }
        strRes = false;
        if (input.equals("0")) {
            // regular attack
            hero.attack(monster);
        } else if (input.equals("1")) {
            // use a spell
            useSpell(hero, monster);
        } else if (input.equals("2")) {
            // use Potion
            usePotion(hero);
        } else if (input.equals("3")) {
            // change weapon/armor
            changeEquipment(hero);
        }
    }

    private void useSpell(role hero, role monster) {
        String input = "";
        boolean strRes = false;
        List<Spell> spellList = ((Hero) hero).getInventory().getSpell();
        if (spellList.size() == 0) {
            System.out.println("This Hero doesn't have any spells.");
        } else {
            System.out.println("Hero " + hero.getName() + " has " + ((Hero) hero).getMP() + " left.");
            ((Hero) hero).getInventory().showSpell();
            while (!strRes) {
                input = getInput("Choose a spell to use:");
                strRes = (input != null) && isValid(input, spellList.size() - 1);
                if (!strRes) System.out.println("Invalid input, choose input from 0~" + (spellList.size() - 1));
            }
            if (!((Hero) hero).useSpellTo(Integer.valueOf(input), monster)) {
                System.out.println("Hero doesn't have enough MANA left to cast the spell!");
            }
        }
    }

    private void usePotion(role hero) {
        boolean strRes = false;
        String input = "";
        List<Potion> potionList = ((Hero) hero).getInventory().getPotion();
        if (potionList.size() == 0) System.out.println("This hero doesn't have any potions!");
        else {
            ((Hero) hero).getInventory().showPotion();
            while (!strRes) {
                input = getInput("Choose a Potion to use:");
                strRes = (input != null) && isValid(input, potionList.size() - 1);
                if (!strRes) System.out.println("Invalid input, choose input from 0~" + (potionList.size() - 1));
            }
            ((Hero) hero).usePotion(Integer.valueOf(input));
            System.out.println("After using potion, the property of hero: " + hero.getName() + ": \n"
                    + ((Hero) hero).toString());
        }
    }

    private void changeEquipment(role hero) {
        boolean strRes = false;
        String input = "";
        List<RPGItem> weaponsAndArmors = new ArrayList<>();
        weaponsAndArmors.addAll(((Hero) hero).getInventory().getWeapon());
        weaponsAndArmors.addAll(((Hero) hero).getInventory().getArmor());
        if (weaponsAndArmors.size() == 0) {
            System.out.println("Currently there are no any Weapons or Armors in this hero's inventory.");
        } else {
            Weapon equippedWeapon = ((Hero) hero).getWeapon();
            Armor equippedArmor = ((Hero) hero).getArmor();
            if (equippedWeapon == null) System.out.println("Equipped Weapon is None");
            else
                System.out.println("Equipped Weapon: " + equippedWeapon.getName() + ", Damage: " + equippedWeapon.getDamage());
            if (equippedArmor == null) System.out.println("Equipped Armor is None");
            else
                System.out.println("Equipped Armor: " + equippedArmor.getName() + ", Reduction: " + equippedArmor.getReduction());
            System.out.println("You can choose Armors and Weapons in the current inventory to equip.");
            List<mountable> mountables = ((Hero) hero).getInventory().showMountable();
            while (!strRes) {
                input = getInput("Choose a Weapon or Armor to equip:");
                strRes = (input != null) && isValid(input, mountables.size() - 1);
                if (!strRes) System.out.println("Invalid input, choose input from 0~" + (mountables.size() - 1));
            }
            mountable equipment = mountables.get(Integer.valueOf(input));
            equipment.equip((Hero) hero);
            System.out.println("Hero successfully equipped " + equipment.getType() + ": " + equipment.getName());
        }
    }

    private void printBattleField() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=   Battle   =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        for (int i = 0; i < monsterParty.getParty().size(); i++) {
            role hero = heroParty.getParty().get(i);
            role monster = monsterParty.getParty().get(i);
            System.out.format(" " + BLUE + " %s : %s " + RESET + "         VS          " + RED + "  %s : %s  " + RESET + "\n",
                    ((Hero) hero).getHeroType(), hero.getName(), ((Monster) monster).getMonsterType(), monster.getName());
        }
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }

    /**
     * Check if the input of the user is value and within heroIdx range
     *
     * @param input user input
     * @return true if the input is an Integer and within hero idx range
     */
    private boolean isValid(String input, int upperValue) {
        for (char s : input.toCharArray()) if (!Character.isDigit(s)) return false;
        int value = Integer.valueOf(input);
        if (value < 0 && value > upperValue) return false;
        return true;
    }
}
