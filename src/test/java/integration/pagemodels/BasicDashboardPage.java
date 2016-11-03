package integration.pagemodels;

import integration.configuration.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class BasicDashboardPage extends AbstractWebComponent {

    @FindBy(css = "a[href='/vacation']")
    public WebElement myVacationsNavPannelLink;

    @FindBy(css = "a[href='/event']")
    public WebElement administrationNavPannelLink;

    

    public BasicDashboardPage(WebDriver driver) {
        super(driver);
    }


    //methods _______________________________________________________________________________________

    public void waitForPageToLoad() {
        waitForPage.until(ExpectedConditions.visibilityOf(myVacationsNavPannelLink));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void clickMyVacationsNavPannelLink() {
        myVacationsNavPannelLink.click();
    }

    public void clickAdministrationNavPannelLink() {
        administrationNavPannelLink.click();
    }


}