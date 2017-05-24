package internal.view;

import internal.MainApp;
import internal.loaders.BinaryLoader;
import internal.loaders.DOMLoader;
import internal.loaders.JAXBLoader;
import internal.loaders.SAXLoader;
import internal.savers.BinarySaver;
import internal.savers.JAXBSaver;
import internal.utils.Util;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Samsung on 22.05.2017.
 */
public class RootLayoutController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private void handleDOMLoad() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(MainApp.primaryStage);
        if (file != null) {
            MainApp.settingData.setLoader(new DOMLoader());
            String message = MainApp.settingData.load(file);
            Util.showAlert("Open dialog", message, MainApp.primaryStage, Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void handleSAXLoad() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(MainApp.primaryStage);
        if (file != null) {
            MainApp.settingData.setLoader(new SAXLoader());
            String message = MainApp.settingData.load(file);
            Util.showAlert("Open dialog", message, MainApp.primaryStage, Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void handleBinLoad() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(MainApp.primaryStage);
        if (file != null) {
            MainApp.settingData.setLoader(new BinaryLoader());
            String message = MainApp.settingData.load(file);
            Util.showAlert("Open dialog", message, MainApp.primaryStage, Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void handleBinSave() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Bin files (*.bin)", "*.bin");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(MainApp.primaryStage);
        if (file != null) {
            if (!file.getPath().endsWith(".bin")) {
                file = new File(file.getPath() + ".bin");
            }
            MainApp.settingData.setSaver(new BinarySaver());
            String message = MainApp.settingData.save(file);
            Util.showAlert("Save dialog", message, MainApp.primaryStage, Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(MainApp.primaryStage);
        if (file != null) {
            MainApp.settingData.setLoader(new JAXBLoader());
            String message = MainApp.settingData.load(file);
            Util.showAlert("Open dialog", message, MainApp.primaryStage, Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void handleSave() {
        MainApp.settingData.setSaver(new JAXBSaver());
        String message = MainApp.settingData.save();
        Util.showAlert("Save dialog", message, MainApp.primaryStage, Alert.AlertType.INFORMATION);
    }

    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(MainApp.primaryStage);
        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            MainApp.settingData.setSaver(new JAXBSaver());
            String message = MainApp.settingData.save(file);
            Util.showAlert("Save dialog", message, MainApp.primaryStage, Alert.AlertType.INFORMATION);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!MainApp.isSplashLoaded) {
            loadSplashScreen();
        }
    }

    private void loadSplashScreen() {
        try {
            MainApp.isSplashLoaded = true;

            StackPane pane = FXMLLoader.load(getClass().getResource("fxml/SplashFXML.fxml"));
            root.setCenter(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource("fxml/SettingOverview.fxml"));
                    root.setCenter(parentContent);
                    MainApp.settingData.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}