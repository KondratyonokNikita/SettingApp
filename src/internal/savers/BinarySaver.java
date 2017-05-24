package internal.savers;

import internal.model.PlainSetting;
import internal.model.Setting;
import internal.model.SettingListWrapper;
import internal.utils.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samsung on 23.05.2017.
 */
public class BinarySaver implements Saver {
    @Override
    public String save(File file, SettingListWrapper storage) {
        try {
            List<PlainSetting> plain = new ArrayList<>();
            for (Setting setting : storage.getSetting()) {
                PlainSetting plainSetting = setting.getPlain();
                if (plainSetting != null) {
                    plain.add(setting.getPlain());
                }
            }
            FileOutputStream f_out = new FileOutputStream(file);
            ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
            obj_out.writeObject(plain);
            obj_out.close();
            return "Successfully saved to file:\n" + file.getAbsolutePath();
        } catch (Exception e) {
            return "Could not save data to file:\n" + file.getPath() + "\nCause: \n" + e.toString();
        }
    }

    @Override
    public String save(SettingListWrapper storage) {
        File file = Util.getSettingFilePath();
        if (file != null) {
            save(file, storage);
            return "Successfully saved to file:\n" + file.getAbsolutePath();
        } else {
            return "Need to save as first";
        }
    }
}
