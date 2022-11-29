package game.role.places;

import game.RPGGame.normalCell;
import game.role.heroes.Hero;
import game.role.monsters.Monster;
import game.utils.ConsoleColorsCodes;

/**
 * @projectName: monstersAndHeroes
 * @package: game.role.places
 * @className: Cave
 * @author: Danny
 * @description: Cave place which boosts role's agility
 * @date: 2022/11/20 16:49
 * @version: 1.0
 */
public class Cave extends normalCell {
    public Cave(String content) {
        super(content);
    }

    public Cave(String content, String type) {
        super(content, type);
    }

    public Cave() {

    }

    @Override
    public void heroEntersSpace(Hero hero) {
//        super.heroEntersSpace(hero);
        hasHero=true;
        System.out.println(ConsoleColorsCodes.WHITE_BRIGHT + "Hero entered Cave space and gained 10% of Agility" + ConsoleColorsCodes.RESET);
        int newAgility = (int) (hero.getAgility() * (1.1));
        hero.setAgility(newAgility);
    }

    @Override
    public void heroExitsSpace(Hero hero) {
//        super.heroExitsSpace(hero);
        System.out.println(ConsoleColorsCodes.BLACK_BACKGROUND + "Hero exited the Cave space and lost extra 10% of Agility"+ConsoleColorsCodes.RESET);
        int newAgility = (int) (hero.getAgility() * (0.9));
        hero.setAgility(newAgility);
        hasHero=false;
    }

    @Override
    public void monsterEntersSpace(Monster monster) {
//        super.monsterEntersSpace(monster);
        hasMonster=true;
        System.out.println(ConsoleColorsCodes.WHITE_BRIGHT + "Monster entered Cave space and gained 10% of Dodge" + ConsoleColorsCodes.RESET);
        int newDodge = (int) (monster.getDodge() * (1.1));
        monster.setDodge(newDodge);

    }

    @Override
    public void monsterExitsSpace(Monster monster) {
//        super.monsterExitsSpace(monster);
        System.out.println(ConsoleColorsCodes.BLACK_BACKGROUND+"Monster exited the Cave space and lost extra 10% of Dodge"+ConsoleColorsCodes.RESET);
        int newDodge = (int) (monster.getDodge() * (0.9));
        monster.setDodge(newDodge);
        hasMonster=false;
    }

}
