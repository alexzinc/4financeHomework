package stepdefs.PasswordRecovery;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import selenium.Web;
import user.TreatmentType;
import user.User;
import web.*;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by aleksandrs on 10/09/2017.
 */
public class PasswordRecoveryStepdef {
    Web web = new Web();
    User user = new User();
    EmailBoxPage emailBoxPage;
    InboxLvEmailPage inboxLvEmailPage;
    SignUpPage signUpPage;
    PasswordRecoveryPage passwordRecoveryPage;
    LogInPage logInPage;
    MainPage mainPage;
    InternalWebPage internalWebPage;
    NewPasswordPage newPasswordPage;
    HomePage homePage;


    @Given("user opens (.*) page")
    public void openHomePage(final String url) {
        web.open(url);
    }

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
        mainPage.openLoginScreen();
        logInPage = new LogInPage(web);
        logInPage.openSignUp();
        signUpPage = new SignUpPage(web, emailBoxPage);
        signUpPage.inputEmail(credentials.get("email"));
        signUpPage.inputRepeatEmail(credentials.get("repeat email"));
        signUpPage.inputTitle(TreatmentType.valueOf(credentials.get("title").toUpperCase()));
        signUpPage.inputFirstname(credentials.get("firstname").equalsIgnoreCase("*generated*") ? "AUTO" + randomAlphabeticPart + "" : credentials.get("firstname"));
        signUpPage.inputLastname(credentials.get("lastname").equalsIgnoreCase("*generated*") ? "Smith " + randomAlphabeticPart + "" : credentials.get("lastname"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - Integer.parseInt(credentials.get("age")));
        signUpPage.selectBirthDay(calendar.get(Calendar.DAY_OF_MONTH));
        signUpPage.selectBirthMonth(calendar.get(Calendar.MONTH));
        signUpPage.selectBirthYear(calendar.get(Calendar.YEAR));
        signUpPage.inputPassowrd(credentials.get("password").equalsIgnoreCase("*default*") ? Web.DEFAULT_PASSWORD : credentials.get("password"));
        signUpPage.inputPasswordConfirmation(credentials.get("password").equalsIgnoreCase("*default*") ? Web.DEFAULT_PASSWORD : credentials.get("password"));
        signUpPage.signUp();
    }

    @When("user logs out")
    public void logOut() {
        internalWebPage.closeDialog();
        mainPage.logout();
    }

//    @When("user recovers his password")
//    public void recoverPassword() throws Exception {
//
//    }

    @Then("user should be able to set his new password to following:")
    public void forgottenPasswordIsReceived(DataTable rawPassword) throws Exception {
        Map<String, String> credentials = rawPassword.asMap(String.class, String.class);
        String randomAlphabeticPart = RandomStringUtils.randomAlphabetic(7);
        web.pause(3000);
        web.switchToTabNumber(1);
        newPasswordPage = new NewPasswordPage(web);
        newPasswordPage.inputNewPassword(credentials.get("password").equalsIgnoreCase("*generated*") ? "Test" + randomAlphabeticPart + "" : credentials.get("password"));
        newPasswordPage.inputNewPasswordConfirmation(credentials.get("password").equalsIgnoreCase("*generated*") ? "Test" + randomAlphabeticPart + "" : credentials.get("password"));
        newPasswordPage.passwordConfirmation();
        newPasswordPage.continueButton();
    }

    @When("user is able to log in")
    public void logIn() throws Exception {
        logInPage.inputEmail(emailBoxPage.getEmail());
        logInPage.inputPassword(newPasswordPage.getPassword());
    }


    @When("user opens received email in (.*)")
    public void openEmail(String emailProvider) {

        switch (emailProvider) {
            case "10minutemail.info":
                emailBoxPage.openReceivedEmail();
                emailBoxPage.openReceivedPasswordConfirmationLetter();

                break;
            case "inbox.lv":
                inboxLvEmailPage = new InboxLvEmailPage(web);
                inboxLvEmailPage.openReceivedEmail();
                inboxLvEmailPage.openLink();
        }
    }

    @When("user recovers his password to: (.*)")
    public void recoverPasswordTo(String rawEmail) {
        internalWebPage = new InternalWebPage(web);
        internalWebPage.closeDialog();
        mainPage = new MainPage(web);
        mainPage.openLoginScreen();
        logInPage = new LogInPage(web);
        logInPage.openPasswordRecovery();
        switch (rawEmail) {
            case "10minutemail.info":
                passwordRecoveryPage = new PasswordRecoveryPage(web, emailBoxPage);
                passwordRecoveryPage.recoverPassword(emailBoxPage.getEmail());
                assert passwordRecoveryPage.confirmationMessageIsDisplayed();
                break;
            case "inbox.lv":
                passwordRecoveryPage = new PasswordRecoveryPage(web, emailBoxPage);
                passwordRecoveryPage.inputEmail(user.email());
                passwordRecoveryPage.confirmationMessageIsDisplayed();
                break;
        }
    }

    @When("user logs in to (.*) via login: (.*) and password: (.*)")
    public void logInToThirdPartyEmail(String emailProvider, String socialEmail, String socialPassword) {
        web.open(emailProvider);
        emailBoxPage = new EmailBoxPage(web);
        emailBoxPage.inputEmail(socialEmail);
        emailBoxPage.inputPassword(socialPassword);
        emailBoxPage.submitButton();
    }

    @When("user logs in with login: (.*) and new password")
    public void login(String email) {
        internalWebPage = new InternalWebPage(web);
        internalWebPage.closeDialog();
        mainPage = new MainPage(web);
        mainPage.openLoginScreen();
        logInPage = new LogInPage(web);
        logInPage.inputEmail(email);
        logInPage.inputPassword(newPasswordPage.getPassword());
        logInPage.logIn();
    }

    @Then("user is logged in")
    public void homePageIsDisplayed() {
        homePage = new HomePage(web);
        assert homePage.isDisplayed();
    }
}
