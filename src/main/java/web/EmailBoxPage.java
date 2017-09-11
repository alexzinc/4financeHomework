package web;

import org.openqa.selenium.By;
import selenium.Web;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by aleksandrs on 10/09/2017.
 */
public class EmailBoxPage {

    private static final By EMAIL_TEXT = By.xpath("//*[@id='fe_text']");
    private static final By PASSWORD_TEXT = By.id("");
    public String email;
    public String password;
    Web web;

    public EmailBoxPage(Web web) {
        this.web = web;
    }


    public void saveEmail() throws Exception {
        web.waitUntil(visibilityOfElementLocated(EMAIL_TEXT));
        email = web.findElement(EMAIL_TEXT).getAttribute("value");
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() throws Exception {
        web.waitUntil(visibilityOfElementLocated(PASSWORD_TEXT));
        return password = web.findElement(PASSWORD_TEXT).getAttribute("value");
    }
}
