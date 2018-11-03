package manager.functional.ussd;

import logic.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.landing.pages.registration.AuthorizationPage;
import pages.manager.ManagerMainPage;
import pages.manager.modal_main_page.SimPanelSendManualUssdPage;
import pages.manager.modal_main_page.SimPanelUssdHistory;
import test.utils.Constants;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Tester3
 */
public class TEST_001_FUNC_Ussd_Manual extends TestBase {

    private ManagerMainPage mainPage;

    @Test
    public void shouldSendUssdManual() {
        //login
        AuthorizationPage auth = open("", AuthorizationPage.class);
        mainPage = auth.autorization(TestBase.Username, TestBase.Password).waitUntilPageLoad();
        //choose sim bank
        mainPage.selectFilterInSimPanelByName(Constants.SI);
        //select first sim
        int first = 0;
        mainPage.selectSimPositionAndContextMenu(first);
        //call send manual ussd
        SimPanelSendManualUssdPage ussdP = mainPage.clickOnSendManualUSSD();
        ussdP.inputUssd("*101#");
        ussdP.clickONButtonSend();
        //get message from modal window
        String actual = ussdP.getMessage();
        mainPage = ussdP.cancel();
        mainPage.selectSimPositionAndContextMenu(first);
        SimPanelUssdHistory simussdP = mainPage.callUssdHistory();
        //get messadge from history
        String actual2 = simussdP.getText();
        mainPage = simussdP.close();
        Assert.assertNotNull(actual2, "Not null message");
        Assert.assertEquals(actual, actual2);
        mainPage.logOUT();
    }
}
