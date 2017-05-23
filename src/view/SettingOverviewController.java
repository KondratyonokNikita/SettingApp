package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import model.Setting;
import source.MainApp;

import java.io.IOException;

/**
 * Created by Samsung on 22.05.2017.
 */
public class SettingOverviewController {
    @FXML
    private TableView<Setting> personTable;
    @FXML
    private TableColumn<Setting, String> nameColumn;
    @FXML
    private TableColumn<Setting, String> infoColumn;
    @FXML
    private SplitPane splitPane;
    private MainApp mainApp;

    public SettingOverviewController() {
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        infoColumn.setCellValueFactory(cellData -> cellData.getValue().infoProperty());
        showSettingDetails(null);
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSettingDetails(newValue));
        Platform.runLater(() -> {
            personTable.requestFocus();
            personTable.getSelectionModel().select(0);
            personTable.getFocusModel().focus(0);
        });
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        personTable.setItems(mainApp.getPersonData().getPersons());
    }

    private void showSettingDetails(Setting setting) {
        if (setting == null)
            return;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(setting.getView()));
            AnchorPane personOverview = loader.load();
            splitPane.getItems().set(1, personOverview);
            OverviewController controller = loader.getController();
            controller.setMainApp(mainApp);
            controller.showSetting(setting);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}