package web;

import org.joda.time.Duration;
import org.openqa.selenium.By;
import selenium.Web;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by aleksandrs on 10/09/2017.
 */
public class EmailBoxPage {

    private static final By EMAIL_TEXT = By.xpath("//*[@id='fe_text']");
    private static final By RECEIVED_EMAIL = By.xpath("//*[@title='Sports Direct Latvia - Forgotten Password']");
    private static final By FORGOTTEN_PASSWORD_LINK = By.id("//*[contains(text(),'sportsdirect.com/Login/PasswordReset')]");
    private static final By EMAIL_INPUT_FIELD = By.id("imapuser");
    private static final By PASSWORD_INPUT_FIELD = By.id("pass");
    private static final By SUBMIT_BUTTON = By.id("btn_sign-in");

    public String email;
    public String password;
    Web web;


    /**
     * Emailbox page is loaded.
     */
    public EmailBoxPage(Web web) {
        this.web = web;
    }

    /**
     * method saves email value
     */

    public void saveEmail() throws Exception {
        web.waitUntil(visibilityOfElementLocated(EMAIL_TEXT));
        email = web.findElement(EMAIL_TEXT).getAttribute("value");
    }

    /**
     * method returns email value
     */

    public String getEmail() {
        return email;
    }

    /**
     * method  clicks on received email when it is received.
     */
    public void openReceivedEmail() {
        web.waitUntil(visibilityOfElementLocated(RECEIVED_EMAIL), Duration.standardSeconds(500), false);
        web.click(RECEIVED_EMAIL);
    }


    /**
     * method opens link in the received email
     */

    public void openReceivedPasswordConfirmationLetter() {
        web.waitUntil(visibilityOfElementLocated(FORGOTTEN_PASSWORD_LINK));
        web.click(FORGOTTEN_PASSWORD_LINK);
    }

    /**
     * method types email to email input field
     *
     * @param socialEmail - user email
     */
    public void inputEmail(String socialEmail) {
        web.waitUntil(visibilityOfElementLocated(EMAIL_INPUT_FIELD));
        web.type(EMAIL_INPUT_FIELD, socialEmail);
    }


    /**
     * method types password to password input field
     *
     * @param socialPassword - user email
     */
    public void inputPassword(String socialPassword) {
        this.password = socialPassword;
        web.waitUntil(visibilityOfElementLocated(PASSWORD_INPUT_FIELD));
        web.type(PASSWORD_INPUT_FIELD, socialPassword);
    }


    /**
     * method clicks on submit button
     */
    public void submitButton() {
        web.click(SUBMIT_BUTTON);
    }

}