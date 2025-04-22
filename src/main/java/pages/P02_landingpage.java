package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.LogsUtils;
import utilities.Utility;

import java.util.List;
import java.util.Set;

public class P02_landingpage {
    static float TotalPrices = 0;
    private static List<WebElement> allproducts;
    private static List<WebElement> selectedproducts;


    private final By addtocartbuttonforallproducts = By.xpath("//button[@class]");
    private final By numberproductoncarticon = By.className(".shopping_cart_badge");
    private final By numberofselectedproducts = By.xpath("//button[.='remove']");
    private final By CartIcon = By.className("shopping_cart_link");
    private final By PricesOfSelectedLocator = By.xpath("//button[.='REMOVE']//preceding-sibling::div[@class='inventory_item_price']");
    private final WebDriver driver;


    public P02_landingpage(WebDriver driver) {

        this.driver = driver;
    }


    public P02_landingpage addproductstocard() {
        allproducts = driver.findElements(addtocartbuttonforallproducts);//6 1,2,3,4,5,6
        LogsUtils.info("number of all products:" + allproducts.size());
        //dynamic locator fe 7aga fe elnos btt8er hna index[i] number of add tocart
        //dynamic locator fe i index by match ma3 elemnt
        for (int i = 1; i <= allproducts.size(); i++) {
            By addtocardbuttonforallproducts = By.xpath("(//button[@class])[" + i + "]");
            Utility.clickelement(driver, addtocardbuttonforallproducts);
        }
        return this;
    }


    public String getnumberofproductsicon() {
        try {
            LogsUtils.info("numberofproductsonicon:" + Utility.gettext(driver, numberproductoncarticon));
            return Utility.gettext(driver, numberproductoncarticon);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    public String getnumberofselctedproducts() {
        try {
            LogsUtils.info("selectedproducts" + String.valueOf(selectedproducts.size()));
            selectedproducts = driver.findElements(numberofselectedproducts);
            return String.valueOf(selectedproducts.size());
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }

    }

    public P02_landingpage addrandomproducts(int numberofproductsneeded, int numberallproducts) {
        Set<Integer> randomnumbers = Utility.generateuniquenumber(numberofproductsneeded, numberallproducts);
        for (int random : randomnumbers) {
            LogsUtils.info("randomnumber" + random);
            By addtocardbuttonforallproducts = By.xpath("(//button[@class])[" + random + "]");//dynamic locator
            Utility.clickelement(driver, addtocardbuttonforallproducts);
        }
        return this;
    }

    public boolean comparingnumberofselectedproductwithcart() {
        return getnumberofproductsicon().equals(getnumberofproductsicon());
    }

    public P03_CartIconPage clickoncarticon() {
        Utility.clickelement(driver, CartIcon);
        return new P03_CartIconPage(driver);
    }


    public String gettotalpricesselected() {
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
}

