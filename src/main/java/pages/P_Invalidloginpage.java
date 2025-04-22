package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Utility;

public class P_Invalidloginpage {
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginbutton = By.id("login-button");

    private final WebDriver driver;

    public P_Invalidloginpage(WebDriver driver) {
        this.driver = driver;
    }

    public P_Invalidloginpage enterusername(String usernametext) {
        Utility.senddata(driver, username, usernametext);
        return this;
    }

    public P_Invalidloginpage enterpassword(String passwordtext) {
        Utility.senddata(driver, password, passwordtext);
        return this;
    }

    public P02_landingpage clickloginbutton() {
        Utility.clickelement(driver, loginbutton);
        return new P02_landingpage(driver);
    }

    public boolean assertloginTC(String expectedvalue) {
        return driver.getCurrentUrl().equals(expectedvalue);
    }
}
