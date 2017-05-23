package source; /**
 * Created by Samsung on 22.05.2017.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Person;
import model.Setting;
import model.SettingListWrapper;
import view.RootLayoutController;
import view.SettingOverviewController;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private SettingListWrapper settingData = new SettingListWrapper();

    public MainApp() {
        ObservableList<Setting> temp = FXCollections.observableArrayList();
        temp.add(new Person("Hans", "Muster"));
        temp.add(new Person("Ruth", "Mueller"));
        temp.add(new Person("Heinz", "Kurz"));
        settingData.setSetting(temp);
    }

    public SettingListWrapper getPersonData() {
        return settingData;
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SettingApp");
        this.primaryStage.getIcons().add(new Image("file:resources/images/setting_icon.png"));
        initRootLayout();
        showSettingOverview();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            RootLayoutController controller = loader.getController();
            controller.setStorage(settingData);
            controller.setMainApp(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        settingData.load();
    }

    public void showSettingOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/SettingOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(personOverview);
            SettingOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
