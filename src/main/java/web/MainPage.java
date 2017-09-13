package web;

import org.joda.time.Duration;
import org.openqa.selenium.By;
import selenium.Web;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


/**
 * Created by aleksandrs on 06/09/2017.
 */
public class MainPage {

    private static final By CURRENCY_ELEMENT_BY = By.className("spanCurrencyLanguageSelector");

    private static final By CATEGORY_OPTION = By.xpath("//*[contains(@class,'mmHasChild root multicolumn')]/a");

    private static final By SIGNUP_BUTTON = By.id("dnn_dnnLOGIN_loginLink");

    private static final By CONTINUE_BUTTON = By.id("dnn_ctr42569657_LoginScreen_cmdRegister");

    private static final By ACCOUNT_BUTTON = By.xpath("//*[@class='TopLinkDrop TopLink1 multicolumn lillAccounts TopLink']/*[contains(@href,'accountinformation')]");

    private static final By LOGOUT_BUTTON = By.xpath("//*[@class='SignOut TopSubViewAll']//*[@class='hidden-xs logoutTxt']");
    Web web;

    /**
     * MainPage page is loaded.
     */
    public MainPage(Web web) {
        this.web = web;
    }


    /**
     * method clicks on a chosen category
     *
     * @param category - item category
     */
    public void chooseCategory(String category) {
        By CATEGORY_OPTION_BUTTON = By.xpath("//*[contains(@class,'mmHasChild root multicolumn')]//a[contains(@href,'/" + category + "')]");
        web.waitUntil(visibilityOfElementLocated(CATEGORY_OPTION_BUTTON));
        web.click(CATEGORY_OPTION_BUTTON);
    }

    /**
     * method checks if EURO is displayed, if not, then it changes current currency to EUR
     *
     * @param currency - currency
     */
    public void selectCurrency(String currency) {
        By CURRENCY_LOCATOR_BY = By.xpath("//*[@class='spanCurrencyLanguageSelector']/p[contains(text(),'" + currency + "')]");
        web.waitUntil(visibilityOfElementLocated(CURRENCY_LOCATOR_BY), Duration.standardSeconds(5), false);
        if (!web.isDisplayed(CURRENCY_LOCATOR_BY)) {
            web.click(CURRENCY_ELEMENT_BY);
            By SELECT_CURRENCY_RADIO_BUTTON = By.xpath("//*[@id='dnn_sdLanguageSwitcherOptions_rbCurrencies_1' and @value='" + currency + "']");
            web.waitUntil(visibilityOfElementLocated(SELECT_CURRENCY_RADIO_BUTTON));
            web.click(SELECT_CURRENCY_RADIO_BUTTON);
        }
    }

    /**
     * method allows to open log in screen
     */
    public void openLoginScreen() {
        web.waitUntil(visibilityOfElementLocated(SIGNUP_BUTTON));
        web.click(SIGNUP_BUTTON);
    }

    /**
     * method allows user to log out
     */
    public void logout() {
        web.waitUntil(visibilityOfElementLocated(ACCOUNT_BUTTON));
        web.moveToElementAction(web.findElement(ACCOUNT_BUTTON));
        web.waitUntil(elementToBeClickable(LOGOUT_BUTTON));
        web.click(LOGOUT_BUTTON);
    }
}