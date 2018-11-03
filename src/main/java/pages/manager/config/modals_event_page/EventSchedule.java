package pages.manager.config.modals_event_page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import pages.AnyPage;
import pages.manager.ManagerMainPage;
import pages.manager.config.EventPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by Tester3
 */
public class EventSchedule implements AnyPage<ManagerMainPage> {

    @FindBy(xpath = "//label[text()='Enabled:']/../..//input")
    private SelenideElement checkboxEnabled;
    @FindBy(xpath = "//label[text()='Closing channel:']/../..//input")
    private SelenideElement checkboxClosingChannel;
    @FindBy(name = "fails_close")
    private SelenideElement editFieldFailsToClose;
    @FindBy(name = "fails_unload")
    private SelenideElement editFieldFailsToUnload;
    @FindBy(name = "fails_block")
    private SelenideElement editFieldFailsToBlock;
    @FindBy(name = "fail_pause")
    private SelenideElement editFieldFailPause;
    @FindBy(name = "fail_pause_delta")
    private SelenideElement editFieldFailPauseDelta;
    @FindBy(name = "order")
    private SelenideElement editFieldOrder;
    @FindBy(xpath = "//label[text()='Use it once:']/../..//input")
    private SelenideElement checkboxUsingOnce;
    @FindBy(xpath = "//label[text()='On load:']/../..//input")
    private SelenideElement checkboxOnLoad;
    @FindBy(name = "calls")
    private SelenideElement editFieldCalls;
    @FindBy(name = "calls_delta")
    private SelenideElement editFieldCallsDelta;
    @FindBy(name = "duration")
    private SelenideElement editFieldDuration;
    @FindBy(name = "duration_delta")
    private SelenideElement editFieldDurationDelta;
    @FindBy(name = "interval")
    private SelenideElement editFieldInterval;
    @FindBy(name = "interval_delta")
    private SelenideElement editFieldIntervalDelta;
    @FindBy(name = "fixed_time")
    private SelenideElement editFieldExactTime;
    @FindBy(xpath = "//label[text()='Skip first interval execution:']/../..//input")
    private SelenideElement checkboxSkipFirstInterval;
    @FindBy(id = "schedule-form-save")
    private SelenideElement buttonSave;

    @Override
    public ManagerMainPage close() {
        return null;
    }

    public EventPage fillNewGeneralData(boolean enabled,
                                        String tariff,
                                        String tariffOption,
                                        boolean closingChannel,
                                        Integer failsToClose,
                                        Integer failsToUnload,
                                        Integer failsToBlock,
                                        String failPause, Integer failPauseDelta,
                                        Integer order,
                                        boolean usingOnce,
                                        boolean onLoad,
                                        String calls, Integer callsDelta,
                                        String duration, Integer durationDelta,
                                        String interval, Integer intervalDelta,
                                        String exactTime,
                                        boolean skipFirstInterval) {
        if (enabled)
            checkboxEnabled.shouldBe(visible).click();
        //choose tariff
        $(byName("tarif_id")).shouldBe(visible).click();
        $$(byXpath("//div[contains(@id,'boundlist-')]//li")).findBy(text(tariff)).click();
        $(byName("tarif_id")).shouldBe(visible).click();

        //choose tariff options
        $(byName("tariff_options")).shouldBe(visible).click();
        $$(byXpath("//div[contains(@id,'boundlist-')]//li")).findBy(text(tariffOption)).click();
        $(byName("tariff_options")).shouldBe(visible).click();

        if (closingChannel)
            checkboxClosingChannel.should(visible).click();

        editFieldFailsToClose.shouldBe(visible).setValue(String.valueOf(failsToClose));
        editFieldFailsToUnload.shouldBe(visible).setValue(String.valueOf(failsToUnload));
        editFieldFailsToBlock.shouldBe(visible).setValue(String.valueOf(failsToBlock));
        editFieldFailPause.shouldBe(visible).setValue(failPause);
        editFieldFailPauseDelta.shouldBe(visible).setValue(String.valueOf(failPauseDelta));
        editFieldOrder.shouldBe(visible).setValue(String.valueOf(order));

        if (usingOnce)
            checkboxUsingOnce.shouldBe(visible).click();
        if (onLoad)
            checkboxOnLoad.shouldBe(visible).click();

        editFieldCalls.shouldBe(visible).setValue(calls);
        editFieldCallsDelta.shouldBe(visible).setValue(String.valueOf(callsDelta));

        editFieldDuration.shouldBe(visible).setValue(duration);
        editFieldDurationDelta.shouldBe(visible).setValue(String.valueOf(durationDelta));
        editFieldInterval.shouldBe(visible).setValue(interval);
        editFieldIntervalDelta.shouldBe(visible).setValue(String.valueOf(intervalDelta));
        editFieldExactTime.shouldBe(visible).setValue(exactTime);
        if (skipFirstInterval)
            checkboxSkipFirstInterval.shouldBe(visible).click();
        buttonSave.shouldBe(visible).click();

        buttonSave.waitUntil(disappear, 5000);
        return page(EventPage.class);
    }

}
