package Utils;//import java.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReading {
    private Properties prop;

    public PropertyReading(String path) {


        try {
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            prop = new Properties();
            prop.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Properties file not found");
        }
    }

    public String getValue(String key) {
        if (prop.getProperty(key) == null) {
            throw new RuntimeException();
        }
        return prop.getProperty(key);
    }
}
