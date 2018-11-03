package pages.manager;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import pages.AnyPage;
import pages.landing.pages.registration.AuthorizationPage;
import pages.manager.config.*;
import pages.manager.config.sms.SMSIncoming;
import pages.manager.config.sms.SMSSending;
import pages.manager.config.sms.SMSTemplatePage;
import pages.manager.modal_main_page.*;
import pages.manager.reports.FastStatReport;
import pages.manager.reports.HistoryCallReport;
import pages.manager.reports.SimBlockReport;
import test.utils.UtilForElements;
import test.utils.ColorUtils;

import java.util.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static test.utils.Constants.*;


/**
 * Created by Tester3
 */
public class ManagerMainPage implements AnyPage<ManagerMainPage> {

    static SelenideElement notice;
    @FindBy(xpath = ".//*[@id='logid-splitter-collapseEl']")
    private SelenideElement splitterLogs;
    @FindBy(id = "report_menu_button")
    private SelenideElement reportsMenuButton;
    @FindBy(id = "add-gateway")
    private SelenideElement addGateway;
    @FindBy(id = "gateway-menu-delete-textEl")
    private SelenideElement deleteGatewayMenu;
    @FindBy(id = "add-location")
    private SelenideElement addLocation;
    @FindBy(id = "location-menu-delete")
    private SelenideElement deleteLocationMenu;
    @FindBy(id = "location-menu-delete-textEl")
    private SelenideElement delteLocationMenu;
    @FindBy(xpath = "//*[@id='main_left_top-body']/div/table/tbody/tr/td[1]/div/span/span[@class='location_text']")
    private ElementsCollection tableArrayLocations;

    @Override
    public ManagerMainPage close() {
        return this;
    }

    public ManagerMainPage waitUntilPageLoad() {
        $(byXpath("//*[@id='logid-splitter-collapseEl']")).waitUntil(Condition.visible, 25000);

        // Waiting when simpanel loads
        $$(byXpath("//div[text()='Loading...']")).get(1).waitUntil(Condition.disappear, 25000);
        closeNoticeAll();
        return this;
    }

    public void closeNoticeAll() {
        // Уведомление Ваша лицензия истекла
        By element = By.xpath(".//*[starts-with(@id, 'messagebox-')]//img");
        if ($(element).exists()) {
            notice = $(element);
            notice.isEnabled();
            notice.click();
        }
        By elemen = By.xpath("//span/div[starts-with(@id, 'patch')]//span[@role='img']");
        if ($(elemen).exists()) {
            notice = $(elemen);
            notice.click();
        }
        // Презентация
        if ($(byXpath("//div[@class='introjs-tooltip']")).exists()) {
            // Если есть окошко, закрываем его
            $(byXpath("//div[@class='introjs-tooltip']//a[text()='Skip']")).shouldBe(visible).click();
            $(byXpath("//div[@class='introjs-tooltip']//a[text()='Skip']")).shouldBe(disappear);
        }
        // Оператор
        if ($(byXpath("//*[@id='chat-invite']/div")).isDisplayed()) {
            // Если есть окошко, закрываем его
            $(byXpath(".//*[@id='chat_no']")).shouldBe(visible).click();
            $(byXpath(".//*[@id='chat_no']")).shouldBe(disappear);
        }
    }

    public AnyPage openReports(String report) {
        $(byId("report_menu_button")).click();
        $$(byXpath("//div[@id='report_menu-targetEl']/div")).shouldHaveSize(7);

        switch (report) {
            case HISTORY_CALL:
                $("#report-menu-call-history-itemEl").should(enabled).click();
                return page(HistoryCallReport.class);
            case FASTSTAT:
                $("#report-menu-faststat-itemEl").should(enabled).click();
                return page(FastStatReport.class);
            case SIMBLOCK:
                $("#report-menu-simblock-itemEl").should(enabled).click();
                return page(SimBlockReport.class);
            case STATISTIC_BY_DAY:
                $("#grouped-call-history-itemEl").should(enabled).click();
                return page(HistoryCallReport.class);
            case TRAFFIC_LOG:
                $("#report-menu-trafficlog-itemEl").should(enabled).click();
                return page(HistoryCallReport.class);
            case SIM_REPORT:
                $("#config-menu-SIMReport-itemEl").should(enabled).click();
                return page(HistoryCallReport.class);
            case GENERATION_NUMBER:
                $("#report-menu-numgen-itemEl").should(enabled).click();
                return page(HistoryCallReport.class);
            default:
                return page(ManagerMainPage.class);
        }
    }

