//filename: tests/TestLogin.java

package tests;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import org.junit.experimental.categories.Category;
import pageobjects.Login;
import tests.groups.Shallow;

public class TestLogin extends Base {

    private Login login;

    // Loading page
    @Before
    public void setUp() {
        login = new Login(driver);
    }

    // Check if login was success (correct credentials)
    @Test
    @Category(Shallow.class)
    public void succeeded() {
        login.with("tomsmith", "SuperSecretPassword!");
        assertTrue("success message not present", login.successMessagePresent());
    }

    // Check if login was failed (not correct credentials)
    @Test
    @Category(Shallow.class)
    public void failed() {
        login.with("tomsmith", "bad password");
        assertTrue("failure message wasn't present after providing bogus credentials", login.failureMessagePresent());
    }

    // Test which should fail
    @Test
    @Category(Shallow.class)
    public void forcedFailure() {
        login.with("tomsmithasdf", "SuperSecretPassword!");
        assertTrue("failure message wasn't present after providing bogus credentials", login.successMessagePresent());
    }
}