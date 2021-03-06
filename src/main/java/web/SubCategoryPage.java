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


    /**
     * Sub category page is loaded
     */
    public SubCategoryPage(Web web) {
        this.web = web;
    }


    /**
     * method allows user to choose a sub category
     *
     * @param subCategory - uses sub category
     */
    public void chooseSubcategoryOption(String subCategory) {
        By SHOES_OPTION_BUTTON = By.xpath("//*[contains(@class,'menu-item') and @href='#" + subCategory + "']");
        web.waitUntil(ExpectedConditions.visibilityOfElementLocated(SHOES_OPTION_BUTTON), Duration.standardSeconds(3), false);
        web.click(SHOES_OPTION_BUTTON);
    }

    /**
     * method gets a list of items in subcategory
     *
     * @return list of items
     */
    public List<WebElement> getItems() {
        web.waitUntil(visibilityOfAllElementsLocatedBy(FOOTWEAR_ITEM_OPTIONS), Duration.standardSeconds(10), false);
        return web.findElements(FOOTWEAR_ITEM_OPTIONS);
    }

    /**
     * method takes a list of items in subcategory and checks them one by one, untill it clicks on the correct item
     *
     * @param item - item on which user must click
     */
    public void choseItem(String item) {
        List<WebElement> itemList = getItems();
        for (WebElement tempItem : itemList) {
            if (tempItem.getText().equalsIgnoreCase(item)) {
                By ITEM_OPTION_BUTTON = By.xpath("//*[@id='mobNavigation']//*[text()='" + tempItem.getText().toLowerCase() + "']");
                web.waitUntil(visibilityOfElementLocated(ITEM_OPTION_BUTTON));
                web.click(ITEM_OPTION_BUTTON);
                break;
            }
        }
    }
}
