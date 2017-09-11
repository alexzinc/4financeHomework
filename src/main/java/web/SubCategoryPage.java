package web;

import org.joda.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.Web;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by aleksandrs on 07/09/2017.
 */
public class SubCategoryPage {
    Web web;

    private static final By ITEM_OPTION_BUTTON = By.className("//*[@id='mobNavigation']//li//a");
    private static final By FOOTWEAR_ITEM_OPTIONS = By.xpath("//*[@id='footwear']//li//a");

    public SubCategoryPage(Web web) {
        this.web = web;
    }

    public void chooseSubcategoryOption(String subCategory) throws Exception {
        By SHOES_OPTION_BUTTON = By.xpath("//*[contains(@class,'menu-item') and @href='#" + subCategory + "']");
        web.waitUntil(ExpectedConditions.visibilityOfElementLocated(SHOES_OPTION_BUTTON), Duration.standardSeconds(3), false);
        web.click(SHOES_OPTION_BUTTON);
    }

    public List<WebElement> getItems() throws Exception {
        web.waitUntil(visibilityOfAllElementsLocatedBy(FOOTWEAR_ITEM_OPTIONS), Duration.standardSeconds(10), false);
        return web.findElements(FOOTWEAR_ITEM_OPTIONS);
    }

    public void choseItem(String item) throws Exception {
//        By azaza = By.xpath("//*[@id='mobNavigation']//*[text()='" + item + "']");
//
//        web.waitUntil(visibilityOfElementLocated(azaza), Duration.standardSeconds(10), false);
//        web.click(azaza);

        List<WebElement> itemList = getItems();
        for (WebElement tempItem : itemList) {
            if (tempItem.getText().contains(item)) {
                By ITEM_OPTION_BUTTON = By.className("//*[@id='mobNavigation']//*[text()='" + tempItem.getText() + "']");
                web.waitUntil(visibilityOfElementLocated(ITEM_OPTION_BUTTON));
                web.click(ITEM_OPTION_BUTTON);
                break;
            }
        }
    }
}
