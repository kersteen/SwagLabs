package utilities;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Utility {


    private static final File SCREANSHOOTS_PATH = new File("Test-outputs/screanshoots");

    public static void clickelement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))// 1-bn3ml wait( 2abl m aclick 3la element b wait yba2a clickable)
                .until(ExpectedConditions.elementToBeClickable(locator));

        driver.findElement(locator).click();//selenium  2- find element 3- click on element
    }


    public static void senddata(WebDriver driver, By locator, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));

        driver.findElement(locator).sendKeys(text);
    }

    public static String gettext(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    public static WebDriverWait generalwait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(5));

    }

    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoview();", findwebelement(driver, locator));
    }

    public static WebElement findwebelement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public static void selectingfromdropdown(WebDriver driver, By locator, String option) {
        new Select(findwebelement(driver, locator)).selectByVisibleText(option);
    }

    public static String gettimestamp() {
        return new SimpleDateFormat("yyyy-mm-dd-h-m-ssa").format(new Date());
    }

    public static void screanshoot(WebDriver driver, String screanshootname) throws IOException {
        try {
            File screanSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);//a5dt screan bt3ty w mtshala fe screanSrc
            File screanDest = new File(SCREANSHOOTS_PATH + screanshootname + "-" + gettimestamp() + ".png");
            FileUtils.copyFile(screanSrc, screanDest);
            Allure.addAttachment(screanshootname, Files.newInputStream(Path.of(screanDest.getPath())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int generaterandomnumber(int upperBound) {
        return new Random().nextInt(upperBound) + 1;
    }

    public static Set<Integer> generateuniquenumber(int numberofproductsneeded, int numberallproducts)//ex 5(productnumberneeded) ,50 (numberallproduct)
    {
        //initialize of set
        Set<Integer> generatednumbers = new HashSet<>();
        while (generatednumbers.size() < numberofproductsneeded) {
            int randomnumber = generaterandomnumber(numberallproducts);
            generatednumbers.add(randomnumber);
        }
        return generatednumbers;
    }

    public static boolean verifyurl(WebDriver driver, String expectedurl) {
        try {
            generalwait(driver).until(ExpectedConditions.urlToBe(expectedurl));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
