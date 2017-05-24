package internal.loaders;

import internal.model.SettingListWrapper;
import internal.utils.Util;

import java.io.File;
import java.io.Serializable;

/**
 * Created by Samsung on 23.05.2017.
 */
public interface Loader extends Serializable {
    String load(File file, SettingListWrapper storage);

    default String load(SettingListWrapper storage) {
        File file = Util.getSettingFilePath();
        if (file != null) {
            return load(file, storage);
        }
        return "Cannot load latest file";
    }
}
