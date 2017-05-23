package view.person;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Person;
import model.Setting;
import source.MainApp;
import utils.DateUtil;
import view.OverviewController;

/**
 * Created by Samsung on 23.05.2017.
 */
public class PersonOverviewController implements OverviewController {
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    private Setting setting;

    @Override
    public void showSetting(Setting setting) {
        this.setting = setting;
        Person person = (Person) setting;
        if (person != null) {
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    @Override
    public void setMainApp(MainApp mainApp) {

    }

//    @FXML
//    private void handleDeleteSetting() {
//        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
//        if (selectedIndex >= 0) {
//            personTable.getItems().remove(selectedIndex);
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
//            alert.setTitle("No Selection");
//            alert.setHeaderText("No Setting Selected");
//            alert.setContentText("Please select a person in the table.");
//
//            alert.showAndWait();
//        }
//    }

//    @FXML
//    private void handleNewSetting() {
//        Setting tempSetting = new Person();
//        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
//        if (okClicked) {
//            mainApp.getPersonData().add(tempSetting);
//        }
//    }

//    @FXML
//    private void handleEditPerson() {
//        Setting selectedPerson = setting;
//        if (selectedPerson != null) {
//            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
//            if (okClicked) {
//                showPersonDetails(selectedPerson);
//            }
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
//            alert.setTitle("No Selection");
//            alert.setHeaderText("No Person Selected");
//            alert.setContentText("Please select a person in the table.");
//
//            alert.showAndWait();
//        }
//    }

//    public boolean showPersonEditDialog(Person person) {
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
//            AnchorPane page = (AnchorPane) loader.load();
//            Stage dialogStage = new Stage();
//            dialogStage.setTitle("Edit Person");
//            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primaryStage);
//            Scene scene = new Scene(page);
//            dialogStage.setScene(scene);
//            PersonEditDialogController controller = loader.getController();
//            controller.setDialogStage(dialogStage);
//            controller.setPerson(person);
//            dialogStage.showAndWait();
//            return controller.isOkClicked();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