    public AnyPage openConfigPage(String configPage) {

        $(byId("config_menu_button")).shouldBe(visible, enabled).click();

        switch (configPage) {
            case SIMBANK:
                $(byId("config-menu-simbank-textEl")).shouldBe(visible, enabled).click();
                return page(SimBankManagerPage.class);
            case TARIFFS:
                $(byId("config-menu-tariffs-textEl")).shouldBe(visible, enabled).click();
                return page(TariffPage.class);
            case CARRIERS:
                $(byId("config-menu-carriers-textEl")).shouldBe(visible, enabled).click();
                return page(CarriersPage.class);
            case COLOR:
                $(byId("config-menu-colors-textEl")).shouldBe(visible, enabled).click();
                return page(ColorPage.class);
            case USSD:
                $(byId("config-menu-ussd-textEl")).shouldBe(visible, enabled).click();
                return page(UssdPage.class);
            case USSN:
                $(byId("config-menu-ussn-textEl")).shouldBe(visible, enabled).click();
                return page(USSNPage.class);
            case EVENT:
                $(byId("config-menu-events-itemEl")).shouldBe(visible, enabled).click();
                return page(EventPage.class);
            case ROADMAP:
                $(byId("config-menu-road-map-itemEl")).shouldBe(visible, enabled).click();
                return page(RoadMap.class);
            case SMSINCOMMING:
                $(byId("config-menu-sms-itemEl")).shouldBe(visible, enabled).click();
                $(byId("config-menu-get-sms-itemEl")).shouldBe(visible, enabled).click();
                return page(SMSIncoming.class);
            case SMSSENDING:
                $(byId("config-menu-sms-itemEl")).shouldBe(visible, enabled).click();
                $(byId("config-menu-send-sms")).shouldBe(visible, enabled).click();
                return page(SMSSending.class);
            case SMSTEMPLATE:
                $(byId("config-menu-sms-itemEl")).shouldBe(visible, enabled).click();
                $(byId("config-menu-sms-tmplates")).shouldBe(visible, enabled).click();
                return page(SMSTemplatePage.class);
            case SIM_GROUP:
                $(byXpath("//div[starts-with(@id,'menuitem-')]/a")).click();
                $(byId("config-menu-simgroup-itemEl")).shouldBe(visible).click();
                return page(SimGroup.class);
            case SIM_BONUSES:
                $(byXpath("//div[starts-with(@id,'menuitem-')]/a")).click();
                $$(byXpath("//span[@class='x-menu-item-text']")).findBy(text("Bonuses")).click();
                return page(SimBonuses.class);
            case MULTIUSERS:
                $(byId("config-menu-multiuser-itemEl")).shouldBe(visible).click();
                return page(MultiusersPage.class);
            case BLOCKREASONS:
                $(byId("config-menu-block-reason-itemEl")).shouldBe(visible).click();
                return page(BlockReasons.class);
            case RECHARGESIM:
                $(byId("config-menu-recharge-itemEl")).shouldBe(visible).click();
                return page(RechargeSIM.class);
            case EXCHANGE_GROUP:
                $(byId("config-menu-exchange-textEl")).shouldBe(visible).click();
                return page(ExchangeGroup.class);
            case TAGS:
                $(byId("config-menu-tag-itemEl")).shouldBe(visible).click();
                return page(Tags.class);
            case LIST_NUMBERS:
                $(byId("config-menu-black-list-itemEl")).shouldBe(visible).click();
                return page(ListNumbers.class);
            default:
                return page(ManagerMainPage.class);
        }
    }

