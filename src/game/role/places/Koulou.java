package game.role.places;

import game.RPGGame.normalCell;
import game.role.heroes.Hero;
import game.role.monsters.Monster;

/**
 * @projectName: monstersAndHeroes
 * @package: game.role.places
 * @className: Koulou
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/20 16:50
 * @version: 1.0
 */
public class Koulou extends normalCell {
    public Koulou(String content) {
        super(content);
    }

    public Koulou(String content, String type) {
        super(content, type);
    }

    @Override
    public void heroEntersSpace(Hero hero) {
//        super.heroEntersSpace(hero);
        hasHero=true;
        System.out.println("Hero entered Koulou space and gained 10% of Strength");
        int newPower = (int) (hero.getPower() * (1.1));
        hero.setAgility(newPower);
    }

    @Override
    public void heroExitsSpace(Hero hero) {
//        super.heroExitsSpace(hero);
        System.out.println("Hero exited Koulou space and lost extra 10% of Strength");
        int newPower = (int) (hero.getPower() * (0.9));
        hero.setAgility(newPower);
        hasHero=false;
    }

    @Override
    public void monsterEntersSpace(Monster monster) {
//        super.monsterEntersSpace(monster);
        hasMonster=true;
        System.out.println("Monster entered Koulou space and gained 10% of Strength");
        int newPower = (int) (monster.getPower() * (1.1));
        monster.setDodge(newPower);

    }

    @Override
    public void monsterExitsSpace(Monster monster) {
//        super.monsterExitsSpace(monster);
        System.out.println("Monster exited Koulou space and lost extra 10% of Strength");
        int newPower = (int) (monster.getPower() * (0.9));
        monster.setDodge(newPower);
        hasMonster=false;
    }

}
