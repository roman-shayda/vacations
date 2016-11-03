package integration.pagemodels;

import integration.configuration.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends AbstractWebComponent {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "LoginForm_username")
    private WebElement usernameField;

    @FindBy(id = "LoginForm_password")
    private WebElement passwordField;

    @FindBy(css = "input[value='Log In']")
    public WebElement loginButton;

    //methods _______________________________________________________________________________________


    public void enterUsernameAndPassword(String username, String password) {
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        waitForAjax.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public void clickLogin() {
        loginButton.click();
    }


}
