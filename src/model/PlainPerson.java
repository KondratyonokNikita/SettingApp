package model;

import java.time.LocalDate;

/**
 * Created by Samsung on 23.05.2017.
 */
public class PlainPerson implements PlainSetting {
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
}
