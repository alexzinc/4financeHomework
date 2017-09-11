package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.Web;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


/**
 * Created by aleksandrs on 09/09/2017.
 */
public class LogInPage extends Web {
    Web web;
    private static final By CONTINUE_BUTTON = By.id("dnn_ctr42569657_LoginScreen_cmdRegister");
    private static final By PASSWORD_RECOVERY_BUTTON = By.id("dnn_ctr42569657_LoginScreen_registerLogin_cmdForgottenPassword");
    private static final By EMAIL_INPUT_FIELD = By.xpath("");
    private static final By PASSWORD_INPUT_FIELD = By.xpath("");


    public void createAccount() throws Exception {
        web.waitUntil(ExpectedConditions.visibilityOfElementLocated(CONTINUE_BUTTON));
        web.click(CONTINUE_BUTTON);
    }

    public void passwordRecovery() throws Exception {
        web.waitUntil(ExpectedConditions.visibilityOfElementLocated(PASSWORD_RECOVERY_BUTTON));
        web.click(PASSWORD_RECOVERY_BUTTON);
    }

    public void inputEmail(String email) throws Exception {
        web.waitUntil(visibilityOfElementLocated(EMAIL_INPUT_FIELD));
        WebElement usernameInputField = web.findElement(EMAIL_INPUT_FIELD);
        usernameInputField.clear();
        usernameInputField.sendKeys(email);
    }

    public void inputPassword(String password) throws Exception {
        WebElement passwordInputField = web.findElement(PASSWORD_INPUT_FIELD);
        passwordInputField.clear();
        passwordInputField.sendKeys(password);
    }
}
