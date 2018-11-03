package manager.functional.ussd;

import logic.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.landing.pages.registration.AuthorizationPage;
import pages.manager.ManagerMainPage;
import pages.manager.modal_main_page.SimPanelUssdHistory;
import test.utils.Constants;
import test.utils.ToolFormatData;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Tester3 on 10.08.2016.
 */
public class TEST_003_FUNC_Ussd_Execute extends TestBase {

    private ManagerMainPage mainPage;

    @Test
    public void shouldBeSendUssdUsingExecuteModule() {
        //login
        AuthorizationPage auth = open("", AuthorizationPage.class);
        mainPage = auth.autorization(TestBase.Username, TestBase.Password).waitUntilPageLoad();
        //choose sim bank
        mainPage.selectFilterInSimPanelByName(Constants.SE);
        //select first sim
        int first = 0;
        mainPage.selectSimPositionAndContextMenu(first);
        //call send manual ussd
        String command = "Ussd";
        String options = "tariff";
        String name = Constants.USSD_NAME;
        mainPage.execute(command, options, name);
        mainPage.selectSimPositionAndContextMenu(first);
        SimPanelUssdHistory simussdP = mainPage.callUssdHistory();

        //get messadge from history
        String message = simussdP.getText();
        String time = simussdP.getDataLastUssd();
        String exactTime = ToolFormatData.getPriciseTime();
        mainPage = simussdP.close();
        Assert.assertNotNull(message, "Not null message");
        Assert.assertEquals((ToolFormatData.getActualData() + " " + exactTime.substring(0, 5)), time.substring(0, 16));
        mainPage.logOUT();
    }
}
