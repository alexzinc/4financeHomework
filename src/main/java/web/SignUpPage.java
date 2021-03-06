package web;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.Web;
import user.TreatmentType;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by aleksandrs on 09/09/2017.
 */
public class SignUpPage {
    Web web;
    EmailBoxPage emailBoxPage;


    /**
     * Sign up page and Email box page are loaded
     */
    public SignUpPage(Web web, EmailBoxPage emailBoxPage) {
        this.web = web;
        this.emailBoxPage = emailBoxPage;
    }

    private static final By EMAIL_INPUT_FIELD = By.id("dnn_ctr42569679_View_txtEmailAddress");
    private static final By RETYPE_EMAIL_INPUT_FIELD = By.id("dnn_ctr42569679_View_txtEmailAddressValidate");
    private static final By TREATMENT_TYPE_INPUT_FIELD = By.id("dnn_ctr42569679_View_ddlTitle");
    private static final By FIRSTNAME_INPUT_FIELD = By.id("dnn_ctr42569679_View_txtFirstName");
    private static final By LASTNAME_INPUT_FIELD = By.id("dnn_ctr42569679_View_txtLastName");
    private static final By INPUT_DAY_OF_BIRTH = By.id("dnn_ctr42569679_View_dobEntry_ddlDay");
    private static final By INPUT_MONTH_OF_BIRTH = By.id("dnn_ctr42569679_View_dobEntry_ddlMonth");
    private static final By INPUT_YEAR_OF_BIRTH = By.id("dnn_ctr42569679_View_dobEntry_ddlYear");
    private static final By PASSWORD_INPUT_FIELD = By.id("dnn_ctr42569679_View_txtPassword");
    private static final By PASSWORD_CONFIRMATION_INPUT_FIELD = By.id("dnn_ctr42569679_View_txtConfirm");
    private static final By SIGN_UP_BUTTON = By.id("dnn_ctr42569679_View_cmdRegister");


    /**
     * method allows to input email
     *
     * @param email - email
     */
    public void inputEmail(String email) {
        web.waitUntil(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT_FIELD));
        String getEmail = emailBoxPage.getEmail();
        web.type(EMAIL_INPUT_FIELD, email.equalsIgnoreCase("*email*") ? getEmail : email);
    }

    /**
     * method allows to input email again
     *
     * @param email - email
     */
    public void inputRepeatEmail(String email) {
        web.click(RETYPE_EMAIL_INPUT_FIELD);
        String getEmail = emailBoxPage.getEmail();
        web.type(RETYPE_EMAIL_INPUT_FIELD, email.equalsIgnoreCase("*email*") ? getEmail : email);
    }

    /**
     * method allows to select a title
     *
     * @param title - user title
     */
    public void inputTitle(TreatmentType title) {
        web.select(TREATMENT_TYPE_INPUT_FIELD).selectByValue(title.getTitle());
    }

    /**
     * method allows to select a day
     *
     * @param day - day of a month
     */
    public void selectBirthDay(int day) {
        web.select(INPUT_DAY_OF_BIRTH).selectByValue(String.valueOf(day));
    }

    /**
     * method allows to select a month
     *
     * @param month - month of the year
     */
    public void selectBirthMonth(int month) {
        web.select(INPUT_MONTH_OF_BIRTH).selectByValue(String.valueOf(month));
    }

    /**
     * method allows to set a year
     *
     * @param year - year
     */
    public void selectBirthYear(int year) {
        web.select(INPUT_YEAR_OF_BIRTH).selectByValue(String.valueOf(year));
    }

    /**
     * method allows to set users firstName
     *
     * @param firstname - users firstName
     */
    public void inputFirstname(String firstname) {
        web.click(FIRSTNAME_INPUT_FIELD);
        web.type(FIRSTNAME_INPUT_FIELD, firstname);
    }

    /**
     * method allows to set users lastName
     *
     * @param lastname - users lastName
     */
    public void inputLastname(String lastname) {
        web.click(LASTNAME_INPUT_FIELD);
        web.type(LASTNAME_INPUT_FIELD, lastname);
    }

    /**
     * method allows user to set his password
     *
     * @param password - user password
     */
    public void inputPassowrd(String password) {
        web.click(PASSWORD_INPUT_FIELD);
        web.type(PASSWORD_INPUT_FIELD, password);
    }

    /**
     * method allows user to set his password again
     *
     * @param password - user password
     */
    public void inputPasswordConfirmation(String password) {
        web.click(PASSWORD_CONFIRMATION_INPUT_FIELD);
        web.type(PASSWORD_CONFIRMATION_INPUT_FIELD, password);
    }

    /**
     * user is able to click to sign up his account
     */
    public void signUp() {
        web.waitUntil(visibilityOfElementLocated(SIGN_UP_BUTTON));
        web.click(SIGN_UP_BUTTON);
    }
}
