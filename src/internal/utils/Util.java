package internal.utils;

import internal.MainApp;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static boolean validDate(String dateString) {
        return Util.parse(dateString) != null;
    }
}
