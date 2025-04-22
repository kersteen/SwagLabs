package Tests;

import Driverfactory.driverfactoy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.P01_loginpage;
import pages.P02_landingpage;
import utilities.DataUtils;
import utilities.Utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static Driverfactory.driverfactoy.getDriver;
import static Driverfactory.driverfactoy.setupDriver;
import static utilities.DataUtils.getpropertyvalue;

public class TC02_LandingTest {
    private final String Username = DataUtils.getJsonData("validlogin", "Username");
    private final String Password = DataUtils.getJsonData("validlogin", "Password");

    public TC02_LandingTest() throws FileNotFoundException {
    }


    @BeforeMethod
    public void setup() throws IOException //fe setup intitiize eldriver
    {
        setupDriver(DataUtils.getpropertyvalue("environment", "Browser"));
        getDriver().get(getpropertyvalue("environment", "Base_Url"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Test
    public void CheckingNumberOfSelctedProductsTC() throws IOException {
        new P01_loginpage(getDriver())
                .enterusername(Username)
                .enterpassword(Password)
                .clickloginbutton()
                .addproductstocard();
        Assert.assertTrue(new P02_landingpage(getDriver()).comparingnumberofselectedproductwithcart());
    }

    @Test
    public void AddRandomProductsToCart() {
        new P01_loginpage(getDriver())
                .enterusername(Username)
                .enterpassword(Password)
                .clickloginbutton()
                .addrandomproducts(3, 6);
        Assert.assertTrue(new P02_landingpage(getDriver()).comparingnumberofselectedproductwithcart());
    }


    @Test
    public void ClickOnCartIcon() throws IOException {
        new P01_loginpage(getDriver())
                .enterusername(Username)
                .enterpassword(Password)
                .clickloginbutton()
                .clickoncarticon();
        Assert.assertTrue(Utility.verifyurl(getDriver(), DataUtils.getpropertyvalue("environment", "Cart_Url")));
    }

    @AfterMethod
    public void quit() {
        driverfactoy.quit();

    }
}
