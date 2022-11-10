package game.RPGGame;

import game.Product;
import game.role.Buyable;
import game.role.Sellable;
import game.role.role;

import java.util.Arrays;

/**
 * @projectName: monstersAndHerios
 * @package: game.RPGGame
 * @className: RPGItem
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/3 21:44
 * @version: 1.0
 */
public class RPGItem implements Product, Sellable {

    private String name;

    private int price;

    private int level;

    private String type;

    public RPGItem(String name, int price, int level, String type) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int getReqLevel() {
        return level;
    }

    @Override
    public boolean sellTo(Buyable buyer) {
        if(buyer.getLevel() >= this.level && buyer.getMoney() >= this.price) {
            buyer.getInventory().addItem(this);
            buyer.setMoney(buyer.getMoney() - this.price);
            return true;
        }
        return false;
    }

    public int getLevel() {
        return level;
    }

    public String getType() {
        return type;
    }
}
