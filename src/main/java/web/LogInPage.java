package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.Web;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


/**
 * Created by aleksandrs on 09/09/2017.
 */
public class LogInPage {
    Web web;
    private static final By CONTINUE_BUTTON = By.id("dnn_ctr42569657_LoginScreen_cmdRegister");
    private static final By PASSWORD_RECOVERY_BUTTON = By.id("dnn_ctr42569657_LoginScreen_registerLogin_cmdForgottenPassword");
    private static final By EMAIL_INPUT_FIELD = By.id("dnn_ctr42569657_LoginScreen_registerLogin_txtExistingCustomerEmailAddress");
    private static final By PASSWORD_INPUT_FIELD = By.id("dnn_ctr42569657_LoginScreen_registerLogin_txtPassword");
    private static final By SIGNUP_BUTTON = By.id("dnn_dnnLOGIN_loginLink");
    private static final By LOGIN_BUTTON = By.id("dnn_ctr42569657_LoginScreen_registerLogin_btnRegisteredCustomer");

    public LogInPage(Web web) {
        this.web = web;
    }

    /**
     * method allows to open sign up screen
     */
    public void openSignUp() {
        web.waitUntil(visibilityOfElementLocated(CONTINUE_BUTTON));
        web.click(CONTINUE_BUTTON);
    }

    /**
     * method opens log in screen and passwordRecovery opens password recovery screen
     */
    public void openPasswordRecovery() {
        web.waitUntil(visibilityOfElementLocated(SIGNUP_BUTTON));
        web.click(SIGNUP_BUTTON);
        web.waitUntil(visibilityOfElementLocated(PASSWORD_RECOVERY_BUTTON));
        web.click(PASSWORD_RECOVERY_BUTTON);
    }

    /**
     * method types email to log in screen email input field
     *
     * @param email - email
     */
    public void inputEmail(String email) {
        web.waitUntil(visibilityOfElementLocated(EMAIL_INPUT_FIELD));
        WebElement usernameInputField = web.findElement(EMAIL_INPUT_FIELD);
        usernameInputField.clear();
        usernameInputField.sendKeys(email);
    }

    /**
     * method types password to log in screen password input field
     *
     * @param password - password
     */
    public void inputPassword(String password) {
        WebElement passwordInputField = web.findElement(PASSWORD_INPUT_FIELD);
        passwordInputField.clear();
        passwordInputField.sendKeys(password);
    }

    /**
     * method makes a click on log in button
     */
    public void logIn() {
        web.waitUntil(visibilityOfElementLocated(LOGIN_BUTTON));
        web.click(LOGIN_BUTTON);
    }
}
