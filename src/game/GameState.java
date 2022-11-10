package game;

/**
 * @projectName: monstersAndHerios
 * @package: game
 * @className: GameState
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/3 22:24
 * @version: 1.0
 */
public interface GameState {
    /**
     * Judge if the game reaches over condition
     * @return True if the condition is met
     */
    boolean isOver();

    /**
     * run the game
     */
    void run();

    /**
     * start the game
     */

    void start();

    /**
     * Initialize the parameters needed in the game
     */

    void init();
}
