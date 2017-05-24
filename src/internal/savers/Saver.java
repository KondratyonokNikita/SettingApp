package internal.savers;

import internal.model.SettingListWrapper;

import java.io.File;
import java.io.Serializable;

/**
 * Created by Samsung on 23.05.2017.
 */
public interface Saver extends Serializable {
    String save(File file, SettingListWrapper storage);
    String save(SettingListWrapper storage);
}
