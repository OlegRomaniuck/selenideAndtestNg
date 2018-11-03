package pages.manager.modal_main_page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import pages.manager.ManagerMainPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by Tester3
 */
public class LocationPage extends ManagerMainPage {


    @FindBy(xpath = ".//*[@id='location_win-body']//input[@name='name']")
    private SelenideElement nameInput;

    @FindBy(xpath = ".//*[@id='location_win-body']//input[@name='load_interval']")
    private SelenideElement loadInterval;

    @FindBy(xpath = ".//*[@id='location_win-body']//input[@name='load_interval_delta']")
    private SelenideElement loadIntervalDelta;

    @FindBy(xpath = ".//*[@id='location_win-body']//input[@name='l_calls']")
    private SelenideElement limitCall;

    @FindBy(xpath = ".//*[@id='location_win-body']//input[@name='l_calls_delta']")
    private SelenideElement limitCallDelta;

    @FindBy(xpath = ".//*[@id='location_win-body']//input[@name='l_duration']")
    private SelenideElement limitDuration;

    @FindBy(xpath = ".//*[@id='location_win-body']//input[@name='l_duration_delta']")
    private SelenideElement limitDurationDelta;

    @FindBy(xpath = ".//*[@id='location_win-body']//input[@name='unload_interval']")
    private SelenideElement unloadInteraval;

    @FindBy(xpath = ".//*[@id='location_win-body']//input[@name='unload_interval_delta']")
    private SelenideElement unloadIntervalDelta;

    @FindBy(xpath = ".//*[@id='location_win-body']//input[@name='unload_calls']")
    private SelenideElement unloadCalls;

    @FindBy(xpath = ".//*[@id='location_win-body']//input[@name='unload_calls_delta']")
    private SelenideElement unloadCallsDelta;

    @FindBy(xpath = ".//*[@id='location_win-body']//input[@name='unload_duration']")
    private SelenideElement unloadDuration;

    @FindBy(xpath = ".//*[@id='location_win-body']//input[@name='unload_duration_delta']")
    private SelenideElement unloadDurationDelta;
    @FindBy(id = "location-form-save")
    private SelenideElement locationSaveButton;
    @FindBy(id = "location-form-cancel")
    private SelenideElement locationCancelButton;


    public ManagerMainPage saveFormLocation() {
        locationSaveButton.click();
        locationCancelButton.shouldNotBe(Condition.visible);
        return page(ManagerMainPage.class);
    }

    public ManagerMainPage cancelFormLocation() {
        locationCancelButton.click();
        return page(ManagerMainPage.class);
    }


    public SelenideElement getLimitCall() {
        return limitCall;
    }

    public void setLimitCall(String txt) {
        limitCall.setValue(txt);
    }

    public SelenideElement getLimitCallDelta() {
        return limitCallDelta;
    }

    public void setLimitCallDelta(String txt) {
        limitCallDelta.setValue(txt);
    }

    public SelenideElement getLimitDuration() {
        return limitDuration;
    }

    public void setLimitDuration(String txt) {
        limitDuration.setValue(txt);
    }

    public SelenideElement getLimitDurationDelta() {
        return limitDurationDelta;
    }

    public void setLimitDurationDelta(String txt) {
        limitDurationDelta.setValue(txt);
    }

    public SelenideElement getUnloadInteraval() {
        return unloadInteraval;
    }

    public void setUnloadInteraval(String txt) {
        unloadInteraval.setValue(txt);
    }

    public SelenideElement getUnloadIntervalDelta() {
        return unloadIntervalDelta;
    }

    public void setUnloadIntervalDelta(String txt) {
        unloadIntervalDelta.setValue(txt);
    }

    public SelenideElement getUnloadCalls() {
        return unloadCalls;
    }

    public void setUnloadCalls(String txt) {
        unloadCalls.setValue(txt);
    }

    public SelenideElement getUnloadCallsDelta() {
        return unloadCallsDelta;
    }

    public void setUnloadCallsDelta(String txt) {
        unloadCallsDelta.setValue(txt);
    }

    public SelenideElement getUnloadDuration() {
        return unloadDuration;
    }

    public void setUnloadDuration(String txt) {
        unloadDuration.setValue(txt);
    }

    public SelenideElement getUnloadDurationDelta() {
        return unloadDurationDelta;
    }

    public void setUnloadDurationDelta(String txt) {
        unloadDurationDelta.setValue(txt);
    }

    public String getNameLocation() {
        return nameInput.getText();
    }

    public void setName(String name) {
        nameInput.setValue(name);
    }

    public void setLoadInterval(String txt) {
        loadInterval.setValue(txt);
    }

    public void setLoadIntervalDelta(String txt) {
        loadIntervalDelta.setValue(txt);
    }

    public ManagerMainPage cancelLocation() {
        $(byXpath("//a[@id='location-form-cancel']")).shouldBe(visible).click();
        return page(ManagerMainPage.class);
    }
}
