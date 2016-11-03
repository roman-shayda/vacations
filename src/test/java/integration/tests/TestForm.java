package integration.tests;

import integration.configuration.IntegrationBase;
import integration.pagemodels.AdministrationUsersListPage;
import integration.pagemodels.BasicDashboardPage;
import integration.pagemodels.LoginPage;
import integration.pagemodels.ManageVacationsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;


public class TestForm extends IntegrationBase {

    //variables
    private static final String EMAIL_ADDRESS = "testone@test.com";
    private static final String FIRST_NAME = "Robot";
    private static final String LAST_NAME = "Framework";

    private LoginPage loginPage;
    private BasicDashboardPage basicDashboardPage;
    private AdministrationUsersListPage administrationUsersListPage;
    private ManageVacationsPage manageVacationsPage;



    public TestForm(String browser) {
        super(browser);
    }

    @BeforeMethod
    public void setupHomePage() {
        loginPage = new LoginPage(driver);
        basicDashboardPage = new BasicDashboardPage(driver);
        administrationUsersListPage = new AdministrationUsersListPage(driver);
        manageVacationsPage = new ManageVacationsPage(driver);
        openBasicHomePage(BASIC_URL);

    }

    //Login as Admin User
    @Test
    public void firstTest() throws IOException {
        loginPage.enterUsernameAndPassword(USERNAME_2, PASSWORD_2);
        loginPage.clickLogin();
        basicDashboardPage.waitForPageToLoad();
        basicDashboardPage.clickAdministrationNavPannelLink();
        administrationUsersListPage.waitForPageToLoad();
        administrationUsersListPage.clickVacationsNavPannelLink();
        manageVacationsPage.waitForPageToLoad();
        manageVacationsPage.clickManageUserButton();
        manageVacationsPage.navigateToStep(2);
        manageVacationsPage.clickManageUserButton();
        manageVacationsPage.navigateToStep(3);
        manageVacationsPage.clickManageUserButton();
        manageVacationsPage.navigateToStep(4);
        manageVacationsPage.clickManageUserButton();
        manageVacationsPage.navigateToStep(5);
        manageVacationsPage.clickManageUserButton();

        //Now, there were only 5 pages (check pages numbr before next execution!
        //manageVacationsPage.navigateToStep(6);
        //manageVacationsPage.clickManageUserButton();

    }





}
