package loaders;

import model.PlainSetting;
import model.Setting;
import model.SettingListWrapper;
import utils.Util;

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

    @Override
    public String load(SettingListWrapper storage) {
        File file = Util.getSettingFilePath();
        if (file != null) {
            return load(file, storage);
        }
        return "Cannot load latest file";
    }
}
