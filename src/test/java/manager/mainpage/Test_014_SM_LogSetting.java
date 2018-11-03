package manager.mainpage;

import logic.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.landing.pages.registration.AuthorizationPage;
import pages.manager.ManagerMainPage;
import pages.manager.modal_main_page.LogSettingPage;
import test.utils.Constants;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Tester3
 */
public class Test_014_SM_LogSetting extends TestBase {

    @Test
    public void shouldBeSettParamToLog() {
        AuthorizationPage auth = open("", AuthorizationPage.class);
        ManagerMainPage mainPage = auth.autorization(TestBase.Username, TestBase.Password).waitUntilPageLoad();
        LogSettingPage logP = mainPage.openLog();
        logP.setName(Constants.LOG_NAME);
        logP.setRows(100);
        Assert.assertTrue(logP.assertThatCheckButtonWork(), "all checkbox work");
        logP.save().logOUT();
    }


}
