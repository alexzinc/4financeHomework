package selenium;

import org.apache.log4j.Logger;
import org.joda.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by aleksandrs on 06/09/2017.
 */

public class Web {
    public static final int DEFAULT_WEBDRIVER_TIMEOUT_SECONDS = 30;
    public static final int DEFAULT_AGE = 20;
    public static final String DEFAULT_PASSWORD = "test12345";
    public static final int WAIT_FOR_EMAIL = 1000;
    private static final String FIREFOX_DRIVER_LOCATION = "/Users/aleksandrs/geckodriver";
    private static final Logger LOGGER = Logger.getLogger(Web.class);

    WebDriver driver;

    public Web() {
        System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_LOCATION);
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-foreground");
        driver = new FirefoxDriver(options);
//        driver.manage().window().maximize();
    }

    public void open(String path) {
        if (!path.contains("http://")) {
            path = "http://" + path;
        }
        driver.get(path);
    }


    public void closeBrowser() {
        driver.quit();
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public void click(By element) {
        driver.findElement(element).click();
    }

    public WebElement findElement(By by, boolean stopOnFailure) {
        waitUntil(visibilityOfElementLocated(by), Duration.standardSeconds(2), false);
        WebElement elementToFind = null;
        try {
            elementToFind = driver.findElement(by);
        } catch (Exception e) {
            if (stopOnFailure) {
                throw e;
            }
        }
        return elementToFind;
    }

    public void moveToElementAction(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public void moveToElementAction(WebElement element1, WebElement element2) {
        Actions action = new Actions(driver);
        action.moveToElement(element1).moveToElement(element2).build().perform();
    }

    public void waitUntil(ExpectedCondition<?> until) {
        waitUntil(until, Duration.standardSeconds(DEFAULT_WEBDRIVER_TIMEOUT_SECONDS), true);
    }

    public void waitUntil(ExpectedCondition<?> until, Duration duration, boolean stopOnFailure) {
        WebDriverWait wait = new WebDriverWait(driver, duration.getStandardSeconds());
        try {
            wait.until(until);
        } catch (Exception e) {
            if (stopOnFailure) {
                throw e;
            }
        }
    }

    public WebElement findElement(By by) {
        waitUntil(visibilityOfElementLocated(by));
        WebElement elementToFind;
        try {
            elementToFind = driver.findElement(by);
        } catch (Exception e) {
            throw e;
        }
        return elementToFind;
    }

    public void type(By by, String text) {
        findElement(by).clear();
        findElement(by).sendKeys(text);
    }

    public Select select(By by) {
        return new Select(findElement(by));
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public boolean isDisplayed(By by) {
        boolean elementIsDisplayed = false;
        try {
            elementIsDisplayed = isDisplayed(findElement(by, false));
        } catch (Exception e) {
        }
        return elementIsDisplayed;
    }

    public boolean isDisplayed(WebElement element) {
        boolean elementIsDisplayed = false;
        try {
            elementIsDisplayed = element.isDisplayed();
        } catch (Exception e) {
        }
        return elementIsDisplayed;
    }

    public void switchToTabNumber(Integer tab) {
        ArrayList<String> getListOfTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(getListOfTabs.get(tab));
    }

    public void pause(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
