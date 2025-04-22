package Tests;

import Driverfactory.driverfactoy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utilities.DataUtils;
import utilities.Utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static Driverfactory.driverfactoy.getDriver;
import static Driverfactory.driverfactoy.setupDriver;
import static utilities.DataUtils.getpropertyvalue;

public class TC06_FinishingTC {
    private final String Username = DataUtils.getJsonData("validlogin", "Username");
    private final String Password = DataUtils.getJsonData("validlogin", "Password");
    private final String FirstName = DataUtils.getJsonData("Information", "fname") + "-" + Utility.gettimestamp();
    private final String LastName = DataUtils.getJsonData("Information", "lname") + "-" + Utility.gettimestamp();
    private final String ZipCode = DataUtils.getJsonData("Information", "zipcode");  //a3meha faker


    public TC06_FinishingTC() throws FileNotFoundException {
    }


    @BeforeMethod
    public void setup() throws IOException //fe setup intitiize eldriver
    {
        setupDriver(DataUtils.getpropertyvalue("environment", "Browser"));
        getDriver().get(getpropertyvalue("environment", "Base_Url"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Test
    public void CheckOutStepOneTC() throws IOException {

        //TODO:Login page
        new P01_loginpage(getDriver())
                .enterusername(Username)
                .enterpassword(Password)
                .clickloginbutton();
        //TODO:Landing Page
        new P02_landingpage(getDriver())
                .addrandomproducts(2, 6)
                .clickoncarticon();
        //TODO:CheckoutPage
        new P03_CartIconPage(getDriver())
                .ClickOnCheckOutButton();
        //TODO:Filling Information
        new P04_CheckOutPage(getDriver())
                .fillinginformationform(FirstName, LastName, ZipCode)
                .ClickOnContinueButton();
        //TODO:FinishingButton
        new P05_OverViewPage(getDriver())
                .clickonfinishbutton();


        Assert.assertTrue(new P06_FinishingOrder(getDriver()).checkingvisibilitythanksmessagez());
    }

    //  @AfterMethod
    public void quit() {
        driverfactoy.quit();

    }
}
