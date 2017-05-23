package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import model.SettingListWrapper;
import source.MainApp;
import utils.Util;

import java.io.File;

/**
 * Created by Samsung on 22.05.2017.
 */
public class RootLayoutController {

    private SettingListWrapper storage;
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setStorage(SettingListWrapper storage) {
        this.storage = storage;
    }

    @FXML
    private void handleNew() {
        storage.clear();
    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if (file != null) {
            String message = storage.load(file);
            Util.showAlert("Open dialog", message, mainApp.getPrimaryStage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void handleSave() {
        String message = storage.save();
        Util.showAlert("Save dialog", message, mainApp.getPrimaryStage(), Alert.AlertType.INFORMATION);
    }

    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            String message = storage.save(file);
            Util.showAlert("Save dialog", message, mainApp.getPrimaryStage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SettingApp");
        alert.setHeaderText("About");
        alert.setContentText("Author: Nikita Kondratyonok\nWebsite: https://github.com/KondratyonokNikita");
        alert.showAndWait();
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}