// filename: pageobjects/Login.java

package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;

public class Login extends Base {

    private WebDriver driver;

    // Locators
    By loginFormLocator = By.id("login");
    By usernameLocator = By.id("username");
    By passwordLocator = By.id("password");
    By submitButton = By.cssSelector("button");
    By successMessageLocator = By.cssSelector(".flash.success");
    By failureMessageLocator = By.cssSelector(".flash.error");

    // Constructor
    public Login (WebDriver driver) {
        super(driver);
       visit("/login");
       // Check if needed page is loaded
        assertTrue("The login form is not present", isDisplayed(loginFormLocator));
    }

    // Input login credentials & submit
    public void with(String username, String password) {
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(submitButton);
    }

    // Check if login to the site was successful
    public Boolean successMessagePresent() {
        isDisplayed(successMessageLocator, 1);
        return isDisplayed(successMessageLocator);
    }

    // Check if login to the site was failed
    public Boolean failureMessagePresent() {
        isDisplayed(failureMessageLocator, 1);
        return isDisplayed(failureMessageLocator);
    }
}