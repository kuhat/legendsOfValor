package game.RPGGame;

import game.role.heroes.Party;

/**
 * @projectName: monstersAndHerios
 * @package: game.boardGame
 * @className: Cell
 * @author: Danny
 * @description: Each cell in the game board
 * @date: 2022/11/3 18:25
 * @version: 1.0
 */
public abstract class Cell {

    /**
     * The content inside the Cell
     */
    protected String content;

    /**
     * A type to distinguish Cell type: Bush, Plain, Koulou, Cave, Nexus
     */
    private String type;

    /**
     * type X stands for Inaccessible, M stands for Market
     * @param content content inside the cell
     */
    public Cell(String content) {
        this.content = content;
        if (content.equals("X")) type = "X";
        else if (content.equals("M")) type = "M";
        else type = " ";
    }

    public Cell (String content, String type) {
        this.content = content;
        this.type = type;
    }

    public void printContent(){
        System.out.print("|");
        System.out.print(content);
        System.out.print("|");
    }

    public void printFirstAndLast(){
        for (int i = 0; i < 9; i ++) {
            if (i == 0 || i == 4 || i == 8) System.out.print(type);
            else if (i % 2 == 1) System.out.print(" ");
            else  System.out.print("-");
        }
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