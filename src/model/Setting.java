package model;

import javafx.beans.property.StringProperty;

import java.io.Serializable;

/**
 * Created by Samsung on 22.05.2017.
 */
public interface Setting extends Serializable {
    StringProperty nameProperty();
    StringProperty infoProperty();
    String getView();

    PlainSetting getPlain();
}
