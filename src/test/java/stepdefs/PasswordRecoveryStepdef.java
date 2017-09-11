package stepdefs;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import selenium.Web;
import user.TreatmentType;
import web.*;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by aleksandrs on 10/09/2017.
 */
public class PasswordRecoveryStepdef {
    Web web = new Web();
    EmailBoxPage emailBoxPage;
    SignUpPage signUpPage;
    PasswordRecoveryPage passwordRecoveryPage;
    LogInPage logInPage;
    MainPage mainPage;
    InternalWebPage internalWebPage;
//
//    @Given("user opens (.*) page")
//    public void openHomePage(final String url) throws Exception {
//        web.open(url);
//    }

    @When("user gets an email")
    public void getEmail() throws Exception {
        emailBoxPage = new EmailBoxPage(web);
        emailBoxPage.saveEmail();
        emailBoxPage.getEmail();
    }


    @When("user signs up with following credentials:")
    public void createAccount(DataTable rawUserData) throws Exception {
        Map<String, String> credentials = rawUserData.asMap(String.class, String.class);
        String randomAlphabeticPart = RandomStringUtils.randomAlphabetic(7);
        internalWebPage = new InternalWebPage(web);
        internalWebPage.closeDialog();
        mainPage = new MainPage(web);
        mainPage.openSignUp();
        signUpPage = new SignUpPage(web, emailBoxPage);
        signUpPage.inputEmail(credentials.get("email"));
        signUpPage.inputRepeatEmail(credentials.get("repeat email"));
        signUpPage.inputTitle(TreatmentType.valueOf(credentials.get("title").toUpperCase()));
        signUpPage.inputFirstname(credentials.get("firstname").equalsIgnoreCase("*generated*") ? "AUTO" + randomAlphabeticPart + "" : credentials.get("firstname"));
        signUpPage.inputLastname(credentials.get("lastname").equalsIgnoreCase("*generated*") ? "Smith " + randomAlphabeticPart + "" : credentials.get("lastname"));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - Integer.parseInt(credentials.get("age")));
        signUpPage.selectBirthDay(calendar.get(Calendar.DAY_OF_YEAR));
        signUpPage.selectBirthMonth(calendar.get(Calendar.MONTH));
        signUpPage.selectBirthYear(calendar.get(Calendar.YEAR));
        signUpPage.inputPassowrd(credentials.get("password").equalsIgnoreCase("*default*") ? Web.DEFAULT_PASSWORD : credentials.get("password"));
        signUpPage.inputPasswordConfirmation(credentials.get("password").equalsIgnoreCase("*default*") ? Web.DEFAULT_PASSWORD : credentials.get("password"));
        signUpPage.signUp();
    }

    @When("user logs out")
    public void logOut() throws Exception {
        mainPage.logout();
    }

    @When("user recovers his password")
    public void recoverPassword() throws Exception {
        passwordRecoveryPage.recoverPassword();
    }

    @Then("user should receive his forgotten password")
    public void forgottenPasswordIsReceived() throws Exception {
        emailBoxPage.getPassword();
    }

    @When("user is able to log in")
    public void logIn() throws Exception {
        logInPage.inputEmail(emailBoxPage.getEmail());
        logInPage.inputPassword(emailBoxPage.getPassword());
    }
}
