package integration.configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class IntegrationBase {
    public static final String BASIC_URL;
    public static final Integer DEFAULT_WAITFOR_PAGE_SECONDS;
    public static final Integer DEFAULT_WAITFOR_AJAX_SECONDS;
    public static final String USERNAME_1;
    public static final String USERNAME_2;
    public static final String PASSWORD_1;
    public static final String PASSWORD_2;
    public static final String ROBOT_NAME;

    static {
        BASIC_URL = "http://internal.lv.remit.se/login";
        DEFAULT_WAITFOR_PAGE_SECONDS = 30;
        DEFAULT_WAITFOR_AJAX_SECONDS = 30;
        USERNAME_1 = "";
        USERNAME_2 = "";
        PASSWORD_1 = "";
        PASSWORD_2 = "";
        ROBOT_NAME = "";
    }

    protected WebDriver driver;
    protected String browser;


    public IntegrationBase(String browser) {
        this.browser = browser;
    }




    @BeforeMethod
    public final void setup() {
        driver = createWebDriver();
    }

    private WebDriver createWebDriver() {
        //System.setProperty("webdriver.chrome.driver", "F:\\chromedriver\\chromedriver.exe");
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        return driver;
    }

    public void openBasicHomePage(String url) {
        driver.navigate().to(url);
        driver.manage().window().maximize();
        waitUntilPageToLoad();
    }

    public void waitUntilPageToLoad() {
        WebElement isDisplayedNavigation = (WebElement) (new WebDriverWait(driver, 10L))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[value='Log In']")));
    }



    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @AfterMethod
    public final void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
