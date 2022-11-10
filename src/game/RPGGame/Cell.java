package game.RPGGame;

import game.role.heroes.Party;

/**
 * @projectName: monstersAndHerios
 * @package: game.boardGame
 * @className: Cell
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/3 18:25
 * @version: 1.0
 */
public abstract class Cell {

    protected String content;

    /**
     * A type to distinguish inaccessible cell. Normal Cell and market Cell
     */
    private String type;

    public Cell(String content) {
        this.content = content;
        if (content.equals("X")) type = "X";
        else if (content.equals("M")) type = "M";
        else type = " ";
    }

    public String getType(){
        return this.type;
    }

    public abstract void enter(Party party);

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}