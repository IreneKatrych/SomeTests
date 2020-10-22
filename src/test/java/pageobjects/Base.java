// filename: pageobjects/Base.java

package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static tests.Config.*;

public class Base {

    private WebDriver driver;

    // Constructor
    public Base(WebDriver driver) {
        this.driver = driver;
    }

    // Open web-page
    public void visit(String url) {
        if(url.contains("http")) {
            driver.get(url);
        } else {
            driver.get(baseUrl + url);
        }
    }

    // Find element by its locator
    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    // Click on button using its locator
    public void click(By locator) {
        find(locator).click();
    }

    // Find text field by locator & input data
    public void type(String inputText, By locator) {
        find(locator).sendKeys(inputText);
    }

    // Check if element is on page
    public Boolean isDisplayed(By locator) {
        try {
            return find(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            return false;
        }
    }

    // Check if element will appear on page after some time
    public Boolean isDisplayed(By locator, Integer timeout){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (org.openqa.selenium.TimeoutException exception) {
            return false;
        }
        return true;
    }
}