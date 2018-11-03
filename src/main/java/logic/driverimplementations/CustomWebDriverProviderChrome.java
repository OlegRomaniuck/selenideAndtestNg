package logic.driverimplementations;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

/**
 * Created by Tester3 on 01.07.2016.
 */
public class CustomWebDriverProviderChrome implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-data-dir=./src/test/profiles/chrome/testProfile/");
        chromeOptions.addExtensions(new File("./src/test/profiles/chrome/extentions/bugmagnet.crx"));
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("disable-popup-blocking", "true");
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return new ChromeDriver(capabilities);
    }
}
