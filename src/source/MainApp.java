package source; /**
 * Created by Samsung on 22.05.2017.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Person;
import model.Setting;
import model.SettingListWrapper;
import view.RootLayoutController;

import java.io.IOException;

public class MainApp extends Application {

    public static Stage primaryStage;
    private BorderPane rootLayout;

    public static boolean isSplashLoaded = false;
    public static SettingListWrapper settingData = new SettingListWrapper();

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

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(MainApp.class.getResource("../view/RootLayout.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("SettingApp");
        stage.getIcons().add(new Image("file:resources/images/setting_icon.png"));
        stage.show();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            RootLayoutController controller = loader.getController();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        settingData.load();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
