package game.role;

/**
 * attackable interface implemented by monster and hero
 */

public interface attackable {
    void attack(role other);

    void setHp(int hp);

}
