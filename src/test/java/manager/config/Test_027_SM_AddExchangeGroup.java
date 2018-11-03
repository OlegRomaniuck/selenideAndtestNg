package manager.config;

import logic.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.landing.pages.registration.AuthorizationPage;
import pages.manager.ManagerMainPage;
import pages.manager.config.ExchangeGroup;
import test.utils.Constants;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by
 */
public class Test_027_SM_AddExchangeGroup extends TestBase {

    @Test
    public void shouldBeAddedExchangeGroup() {
        String nameExchangeGroup = Constants.EXCHANGEGROUP_NAME;
        String masterSimGroup = Constants.SIM_GROUP_NAME;
        String slaveSimGroup = Constants.SIM_GROUP_NAME;
        double maxAmount = 5;
        double minAmount = 1.2;
        double masterMinBalance = 3;
        double slaveMinBalance = 2.3;
        boolean roundUp = false;

        AuthorizationPage auth = open("", AuthorizationPage.class);
        ManagerMainPage mainPage = auth.autorization(TestBase.Username, TestBase.Password).waitUntilPageLoad();
        ExchangeGroup exchangeGroup = (ExchangeGroup) mainPage.openConfigPage(Constants.EXCHANGE_GROUP);

        while (exchangeGroup.findExchangeGroupByName(nameExchangeGroup)) {
            exchangeGroup.deleteByName(nameExchangeGroup, exchangeGroup.getTableValues());
        }

        exchangeGroup.addExchangeGroup(nameExchangeGroup, masterSimGroup, slaveSimGroup,
                maxAmount, minAmount, masterMinBalance, slaveMinBalance, roundUp);

        exchangeGroup.updateTable();

        boolean actual = exchangeGroup.findExchangeGroupByName(nameExchangeGroup);
        Assert.assertTrue(actual);
    }
}
