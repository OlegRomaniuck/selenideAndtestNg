package manager.reports;

import http.RestAssureBackend;
import http.pogoclass.FastStatObj;
import http.pogoclass.FastStatRequestObject;
import logic.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.landing.pages.registration.AuthorizationPage;
import pages.manager.ManagerMainPage;
import pages.manager.reports.FastStatReport;
import test.utils.Constants;
import test.utils.JsonCreator;
import test.utils.ToolFormatData;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by
 */
public class TEST_002_Fast_Stat_Report extends TestBase {

    RestAssureBackend rest = new RestAssureBackend();
    String data_to = ToolFormatData.getActualData();
    FastStatRequestObject fastStatRequestObject = new FastStatRequestObject();
    String dataYesterday = ToolFormatData.getYesterdayData();

    @Test(priority = 0)
    public void shouldBeEmptyDataGoip101() {
        String token = rest.getTokenBeforeLogin();
        int goip101RowNumber = 5;
        int acdColumnNumber = 2;

        rest.signInService(token);
        rest.testGetListSimServersStatus200();
        rest.deleteAllFastStat();

        AuthorizationPage auth = open("", AuthorizationPage.class);
        ManagerMainPage mainPage = auth.autorization(TestBase.Username, TestBase.Password).waitUntilPageLoad();
        FastStatReport fastStatReport = (FastStatReport) mainPage.openReports(Constants.FASTSTAT);

        FastStatObj obj = fastStatReport.getObjectDataByNumberRow(goip101RowNumber);

        String actualColorFromACDcolumn = fastStatReport.getColorByRowCell(goip101RowNumber, acdColumnNumber);
        Assert.assertEquals(actualColorFromACDcolumn, "transparent");
        Assert.assertEquals(obj.getACD(), " ");
        Assert.assertEquals(obj.getASR(), " ");
        Assert.assertEquals(obj.getCalls(), " ");
        Assert.assertEquals(obj.getMinutes(), " ");
        Assert.assertEquals(obj.getSuccess(), " ");
        Assert.assertEquals(obj.getClosed(), "0");
        Assert.assertEquals(obj.getInCall(), "0");
        Assert.assertEquals(obj.getInUse(), "0");
        Assert.assertEquals(obj.getLoading(), "0");
        Assert.assertEquals(obj.getChannels(), "0:0:0:0:4");
    }

    //get various param
    @Test(priority = 1)
    public void preConditionsGetParam() {
        String token = rest.getTokenBeforeLogin();
        rest.signInService(token);
        rest.testGetListSimServersStatus200();

        String locName = "test";
        String locationId = rest.getLocationID(locName);

        String getName = "101";
        String gateId = rest.getGateId(getName);

        String tariffName = "testhard";
        String taiffId = rest.getTariffID(tariffName);

        String carrierName = "Kyivstar";
        String carrierId = rest.getCarrierID(carrierName);

        fastStatRequestObject.setLocationId(locationId);
        fastStatRequestObject.setGateId(gateId);
        fastStatRequestObject.setTariffId(taiffId);
        fastStatRequestObject.setCarrierId(carrierId);
    }

    @Test(priority = 2)
    public void shouldBeCreated20callsForGoip101() {
        String token = rest.getTokenBeforeLogin();
        rest.signInService(token);
        rest.testGetListSimServersStatus200();

        fastStatRequestObject.setDuration("00:07:32");
        fastStatRequestObject.setSuccess("1");
        fastStatRequestObject.setCalls("1");

        // Logic for make different time stamp calls
        Random random = new Random();
        int minutes;
        for (int count = 0; count < 20; count++) {
            minutes = 10 + random.nextInt(49);
            System.out.println(minutes);

            String timestamp = data_to + " " + "01:" + minutes + ":00";
            fastStatRequestObject.setTimestamp(timestamp);
            String jsonFastStat = new JsonCreator().createJSONfromFastStat(fastStatRequestObject);
            rest.sendFastStat(jsonFastStat);
        }
    }

    @Test(priority = 3)
    public void shouldBeCorrectDataGoip101() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        int goip101RowNumber = 3;

        refresh();
        ManagerMainPage mainPage = page(ManagerMainPage.class);
        FastStatReport fastStatReport = (FastStatReport) mainPage.openReports(Constants.FASTSTAT);

        FastStatObj obj = fastStatReport.getObjectDataByNumberRow(goip101RowNumber);
        String actualColorFromACDcolumn = fastStatReport.getColorByRowCell(goip101RowNumber, 2);
        String actualColorFromASRcolumn = fastStatReport.getColorByRowCell(goip101RowNumber, 3);

        softAssert.assertEquals(obj.getName(), "101");
        softAssert.assertEquals(obj.getACD(), "7.53");
        softAssert.assertEquals(obj.getASR(), "100");
        softAssert.assertEquals(obj.getCalls(), "20");
        softAssert.assertEquals(obj.getMinutes(), "150.67");
        softAssert.assertEquals(obj.getSuccess(), "20");

        fastStatReport.changeSettings(200, 10, 0, 5);
        fastStatReport.clickShowButton();

        //check that ACD red
        softAssert.assertEquals(actualColorFromACDcolumn, "rgba(255, 132, 137, 0.8)");
        //check that ASR green
        softAssert.assertEquals(actualColorFromASRcolumn, "rgba(52, 255, 137, 0.8)");

        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void testfiltersSettings() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        int goip101RowNumber = 3;

        refresh();
        ManagerMainPage mainPage = page(ManagerMainPage.class);
        FastStatReport fastStatReport = (FastStatReport) mainPage.openReports(Constants.FASTSTAT);

        fastStatReport.changeSettings(200, 10, 0, 5);
        fastStatReport.clickShowButton();

        String actualColorFromACDcolumn = fastStatReport.getColorByRowCell(goip101RowNumber, 2);
        String actualColorFromASRcolumn = fastStatReport.getColorByRowCell(goip101RowNumber, 3);

        //check that ACD red
        softAssert.assertEquals(actualColorFromACDcolumn, "rgba(52, 255, 137, 0.8)");
        //check that ASR green
        softAssert.assertEquals(actualColorFromASRcolumn, "rgba(255, 136, 136, 0.8)");

        FastStatObj obj = fastStatReport.getObjectDataByNumberRow(goip101RowNumber);
        softAssert.assertEquals(obj.getACD(), "7.53");
        softAssert.assertEquals(obj.getASR(), "100");

        fastStatReport.changeSettings(0, 10, 0, 100);
        fastStatReport.clickShowButton();

        actualColorFromACDcolumn = fastStatReport.getColorByRowCell(goip101RowNumber, 2);
        actualColorFromASRcolumn = fastStatReport.getColorByRowCell(goip101RowNumber, 3);

        //check that ACD green
        softAssert.assertEquals(actualColorFromACDcolumn, "rgba(255, 132, 137, 0.8)");
        //check that ASR red
        softAssert.assertEquals(actualColorFromASRcolumn, "rgba(52, 255, 137, 0.8)");

        obj = fastStatReport.getObjectDataByNumberRow(goip101RowNumber);
        softAssert.assertEquals(obj.getACD(), "7.53");
        softAssert.assertEquals(obj.getASR(), "100");

        softAssert.assertAll();
    }

}
