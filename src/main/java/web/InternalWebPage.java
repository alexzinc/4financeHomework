package web;

import org.joda.time.Duration;
import org.openqa.selenium.By;
import selenium.Web;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by aleksandrs on 07/09/2017.
 */
public class InternalWebPage {
    Web web;
    private static final By CLOSE_POPUP_ITEM_BUTTON = By.xpath("//*[@class='modal-dialog advertPopup']//*[@class='modal-content']//*[@class='close' and @data-dismiss='modal']");


    public InternalWebPage(Web web) {
        this.web = web;
    }

    public void closeDialog() throws Exception {
        if (web.isDisplayed(CLOSE_POPUP_ITEM_BUTTON)) {
            web.waitUntil(visibilityOfElementLocated(CLOSE_POPUP_ITEM_BUTTON), Duration.standardSeconds(10), false);
            web.click(CLOSE_POPUP_ITEM_BUTTON);
        }
    }
}
