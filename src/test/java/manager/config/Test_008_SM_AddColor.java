package manager.config;

import logic.TestBase;
import org.testng.annotations.Test;
import pages.landing.pages.registration.AuthorizationPage;
import pages.manager.ManagerMainPage;
import pages.manager.config.ColorPage;
import test.utils.Constants;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Tester3 on 14.07.2016.
 */
public class Test_008_SM_AddColor extends TestBase {

    @Test
    public void shouldBeAddNewColor() {
        AuthorizationPage auth = open("", AuthorizationPage.class);
        ManagerMainPage mainPage = auth.autorization(TestBase.Username, TestBase.Password).waitUntilPageLoad();
        ColorPage colorP = (ColorPage) mainPage.openConfigPage(Constants.COLOR);
        colorP.openModuleColorPage();
        colorP.setName(Constants.COLOR_NAME);
        colorP.chooseColor();
        colorP.saveColor();
        colorP.assertThatNameIsPresent(Constants.COLOR_NAME);
        mainPage.logOUT();
    }

}
