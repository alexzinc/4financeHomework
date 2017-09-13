package web;

import org.openqa.selenium.By;
import selenium.Web;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by aleksandrs on 13/09/2017.
 */
public class HomePage {
    Web web;

    private static final By USER_SETTINGS_BUTTON = By.xpath("//*[@class='AccountIcon FirstTopSub myaccount']//*[@class='hidden-xs logoutTxt']");
    private static final By ACCOUNT_BUTTON = By.xpath("//*[@class='TopLinkDrop TopLink1 multicolumn lillAccounts TopLink']/*[contains(@href,'accountinformation')]");

    public HomePage(Web web) {
        this.web = web;
    }

    public boolean isDisplayed() {
        web.moveToElementAction(web.findElement(ACCOUNT_BUTTON));
        web.waitUntil(visibilityOfElementLocated(USER_SETTINGS_BUTTON));
        return web.isDisplayed(USER_SETTINGS_BUTTON);
    }
}
