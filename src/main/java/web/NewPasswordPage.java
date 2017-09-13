package web;

import org.openqa.selenium.By;
import selenium.Web;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by aleksandrs on 11/09/2017.
 */
public class NewPasswordPage {
    Web web;
    public String password;

    private static final By NEW_PASSWORD_INPUT_FIELD = By.id("dnn_ctr42569821_PasswordReset_txtNewPassword");
    private static final By CONFIRM_NEW_PASSWORD_INPUT_FIELD = By.id("dnn_ctr42569821_PasswordReset_txtConfirmNewPassword");
    private static final By PASSWORD_CONFIRMATION_BUTTON = By.id("dnn_ctr42569821_PasswordReset_lnkbtnChangePassword");
    private static final By PASSWORD_CHANGED_CONTINUE_BUTTON = By.id("dnn_ctr42569821_PasswordReset_lnkbtnContinue");


    /**
     * Creating new password page is loaded.
     */
    public NewPasswordPage(Web web) {
        this.web = web;
    }


    /**
     * Method fils text in the input field with users new password
     *
     * @param password - inputs new password
     */
    public void inputNewPassword(String password) {
        this.password = password;
        web.waitUntil(visibilityOfElementLocated(NEW_PASSWORD_INPUT_FIELD));
        web.click(NEW_PASSWORD_INPUT_FIELD);
        web.type(NEW_PASSWORD_INPUT_FIELD, password);
    }

    /**
     * @return saved password
     */
    public String getPassword() {
        return password;
    }


    /**
     * Method fils text in the input field  to confirm users new password
     *
     * @param password - input new password again
     */

    public void inputNewPasswordConfirmation(String password) {
        web.click(CONFIRM_NEW_PASSWORD_INPUT_FIELD);
        web.type(CONFIRM_NEW_PASSWORD_INPUT_FIELD, password);
    }


    /**
     * Method clicks on password confirmation button
     */
    public void passwordConfirmation() {
        web.click(PASSWORD_CONFIRMATION_BUTTON);
    }


    /**
     * Method clicks on Continue button
     */
    public void continueButton() {
        web.waitUntil(visibilityOfElementLocated(PASSWORD_CHANGED_CONTINUE_BUTTON));
        web.click(PASSWORD_CHANGED_CONTINUE_BUTTON);
    }
}

