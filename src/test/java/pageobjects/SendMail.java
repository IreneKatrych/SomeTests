// filename: pageobjects/SendMail.java

package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class SendMail extends Base {

    private WebDriver driver;

    //Locators for login
    By logInButtonLocator = By.cssSelector(".mail-sign-in.btn.btn-primary");
    By logInFormLocator = By.id("login-form");
    By usernameLocator = By.cssSelector("input[name='login']");
    By passwordLocator = By.cssSelector("input[name='password']");
    By submitButton = By.cssSelector(".btn.btn-primary.btn-login");
    By successLogInMessageLocator = By.cssSelector(".mailbox_name");

    //Locators to send mail
    By writeButtonLocator = By.id("id_send_email");
    By typeReceiverLocator = By.id("send_to");
    By typeSubjectLocator = By.id("subject");
    By inputMessageLocator = By.id("body");
    By sendMessageButtonLocator = By.cssSelector("input[name='send']");
    By successSendMessageLocator = By.id("send_ok");

    //Log out Locators
    By logOutButtonLocator = By.id("id_logout");

    //Locators to check income message
    By receivedSubjectLocator = By.cssSelector(".mess_subj");
    By receivedFromLocator = By.cssSelector(".mess_h_p");
    By receivedMessageLocator = By.id("message_body");

    //Constructor
    public SendMail (WebDriver driver) {
        super(driver);
    }

    // Login
    public void loginToMailWith(String username, String password) {
        visit("https://meta.ua/");
        click(logInButtonLocator);
        assertTrue("The password form is not present", isDisplayed(logInFormLocator, 3));
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(submitButton);
    }

    // Check if login to the site was successful
    public Boolean successLoginMessagePresent() {
        isDisplayed(successLogInMessageLocator, 5);
        return isDisplayed(successLogInMessageLocator);
    }

    //Send mail
    public boolean successSendMessage(String emailAddress, String subject, String message) {
        click(writeButtonLocator);
        type(emailAddress, typeReceiverLocator);
        type(subject, typeSubjectLocator);
        type(message, inputMessageLocator);
        click(sendMessageButtonLocator);
        return isDisplayed(successSendMessageLocator, 5);
    }

    //Exit from mail-box
    public void logOutFromMailBox() {
        click(logOutButtonLocator);
    }

    //Check if mail comes
    public boolean checkIfMailComes(String user, String from, String subject, String text) {
        click(By.xpath("//a[text()='"+ user + "']"));
        return checkIfTextExist(subject, receivedSubjectLocator)
                && checkIfTextExist(from, receivedFromLocator)
                && checkIfTextExist(text, receivedMessageLocator);
    }
}