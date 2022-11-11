package game.role;

import game.FactoryCreator;
import game.Product;

/**
 * @projectName: monstersAndHerios
 * @package: game.role
 * @className: roleFacory
 * @author: Danny
 * @description: A base factory interface to generate roles
 * @date: 2022/11/3 21:01
 * @version: 1.0
 */
public abstract class RoleFactory implements FactoryCreator {

    public abstract Product creatProduct();
}
