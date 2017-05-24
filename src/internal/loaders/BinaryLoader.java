package internal.loaders;

import internal.model.PlainSetting;
import internal.model.Setting;
import internal.model.SettingListWrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by Samsung on 23.05.2017.
 */
public class BinaryLoader implements Loader {
    @Override
    public String load(File file, SettingListWrapper storage) {
        try {
            FileInputStream f_in = new FileInputStream(file);
            ObjectInputStream obj_in = new ObjectInputStream(f_in);
            Object obj = obj_in.readObject();
            if (obj instanceof List) {
                storage.getSetting().clear();
                List<PlainSetting> loaded = (List<PlainSetting>) obj;
                for (PlainSetting setting : loaded) {
                    Setting temp = setting.getSetting();
                    storage.getSetting().add(temp);
                }
            }
            return "Successfully loaded data from file:\n" + file.getAbsolutePath();
        } catch (Exception e) {
            return "Could not load data from file:\n" + file.getPath();
        }
    }
}
