package Driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class driverfactoy {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setupDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start--maxmized");
                driverThreadLocal.set(new ChromeDriver(chromeOptions));
                break;
            case "Firefox":
                driverThreadLocal.set(new FirefoxDriver());
                break;
            default:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start--maxmized");
                driverThreadLocal.set(new EdgeDriver(edgeOptions));

        }
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();

    }

    public static void quit() {
        getDriver().quit();
        driverThreadLocal.remove();
    }
}