    public ManagerMainPage deleteAllGetaway() {
        if (!addGateway.has(visible)) {
            // find list with Gateways name
            String deleteGate = ".//*[@id='main_left_bot-body']/div/table/tbody/tr/td/div/span/span[@class='gateway_text']";
            ElementsCollection collect = $$(byXpath(deleteGate));
            if (collect.size() > 0) {
                for (int i = collect.size(); i > 0; i--) {
                    /* Table */
                    SelenideElement table = $(byXpath("//*[@id='main_left_bot-body']/div/table/tbody/tr/td/div/span")).shouldBe(enabled).contextClick();
                    deleteGatewayMenu.shouldBe(visible).click();
                    // set Gateway Name
                    $(byXpath(".//*[@id='delete-form']/div[2]/div/div/table/tbody[2]/tr/td[2]/input")).shouldBe(visible).setValue(table.getText());
                    // Form Delete -> Button Delete;
                    $(byXpath(".//*[@id='delete-form']/div[3]/div/div/a[1]")).shouldBe(enabled).click();
                }
            }
            return this;
        }
        return page(ManagerMainPage.class);
    }

    public ManagerMainPage deleteAllLocations() {
        if (!addLocation.has(visible)) {
            // find list with Gateways name
            ElementsCollection collect = tableArrayLocations;
            if (collect.size() > 0) {
                String[] nameLoc = collect.getTexts();
                for (int i = collect.size(); i > 0; i--) {
                    deleteLocationsByName(nameLoc[i - 1]);
                }
            }
            return this;
        }
        return page(ManagerMainPage.class);
    }

    public void deleteLocationsByName(String text) {
         /* Table */
        tableArrayLocations.findBy(text(text)).shouldBe(visible, enabled).contextClick();
        deleteLocationMenu.shouldBe(visible).click();
        // set Gateway Name
        $(byXpath(".//*[@id='delete-form-body']/div/div/table/tbody[2]/tr/td[2]/input")).shouldBe(visible).setValue(text);
        // Form Delete -> Button Delete;
        $(byId("delete-form-submit-btnIconEl")).shouldBe(enabled).click();
    }

    public AuthorizationPage logOUT() {
        $(byXpath("//*[@id='toolbar-profile']/span")).shouldBe(visible, enabled).click();
        $(byXpath(".//*[@id='toolbar-profile-menu-body']/div[2]/div[2]/div[7]")).waitUntil(enabled, 4000).shouldBe(visible).click();
        return page(AuthorizationPage.class);
    }

    public LocationPage addLocation() {
        if ($(byXpath("//a[@id='add-location']")).isDisplayed()) {
            $(byXpath("//a[@id='add-location']")).shouldBe(enabled).click();
            return page(LocationPage.class);
        } else {
            $(byXpath(".//*[@id='main_left_top-body']/div/table")).shouldBe(visible).contextClick();
            $(byId("location-menu-add-textEl")).shouldBe(visible, enabled).click();

            return page(LocationPage.class);
        }
    }

    public Boolean isLocationByNamePresent(String name) {
        Boolean param = new ArrayList<String>(Arrays.asList($$(byXpath(".//span[@class='location_text']")).shouldBe(CollectionCondition.sizeGreaterThan(0)).getTexts())).contains(name);
        return param;
    }

    public LogSettingPage openLog() {
        $(byXpath(".//*[@id='logid']//a[1]")).shouldBe(visible).contextClick();
        $(byXpath("//div[starts-with(@id, 'menu-')]//div[2]/div[6]/a")).shouldBe(visible, enabled).click();
        return page(LogSettingPage.class);
    }


    public TabSettingPage openTabSetting() {
        $(byXpath(".//*[@id='main_tabs']/div[1]/div[1]/div[2]//a/span[2]")).shouldBe(visible).click();
        $(byXpath(".//div[starts-with(@id, 'menu-')]/div/div[7]")).shouldBe(visible, enabled).click();
        return page(TabSettingPage.class);
    }

