package model;

import java.time.LocalDate;

/**
 * Created by Samsung on 23.05.2017.
 */
public class PlainPerson implements PlainSetting {
    static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private String street;
    private Integer postalCode;
    private String city;
    private LocalDate birthday;

    public PlainPerson(Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.street = person.getStreet();
        this.postalCode = person.getPostalCode();
        this.city = person.getCity();
        this.birthday = person.getBirthday();
    }

    @Override
    public Setting getSetting() {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setStreet(street);
        person.setPostalCode(postalCode);
        person.setCity(city);
        person.setBirthday(birthday);
        return person;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
