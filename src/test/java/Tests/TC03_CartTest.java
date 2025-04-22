package Tests;

import Driverfactory.driverfactoy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.P01_loginpage;
import pages.P02_landingpage;
import pages.P03_CartIconPage;
import utilities.DataUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static Driverfactory.driverfactoy.getDriver;
import static Driverfactory.driverfactoy.setupDriver;
import static utilities.DataUtils.getpropertyvalue;

public class TC03_CartTest {
    private final String Username = DataUtils.getJsonData("validlogin", "Username");
    private final String Password = DataUtils.getJsonData("validlogin", "Password");

    public TC03_CartTest() throws FileNotFoundException {
    }


    @BeforeMethod
    public void setup() throws IOException //fe setup intitiize eldriver
    {
        setupDriver(DataUtils.getpropertyvalue("environment", "Browser"));
        getDriver().get(getpropertyvalue("environment", "Base_Url"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Test
    public void comparngpricesTC() throws IOException {
        String totalprices = new P01_loginpage(getDriver())
                .enterusername(Username)
                .enterpassword(Password)
                .clickloginbutton()
                .addrandomproducts(2, 6)
                .gettotalpricesselected();
        new P02_landingpage(getDriver()).clickoncarticon();

        Assert.assertTrue(new P03_CartIconPage(getDriver()).comparingprices(totalprices));
    }

    //@AfterMethod
    public void quit() {
        driverfactoy.quit();

    }

}
