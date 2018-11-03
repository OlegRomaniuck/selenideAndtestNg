package logic;

import logic.driverimplementations.DriverMaster;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import test.utils.Highlighter;
import test.utils.UtilPropertyLoader;

import java.util.logging.Logger;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.*;

/**
 * Created by Tester3
 */
public abstract class TestBase {

    public static final Logger LOG = Logger.getLogger(TestBase.class.getName());
    public static String Username = UtilPropertyLoader.loadProperty("user.username");
    public static String Password = UtilPropertyLoader.loadProperty("user.password");
    public static String Base_URL = UtilPropertyLoader.loadProperty("site.url");

    // @Rule
    // public ScreenShooter screenShooter = ScreenShooter.failedTests();
    @BeforeClass
    @Parameters({"browser"})
    public static void openStartPage(@Optional("selenide") String browser) {
        baseUrl = Base_URL;
        setWebDriver(DriverMaster.getDriver(browser));
        timeout = 15000;
        collectionsTimeout = 12000;
        startMaximized = false;
        browserSize = "1280x1024";
        addListener(new Highlighter());
    }

    protected static void waitUntilPagesIsLoaded() {
        $(byText("Loading")).waitUntil(disappears, 25000);
    }

    @AfterClass
    public static void logout() {
        closeWebDriver();
    }

    private static void login() {
        $("#Email").val(Username).pressEnter();
        $("#Passwd").val(Password);
        $("#signIn").click();
        $(".error-msg").waitUntil(disappears, 2000);
    }

    private static void openURL() {

    }
}
