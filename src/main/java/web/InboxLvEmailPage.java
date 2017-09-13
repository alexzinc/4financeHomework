package web;

import org.joda.time.Duration;
import org.openqa.selenium.By;
import selenium.Web;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by aleksandrs on 12/09/2017.
 */
public class InboxLvEmailPage {
    Web web;

    private static final By RECEIVED_EMAIL = By.xpath("(//*[@title='Sports Direct Latvia - Forgotten Password'])[1]");
    private static final By RECEIVED_EMAIL_LINK = By.xpath("//*[contains(@href,'asswordReset?token')]");

    /**
     * Inbox.lv email box page is loaded.
     */
    public InboxLvEmailPage(Web web) {
        this.web = web;
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
    public void openLink() {
        web.waitUntil(visibilityOfElementLocated(RECEIVED_EMAIL_LINK));
        web.click(RECEIVED_EMAIL_LINK);
    }
}
