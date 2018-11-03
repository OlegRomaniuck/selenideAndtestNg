package logic.driverimplementations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by Tester3 on 01.07.2016.
 */
public class DriverMaster {

    private DriverMaster() {

    }

    public static WebDriver getDriver(String driverKey) {
        BrowserType browser = BrowserType.get(driverKey);
        WebDriver driver;
        switch (browser) {
            case FIREFOX:
                driver = new CustomWebDriverProviderFireFox().createDriver(DesiredCapabilities.firefox());
                break;
            case CHROME:
                driver = new CustomWebDriverProviderFireFox().createDriver(DesiredCapabilities.chrome());
                break;
            default:
                driver = getWebDriver();
                break;
        }
        return driver;
    }
}
