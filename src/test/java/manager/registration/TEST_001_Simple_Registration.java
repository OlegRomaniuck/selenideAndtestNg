package manager.registration;

import com.codeborne.selenide.Condition;
import logic.TestBase;
import org.testng.annotations.Test;
import pages.landing.pages.registration.AuthorizationPage;
import pages.manager.ManagerMainPage;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertTrue;

/**
 * Created by Tester3
 */

//@Listeners(ListenerScreenshotSentHTTP.class)
public class TEST_001_Simple_Registration extends TestBase {

    @Test
    public void simpleRegistration() {
        AuthorizationPage auth = open("", AuthorizationPage.class);
        ManagerMainPage mainPage = auth.autorization(TestBase.Username, TestBase.Password);
        mainPage.waitUntilPageLoad();
        assertTrue($(byXpath("//*[@id='sim_panel']")).should(Condition.visible).exists());
    }

}
