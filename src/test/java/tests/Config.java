// filename: tests/Config.java

package tests;

// Setting basic variables & their default values
public class Config{
    public static final String baseUrl = System.getProperty("baseUrl", "http://the-internet.herokuapp.com");
    public static final String browser = System.getProperty("browser", "firefox");

    public static final String host = System.getProperty("host", "localhost");
    public static final String browserVersion = System.getProperty("browserVersion", "50");
    public static final String platform = System.getProperty("platform", "Windows 10");

    public static final String sauceUser = System.getenv("SAUCE_USERNAME");
    public static final String sauceKey = System.getenv("SAUCE_ACCESS_KEY");
}