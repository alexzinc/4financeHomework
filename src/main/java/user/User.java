package user;

import org.joda.time.DateTime;

/**
 * Created by aleksandrs on 10/09/2017.
 */
public class User {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private DateTime birthDate;

    String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    DateTime getBirthDate() {
        return birthDate;
    }

    void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User:{status:name:firstName,surname:lastName,password:$password,email:$email,birthDate:$birthDate,}}";
    }
}
