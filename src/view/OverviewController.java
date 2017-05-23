package view;

import javafx.stage.Stage;
import model.Setting;

/**
 * Created by Samsung on 23.05.2017.
 */
public interface OverviewController {
    void showSetting(Setting setting);

    void setParentController(SettingOverviewController controller);

    void setMainStage(Stage stage);
}
