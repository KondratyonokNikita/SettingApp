package model;

import javafx.beans.property.*;
import utils.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Created by Samsung on 22.05.2017.
 */
public class Person implements Setting {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty street;
    private final IntegerProperty postalCode;
    private final StringProperty city;
    private final ObjectProperty<LocalDate> birthday;

    public Person() {
        this(null, null);
    }

    public Person(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        this.street = new SimpleStringProperty("какая-то улица");
        this.postalCode = new SimpleIntegerProperty(1234);
        this.city = new SimpleStringProperty("какой-то город");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public PlainSetting getPlain() {
        return new PlainPerson(this);
    }

    public StringProperty nameProperty() {
        return firstName;
    }

    public StringProperty infoProperty() {
        return lastName;
    }

    @Override
    public String getView() {
        return "../view/person/PersonOverview.fxml";
    }

    @XmlAttribute
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public int getPostalCode() {
        return postalCode.get();
    }

    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }
}