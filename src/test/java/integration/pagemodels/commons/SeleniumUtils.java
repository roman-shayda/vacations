package integration.pagemodels.commons;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumUtils {
    public static final Integer ONE_SECOND = 1;
    public static final Integer TWO_SECONDS = 2;
    public static final Integer THREE_SECONDS = 3;
    public static final Integer FOUR_SECONDS = 4;
    public static final Integer FIVE_SECONDS = 5;
    public static final Integer SIX_SECONDS = 6;
    public static final Integer SEVEN_SECONDS = 7;


    public synchronized static String generateUniqueProductName() {
        return "SelTestProduct_" + System.nanoTime();
    }

    public static void fixedWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {

        }
    }

    public static boolean isElementExists(WebElement element) {
        try {
            element.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }

        return true;
    }

    public static boolean isElementExists(WebDriver driver, By elementSelector) {
        try {
            driver.findElement(elementSelector);
        } catch (NoSuchElementException ex) {
            return false;
        }

        return true;
    }

    public static boolean isElementExists(WebElement parent, By elementSelector) {
        try {
            parent.findElement(elementSelector);
        } catch (NoSuchElementException ex) {
            return false;
        }

        return true;
    }

    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public static boolean isElementDisplayed(WebDriver driver, By elementSelector) {
        try {
            return driver.findElement(elementSelector).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public static boolean isElementDisplayed(WebElement parent, By elementSelector) {
        try {
            return parent.findElement(elementSelector).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public static String getElementValue(WebElement element) {
        return element.getAttribute("value");
    }

    public static String normalizeStringForComparison(String text) {
        return text.replaceAll("[^a-zA-Z\\d]+", "");
    }

    public static int compareJSSortedStrings(String str1, String str2) {
        return normalizeStringForComparison(str1).compareTo(normalizeStringForComparison(str2));
    }

    public static Boolean isElementEnabled(WebElement element) {
        try {
            if (element.getAttribute("readonly") != null || element.getAttribute("disabled") != null) {
                return false;
            } else {
                return true;
            }
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
