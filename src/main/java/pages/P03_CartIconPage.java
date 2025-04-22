package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.LogsUtils;
import utilities.Utility;

import java.util.List;

public class P03_CartIconPage {
    static float TotalPrices = 0;
    private final WebDriver driver;
    private final By PricesOfSelectedLocator = By.xpath("//button[.='REMOVE']//preceding-sibling::div[@class='inventory_item_price']");
    //  private final By CheckOutButton = By.id("checkout");

    private final By CheckOutButton = By.cssSelector("a.btn_action.checkout_button");


    public P03_CartIconPage(WebDriver driver) {
        this.driver = driver;
    }

    public String gettotalprices() {
        try {
            List<WebElement> pricesofselectedproducts = driver.findElements(PricesOfSelectedLocator);
            for (int i = 1; i <= pricesofselectedproducts.size(); i++) {
                By element = By.xpath("//button[.='REMOVE']//preceding-sibling::div[@class='inventory_item_price'][" + i + "]");//dynamic locator
                String fulltext = Utility.gettext(driver, element);
                TotalPrices += Float.parseFloat(fulltext.replace("$", ""));
            }
            return String.valueOf(TotalPrices);

        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    public boolean comparingprices(String price) {
        return gettotalprices().equals(price);
    }


    public P04_CheckOutPage ClickOnCheckOutButton() {
        try {
            Utility.clickelement(driver, CheckOutButton);


            return new P04_CheckOutPage(driver);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return new P04_CheckOutPage(driver);
        }

    }


}



