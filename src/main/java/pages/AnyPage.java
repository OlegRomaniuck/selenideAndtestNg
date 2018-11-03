package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import pages.manager.ManagerMainPage;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by Tester3 on
 */
public interface AnyPage<T> {

    T close();
    default ManagerMainPage goToMain() {
        $(byXpath(".//*[@id='main_tabs']/div[1]//a[1]/span[1]")).click();
        return page(ManagerMainPage.class);
    }

    default boolean waitIs(SelenideElement element, Condition condition) {
        try {
            element.should(condition);
        } catch (UIAssertionError err) {
            return false;
        }
        return true;
    }

}
