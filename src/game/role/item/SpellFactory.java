package game.role.item;

import game.Product;
import game.role.RoleFactory;
import game.utils.fileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @projectName: monstersAndHerios
 * @package: game.role.item
 * @className: spellFactory
 * @author: Danny
 * @description: Spell factory to create spells
 * @date: 2022/11/7 11:15
 * @version: 1.0
 */
public class SpellFactory extends RoleFactory {

    private static SpellFactory spellFactory;

    private final String icePath = "IceSpells.txt";

    private final String firePath = "FireSpells.txt";

    private final String lighteningPath = "LightningSpells.txt";

    private List<List<String>> iceInfo;

    private List<List<String>> fireInfo;

    private List<List<String>> lightningInfo;

    private SpellFactory() {
        iceInfo = readInfo(icePath);
        fireInfo = readInfo(firePath);
        lightningInfo = readInfo(lighteningPath);
    }

    private List<List<String>> readInfo(String path) {
//        Name/cost/required level/damage/mana cost
        List<String> allIce = fileReader.readFile(path);
        List<List<String>> info = new ArrayList<>();
        for (int i = 1; i < allIce.size(); i++) {
            if (!allIce.get(i).equals("")) info.add(Arrays.asList(allIce.get(i).split(" ")));
        }
        return info;
    }

    @Override
    public Product creatProduct() {
        int size = iceInfo.size() + fireInfo.size() + lightningInfo.size();
        int random = (int) (Math.random() * size);
        int i = 0;
        Product spell = null;
        List<String> prop = new ArrayList<>();
        while (i < size) {
            if (i < iceInfo.size()) {
                if (i == random) {
                    for (String s : iceInfo.get(i)) if (!s.equals("")) prop.add(s);
                    spell = new iceSpell(prop.get(0), Integer.valueOf(prop.get(1)), Integer.valueOf(prop.get(2)),
                            Integer.valueOf(prop.get(3)), Integer.valueOf(prop.get(4)));
                }
            } else if (i < iceInfo.size() + fireInfo.size()) {
                if (i == random) {
                    int idx = i  - iceInfo.size();
                    for (String s: fireInfo.get(idx)) if (!s.equals("")) prop.add(s);
                    spell = new fireSpell(prop.get(0), Integer.valueOf(prop.get(1)), Integer.valueOf(prop.get(2)),
                            Integer.valueOf(prop.get(3)), Integer.valueOf(prop.get(4)));
                }
            } else if (i == random) {
                int idx = i - iceInfo.size() - fireInfo.size();
                for (String s: fireInfo.get(idx)) if (!s.equals("")) prop.add(s);
                spell = new lingtningSpell(prop.get(0), Integer.valueOf(prop.get(1)), Integer.valueOf(prop.get(2)),
                        Integer.valueOf(prop.get(3)), Integer.valueOf(prop.get(4)));
            }
            i++;
        }
        return spell;
    }

    /**
     * getInstance of the ItemFactory
     */
    public static SpellFactory getInstance() {
        if (spellFactory == null) return new SpellFactory();
        else return spellFactory;
    }
}
