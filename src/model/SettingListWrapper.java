package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import loaders.Loader;
import loaders.MyLoader;
import savers.MySaver;
import savers.Saver;
import source.MainApp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.File;
import java.util.prefs.Preferences;

/**
 * Created by Samsung on 22.05.2017.
 */
@XmlRootElement(name = "persons")
public class SettingListWrapper {

    private ObservableList<Setting> storage;

    @XmlTransient
    private Saver saver;
    @XmlTransient
    private Loader loader;

    public SettingListWrapper() {
        storage  = FXCollections.observableArrayList();
        saver = new MySaver();
        loader = new MyLoader();
    }

    @XmlElement(name = "person")
    public ObservableList<Setting> getPersons() {
        return storage;
    }

    public void setPersons(ObservableList<Setting> storage) {
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