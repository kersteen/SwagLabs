package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class P06_FinishingOrder {
    private final WebDriver driver;
    private final By thanksmessage = By.tagName("h2");

    public P06_FinishingOrder(WebDriver driver) {
        this.driver = driver;
        final By thanksmessage = By.tagName("h2");

    }

    public boolean checkingvisibilitythanksmessagez() {
        return findWebElement(driver, thanksmessage).isDisplayed();
    }

    /* private WebElement findWebElement(WebDriver driver, By thanksmessage) {
         return null;


     }*/
    private WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

}
