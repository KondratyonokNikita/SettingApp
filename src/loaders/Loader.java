package loaders;

import model.SettingListWrapper;

import java.io.File;

/**
 * Created by Samsung on 23.05.2017.
 */
public interface Loader {
    String load(File file, SettingListWrapper storage);
    String load(SettingListWrapper storage);
}
