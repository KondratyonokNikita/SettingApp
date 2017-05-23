package loaders;

import model.SettingListWrapper;
import utils.Util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Samsung on 23.05.2017.
 */
public class JAXBLoader implements Loader {

    @Override
    public String load(File file, SettingListWrapper storage) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(SettingListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            SettingListWrapper wrapper = (SettingListWrapper) um.unmarshal(file);
            storage.getSetting().clear();
            storage.getSetting().addAll(wrapper.getSetting());
            Util.setSettingFilePath(file);
            return "Successfully loaded data from file:\n" + file.getAbsolutePath();
        } catch (Exception e) {
            return "Could not load data from file:\n" + file.getPath();
        }
    }

    @Override
    public String load(SettingListWrapper storage) {
        return null;
    }
}
