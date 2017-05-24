package internal; /**
 * Created by Samsung on 22.05.2017.
 */

import internal.model.SettingListWrapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MainApp extends Application {

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

    public static void main(String[] args) {
        try {
            MainApp.properties = new Properties();
            File file = new File("test.properties");
            FileInputStream fileInput = new FileInputStream(file);
            properties.load(fileInput);
            fileInput.close();

            String value = properties.getProperty("favoriteAnimal");
            System.out.println(value);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        // Получаем имя класса из файла builder.properties
//        PropertyResourceBundle pr = (PropertyResourceBundle)
//                PropertyResourceBundle.getBundle("application", ResourceBundle.Control.getControl(FORMAT_PROPERTIES));
//        String className = pr.getString(BUILDER_CLASS);
//        System.out.println(className);
//        //launch(args);
    }
}
