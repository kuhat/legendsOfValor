package game.role;

import game.role.item.Inventory;

/**
 * @projectName: monstersAndHerios
 * @package: game.role
 * @className: buyable
 * @author: Danny
 * @description: Buyable interface which have common behaviors
 * @date: 2022/11/3 21:53
 * @version: 1.0
 */
public interface Buyable {

    int getLevel();

    int getMoney();

    Inventory getInventory();

    void setMoney(int money);
}
