import game.GameRunner;
import game.GameState;

import java.util.Scanner;

/**
 * @projectName: monstersAndHeroes
 * @package: PACKAGE_NAME
 * @className: game
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/3 17:36
 * @version: 1.0
 */
public class Game {
    public static void startGame() {
        System.out.println("--------------------++++++++------------------");
        System.out.println("        Welcome to Monsters and Heroes!       ");
        System.out.println("--------------------++++++++------------------");
        Scanner scanner = new Scanner(System.in);
        GameRunner gameRunner = new GameRunner(scanner, System.out);
        gameRunner.start();
    }

}
