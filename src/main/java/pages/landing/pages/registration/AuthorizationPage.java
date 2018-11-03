package pages.landing.pages.registration;

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
public class AuthorizationPage {

    @FindBy(id = "login")
    private SelenideElement login;

    @FindBy(id = "password")
    private SelenideElement password;

    @FindBy(id = "submit_btn")
    private SelenideElement submitButton;

    public void waitUntilPageLoaded() {
        $(byXpath("//a[@href='/manager/register/?language=en']")).shouldBe(visible);
    }

    public ManagerMainPage autorization(String log, String pass) {
        waitUntilPageLoaded();
        login.clear();
        login.sendKeys(log);
        password.clear();
        password.sendKeys(pass);
        submitButton.click();
        return page(ManagerMainPage.class);
    }

}