    public int getSIMPanelColumnAmount() {
        return $$(byXpath("//div[@id='sim_panel']/div[2]//div[starts-with(@id,'gridcolumn')]/div/span")).shouldHave(CollectionCondition.sizeGreaterThan(2)).filterBy(visible).size();
    }

    public int getChannelPanelColumnAmount() {
//        $(byXpath("//div[@id='main_left_bot']/div/div[1]/div/div[3]//span")).shouldBe(visible).click();
        return $$(byXpath("//div[@id='channel_panel']/div[2]/div/div/div")).filterBy(visible).size();
    }

    public ManagerMainPage selectFilterInSimPanelByName(String name) {
        $(byName("filter-color-inputEl")).shouldBe(visible).click();
        $(byText(name)).click();
        return this;
    }

    public void selectSimPositionAndContextMenu(int first) {
        $$(byXpath("//div[@id='sim_panel']//div/table//tr[starts-with(@id,'gridview-')]/td[4]")).shouldBe(CollectionCondition.sizeGreaterThan(1)).get(first).contextClick();
    }

    public SimPanelSendManualUssdPage clickOnSendManualUSSD() {
        $(byId("sim-menu-sendussd-itemEl")).shouldBe(visible, enabled).click();
        return page(SimPanelSendManualUssdPage.class);
    }

    public ManagerMainPage executeEventByName(String eventName) {
        $(byId("sim-menu-execute-itemEl")).shouldBe(visible, enabled).click();
        $(byId("sim-menu-execute-event-itemEl")).shouldBe(visible, enabled).click();
        $(byId("sim-menu-execute-event-else-itemEl")).shouldBe(visible, enabled).click();
        $$(byXpath("//div[contains(@id,'menu-')]/*[contains(@id,'sim-menu-execute-events')]/span")).findBy(text(eventName)).click();
        return page(SimPanelSendManualUssdPage.class);
    }

    public SimPanelUssdHistory callUssdHistory() {
        $(byId("sim-menu-history-itemEl")).shouldBe(visible).click();
        $(byId("sim-menu-history-ussd-itemEl")).shouldBe(visible, enabled).click();
        return page(SimPanelUssdHistory.class);
    }

    public void execute(String command, String options, String name) {
        $(byId("sim-menu-execute-itemEl")).shouldBe(visible).click();
        $(byId("sim-menu-execute-" + command.toLowerCase() + "-itemEl")).shouldBe(visible).click();
        $(byId("sim-menu-execute-" + command.toLowerCase() + "-" + options.toLowerCase() + "-itemEl")).shouldBe(visible).click();
        $(byXpath("//span[contains(text(),\'" + name + "\')]/..")).shouldBe(visible).click();
    }

    public ManagerMainPage unloadSim() {
        $(byId("sim-menu-forceunload-itemEl")).shouldBe(visible).click();
        return page(ManagerMainPage.class);
    }

    public void clickOnShowAllGateway() {
        // $(byXpath("//div[@id='main_left_bot']//div/div[not(contains(@data-qtip, 'Location')) and not(contains(@data-qtip, 'Name'))]/span")).shouldBe(visible).click();
        //$$(byXpath("//div[@id='main_left_bot']//div/div/span/..")).excludeWith(and("atribue",attribute("@data-qtip", "Location"),attribute("@data-qtip", "Name"))).get(0).shouldBe(visible).click();
        $$(byXpath("//div[@id='main_left_bot']//div/div/span/..")).filterBy(not(attribute("data-qtip", "Location"))).filterBy(not(attribute("data-qtip", "Name"))).get(0).shouldBe(visible).click();
    }

    public String getTextFromChannelPanelByPozitions(int row, int column) {
        String xpath = "//div[@id='channel_panel-body']//table//tr[" + Integer.toString(row) + "]//td[" + Integer.toString(column) + "]";
        return $(byXpath(xpath)).shouldBe(visible).getText();
    }

