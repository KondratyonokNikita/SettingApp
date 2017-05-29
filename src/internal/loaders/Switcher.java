package internal.loaders;

import internal.MainApp;
import internal.model.Setting;

/**
 * Created by Samsung on 23.05.2017.
 */
public class Switcher {
    public static Setting getObject(String type) {
        try {
            Class aClass = Class.forName(MainApp.properties.getProperty(type));
            System.out.println(aClass.getName());
            Setting setting = (Setting) aClass.newInstance();
            return setting;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
