package game.role.monsters;

import game.Product;
import game.role.RoleFactory;
import game.role.heroes.HeroFactory;
import game.role.role;
import game.utils.fileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.monsters
 * @className: MonsterFactory
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/3 20:51
 * @version: 1.0
 */
public class MonsterFactory extends RoleFactory {

    private static MonsterFactory MonsterFactory;

    private List<List<String>> dragonInfo;

    private List<List<String>> exoskeletonInfo;

    private List<List<String>> spiritInfo;

    private final String exoskeletonPath = "Exoskeletons.txt";

    private final String spiritsPath = "Spirits.txt";

    private final String dragonPath = "Dragons.txt";

    private MonsterFactory() {
        readSpirits();
        readDragon();
        readExoskeleton();
    }

    private void readSpirits() {
        List<String> allSpiritsInfo = fileReader.readFile(spiritsPath);
        spiritInfo = new ArrayList<>();
        for (int i = 1; i < allSpiritsInfo.size(); i++) {
            if (!allSpiritsInfo.get(i).equals("")) spiritInfo.add(Arrays.asList(allSpiritsInfo.get(i).split(" ")));
        }
    }

    private void readDragon() {
        List<String> allDragonsInfo = fileReader.readFile(dragonPath);
        dragonInfo = new ArrayList<>();
        for (int i = 1; i < allDragonsInfo.size(); i++) {
            if (!allDragonsInfo.get(i).equals("")) dragonInfo.add(Arrays.asList(allDragonsInfo.get(i).split(" ")));
        }
//        System.out.println(Arrays.toString(dragonInfo.toArray()));
    }

    private void readExoskeleton() {
        List<String> allExoskeleton = fileReader.readFile(exoskeletonPath);
        exoskeletonInfo = new ArrayList<>();
        for (int i = 1; i < allExoskeleton.size(); i++) {
            if (!allExoskeleton.get(i).equals("")) exoskeletonInfo.add(Arrays.asList(allExoskeleton.get(i).split(" ")));
        }
    }

    private role createExoskeleton() {
//        Name/level/damage/defense/dodge chance
        // Generate random exoskeleton
        int idx = (int) (Math.random() * exoskeletonInfo.size());
        List<String> info = exoskeletonInfo.get(idx);
//        System.out.println("Index: " + idx +Arrays.toString(new List[]{Arrays.asList(info)}));
        List<String> prop = new ArrayList<>();
        for (String s : info) if (!s.equals("")) prop.add(s.trim());
        return new Exoskeleton(prop.get(0), 100 * Integer.valueOf(prop.get(1)), Integer.valueOf(prop.get(2)),
                Integer.valueOf(prop.get(3)), Integer.valueOf(prop.get(1)), Integer.valueOf(prop.get(4)));
    }

    private role createDragon() {
        int idx = (int) (Math.random() * dragonInfo.size());
        List<String> info = dragonInfo.get(idx);
        List<String> prop = new ArrayList<>();
        for (String s : info) if (!s.equals("")) prop.add(s.trim());
//        Arrays.toString(prop.toArray());
        return new Dragon(info.get(0), 100 * Integer.valueOf(prop.get(1)), Integer.valueOf(prop.get(2)),
                Integer.valueOf(prop.get(3)), Integer.valueOf(prop.get(1)), Integer.valueOf(prop.get(4)));
    }

    private role createSpirit() {
        int idx = (int) (Math.random() * spiritInfo.size());
        List<String> info = spiritInfo.get(idx);
        List<String> prop = new ArrayList<>();
        for (String s : info) if (!s.equals("")) prop.add(s.trim());
        return new Spirit(info.get(0), 100 * Integer.valueOf(prop.get(1)), Integer.valueOf(prop.get(2)),
                Integer.valueOf(prop.get(3)), Integer.valueOf(prop.get(1)), Integer.valueOf(prop.get(4)));
    }

    /**
     * even chance of creating each kind of monster
     * @return
     */
    @Override
    public Product creatProduct() {
        double random = Math.random();
        if (random < 0.33) return createDragon();
        else if (random < 0.66) return createExoskeleton();
        else return createSpirit();
    }

    /**
     * Return an instance of MonsterFactory
     *
     * @return MonsterFactory if the instance is null
     */
    public static MonsterFactory getInstance() {
        if (MonsterFactory == null) return new MonsterFactory();
        else return MonsterFactory;
    }
}
