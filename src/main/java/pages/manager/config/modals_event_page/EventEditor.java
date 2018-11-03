package pages.manager.config.modals_event_page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import pages.AnyPage;
import pages.manager.ManagerMainPage;
import pages.manager.config.EventPage;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by Tester3
 */
public class EventEditor implements AnyPage<ManagerMainPage> {

    @FindBy(name = "name")
    private SelenideElement editFieldName;
    @FindBy(xpath = "//div[contains(@id,'events-test-test-')]/a")
    private SelenideElement buttonSendTest;
    @FindBy(xpath = "//div[contains(@id,'ussd-action-panel-')]//div[contains(@id,'panel-')]/a")
    private SelenideElement buttonAddAction;
    @FindBy(xpath = "//span[text()='Save']/../../..")
    private SelenideElement buttonSave;
    @FindBy(xpath = "//span[text()='Cancel']/../../..")
    private SelenideElement buttonCancel;
    @FindBy(name = "module")
    private SelenideElement dropdownModule;
    @FindBy(name = "method")
    private SelenideElement dropdownAction;
    @FindBy(name = "ussd")
    private SelenideElement dropdownField;
    @FindBy(name = "id")
    private SelenideElement dropdownFilter;

    @Override
    public ManagerMainPage close() {
        return null;
    }

    public EventPage fillNewGeneralData(String nameEvent, String module, String action, String field, String filter) {
        editFieldName.shouldBe(visible).setValue(nameEvent);
        if (module != null) {
            // Click add new module
            buttonAddAction.shouldBe(visible).click();
            dropdownModule.shouldBe(visible).click();
            $(byXpath("//li[text()='" + module + "']")).click();
        }
        if (action != null) {
            dropdownAction.shouldBe(visible).click();
            $(byXpath("//li[text()='" + action + "']")).click();
        }
        if (field != null) {
            dropdownField.shouldBe(visible).click();
            $(byXpath("//li[text()='" + field + "']")).click();
        }
        if (filter != null) {
            dropdownFilter.shouldBe(visible).click();
            $(byXpath("//li[text()='" + filter + "']")).click();
        }

        buttonSave.shouldBe(visible).click();

        buttonSave.waitUntil(disappear, 5000);
        return page(EventPage.class);
    }


}
