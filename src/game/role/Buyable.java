package game.role;

import game.RPGGame.RPGItem;
import game.role.item.Inventory;

/**
 * @projectName: monstersAndHerios
 * @package: game.role
 * @className: buyable
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/3 21:53
 * @version: 1.0
 */
public interface Buyable {

    int getLevel();

    int getMoney();

    Inventory getInventory();

    void setMoney(int money);
}
