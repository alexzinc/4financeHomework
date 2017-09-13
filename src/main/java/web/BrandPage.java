package web;

import org.joda.time.Duration;
import org.junit.Assert;
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
    private static final By ITEM_PRICE = By.xpath("//*[@class='CurrencySizeLarge curprice productHasRef']");

    /**
     * Brand page is loaded
     */
    public BrandPage(Web web) {
        this.web = web;
    }


    /**
     * Method selects brand type
     *
     * @param brandType type of a brand
     */
    public void selectBrand(String brandType) {
        By BRAND_CHECKBOX = By.xpath("//*[@data-filtername='" + brandType + "']//ancestor::span[@role='checkbox']");
        web.waitUntil(visibilityOfElementLocated(BRAND_CHECKBOX));
        web.click(BRAND_CHECKBOX);
    }

    /**
     * Method gets a list of items
     */
    public List<WebElement> getItemAmount() {
        web.waitUntil(visibilityOfAllElementsLocatedBy(ITEM_ELEMENTS));
        return web.findElements(ITEM_ELEMENTS);
    }

    /**
     * Method gets a list of product counter
     */
    public List<WebElement> getProductCounter() {
        web.waitUntil(visibilityOfAllElementsLocatedBy(ITEM_COUNTER));
        return web.findElements(ITEM_COUNTER);
    }

    /**
     * Method gets a list of product prices
     */
    public List<WebElement> getRawItemPrice() {
        web.waitUntil(visibilityOfAllElementsLocatedBy(ITEM_PRICE), Duration.standardSeconds(5), false);
        return web.findElements(ITEM_PRICE);
    }

    /**
     * Method gets a list of product prices,
     * gets the whole size of elements of a list
     * and removes all redundant symbols for every price
     *
     * @return handles prices
     */
    public ArrayList<Double> getItemPrice() {
        List<WebElement> priceList = getRawItemPrice();
        ArrayList<Double> price = new ArrayList<>(priceList.size());
        for (WebElement tempPrice : priceList) {
            String priceText = tempPrice.getText();
            priceText = priceText.replaceAll("[^0-9.,]", "").replaceAll(",", ".");
            price.add(Double.parseDouble(priceText));
        }
        return price;
    }

    /**
     * method gets a list of product counters and sums them up
     *
     * @return - sum of product counters
     */
    public int getProductCounterSum() {
        int sum = 0;
        List<WebElement> productList = getProductCounter();
        for (WebElement tempProduct : productList) {
            String product = tempProduct.getText();
            product = product.replaceAll("[)]", "").replaceAll("[(]", "");
            int productCounter = Integer.parseInt(product);
            sum += productCounter;
        }
        return sum;
    }

    /**
     * Method checks if product counter sum is the same as a size of items in a list
     */
    public void correctAmountOfItemsIsDisplayed() {
        Assert.assertTrue(getProductCounterSum() == getItemAmount().size());
    }

    /**
     * Method inputs starting price and end price to price range input fields and clicks on submit
     *
     * @param startingPrice price from
     * @param endPrice      price to
     */
    public void setItemPriceRange(int startingPrice, int endPrice) {
        web.waitUntil(visibilityOfElementLocated(ITEM_MINIMUM_PRICE_INPUT_FIELD), Duration.standardSeconds(10), false);
        web.type(ITEM_MINIMUM_PRICE_INPUT_FIELD, String.valueOf(startingPrice));
        web.type(ITEM_MAXIMUM_PRICE_INPUT_FIELD, String.valueOf(endPrice));
        web.click(PRICE_RANGE_SUBMIT_BUTTON);
    }

    /**
     * Method checks if all item prices displayed on page fit selected price range
     *
     * @param startingPrice prices from
     * @param endPrice      prices to
     */

    public void itemsFitDisplayedPriceRange(int startingPrice, int endPrice) {
        for (Double itemPrice : getItemPrice()) {
            Assert.assertTrue("Price is not in range", itemPrice >= startingPrice && itemPrice <= endPrice);
        }
    }
}