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

    public void inputEmail(String email) throws Exception {
        web.waitUntil(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT_FIELD));
        String getEmail = emailBoxPage.getEmail();
        web.type(EMAIL_INPUT_FIELD, email.equalsIgnoreCase("*email*") ? getEmail : email);
    }

    public void inputRepeatEmail(String email) throws Exception {
        web.click(RETYPE_EMAIL_INPUT_FIELD);
        String getEmail = emailBoxPage.getEmail();
        web.type(RETYPE_EMAIL_INPUT_FIELD, email.equalsIgnoreCase("*email*") ? getEmail : email);
    }
//    SubCategoryPage subCategoryPage = homePage.openCatalogCategory(MainCategories.MENS.getCategory());

    public void inputTitle(TreatmentType title) throws Exception {
        web.select(TREATMENT_TYPE_INPUT_FIELD).selectByValue(title.getTitle());
    }

    public void selectBirthDay(int day) throws Exception {
        web.select(INPUT_DAY_OF_BIRTH).selectByValue(String.valueOf(day));
    }

    public void selectBirthMonth(int month) throws Exception {
        web.select(INPUT_MONTH_OF_BIRTH).selectByValue(String.valueOf(month));
    }

    public void selectBirthYear(int year) throws Exception {
        web.select(INPUT_YEAR_OF_BIRTH).selectByValue(String.valueOf(year));
    }

    public void inputFirstname(String firstname) throws Exception {
        web.click(FIRSTNAME_INPUT_FIELD);
        web.type(FIRSTNAME_INPUT_FIELD, firstname);
    }

    public void inputLastname(String lastname) throws Exception {
        web.click(LASTNAME_INPUT_FIELD);
        web.type(LASTNAME_INPUT_FIELD, lastname);
    }

    public void inputPassowrd(String password) throws Exception {
        web.click(PASSWORD_INPUT_FIELD);
        web.type(PASSWORD_INPUT_FIELD, password);
    }

    public void inputPasswordConfirmation(String password) throws Exception {
        web.click(PASSWORD_CONFIRMATION_INPUT_FIELD);
        web.type(PASSWORD_CONFIRMATION_INPUT_FIELD, password);
    }

    public void signUp() throws Exception {
        web.waitUntil(visibilityOfElementLocated(SIGN_UP_BUTTON));
        web.click(SIGN_UP_BUTTON);
    }
}