    public String waitUntilSimLoaded(int pos, String describe, long sec) {
        try {
            if (describe.equals("LOAD")) {
                $$(byXpath("//div[@id='sim_panel']//div/table//tr[starts-with(@id,'gridview-')]/td[4]/div/div")).get(pos).waitUntil(attribute("class", "sim_status sim_icon_orange"), sec);
                $$(byXpath("//div[@id='sim_panel']//div/table//tr[starts-with(@id,'gridview-')]/td[4]/div/div")).get(pos).waitUntil(attribute("class", "sim_status sim_icon_green"), sec);
                return describe;
            }
        } catch (UIAssertionError error) {
            System.out.println("TIME OUT EXCEPTION");
            //   error.printStackTrace();
            return "FAIL TIMEOUT";
        }
        return "FAIL TIMEOUT";
    }

    public String getWsKEY() {
        return $(byXpath("//head/meta[@property='wskey']")).getAttribute("content");
    }

    public SimPanelSendManualSmsPage clickOnSendManualSMS() {
        $(byId("sim-menu-manualsms-itemEl")).shouldBe(visible, enabled).click();
        return page(SimPanelSendManualSmsPage.class);
    }

    public SimPanelSmsHistory callSmsHistory() {
        $(byId("sim-menu-history-itemEl")).shouldBe(visible).click();
        $(byId("sim-menu-history-sms-itemEl")).shouldBe(visible, enabled).click();
        return page(SimPanelSmsHistory.class);
    }

    public ManagerMainPage openHistoryInSimPanel(String text) {
        $(byId("sim-menu-history-itemEl")).shouldBe(visible).click();
        $(byId("sim-menu-history-" + text + "-itemEl")).shouldBe(visible, enabled).click();

        if (text.equals("sms")) {
            return page(SimPanelSmsHistory.class);
        }

        if (text.equals("ussd")) {
            return page(SimPanelUssdHistory.class);
        }
        return page(ManagerMainPage.class);
    }

    public void resizeSIMtable() {
        actions().clickAndHold($(byId("sim_panel-splitter"))).moveToElement($(byId("config_menu_button-btnWrap"))).release().perform();
    }

    public String getTextFromSIMPanelByPozitions(int row, int column) {
        String xpath = "//div[@id='sim_panel-body']//table//tr[" + Integer.toString(row) + "]//td[" + Integer.toString(column) + "]";
        return $(byXpath(xpath)).shouldBe(visible).getText();
    }

    public String[] getColorsSimFromSimPanel() {
        ElementsCollection colorsInSimPanel = $$(byXpath("//div[@id='sim_panel-body']//tbody/tr/td//div[@class='sim-color']"));
        ArrayList<String> elementsColor = new ArrayList<>();
        for (SelenideElement color : colorsInSimPanel) {
            String colorStyle = ColorUtils.getRgbFromString(color.attr("style"));
            elementsColor.add(colorStyle);
        }
        return elementsColor.toArray(new String[elementsColor.size()]);
    }

    public int[] getAllSimId() {
        ElementsCollection idInSimPanel = $$(byXpath("//div[@id='sim_panel-body']//tbody/tr/td[2]"));
        ArrayList<Integer> elementsId = new ArrayList<>();
        for (SelenideElement id : idInSimPanel) {
            elementsId.add(Integer.parseInt(id.getText()));
        }
        return convertIntegers(elementsId);
    }

    public ManagerMainPage changeSomeOptions(Optional<String> action) {
        $(byId("sim-menu-change-itemEl")).shouldBe(visible).click();
        //sim-menu-change-options-itemEl
        //sim-menu-change-pin-itemEl
        //sim-menu-changenote-itemEl
        if (action.isPresent()) {
            $(byId("sim-menu-change-" + action.get() + "-itemEl")).shouldBe(visible).click();
            //ned create other class
            return action.get().equals("options") ? page(SimOptionsPage.class) : action.get().equals("pin") ? page(SimPinPage.class) : page(SimNotePage.class);

        }
        $(byId("sim-menu-color-itemEl")).shouldBe(visible).click();
        return page(ManagerMainPage.class);

    }

