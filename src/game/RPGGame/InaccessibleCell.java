package game.RPGGame;

import game.role.heroes.Party;

/**
 * @projectName: monstersAndHerios
 * @package: game.RPGGame
 * @className: InaccessibleCell
 * @author: Danny
 * @description: the cell that cannot be entered
 * @date: 2022/11/3 22:01
 * @version: 1.0
 */
public class InaccessibleCell extends Cell {


    public InaccessibleCell(String content) {
        super(content);
    }

    public InaccessibleCell(String content, String type) {
        super(content, type);
    }

    @Override
    public void enter(Party party) {
        System.out.println("Inaccessible Cell! Cannot enter! ");
    }

    @Override
    public void enter(game.role.role role) {

    }
}
