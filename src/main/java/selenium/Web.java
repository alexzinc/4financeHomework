package selenium;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

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


    public String getText(WebElement element, int tries) throws Exception {
        tries = 3;
        try {
            return element.getText();
        } catch (Exception exc) {
            System.out.println("\nRetrying to get text ... ${exc.class}");
            if (tries > 0) {
                getText(element, tries - 1);
            } else {
                takeScreenshot("getText");
                throw exc;
            }
        }
        return element.getText();
    }

    public String getText(By by) throws Exception {
        return getText(by);
    }


    public void waitForElement(By element, long millis) {
        WebDriverWait wait = new WebDriverWait(driver, millis);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void click(By element) {
        driver.findElement(element).click();
    }


    public WebElement findElement(By by, boolean stopOnFailure) throws Exception {
        waitUntil(visibilityOfElementLocated(by), Duration.standardSeconds(2), false);
        WebElement elementToFind = null;
        try {
            elementToFind = driver.findElement(by);
        } catch (Exception e) {
            if (stopOnFailure) {
                throw e;
            } else {
                if (elementToFind == null) {
                    elementToFind = findElement(by);
                }
                System.out.println("exception in finding $by");
            }
        }
        return elementToFind;
    }


//    public waitUntil(ExpectedCondition<?> until, Duration duration, boolean stopOnFailure) {
//        WebDriverWait wait = new WebDriverWait(driver, duration.getStandardSeconds());
//        try {
//            wait.until(until);
//        } catch (Exception e) {
//            if (stopOnFailure) {
//                takeScreenshot("waitUntil");
//                throw e;
//            }
//        }
//    }
//
//    static waitUntil(ExpectedCondition<?> until, boolean stopOnFailure) {
//        waitUntil(until, Duration.standardSeconds(DEFAULT_WEBDRIVER_TIMEOUT_SECONDS), stopOnFailure);
//    }
//


    public void waitUntil(ExpectedCondition<?> until) throws Exception {
        waitUntil(until, Duration.standardSeconds(DEFAULT_WEBDRIVER_TIMEOUT_SECONDS), true);
    }

    public void waitUntil(ExpectedCondition<?> until, Duration duration, boolean stopOnFailure) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, duration.getStandardSeconds());
        try {
            wait.until(until);
        } catch (Exception e) {
            if (stopOnFailure) {
                throw e;
            }
        }
    }

    public WebElement findElement(By by) throws Exception {
        waitUntil(visibilityOfElementLocated(by));
        WebElement elementToFind;
        try {
            elementToFind = driver.findElement(by);
        } catch (Exception e) {
            throw e;
        }
        return elementToFind;
    }

    public void type(By by, String text) throws Exception {
        findElement(by).clear();
        findElement(by).sendKeys(text);
    }


    public void waitFor(long seconds) throws Exception {
        try {
            //waitUntil(visibilityOfElementLocated(By.xpath("//nonexisting")), duration);
            driver.manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
            //driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw e;
        }
    }

    public Select select(By by) throws Exception {
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
            if (!(e instanceof NoSuchElementException)) {
                System.out.println("\nisDisplayed $by exception ${e.class}");
            }
        }
        return elementIsDisplayed;
    }

    public boolean isDisplayed(WebElement element) {
        boolean elementIsDisplayed = false;
        try {
            elementIsDisplayed = element.isDisplayed();
        } catch (Exception e) {
        }
        try {
            elementIsDisplayed = element.isDisplayed();
        } catch (Exception e) {
        }
        return elementIsDisplayed;
    }

    public void typeInt(By by, Integer num) throws Exception {
        findElement(by).clear();
        findElement(by).sendKeys(num.toString());
    }

    public void takeScreenshot(String postTimestampName) {
        String screenshotsFolderPath = "screenshots";
        if (screenshotsFolderPath != null) {
            DateTime dateTime = new DateTime();
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile, new File(screenshotsFolderPath + "/" + dateTime.toString("dd.MM_HH.mm.ss_") + "_" + postTimestampName + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
