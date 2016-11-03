package integration.pagemodels;

import integration.configuration.AbstractWebComponent;
import integration.pagemodels.commons.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ManageVacationsPage extends AbstractWebComponent {

    //variables
    public static final String ROBOT_EMAIL = "your@email";
    public static final String ROBOT_PASSWORD = "your@password";

    @FindBy(css = "h1")
    public WebElement manageVacationsText;

    @FindBy(css = "a[class='btn btn-success btn-xs']")
    public List<WebElement> allManageButtons;

    @FindBy(xpath = "//td[@class='button-column']/a/ancestor::td[1]/../td[1]")
    public List<WebElement> allUserNames;

    @FindBy(xpath = "//span[contains(@class, 'right')]/strong")
    public WebElement availableVacationDays;

    @FindBy(id = "#profile-name")
    public WebElement profilName;

    @FindBy(css = "#link-signin-different > a")
    public WebElement signWithDifferentAccount;


    String pathToFile26Days = "D:\\your\\path\\to_files\\letter_26_days_and_more.txt"; //"D:\\projects\\vacations\\templates\\letter_26_days_and_more.txt";
    String pathToFile20Days = "D:\\your\\path\\to_files\\letter_20_days.txt"; //"D:\\projects\\vacations\\templates\\letter_20_days.txt";
    String pathToFile20To26Days = "D:\\your\\path\\to_files\\letter_20_between_26_days.txt"; //"D:\\projects\\vacations\\templates\\letter_20_between_26_days.txt";
    //String userNameXpath = "//td[@class='button-column']/a/ancestor::td[1]/../td[1]";


    public ManageVacationsPage(WebDriver driver) {
        super(driver);
    }


    //methods_______________________________________________________________________________________

    public void waitForPageToLoad() {
        waitForPage.until(ExpectedConditions.visibilityOf(manageVacationsText));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public int getManageButtonCount() {
        return allManageButtons.size();
    }


    public double getAvailableVacationDays() {
        String availableVacationDaysValue = availableVacationDays.getText();
        return Double.parseDouble(availableVacationDaysValue);
    }


    public void clickManageUserButton() throws IOException {
        int manageCountButtonsLeft = getManageButtonCount() + 1;
        for (int i = 1; i <= getManageButtonCount(); i++) {
            manageCountButtonsLeft = manageCountButtonsLeft - 1;
            String xpathSelector = "//tr[" + i + "]/td[@class='button-column']/a/ancestor::td[1]/../td[4]/a";
            String userNameColumn = driver.findElement(By.xpath("//tr[" + i + "]/td[@class='button-column']/a/ancestor::td[1]/../td[1]")).getText();
            String userEmailColumn = driver.findElement(By.xpath("//tr[" + i + "]/td[@class='button-column']/a/ancestor::td[1]/../td[2]")).getText();
            int pagination = getPaginationValue();
            driver.findElement(By.xpath(xpathSelector)).click();
            if (getAvailableVacationDays() >= 26) {
                String urlCurrentOne = driver.getCurrentUrl();
                sendEmailIfUserHasMoreThanTwentySixDays(userNameColumn, userEmailColumn);
                driver.navigate().to(urlCurrentOne);
                //System.out.println(userNameColumn + " have more than 20 days of vacation! Go on holiday!!!");
            } else if (getAvailableVacationDays() == 20) {
                String urlCurrentTwo = driver.getCurrentUrl();
                sendEmailIfUserHasTwentyDays(userNameColumn, userEmailColumn);
                driver.navigate().to(urlCurrentTwo);
                //System.out.println(userNameColumn + " have 20 days of vacation. You should think about it...");
            } else if (getAvailableVacationDays() >= 20.5 && getAvailableVacationDays() <= 25.5) {
                String urlCurrentTwo = driver.getCurrentUrl();
                sendEmailIfUserHasFromTwentyToTwentySixDays(userNameColumn, userEmailColumn);
                driver.navigate().to(urlCurrentTwo);
            } else {
                String urlCurrentThree = driver.getCurrentUrl();
                System.out.println(userNameColumn + " has less than 20 days of vacation. Good job! :) ");
                driver.navigate().to(urlCurrentThree);
            }
            //go back to all vacations page
            if (pagination == 2 || pagination == 3 || pagination == 4 || pagination == 5 || pagination == 6) {
                driver.navigate().to("http://internal.lv.remit.se/vacation/manage?User_page=" + pagination);
            } else {
            driver.findElement(By.cssSelector("a[href='/vacation/manage']")).click();
        }
            }
    }

    public void navigateToStep(int stepValue) throws IOException {
        String paginationCssSelector = "li[class='page'] a[href*='User_page=" + stepValue + "']";
        driver.findElement(By.cssSelector(paginationCssSelector)).click();
        getPaginationValue();
    }

    public int getPaginationValue () throws IOException {
        try {
            String url = driver.getCurrentUrl().toString();
            url = url.substring(url.lastIndexOf("=") + 1, url.length());
            int paginationValue = Integer.parseInt(url);
            return paginationValue;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean isElementPresent() {
        if(driver.getPageSource().contains("Remit Robot")) {
            return false;
        } else {
            return true;
        }
    }


    public void sendEmailIfUserHasMoreThanTwentySixDays(String userName, String userEmail) throws IOException {
        driver.navigate().to("https://accounts.google.com/ServiceLogin?");
        if(isElementPresent()) {
            driver.findElement(By.id("Email")).sendKeys(ROBOT_EMAIL);
            driver.findElement(By.id("next")).click();
            waitForPageToLoad();
        }
        driver.findElement(By.id("Passwd")).sendKeys(ROBOT_PASSWORD);
        driver.findElement(By.id("signIn")).click();
        waitForPageToLoad();
        //some optional actions for reaching gmail inbox
        driver.findElement(By.xpath("//*[@title='Google apps']")).click();
        driver.findElement(By.id("gb23")).click();
        SeleniumUtils.fixedWait(SeleniumUtils.THREE_SECONDS);
        //clicks compose
        driver.findElement(By.cssSelector(".T-I.J-J5-Ji.T-I-KE.L3")).click();
        SeleniumUtils.fixedWait(SeleniumUtils.THREE_SECONDS);
        //type email
        driver.findElement(By.cssSelector("textarea[aria-label='To']")).sendKeys(userEmail); //userEmail
        //type email subject
        driver.findElement(By.cssSelector("input[name='subjectbox']")).sendKeys("Keeping track of your vacation days");
        //types message in body without hampering signature
        driver.findElement(By.cssSelector("div[role='textbox']")).sendKeys("Dear " + userName + ", \n\n");
        getDescTextAndReadFromFile(pathToFile26Days);
        //driver.findElement(By.cssSelector("div[role='textbox']")).sendKeys("\n\nSincerely yours HR department");
        //click send email button
        driver.findElement(By.cssSelector("div[data-tooltip='Send ‪(Ctrl-Enter)‬']")).click();
        //logout flow
        WebElement myDynamicElement = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class*='gb_db gb_R']")));
        driver.findElement(By.cssSelector("a[class*='gb_db gb_R']")).click();
        SeleniumUtils.fixedWait(SeleniumUtils.THREE_SECONDS);
        //press signout button
        driver.findElement(By.id("gb_71")).click();
        SeleniumUtils.fixedWait(SeleniumUtils.THREE_SECONDS);

    }

    public void sendEmailIfUserHasTwentyDays(String userName, String userEmail) throws IOException {
        driver.navigate().to("https://accounts.google.com/ServiceLogin?");
        if(isElementPresent()) {
            driver.findElement(By.id("Email")).sendKeys(ROBOT_EMAIL);
            driver.findElement(By.id("next")).click();
        }
        waitForPageToLoad();
        driver.findElement(By.id("Passwd")).sendKeys(ROBOT_PASSWORD);
        driver.findElement(By.id("signIn")).click();
        //some optional actions for reaching gmail inbox
        driver.findElement(By.xpath("//*[@title='Google apps']")).click();
        driver.findElement(By.id("gb23")).click();
        SeleniumUtils.fixedWait(SeleniumUtils.THREE_SECONDS);
        //clicks compose
        driver.findElement(By.cssSelector(".T-I.J-J5-Ji.T-I-KE.L3")).click();
        SeleniumUtils.fixedWait(SeleniumUtils.THREE_SECONDS);
        //type email
        driver.findElement(By.cssSelector("textarea[aria-label='To']")).sendKeys(userEmail); //userEmail rshayda@remit.se
        //type email subject
        driver.findElement(By.cssSelector("input[name='subjectbox']")).sendKeys("Keeping track of your vacation days");
        //types message in body without hampering signature
        driver.findElement(By.cssSelector("div[role='textbox']")).sendKeys("Dear " + userName + ", \n\n");
        getDescTextAndReadFromFile(pathToFile20Days);
        //driver.findElement(By.cssSelector("div[role='textbox']")).sendKeys("\n\nSincerely yours HR department");
        //click send email button
        driver.findElement(By.cssSelector("div[data-tooltip='Send ‪(Ctrl-Enter)‬']")).click();
        //logout flow
        WebElement myDynamicElement = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class*='gb_db gb_R']")));
        driver.findElement(By.cssSelector("a[class*='gb_db gb_R']")).click();
        SeleniumUtils.fixedWait(SeleniumUtils.THREE_SECONDS);
        //press signout button
        driver.findElement(By.id("gb_71")).click();
        SeleniumUtils.fixedWait(SeleniumUtils.THREE_SECONDS);
    }


    public void sendEmailIfUserHasFromTwentyToTwentySixDays(String userName, String userEmail) throws IOException {
        driver.navigate().to("https://accounts.google.com/ServiceLogin?");
        if(isElementPresent()) {
            driver.findElement(By.id("Email")).sendKeys(ROBOT_EMAIL);
            driver.findElement(By.id("next")).click();
        }
        waitForPageToLoad();
        driver.findElement(By.id("Passwd")).sendKeys(ROBOT_PASSWORD);
        driver.findElement(By.id("signIn")).click();
        //some optional actions for reaching gmail inbox
        driver.findElement(By.xpath("//*[@title='Google apps']")).click();
        driver.findElement(By.id("gb23")).click();
        SeleniumUtils.fixedWait(SeleniumUtils.THREE_SECONDS);
        //clicks compose
        driver.findElement(By.cssSelector(".T-I.J-J5-Ji.T-I-KE.L3")).click();
        SeleniumUtils.fixedWait(SeleniumUtils.THREE_SECONDS);
        //type email
        driver.findElement(By.cssSelector("textarea[aria-label='To']")).sendKeys(userEmail); //userEmail
        //type email subject
        driver.findElement(By.cssSelector("input[name='subjectbox']")).sendKeys("Keeping track of your vacation days");
        //types message in body without hampering signature
        driver.findElement(By.cssSelector("div[role='textbox']")).sendKeys("Dear " + userName + ", \n\n");
        getDescTextAndReadFromFile(pathToFile20To26Days);
        //driver.findElement(By.cssSelector("div[role='textbox']")).sendKeys("\n\nSincerely yours HR department");
        //click send email button
        driver.findElement(By.cssSelector("div[data-tooltip='Send ‪(Ctrl-Enter)‬']")).click();
        //logout flow
        WebElement myDynamicElement = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class*='gb_db gb_R']")));
        driver.findElement(By.cssSelector("a[class*='gb_db gb_R']")).click();
        SeleniumUtils.fixedWait(SeleniumUtils.THREE_SECONDS);
        //press signout button
        driver.findElement(By.id("gb_71")).click();
        SeleniumUtils.fixedWait(SeleniumUtils.THREE_SECONDS);
    }

    //read from text file
    public void getDescTextAndReadFromFile(String pathToFile) throws IOException {
        FileReader fileReader = new FileReader(pathToFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String textFile = "";
        //Loop to read all lines one by one from file and print It.

        while ((textFile = bufferedReader.readLine()) != null) {
            if(textFile.trim().equals("")) {
                driver.findElement(By.cssSelector("div[role='textbox']")).sendKeys("\n\n");
            } else {
                driver.findElement(By.cssSelector("div[role='textbox']")).sendKeys(textFile);
            }
        }
    }

}






