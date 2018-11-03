package manager.mainpage;

import logic.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.landing.pages.registration.AuthorizationPage;
import pages.manager.ManagerMainPage;
import pages.manager.modal_main_page.TabSettingPage;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Tester3
 */
public class Test_015_SM_Channel_Setting extends TestBase {


    @Test
    public void shouldBeVisibleSomeColumnInChannelPanel() {
        AuthorizationPage auth = open("", AuthorizationPage.class);
        ManagerMainPage mainPage = auth.autorization(TestBase.Username, TestBase.Password).waitUntilPageLoad();
        TabSettingPage tabP = mainPage.openTabSetting();
        tabP.openChannelPannel();
        tabP.clearCheckBoxOnChannelTab();
        tabP.checkBoxDiez();
        tabP.checkBoxGSM();
        tabP.checkBoxRSSI();
        tabP.checkBoxOnline();
        //  tabP.checkboxPort();
        tabP.checkboxSimChannel();
//        tabP.checkboxStatus();
//        tabP.checkboxPause();
//        tabP.checkboxColorChannel();

        ManagerMainPage mainP = tabP.saveButton();
        int expectedCountColumn = 6;
        Assert.assertEquals(mainP.getChannelPanelColumnAmount(), expectedCountColumn, "columns  in panel channel count must be " + expectedCountColumn);
        mainP.logOUT();
    }
}