    public SimImeiPage imei(String generate) {
        //sim-menu-imei-set-itemEl
        $(byId("sim-menu-imei-itemEl")).shouldBe(visible).click();
        $(byId("sim-menu-imei-" + generate + "-itemEl")).shouldBe(visible).click();
        return generate.equals("set") ? page(SimImeiPage.class) : null;
    }

    public ManagerMainPage saveSetting() {
        $(byXpath("//span[text()='Save']/..")).shouldBe(visible).click();
        return page(ManagerMainPage.class);
    }

    public void selectColorByPosition(int pos) {

        $(byId("sim-menu-color-" + pos + "-itemEl")).shouldBe(visible, enabled).click();
    }

    public ManagerMainPage increaseBalanse(String expected_balanse) {

        $(byId("sim-menu-increase-balance-itemEl")).shouldBe(visible).click();
        $(byXpath("//div[starts-with(@id,'IncreaseBalance-')]//input")).waitUntil(visible, 4000).setValue(expected_balanse);
        return saveSetting();
    }

    public void clickOnGatewayByName(String name) {

        String deleteGate = ".//*[@id='main_left_bot-body']/div/table/tbody/tr/td/div/span/span[@class='gateway_text']";
        $$(byXpath(deleteGate)).find(text(name)).shouldBe(visible).click();

    }

    public void pickOutChannelByPozition(int pos) {
        $(byXpath("//div[@id='channel_panel-body']//table//tr[" + pos + "]//div/div")).shouldBe(visible).click();
    }

    public void selectChannelPositionAndContextMenu(int pos) {

        $(byXpath("//div[@id='channel_panel-body']//table//tr[" + pos + "]//div/div")).shouldBe(visible).contextClick();

    }

    public void searchSimInSimBank() {

        $(byId("channel-menu-searchsim-itemEl")).shouldBe(visible).click();
    }


    public boolean assertThatRowFocused() {
        //$$("#blabla_panel-body tr").filter(and("<special rows>", cssClass("x-grid-row"), cssClass("x-grid-data-row"), cssClass("x-grid-row-focused"), cssClass("x-grid-row-selected")));
        return $(byXpath("//div[@id='sim_panel-body']//tr[contains(@class,'x-grid-row') and contains (@class,'x-grid-data-row') and contains(@class, 'x-grid-row-focused') and contains(@class,'x-grid-row-selected')]")).shouldBe(visible).exists();
    }

    public SimPanelCallHistory showCallStatistics() {

        $(byId("channel-menu-callstatistics-itemEl")).shouldBe(visible).click();
        return page(SimPanelCallHistory.class);
    }

    public void unloadSimByChannel() {
        $(byId("channel-menu-forceunload-itemEl")).shouldBe(visible).click();
    }

    public ManagerMainPage refreshSimPanel() {
        $(byXpath("//a[@id='button-refresh']")).shouldBe(visible).click();
        return  this;
    }

    public void resetCountresByChannel() {
        $(byId("channel-menu-reset-count-itemEl")).shouldBe(visible).click();
    }

    public ManagerMainPage clickOnYesButton() {
        $(byXpath("//span[text()='Yes']/..")).shouldBe(visible).click();
        return page(ManagerMainPage.class);

    }

    public SimPanelSendManualSmsPage clickOnSendManualSMSbyChannel() {

        $(byId("channel-menu-manualsms-itemEl")).shouldBe(visible).click();
        return page(SimPanelSendManualSmsPage.class);
    }

    public String selectRandomColorInFilter() {
        $(byName("filter-color-inputEl")).shouldBe(visible).click();
        // Get all colors from dropDown
        ElementsCollection colors = $$(byXpath("//div[@class='list-type-color']"));
        int countColors = colors.size();
        Random random = new Random();
        // Choose random color
        int randomNumber = random.nextInt(countColors);
        SelenideElement elementColorFromList = colors.get(randomNumber);

        // Get color from this element color
        String styleAttr = elementColorFromList.attr("style");
        styleAttr = ColorUtils.getRgbFromString(styleAttr);

        // Click on this element in dropdown
        elementColorFromList.click();
        return styleAttr;
    }

    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }
}
