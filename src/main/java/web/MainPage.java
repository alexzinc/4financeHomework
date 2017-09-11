package web;

import org.joda.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import selenium.Web;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


/**
 * Created by aleksandrs on 06/09/2017.
 */
public class MainPage {

    private static final By CURRENCY_ELEMENT_BY = By.className("spanCurrencyLanguageSelector");

    private static final By CATEGORY_OPTION = By.xpath("//*[contains(@class,'mmHasChild root multicolumn')]/a");

    private static final By SIGNUP_BUTTON = By.id("dnn_dnnLOGIN_loginLink");

    private static final By CONTINUE_BUTTON = By.id("dnn_ctr42569657_LoginScreen_cmdRegister");

    private static final By ACCOUN_BUTTON = By.className("TopLinkDrop TopLink1 multicolumn lillAccounts TopLink");

    private static final By LOGOUT_BUTTON = By.xpath("//*[@class='SignOut TopSubViewAll']//*[@class='hidden-xs logoutTxt']");
    Web web;
    private WebDriver driver;

    public MainPage(Web web) {
        this.web = web;
    }

    public List<WebElement> getCategoryList() throws Exception {
        web.waitUntil(visibilityOfAllElementsLocatedBy(CATEGORY_OPTION), Duration.standardSeconds(10), false);
        List<WebElement> blockedUserElements = web.findElements(CATEGORY_OPTION);
        String alo = web.getText(CATEGORY_OPTION);
        System.out.println(alo);
        return blockedUserElements;
    }

    public void chooseCategory(String category) throws Exception {
        By CATEGORY_OPTION_BUTTON = By.xpath("//*[contains(@class,'mmHasChild root multicolumn')]//a[contains(@href,'/" + category + "')]");
        web.waitUntil(visibilityOfElementLocated(CATEGORY_OPTION_BUTTON));
        web.click(CATEGORY_OPTION_BUTTON);
    }

    public void selectCurrency(String currency) throws Exception {
        By CURRENCY_LOCATOR_BY = By.xpath("//*[@class='spanCurrencyLanguageSelector']/p[contains(text(),'" + currency + "')]");
        web.waitUntil(visibilityOfElementLocated(CURRENCY_LOCATOR_BY), Duration.standardSeconds(5), false);
        if (!web.isDisplayed(CURRENCY_LOCATOR_BY)) {
            web.click(CURRENCY_ELEMENT_BY);
            By SELECT_CURRENCY_RADIO_BUTTON = By.xpath("//*[@id='dnn_sdLanguageSwitcherOptions_rbCurrencies_1' and @value='" + currency + "']");
            web.waitUntil(visibilityOfElementLocated(SELECT_CURRENCY_RADIO_BUTTON));
            web.click(SELECT_CURRENCY_RADIO_BUTTON);
        }
    }


    public void chooseCategoryList(String category) throws Exception {

        for (WebElement cat : getCategoryList()) {
            System.out.println(cat);
            String a = cat.getText();
        }
    }

    public void openSignUp() throws Exception {
        web.waitUntil(visibilityOfElementLocated(SIGNUP_BUTTON));
        web.click(SIGNUP_BUTTON);
        web.waitUntil(visibilityOfElementLocated(CONTINUE_BUTTON));
        web.click(CONTINUE_BUTTON);
    }

    public void logout() throws Exception {
        Actions action = new Actions(driver);
        WebElement accountButton = web.findElement(ACCOUN_BUTTON);
        action.moveToElement(accountButton).moveToElement(web.findElement(LOGOUT_BUTTON)).click();
    }


//        cat.forEach(if (WebElement cathegoryelement == category)
//        { cathegoryelement.click();
//        }
//        });
//        friends.each { WebElement friendElement -> friendElement.click() }


    /**
     * Method returns filter item by name
     *
     * @param name of filter
     * @return - selected filter wrapper
    //     */
//    public FilterWrapper getFilterByName(final String name) {
//        Optional<FilterWrapper> wrapper = Iterables.tryFind(getAllFilterItems(), new Predicate<FilterWrapper>() {
//            public boolean apply(FilterWrapper filterWrapper) {
//                return filterWrapper.getFilterName().equals(name);
//            }
//        });
//        return wrapper.get();
//    }
//
//    /**
//     * Method select filter options
//     *
//     * @param name filter name
//     */
//    public void selectFilter(String name) {
//        LOGGER.info("User selects filter: " + name);
//        FilterWrapper item = getFilterByName(name);
//        item.selectFilter();
//        baseFunctions.pause(300);
//    }


}