package game.role;

import game.role.heroes.Hero;

public interface mountable {
    void equip(Hero hero);

    String getType();

    String getName();
}
