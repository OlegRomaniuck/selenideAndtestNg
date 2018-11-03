package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.manager.ManagerMainPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;


/**
 * Created by Tester3 on
 */
public interface ConfigPage extends AnyPage<ManagerMainPage> {

    default ConfigPage deleteAllOnThisPage() {
        SelenideElement addButton = getAddButton();
        if (!waitIs(addButton, visible)) {
            ElementsCollection valuesInTable = getTableValues().shouldBe(CollectionCondition.sizeGreaterThan(0));
            if (valuesInTable.size() > 0) {
                String[] names = valuesInTable.getTexts();
                for (int i = valuesInTable.size(); i > 0; i--) {
                    deleteByName(names[i - 1], valuesInTable);
                }
            }
            return this;
        }
        return page(this);
    }


    default ConfigPage deleteByName(String name, ElementsCollection valuesInTable) {
        /* Table */
        valuesInTable.findBy(Condition.text(name)).contextClick();
        /* Context_Menu */
        SelenideElement deleteInMenuButton = getDeleteInMenuButton();
        deleteInMenuButton.shouldBe(Condition.visible).click();
        // Form Delete -> Button Delete;
        $(byXpath("//div[starts-with(@id, 'messagebox-')]/div[3]//a[2]")).shouldBe(Condition.visible, Condition.enabled).click();
        return this;
    }

    ElementsCollection getTableValues();

    SelenideElement getDeleteInMenuButton();

    SelenideElement getAddButton();

}
