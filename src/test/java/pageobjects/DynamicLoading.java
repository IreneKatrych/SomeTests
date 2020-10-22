// filename: pageobjects/DynamicLoading.java

package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoading extends Base {

    // Locators
    By startButton = By.cssSelector("#start button");
    By finishText = By.id("finish");

    // Constructor
    public DynamicLoading(WebDriver driver) {
        super(driver);
    }

    // Open page & click on button
    public void loadExample(String exampleNumber) {
        visit("/dynamic_loading/" + exampleNumber);
        click(startButton);
    }

    // Check if some text will appear after 10sec
    public Boolean finishTextPresent() {
        return isDisplayed(finishText, 10);
    }
}