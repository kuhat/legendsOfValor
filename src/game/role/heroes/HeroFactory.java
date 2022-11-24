package game.role.heroes;

import game.Product;
import game.role.RoleFactory;
import game.role.role;
import game.utils.fileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @projectName: monstersAndHeroes
 * @package: game.role.heroes
 * @className: HeroFactory
 * @author: Danny
 * @description: A Hero factory to create heroes of different type
 * @date: 2022/11/3 20:51
 * @version: 1.0
 */
public class HeroFactory extends RoleFactory {

    private static HeroFactory heroFactory;

    private List<List<String>> warriorInfo;

    private List<List<String>> sorcererInfo;

    private List<List<String>> paladinInfo;

    private final String warriorPath = "Warriors.txt";

    private final String sorcerersPath = "Sorcerers.txt";

    private final String paladinPath = "Paladins.txt";

    private HeroFactory() {
        readWarrior();
        readSorceror();
        readPaladin();
    }

    /**
     * Read Warrior file and add info into Warrior ArrayList
     */
    private void readWarrior() {
        List<String> allWarriorInfo = fileReader.readFile(warriorPath);
//        System.out.println("warrior info: " + Arrays.toString(allWarriorInfo.toArray()));
        warriorInfo = new ArrayList<>();
        for (int i = 1; i < allWarriorInfo.size(); i++) {
            if (!allWarriorInfo.get(i).equals("")) warriorInfo.add(Arrays.asList(allWarriorInfo.get(i).split(" ")));
        }
    }

    /**
     * Read Sorcerer file and add info into Sorcerer ArrayList
     */
    private void readSorceror() {
        List<String> allSorcererInfo = fileReader.readFile(sorcerersPath);
//        System.out.println(Arrays.toString(allSorcererInfo.toArray()));
        sorcererInfo = new ArrayList<>();
        for (int i = 1; i < allSorcererInfo.size(); i++) {
            if (!allSorcererInfo.get(i).equals("")) sorcererInfo.add(Arrays.asList(allSorcererInfo.get(i).split(" ")));
        }
    }

    /**
     * Read Paladin file and add info into Paladin ArrayList
     */
    private void readPaladin() {
        List<String> allPaladinInfo = fileReader.readFile(paladinPath);
        paladinInfo = new ArrayList<>();
        for (int i = 1; i < allPaladinInfo.size(); i++) {
            if (!allPaladinInfo.get(i).equals("")) paladinInfo.add(Arrays.asList(allPaladinInfo.get(i).split(" ")));
        }
    }

    /**
     * Create a default Warrior instance
     * @return new Warrior with default properties
     */
    public role creatWarrior() {
        // Name/mana/strength/agility/dexterity/starting money/starting experience
        int idx = (int) (Math.random() * warriorInfo.size());
        List<String> info = warriorInfo.get(idx);
//        System.out.println("Index: " + idx +Arrays.toString(new List[]{Arrays.asList(info)}));
        List<String> prop = new ArrayList<>();
        for (String s: info) if (!s.equals("")) prop.add(s);
        return new Warrior(info.get(0), Integer.valueOf(prop.get(1)), 100 * Integer.valueOf(prop.get(6)), Integer.valueOf(prop.get(2)), 0, 1, 0,
                Integer.valueOf(prop.get(3)), Integer.valueOf(prop.get(4)), Integer.valueOf(prop.get(5)),
                Integer.valueOf(prop.get(6)));
    }

    public role creatSorcerer() {
        // Name/mana/strength/agility/dexterity/starting money/starting experience
        int idx = (int) (Math.random() * sorcererInfo.size());
        List<String> info = sorcererInfo.get(idx);
        List<String> prop = new ArrayList<>();
        for (String s: info) if (!s.equals("")) prop.add(s);
        return new Sorceror(info.get(0), Integer.valueOf(prop.get(1)), 100 * Integer.valueOf(prop.get(6)), Integer.valueOf(prop.get(2)), 0, 1, 0,
                Integer.valueOf(prop.get(3)), Integer.valueOf(prop.get(4)), Integer.valueOf(prop.get(5)),
                Integer.valueOf(prop.get(6)));
    }

    public role creatPaladin() {
        // Name/mana/strength/agility/dexterity/starting money/starting experience
        int idx = (int) (Math.random() * paladinInfo.size());
        List<String> info = paladinInfo.get(idx);
        List<String> prop = new ArrayList<>();
        for (String s: info) if (!s.equals("")) prop.add(s);
        return new Paladin(info.get(0), Integer.valueOf(prop.get(1)), 100 * Integer.valueOf(prop.get(6)), Integer.valueOf(prop.get(2)), 0, 1, 0,
                Integer.valueOf(prop.get(3)), Integer.valueOf(prop.get(4)), Integer.valueOf(prop.get(5)),
                Integer.valueOf(prop.get(6)));
    }

    @Override
    public Product creatProduct() {
        double random = Math.random();
        if (random < 0.33) return creatWarrior();
        else if (random < 0.66) return creatSorcerer();
        else return creatPaladin();
    }

    @Override
    public Product createProduct(int num) {
        return null;
    }

    /**
     * Return an instance of HeroFactory
     * @return heroFactory if the instance is null
     */
    public static HeroFactory getInstance() {
        if (heroFactory == null) return new HeroFactory();
        else return heroFactory;
    }
}
