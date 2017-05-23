package loaders;

import model.SettingListWrapper;

import java.io.File;
import java.io.Serializable;

/**
 * Created by Samsung on 23.05.2017.
 */
public interface Loader extends Serializable {
    String load(File file, SettingListWrapper storage);
    String load(SettingListWrapper storage);
}
