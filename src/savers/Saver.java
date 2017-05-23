package savers;

import model.SettingListWrapper;

import java.io.File;

/**
 * Created by Samsung on 23.05.2017.
 */
public interface Saver {
    String save(File file, SettingListWrapper storage);
    String save(SettingListWrapper storage);
}
