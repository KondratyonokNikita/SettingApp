package loaders;

import model.Person;
import model.Setting;

/**
 * Created by Samsung on 23.05.2017.
 */
public class Switcher {
    public static Setting getObject(String type) {
        Setting setting = null;
        switch (type) {
            case "person":
                setting = new Person();
                break;
        }
        return setting;
    }
}
