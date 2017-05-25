package internal; /**
 * Created by Samsung on 22.05.2017.
 */

import internal.model.Setting;
import internal.model.SettingListWrapper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import person.Person;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MainApp extends Application {

    private static String propertiesName = "test.properties";
    public static Stage primaryStage;
    public static Properties properties;
    public static boolean isSplashLoaded = false;
    public static SettingListWrapper settingData = new SettingListWrapper();

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/fxml/RootLayout.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("SettingApp");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("view/fxml/setting_icon.png"))));
        stage.show();
    }

    private static void setProperties() {
        try {
            MainApp.properties = new Properties();
            File file = new File(propertiesName);
            FileInputStream fileInput = new FileInputStream(file);
            properties.load(fileInput);
            fileInput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MainApp() {
        ObservableList<Setting> temp = FXCollections.observableArrayList();
        temp.add(new Person());
        settingData.setSetting(temp);
    }

    public static void main(String[] args) {
        setProperties();
        launch(args);
    }
}
