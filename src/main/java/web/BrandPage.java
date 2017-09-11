package web;

import org.joda.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.Web;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by aleksandrs on 08/09/2017.
 */
public class BrandPage {
    Web web;

    private static final By ITEM_ELEMENTS = By.className("s-productthumbbox");
    private static final By ITEM_MINIMUM_PRICE_INPUT_FIELD = By.id("PriceFilterTextEntryMin");
    private static final By ITEM_MAXIMUM_PRICE_INPUT_FIELD = By.id("PriceFilterTextEntryMax");
    private static final By PRICE_RANGE_SUBMIT_BUTTON = By.id("PriceFilterTextEntryApply");
    private static final By ITEM_COUNTER = By.xpath("//*[@id='dnn_ctr42569670_ViewTemplate_ctl00_ctl07_lstFilters_CollapseDiv_1']//ancestor::*[@class='productFilter']//*[@class='FilterValue']");

    public BrandPage(Web web) {
        this.web = web;
    }

    public void selectBrand(String brandType) throws Exception {
        By BRAND_CHECKBOX = By.xpath("//*[@data-filtername='" + brandType + "']//ancestor::span[@role='checkbox']");
        web.waitUntil(visibilityOfElementLocated(BRAND_CHECKBOX));
        web.click(BRAND_CHECKBOX);
    }

    public List<WebElement> itemAmount() throws Exception {
        web.waitUntil(visibilityOfAllElementsLocatedBy(ITEM_ELEMENTS));
        return web.findElements(ITEM_ELEMENTS);
    }

    public void selectPriceRange(int startingPrice, int endPrice) throws Exception {
        web.waitUntil(visibilityOfElementLocated(ITEM_MINIMUM_PRICE_INPUT_FIELD), Duration.standardSeconds(10), false);
        web.type(ITEM_MINIMUM_PRICE_INPUT_FIELD, String.valueOf(startingPrice));
        web.type(ITEM_MAXIMUM_PRICE_INPUT_FIELD, String.valueOf(endPrice));
        web.click(PRICE_RANGE_SUBMIT_BUTTON);
    }

//    public String checkItemCounter() throws Exception {
//
//ItemFilter filter = get
//
//        for (int i = 0; i < getItems().size(); i++) {
//            ItemWrapper item = getItems().get(i);
//            double itemPrice = item.getItemPrice();
//            Assert.assertTrue("Price does not match the range", itemPrice >= from && itemPrice <= to);
//        }
//
//
//
//        web.waitUntil(visibilityOfAllElementsLocatedBy(ITEM_COUNTER), Duration.standardSeconds(2), false);
//
//        List<WebElement> itemCount = web.findElements(ITEM_COUNTER);
//
//        List<String> all_elements_text = new ArrayList<>();
//        for (WebElement anItemCount : itemCount) {
//
//            all_elements_text.add(anItemCount.getText());
//            all_elements_text.replaceAll("kk", "");
//            System.out.println(anItemCount.getText());
//
//        }
//        String fullCommentaryText = itemCount.getText();
//        System.out.println(Integer.parseInt(fullCommentaryText.substring(1, fullCommentaryText.length() - 1)));
//        anonCommentary = Integer.parseInt(fullCommentaryText.substring(1, fullCommentaryText.length() - 1));
//        return anonCommentary;


        //myList contains all the web elements
        //if you want to get all elements text into array list

            //loading text of each element in to array all_elements_text
//            all_elements_text
            //to print directly
//
//        }
//
//
//    }
//
//    public boolean itemCount() throws Exception {
//        int items = itemAmount();
//
//
//        web.waitUntil(visibilityOfElementLocated(ANONYMOUS_COMMENTATY), Duration.standardSeconds(2), false);
//        WebElement articleCommentCount = web.findElement(ANONYMOUS_COMMENTATY);
//        String fullCommentaryText = articleCommentCount.getText();
//        System.out.println(Integer.parseInt(fullCommentaryText.substring(11, fullCommentaryText.length() - 1)));
//        anonCommentary = Integer.parseInt(fullCommentaryText.substring(11, fullCommentaryText.length() - 1));
//        return anonCommentary;
//    }


//    public boolean priceRangeIsDisplayed(int startPrice, int endPrice) {

//        for (int i = 0; i < getItems().size(); i++) {
//            ItemWrapper item = getItems().get(i);
//            double itemPrice = item.getItemPrice();
//            Assert.assertTrue("Price does not match the range", itemPrice >= from && itemPrice <= to);
//
//        By dadaw = By.xpath("");
//        return web.isDisplayed(dadaw);
//    }

}
