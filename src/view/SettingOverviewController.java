package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import model.Setting;
import source.MainApp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Samsung on 22.05.2017.
 */
public class SettingOverviewController implements Initializable {
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
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        infoColumn.setCellValueFactory(cellData -> cellData.getValue().infoProperty());
        showSettingDetails(null);
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSettingDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        personTable.setItems(mainApp.getPersonData().getSetting());
    }

    private void showSettingDetails(Setting setting) {
        try {
            FXMLLoader loader = new FXMLLoader();
            if (setting != null) {
                loader.setLocation(MainApp.class.getResource(setting.getView()));
            } else {
                loader.setLocation(MainApp.class.getResource("../view/EmptyOverview.fxml"));
            }
            AnchorPane settingOverview = loader.load();
            splitPane.getItems().set(1, settingOverview);
            if (setting != null) {
                OverviewController controller = loader.getController();
                controller.setParentController(this);
                controller.setMainStage(mainApp.getPrimaryStage());
                controller.showSetting(setting);
            }
        } catch (IOException e) {
            e.printStackTrace();
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