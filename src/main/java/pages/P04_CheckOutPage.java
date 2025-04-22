package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Utility;

public class P04_CheckOutPage {

    private final WebDriver driver;

    private final By firstname = By.id("first-name");
    private final By lastname = By.id("last-name");
    private final By postalcode = By.id("postal-code");
    //private final By ContinueButton = By.id("continue");
    private final By ContinueButton = By.cssSelector("input.btn_primary.cart_button");


    public P04_CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }

    public P04_CheckOutPage fillinginformationform(String fname, String lname, String zipcode) {
        Utility.senddata(driver, firstname, fname);
        Utility.senddata(driver, lastname, lname);
        Utility.senddata(driver, postalcode, zipcode);
        return this;

    }

    public P05_OverViewPage ClickOnContinueButton() {
        Utility.clickelement(driver, ContinueButton);
        return new P05_OverViewPage(driver);
    }


}
