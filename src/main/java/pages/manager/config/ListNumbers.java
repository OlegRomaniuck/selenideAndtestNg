package pages.manager.config;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import pages.ConfigPage;
import pages.manager.ManagerMainPage;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by Tester3
 */
public class ListNumbers implements ConfigPage {


    @FindBy(id = "add-phone-list")
    private SelenideElement addButton;
    @FindBy(xpath = "//*[@id='phone-panel-body']/div/div[1]//table")
    private SelenideElement tableListNumber;

    @FindBy(id = "phone-list-menu-delete-itemEl")
    private SelenideElement deleteMenu;
/*
    @FindBy(xpath = "/*//*[@id='recharge-panel-body']/div/div[1]//table")
    private SelenideElement tablePool;*/

    @FindBy(xpath = "//*[@id='phone-panel-body']/div/div[1]/div[3]//tbody//tr/td[2]/div")
    private ElementsCollection tableValues;
    @FindBy(xpath = "//div[@id='phone-panel-body']/div[1]/div[1]//div[contains(@id,'toolbar-')]/a")
    private SelenideElement buttonUpdate;
/*

    @FindBy(xpath = "/*/
/*[@id='recharge-panel-body']/div/div[3]/div[3]/div")
    private SelenideElement tableTextMessage;

    @FindBy(xpath = "//div[@id='recharge-panel-body']/div[1]/div[1]//div[contains(@id,'toolbar-')]/a")
    private SelenideElement buttonUpdatePool;

    @FindBy(xpath = "//div[@id='recharge-panel-body']/div/div[3]/div[1]//a")
    private SelenideElement buttonUpdateCodes;
*/


    @Override
    public ElementsCollection getTableValues() {
        return tableValues;
    }

    @Override
    public SelenideElement getDeleteInMenuButton() {
        return deleteMenu;
    }

    @Override
    public SelenideElement getAddButton() {
        return addButton;
    }


    @Override
    public ManagerMainPage close() {
        return null;
    }

    public boolean findNumberByName(String poolName) {
        String[] names = tableListNumber.$$(byXpath("//tbody/tr/td")).shouldBe(CollectionCondition.sizeGreaterThan(1)).getTexts();
        for (String name : names) {
            if (name.equals(poolName)) {
                return true;
            }
        }
        return false;
    }


    public ListNumbers openModuleAddNumber() {
        if (addButton.exists()) {
            addButton.click();
            return this;
        } else {
            $(byXpath(".//*[@id='phone-panel-body']/div/div[1]/div[3]/div")).shouldBe(Condition.enabled).contextClick();
            $(byId("phone-list-menu-add-itemEl")).shouldBe(Condition.visible, Condition.enabled).click();
            return this;
        }
    }

    public void setName(String poolName) {
        $(byName("name")).shouldBe(visible).setValue(poolName);
    }

    public ListNumbers type(String type) {
        $(byName("blacklist")).shouldBe(visible).click();
        $$(byXpath("//ul[@class='x-list-plain']/li")).findBy(text(type)).shouldBe(visible).click();
        return this;
    }

    public ListNumbers priority(String priority) {
        $(byName("priority")).setValue(priority);
        return this;
    }

    public ListNumbers save() {
        $(byXpath("//span[text()='Save']/../../..")).shouldBe(Condition.visible).click();
        $(byXpath("//span[text()='Save']/../../..")).waitUntil(disappear, 5000);
        return page(ListNumbers.class);
    }

    public ListNumbers updateTable() {
        buttonUpdate.shouldBe(visible).click();
        return this;
    }

    public ListNumbers clickOnAddNumberButton() {
        $(byId("add-phone-number")).shouldBe(visible).click();
        return this;
    }

    public ListNumbers setNumber(String number) {

        $(byName("number")).shouldBe(visible).setValue(number);
        return this;
    }

    public boolean findNumber(String numb) {

        String []names=$$(byXpath("//div[starts-with(@id,'phone-number-grid')]//tr/td[2]/div")).shouldBe(CollectionCondition.sizeGreaterThan(1)).getTexts();
        for (String name : names) {
            if (name.equals(numb)) {
                return true;
            }
        }
        return false;
    }

    public void clickOnListNumberByName(String poolName) {

        tableValues.findBy(Condition.text(poolName)).click();

    }
}
