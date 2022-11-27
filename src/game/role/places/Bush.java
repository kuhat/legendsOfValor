package game.role.places;

import game.RPGGame.normalCell;
import game.role.heroes.Hero;
import game.role.monsters.Monster;
import game.utils.ConsoleColorsCodes;

/**
 * @projectName: monstersAndHeroes
 * @package: game.role.places
 * @className: Bush
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/20 16:50
 * @version: 1.0
 */
public class Bush extends normalCell {
    public Bush(String content) {
        super(content);
    }
    public Bush(String content, String type) {
        super(content, type);
    }

    public Bush() {
        super();
    }

    @Override
    public void heroEntersSpace(Hero hero) {
//        super.heroEntersSpace(hero);
        hasHero=true;
        System.out.println(ConsoleColorsCodes.GREEN_BRIGHT + "Hero entered Bush space and gained 10% of dexterity" + ConsoleColorsCodes.RESET);
        int newDexterity = (int) (hero.getDexterity() * (1.1));
        hero.setDexterity(newDexterity);
    }

    @Override
    public void heroExitsSpace(Hero hero) {
//        super.heroExitsSpace(hero);
        System.out.println(ConsoleColorsCodes.BLACK_BACKGROUND + "Hero exited the Bush space and lost extra 10% of dexterity" + ConsoleColorsCodes.RESET);
        int newDexterity = (int) (hero.getDexterity() * (0.9));
        hero.setDexterity(newDexterity);
        hasHero=false;
    }

    @Override
    public void monsterEntersSpace(Monster monster) {
//        super.monsterEntersSpace(monster);
        hasMonster=true;
        System.out.println(ConsoleColorsCodes.GREEN_BRIGHT + "Monster entered Bush space -- gains 10% of defense for this round"+ConsoleColorsCodes.RESET);
        int newDefense = (int) (monster.getDefense() * (1.1));
        monster.setDefense(newDefense);

    }

    @Override
    public void monsterExitsSpace(Monster monster) {
//        super.monsterExitsSpace(monster);
        System.out.println(ConsoleColorsCodes.BLACK_BACKGROUND + "Monster exited the Bush space -- lost extra 10% of dexterity for this round" + ConsoleColorsCodes.RESET);
        int newDefense = (int) (monster.getDefense() * (0.9));
        monster.setDefense(newDefense);
        hasMonster=false;
    }
}
