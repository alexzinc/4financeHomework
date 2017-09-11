package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.Web;

/**
 * Created by aleksandrs on 09/09/2017.
 */
public class ItemCounterWrapper {


    private final Web web;
    private final WebElement rootElement;
    private static final By ITEM_PRICE = By.xpath("//*[@id='dnn_ctr42569670_ViewTemplate_ctl00_ctl07_lstFilters_CollapseDiv_1']//ancestor::*[@class='productFilter']//*[@class='FilterValue']");

    public ItemCounterWrapper(Web web, WebElement element) {
        rootElement = element;
        this.web = web;
    }

    public double getItemPrice() {
        String priceText = rootElement.findElement(ITEM_PRICE).getText();
        priceText = priceText.replaceAll("[(]", "");
        priceText = priceText.replaceAll("[)]", ".");
        return Double.parseDouble(priceText);
    }
}
