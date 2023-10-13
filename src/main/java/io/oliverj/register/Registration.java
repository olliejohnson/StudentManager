package io.oliverj.register;

import java.util.Date;
import java.util.UUID;

public class Registration {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private UUID uuid;


    /**
     * This class allows you to create a registration object.
     * To Use it you create an instance of the registration class.
     * To use you {@link RegistrationDatabase}
     * to add registration to the database
     * @see RegistrationDatabase
     *
     * @author olliejohnson
     * @version 1.1
     */
    public Registration(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.uuid = UUID.randomUUID();
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String toString() {
        return String.format("%s:%s:%s",uuid,firstName,lastName);
    }

}
