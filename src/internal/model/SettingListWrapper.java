package internal.model;

import internal.loaders.JAXBLoader;
import internal.loaders.Loader;
import internal.savers.JAXBSaver;
import internal.savers.Saver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import person.Person;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.io.File;
import java.io.Serializable;

/**
 * Created by Samsung on 22.05.2017.
 */
@XmlRootElement(name = "settings")
@XmlSeeAlso({Person.class})
public class SettingListWrapper implements Serializable {

    private ObservableList<Setting> storage;

    @XmlTransient
    private Saver saver;
    @XmlTransient
    private Loader loader;

    public void setLoader(Loader loader) {
        this.loader = loader;
    }

    public void setSaver(Saver saver) {
        this.saver = saver;
    }

    public SettingListWrapper() {
        storage  = FXCollections.observableArrayList();
        saver = new JAXBSaver();
        loader = new JAXBLoader();
    }

    @XmlElement(name = "setting", type = Object.class)
    public ObservableList<Setting> getSetting() {
        return storage;
    }

    public void setSetting(ObservableList<Setting> storage) {
        this.storage = storage;
    }

    public void clear() {
        storage.clear();
    }

    public String load(File from) {
        return loader.load(from, this);
    }

    public String load() {
        return loader.load(this);
    }

    public String save(File to) {
        return saver.save(to, this);
    }

    public String save() {
        return saver.save(this);
    }
}