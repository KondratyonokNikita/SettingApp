package loaders;

import model.Person;
import model.Setting;
import org.w3c.dom.Element;

/**
 * Created by Samsung on 23.05.2017.
 */
public class DOMSwitcher {
    public static Setting parceNode(String type, Element element) {
        Setting setting = null;
        switch (type) {
            case "person":
                setting = new Person();
                break;
        }
        setting.setNode(element);
        return setting;
    }
}
