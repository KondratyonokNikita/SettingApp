package internal.savers;

import internal.model.SettingListWrapper;
import internal.utils.Util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Created by Samsung on 23.05.2017.
 */
public class JAXBSaver implements Saver {
    @Override
    public String save(File file, SettingListWrapper storage) {
        try {
            JAXBContext context = JAXBContext.newInstance(SettingListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(storage, file);
            Util.setSettingFilePath(file);
            return "Successfully saved to file:\n" + file.getAbsolutePath();
        } catch (Exception e) {
            return "Could not save data to file:\n" + file.getPath();
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
