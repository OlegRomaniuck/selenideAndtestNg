package manager.config;

import logic.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.landing.pages.registration.AuthorizationPage;
import pages.manager.ManagerMainPage;
import pages.manager.modal_main_page.LocationPage;
import test.utils.Constants;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Tester3 on 13.07.2016.
 */
public class Test_002_SM_AddLocation extends TestBase {

    // add locations with param
    @Test
    public void shouldBeAddedLocation() {
        AuthorizationPage auth = open("", AuthorizationPage.class);
        ManagerMainPage mainPage = auth.autorization(TestBase.Username, TestBase.Password).waitUntilPageLoad();
        LocationPage locationP = mainPage.addLocation();
        locationP.setName(Constants.LOCATION_NAME);
        locationP.setLoadInterval("00:00:30");
        locationP.setLoadIntervalDelta("33");
        locationP.setLimitCall("2");
        locationP.setLimitCallDelta("0");
        locationP.setLimitDuration("00:15:00");
        locationP.setLimitDurationDelta("22");
        locationP.setUnloadInteraval("24:00:00");
        locationP.setUnloadIntervalDelta("10");
        locationP.setUnloadCalls("4");
        locationP.setUnloadCallsDelta("0");
        locationP.setUnloadDuration("01:15:00");
        locationP.setUnloadDurationDelta("0");
        ManagerMainPage mainP = locationP.saveFormLocation();

        Assert.assertTrue(mainP.isLocationByNamePresent(Constants.LOCATION_NAME), "assert that location create with name " + Constants.LOCATION_NAME);

        mainP.logOUT();
    }
}
