package game.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: monstersAndHerios
 * @package: game.utils
 * @className: fileReader
 * @author: Danny
 * @description: TODO
 * @date: 2022/11/4 21:57
 * @version: 1.0
 */
public class fileReader {

    private static String dir = System.getProperty("user.dir") + "/configs/";

    public static List<String> readFile(String fileName) {
        String filePath = dir + fileName;
        List<String> res = new ArrayList<>();
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                res.add(line);
            }
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

}
