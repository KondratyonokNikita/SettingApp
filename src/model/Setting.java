package model;

import javafx.beans.property.StringProperty;

/**
 * Created by Samsung on 22.05.2017.
 */
public interface Setting {
    StringProperty nameProperty();
    StringProperty infoProperty();
    String getView();
}
