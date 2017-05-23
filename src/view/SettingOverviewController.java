package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
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
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        personTable.setItems(mainApp.getPersonData().getPersons());
    }

    private void showSettingDetails(Setting setting) {
        if (setting == null) {
            splitPane.getItems().set(1, new AnchorPane());
        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource(setting.getView()));
                AnchorPane personOverview = loader.load();
                splitPane.getItems().set(1, personOverview);
                OverviewController controller = loader.getController();
                controller.setParentController(this);
                controller.setMainStage(mainApp.getPrimaryStage());
                controller.showSetting(setting);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Setting getSelectedSetting() {
        return personTable.getSelectionModel().getSelectedItem();
    }

    public void deleteSettingDetails() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Setting Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }
}