package stepdefs.ItemFilter;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import selenium.Web;
import web.BrandPage;
import web.InternalWebPage;
import web.MainPage;
import web.SubCategoryPage;

/**
 * Created by aleksandrs on 06/09/2017.
 */
public class ItemFilterStepdef {
    Web web = new Web();
    MainPage mainPage = new MainPage(web);
    InternalWebPage internalWebPage = new InternalWebPage(web);
    SubCategoryPage subCategoryPage = new SubCategoryPage(web);
    BrandPage brandPage = new BrandPage(web);

    @Given("user opens (.*) page")
    public void openHomePage(final String url) {
        web.open(url);
    }

    @When("user selects (.*) in (.*) category")
    public void selectCategory(String category, String categoryType)  {
        internalWebPage.closeDialog();
        switch (categoryType) {
            case "main":
                mainPage.chooseCategory(category);
                break;
            case "sub":
                subCategoryPage.chooseSubcategoryOption(category);
                break;
        }
    }

    @When("user selects (.*) from subcategory list")
    public void selectItemFromSubCategory(String item) throws Exception {
        subCategoryPage.choseItem(item);
    }

    @When("user closes the browser")
    public void closeBrowser() {
        web.closeBrowser();
    }

    @When("user selects currency: (.*)")
    public void selectCurrency(String currency) throws Exception {
        mainPage.selectCurrency(currency);
    }

    @When("user selects (.*) brand")
    public void selectBrand(String brandType) throws Exception {
        brandPage.selectBrand(brandType);
    }

    @Then("user selects a price range between (\\d+) and (\\d+)")
    public void setPriceRange(int startPriceRange, int endPriceRange) {
        brandPage.setItemPriceRange(startPriceRange, endPriceRange);
    }

    @Then("selected brand items in price range of (.*) and (.*) should be displayed")
    public void priceRangeIsDisplayed(int startPrice, int endPrice) throws Exception {
        brandPage.correctAmountOfItemsIsDisplayed();
        brandPage.itemsFitDisplayedPriceRange(startPrice, endPrice);
    }
}