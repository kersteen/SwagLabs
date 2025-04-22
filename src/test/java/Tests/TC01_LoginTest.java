package Tests;

import Driverfactory.driverfactoy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.P01_loginpage;
import utilities.DataUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static Driverfactory.driverfactoy.getDriver;
import static Driverfactory.driverfactoy.setupDriver;
import static utilities.DataUtils.getpropertyvalue;

public class TC01_LoginTest {
    private final String Username = DataUtils.getJsonData("validlogin", "Username");
    private final String Password = DataUtils.getJsonData("validlogin", "Password");

    public TC01_LoginTest() throws FileNotFoundException {
    }

    @BeforeMethod
    public void setup() throws IOException //fe setup intitiize eldriver
    {
        setupDriver(DataUtils.getpropertyvalue("environment", "Browser"));
        getDriver().get(getpropertyvalue("environment", "Base_Url"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Test
    public void validloginTC() throws IOException {
        new P01_loginpage(getDriver())
                .enterusername(Username)
                .enterpassword(Password)
                .clickloginbutton();
        Assert.assertTrue(new P01_loginpage(getDriver()).assertloginTC(getpropertyvalue("environment", "Home_Url")));
    }

    //@AfterMethod
    public void quit() {
        driverfactoy.quit();

    }


}
