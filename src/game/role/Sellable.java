package game.role;

/**
 * @projectName: monstersAndHerios
 * @package: game.role
 * @className: Sellable
 * @author: Danny
 * @description: items that have the same behavior: can be sold
 * @date: 2022/11/3 21:53
 * @version: 1.0
 */
public interface Sellable {

    int getPrice();

    int getReqLevel();

    boolean sellTo(Buyable buyer);
}
