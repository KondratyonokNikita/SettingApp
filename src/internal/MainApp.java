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

public class MainApp extends Application {

    public static Stage primaryStage;

    public static boolean isSplashLoaded = false;
    public static SettingListWrapper settingData = new SettingListWrapper();

    /*public MainApp() {
        ObservableList<Setting> temp = FXCollections.observableArrayList();
        temp.add(new Person("Hans", "Muster"));
        temp.add(new Person("Ruth", "Mueller"));
        temp.add(new Person("Heinz", "Kurz"));
        settingData.setSetting(temp);
    }*/

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/fxml/RootLayout.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("SettingApp");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("view/fxml/setting_icon.png"))));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
