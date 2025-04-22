package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Utility;

public class P05_OverViewPage {

    private final By subtotal = By.className("summary_subtotal_label");
    private final By tax = By.className("summary_tax_label");
    private final By total = By.xpath("//div[contains(@class, 'summary_total_label')]");
    // private final By finishbutton = By.xpath("//a[@class='btn_action cart_button']");
    //private final By finishbutton = By.id("finish");
    private final By finishbutton = By.cssSelector("a.btn_action.cart_button");

    private final WebDriver driver;

    public P05_OverViewPage(WebDriver driver) {
        this.driver = driver;
    }

    public float getsubtotal() {
        return Float.parseFloat(Utility.gettext(driver, subtotal).replace("Item total: $", ""));
    }

    public float gettax() {
        return Float.parseFloat(Utility.gettext(driver, tax).replace("Tax: $", ""));
    }

    public float gettotal() {
        return Float.parseFloat(Utility.gettext(driver, total).replace("Total: $", ""));
    }

    public String caiulatetotalprice() {
        return String.valueOf(getsubtotal() + gettax());
    }

    public boolean comparingprice() {
        return caiulatetotalprice().equals(String.valueOf(gettotal()));
    }

    public P06_FinishingOrder clickonfinishbutton() {
        Utility.clickelement(driver, finishbutton);
        return new P06_FinishingOrder(driver);
    }
}
