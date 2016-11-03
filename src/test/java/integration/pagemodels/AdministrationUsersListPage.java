package integration.pagemodels;

import integration.configuration.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class AdministrationUsersListPage extends AbstractWebComponent {

    @FindBy(css = "button[data-target='#dataRangeReportModal']")
    public WebElement generateCompanyReportButton;

    @FindBy(css = "a[href='/vacation/manage']")
    public WebElement vacationsManageNavPannelLink;


    public AdministrationUsersListPage(WebDriver driver) {
        super(driver);
    }


    //methods _______________________________________________________________________________________

    public void waitForPageToLoad() {
        waitForPage.until(ExpectedConditions.visibilityOf(generateCompanyReportButton));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void clickVacationsNavPannelLink() {
        vacationsManageNavPannelLink.click();
    }

}
