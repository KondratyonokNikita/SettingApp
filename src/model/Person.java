package model;

import javafx.beans.property.*;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import utils.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Created by Samsung on 22.05.2017.
 */
public class Person implements Setting {

    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty street;
    private IntegerProperty postalCode;
    private StringProperty city;
    private ObjectProperty<LocalDate> birthday;

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

    @Override
    public PlainSetting getPlain() {
        return new PlainPerson(this);
    }

    @Override
    public void setNode(Element element) {
        this.setFirstName(element.getAttribute("firstName"));
        this.setLastName(element.getElementsByTagName("lastName").item(0).getTextContent());
        this.setStreet(element.getElementsByTagName("street").item(0).getTextContent());
        this.setPostalCode(Integer.parseInt(element.getElementsByTagName("postalCode").item(0).getTextContent()));
        this.setCity(element.getElementsByTagName("city").item(0).getTextContent());
        this.setBirthday(LocalDate.parse(element.getElementsByTagName("birthday").item(0).getTextContent()));
    }

    @XmlTransient
    private String currentElement;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.currentElement = qName;
        if (currentElement.equalsIgnoreCase("setting")) {
            this.setFirstName(attributes.getValue("firstName"));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        this.currentElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        switch (this.currentElement) {
            case "lastName":
                this.setLastName(new String(ch, start, length));
                break;
            case "street":
                this.setStreet(new String(ch, start, length));
                break;
            case "postalCode":
                this.setPostalCode(Integer.parseInt(new String(ch, start, length)));
                break;
            case "city":
                this.setCity(new String(ch, start, length));
                break;
            case "birthday":
                this.setBirthday(LocalDate.parse(new String(ch, start, length)));
                break;
        }
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