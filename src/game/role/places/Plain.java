package game.role.places;

import game.RPGGame.normalCell;
import game.role.heroes.Hero;
import game.role.monsters.Monster;

/**
 * @projectName: monstersAndHeroes
 * @package: game.role.places
 * @className: Plain
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/20 16:50
 * @version: 1.0
 */
public class Plain extends normalCell {

    public Plain(String content) {
        super(content);
    }

    public Plain(String content, String type) {
        super(content, type);
    }

    public Plain() {

    }

    @Override
    public void heroEntersSpace(Hero hero) {
        //super.heroEntersSpace(hero);
        hasHero=true;
    }

    @Override
    public void heroExitsSpace(Hero hero) {
        //super.heroExitsSpace(hero);
        hasHero=false;
    }

    @Override
    public void monsterEntersSpace(Monster monster) {
        //super.monsterEntersSpace(monster);
        hasMonster=true;
    }

    @Override
    public void monsterExitsSpace(Monster monster) {
        //super.monsterExitsSpace(monster);
        hasMonster=false;
    }
}
