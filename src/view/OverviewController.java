package view;

import model.Setting;
import source.MainApp;

/**
 * Created by Samsung on 23.05.2017.
 */
public interface OverviewController {
    void showSetting(Setting setting);

    void setMainApp(MainApp mainApp);
}
