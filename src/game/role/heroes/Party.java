package game.role.heroes;

import game.role.role;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.heroes
 * @className: Party
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/4 23:01
 * @version: 1.0
 */
public class Party {

    private List<role> party;

    private HeroFactory heroFactory;

    private int[] position;
    public Party() {
        party = new ArrayList<>();
        heroFactory = HeroFactory.getInstance();
    }

    public List<role> getParty() {
        return party;
    }

    /**
     * Add a new Warrior into this party
     * @return new Warrior
     */
    public role addWarrior() {
        role warrior = heroFactory.creatWarrior();
        party.add(warrior);
        return warrior;
    }

    /**
     * Add a new Sorcerer into this party
     * @return a new Sorcerer
     */
    public role addSorcerer() {
        role sorcerer = heroFactory.creatSorcerer();
        party.add(sorcerer);
        return sorcerer;
    }

    public role addPaladin() {
        role paladin = heroFactory.creatPaladin();
        party.add(paladin);
        return paladin;
    }

    public void setPosition(int i, int j) {
        this.position = new int[]{i, j};
    }

    public int[] getPosition() {
        return position;
    }

    /**
     * Add a new member to this party
     * @param member new member, either hero or monster
     */
    public void addMember(role member) {
        party.add(member);
    }


}
