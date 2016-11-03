package integration.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AbstractWebComponent {

    protected WebDriver driver;
    protected WebDriverWait waitForPage;
    protected WebDriverWait waitForAjax;

    public AbstractWebComponent(WebDriver driver) {
        this.driver = driver;
        this.waitForPage = new WebDriverWait(this.driver, IntegrationBase.DEFAULT_WAITFOR_PAGE_SECONDS);
        this.waitForAjax = new WebDriverWait(this.driver, IntegrationBase.DEFAULT_WAITFOR_AJAX_SECONDS);

        PageFactory.initElements(this.driver, this);
    }
}

