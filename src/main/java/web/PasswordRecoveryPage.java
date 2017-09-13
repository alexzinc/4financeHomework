package web;

import org.openqa.selenium.By;
import selenium.Web;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by aleksandrs on 10/09/2017.
 */
public class PasswordRecoveryPage {

    Web web;
    private static final By PASSWORD_RECOVERY_INPUT_FIELD = By.id("dnn_ctr42569821_PasswordReset_UserName");
    private static final By PASSWORD_RECOVERY_SUBMIT_BUTTON = By.id("dnn_ctr42569821_PasswordReset_cmdSendPassword");
    private static final By CONFIRMATION_MESSAGE = By.id("dnn_ctr42569821_ctl00_lblMessage");

    EmailBoxPage emailBoxPage;

    /**
     * Password recovery page and Email box page are loaded
     */

    public PasswordRecoveryPage(Web web, EmailBoxPage emailBoxPage) {
        this.web = web;
        this.emailBoxPage = emailBoxPage;
    }

    /**
     * method allows user to type his email
     */
    public void recoverPassword(String email) {
        web.waitUntil(visibilityOfElementLocated(PASSWORD_RECOVERY_INPUT_FIELD));
        web.click(PASSWORD_RECOVERY_INPUT_FIELD);
        web.type(PASSWORD_RECOVERY_INPUT_FIELD, email);
        web.click(PASSWORD_RECOVERY_SUBMIT_BUTTON);
    }

    /**
     * method checks if confirmation message is displayed
     */
    public boolean confirmationMessageIsDisplayed() {
        web.waitUntil(visibilityOfElementLocated(CONFIRMATION_MESSAGE));
        return web.isDisplayed(CONFIRMATION_MESSAGE);
    }

    /**
     * method allows to input email
     *
     * @param email - email for input
     */
    public void inputEmail(String email) {
        web.waitUntil(visibilityOfElementLocated(PASSWORD_RECOVERY_INPUT_FIELD));
        web.type(PASSWORD_RECOVERY_INPUT_FIELD, email);
        web.click(PASSWORD_RECOVERY_SUBMIT_BUTTON);
    }
}
