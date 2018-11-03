package pages.manager.config;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.ConfigPage;
import pages.manager.ManagerMainPage;
import test.utils.ToolMakeNumbers;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by Tester3
 */
public class ColorPage implements ConfigPage {

    @FindBy(id = "add-color")
    private SelenideElement addColorButton;

    @FindBy(id = "color-menu-delete")
    private SelenideElement deleteMenu;

    @FindBy(xpath = "//*[@id='color_body-body']/div/div[3]//tbody//td[2]/div")
    private ElementsCollection tableValues;

    @Override
    public ManagerMainPage close() {
        return null;
    }

    public ColorPage openModuleColorPage() {
        if (addColorButton.exists()) {
            addColorButton.click();
            return this;
        } else {
            $(byXpath(".//*[@id='color_body-body']/div/div[3]/div")).shouldBe(Condition.enabled).contextClick();
            $(byXpath(".//*[@id='color-menu-add']/a")).shouldBe(Condition.visible, Condition.enabled).click();
            return this;
        }
    }

    public void setName(String colorName) {
        $(byXpath(".//*[@id='color_win']//input[@name='name']")).setValue(colorName);
    }

    public void chooseColor() {
        $(byXpath("//table[starts-with(@id,'colorfield-')]//input")).click();
        int i = ToolMakeNumbers.getRandomFromOneToNumber(70);
        int x = ToolMakeNumbers.getRandomFromOneToNumber(i) + ToolMakeNumbers.getRandomFromOneToNumber(i) + ToolMakeNumbers.getRandomFromOneToNumber(i) + ToolMakeNumbers.getRandomFromOneToNumber(i);
        int y = ToolMakeNumbers.getRandomFromOneToNumber(i) + ToolMakeNumbers.getRandomFromOneToNumber(i) + ToolMakeNumbers.getRandomFromOneToNumber(i) + ToolMakeNumbers.getRandomFromOneToNumber(i);
        int z = ToolMakeNumbers.getRandomFromOneToNumber(40);
        SelenideElement el = $(byXpath(".//div[starts-with(@id, 'advancedcolorpicker-')]/div[1]/span[1]//span[1]/div")).should(Condition.visible);
        //move first picker
        Actions act0 = new Actions(getWebDriver());
        act0.clickAndHold(el).moveToElement(el, x, y - y / 10).release().build().perform();
        SelenideElement colorCursor = $(byXpath(".//div[starts-with(@id, 'advancedcolorpicker-')]/div[2]/span[1]//span[1]/div"));
        //move second picker by Z
        Actions act1 = new Actions(getWebDriver());
        act1.clickAndHold(colorCursor).moveByOffset(0, z).release().build().perform();
        // button WebSafe
        $(byXpath(".//div[starts-with(@id, 'advancedcolorpicker-')]/a[2]")).shouldBe(Condition.enabled).click();
        // button OK
        $(By.xpath(".//div[starts-with(@id, 'advancedcolorpicker-')]/a[1]")).shouldBe(Condition.enabled).click();
    }

    public ColorPage saveColor() {
        $(byId("color-form-save")).waitUntil(Condition.visible, 3000).shouldBe(Condition.enabled).click();
        $(byId("color-form-save")).shouldNotBe(Condition.visible);
        $(byText("Color with the same name already exists")).shouldNotBe(Condition.visible, Condition.enabled);
        return page(ColorPage.class);
    }

    public String[] getNameColors() {
        return $$(byXpath(".//*[@id='color_body-body']/div/div[3]/div//tr/td[2]/div")).getTexts();
    }

    public void assertThatNameIsPresent(String color) {
        $$(byXpath(".//*[@id='color_body-body']/div/div[3]/div//tr/td[2]/div")).findBy(Condition.text(color));
    }

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
        return addColorButton;
    }
}
