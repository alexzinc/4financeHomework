package user;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import selenium.Web;

/**
 * Created by aleksandrs on 10/09/2017.
 */
public class UserGenerator {

    public void generateUser(String userAlias) {
        User user = new User();
        int age = Web.DEFAULT_AGE;

//        user.get
//        user.getFirstName(user) = generateUser();
//        user.getLastName() = "AUTO " + RandomStringUtils.randomAlphabetic(8) + "";
//        user.getPassword() = Web.DEFAULT_PASSWORD;
//        user.getEmail() = "agent.smith.${user.username.substring(4)}@matrix.com";
//        user.getBirthDate() = calculateBirthDate(age);
//        StaticContext.storeUser(userAlias, user);
    }

    public DateTime calculateBirthDate(int age) {
        DateTime now = new DateTime();
        return now.minusYears(age);
    }

    public String generateUser() {
        return "AUTO " + RandomStringUtils.randomAlphanumeric(10) + "";
    }
}

