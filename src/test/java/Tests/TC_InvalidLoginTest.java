package Tests;

import Driverfactory.driverfactoy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.P_Invalidloginpage;
import utilities.DataUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static Driverfactory.driverfactoy.getDriver;
import static Driverfactory.driverfactoy.setupDriver;
import static utilities.DataUtils.getpropertyvalue;

public class TC_InvalidLoginTest {
    private final String Username = DataUtils.getJsonData("Invalidlogin", "Username");
    private final String Password = DataUtils.getJsonData("Invalidlogin", "Password");

    public TC_InvalidLoginTest() throws FileNotFoundException {
    }

    @BeforeMethod
    public void setup() throws IOException //fe setup intitiize eldriver
    {
        setupDriver(DataUtils.getpropertyvalue("environment", "Browser"));
        getDriver().get(getpropertyvalue("environment", "Base_Url"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Test
    public void InvalidloginTC() throws IOException {
        new P_Invalidloginpage(getDriver())
                .enterusername(Username)
                .enterpassword(Password)
                .clickloginbutton();
        Assert.assertTrue(new P_Invalidloginpage(getDriver()).assertloginTC(getpropertyvalue("environment", "Home_Url")));
    }

    //@AfterMethod
    public void quit() {
        driverfactoy.quit();

    }


}
