package game.role;

import game.role.heroes.Hero;

/**
 * Items that can be mounted on a hero
 */
public interface mountable {
    void equip(Hero hero);

    String getType();

    String getName();
}
