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

    EmailBoxPage emailBoxPage;

    public PasswordRecoveryPage(Web web, EmailBoxPage emailBoxPage) {
        this.web = web;
        this.emailBoxPage = emailBoxPage;
    }


    public void recoverPassword() throws Exception {
        web.waitUntil(visibilityOfElementLocated(PASSWORD_RECOVERY_INPUT_FIELD));
        web.click(PASSWORD_RECOVERY_INPUT_FIELD);
        web.type(PASSWORD_RECOVERY_INPUT_FIELD, emailBoxPage.getEmail());
        web.click(PASSWORD_RECOVERY_SUBMIT_BUTTON);
    }
}
