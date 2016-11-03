package integration.pagemodels;

import integration.configuration.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by R.Shayda on 14.07.2016.
 */
public class DetailedManageVacationsPage extends AbstractWebComponent {

    @FindBy(css = "h1")
    public WebElement manageVacationsText;

    @FindBy(css = "a[class='btn btn-success btn-xs']")
    public List<WebElement> allManageButtons;


    public DetailedManageVacationsPage(WebDriver driver) {
        super(driver);
    }


    //methods _______________________________________________________________________________________

    public void waitForPageToLoad() {
        waitForPage.until(ExpectedConditions.visibilityOf(manageVacationsText));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}
