package utils;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import source.MainApp;

import java.io.File;
import java.util.prefs.Preferences;

/**
 * Created by Samsung on 22.05.2017.
 */
public class Util {
    public static File getSettingFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public static void setSettingFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
        } else {
            prefs.remove("filePath");
        }
    }

    public static void showAlert(String title, String message, Stage parent, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.initOwner(parent);
        alert.setTitle("Message");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
