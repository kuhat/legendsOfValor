package game.RPGGame;

import game.role.heroes.Party;
import game.role.role;

import static game.utils.ConsoleColorsCodes.*;

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

    private role hero;

    private role monster;

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

    public role getHero() {
        return hero;
    }

    public role getMonster() {
        return monster;
    }

    public void setHero(role role) {
        this.hero = role;
    }

    public void setMonster(role role) {
        this.monster = role;
    }

    public boolean hasHero() {
        return this.hero != null;
    }

    public boolean hasMonster() {
        return this.monster != null;
    }

    /**
     * Print the content of the cell
     */
    public void printContent(){
        System.out.print("|");
        if (hasHero() && !hasMonster()) {
            System.out.print(BLUE_BOLD_BRIGHT + content + RESET);
        } else if (!hasHero() && hasMonster()) {
            System.out.print(GREEN_BOLD_BRIGHT + content + RESET);
        } else if (hasMonster() && hasHero()) {
            System.out.print(BLUE_BOLD_BRIGHT + content.substring(0, 2) + "   " + GREEN_BOLD_BRIGHT + content.substring(5,7) + RESET);
        } else {
            System.out.print(RED + content + RESET);
        }
        System.out.print("|");
    }

    /**
     * Print the first row and the last row of the cell
     */
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

    public abstract void enter(role role);

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}