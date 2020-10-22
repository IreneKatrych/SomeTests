// filename: tests/Base.java

package tests;

import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.saucerest.SauceREST;

import java.net.URL;

import static tests.Config.*;

public class Base {

    protected WebDriver driver;

    private String testName;
    private String sessionID;
    private SauceREST sauceClient;

    @Rule
    public ExternalResource resource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            // launch remote driver (for sauce lab)
            if(host.equals("saucelabs")) {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", browser);
                capabilities.setCapability("version", browserVersion);
                capabilities.setCapability("platform", platform);
                capabilities.setCapability("name", testName);

                String sauceUrl = String.format("http://%s:%s@ondemand.saucelabs.com:80/wd/hub", sauceUser, sauceKey);
                driver = new RemoteWebDriver(new URL(sauceUrl), capabilities);
                sessionID = ((RemoteWebDriver) driver).getSessionId().toString();
                sauceClient = new SauceREST(sauceUser, sauceKey);
            } else if (host.equals("localhost")) { // launch localhost
                if (browser.equals("firefox")) { // with firefox browser with using geckodriver
                    System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
                    driver = new FirefoxDriver();
                } else if (browser.equals("chrome")) { //with chrome browser with using chromedriver
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/vendor/chromedriver.exe");
                    driver = new ChromeDriver();
                }
            }
        }

        // Quit from webpage
        @Override
        protected void after() {
            driver.quit();
        }
    };

    // Passing test name and status to saucelabs
    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            testName = description.getDisplayName();
        }

        @Override
        protected void failed(Throwable throwable, Description description) {
            if (host.equals("saucelabs")) {
                sauceClient.jobFailed(sessionID);
                System.out.println(String.format("https://saucelabs.com/tests/%s", sessionID));
            }
        }

        @Override
        protected void succeeded (Description description) {
            if (host.equals("saucelabs")) {
                sauceClient.jobPassed(sessionID);
            }
        }
    };
}