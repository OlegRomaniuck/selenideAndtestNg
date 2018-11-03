package logic.driverimplementations;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

/**
 * Created by Tester3 on 01.07.2016.
 */
public class CustomWebDriverProviderFireFox implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        File pathToBinary = new File("src\\main\\resources\\firefoxe driver\\firefox.exe");
        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
        FirefoxProfile profile = new FirefoxProfile();
        //  profile.setAssumeUntrustedCertificateIssuer(false);
        //  profile.setPreference("general.useragent.override", "some UA string");
        profile.setPreference("network.http.phishy-userpass-length", 255);
        profile.setPreference("browser.privatebrowsing.autostart", true);
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        return new FirefoxDriver(ffBinary, profile, capabilities);
    }
}
