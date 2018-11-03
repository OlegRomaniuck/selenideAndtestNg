package pages.manager.reports;

import com.codeborne.selenide.Condition;
import pages.AnyPage;
import pages.manager.ManagerMainPage;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by Tester3
 */
public class SimBlockReport extends ManagerMainPage implements AnyPage<ManagerMainPage> {

    @Override
    public ManagerMainPage close() {
        $(byXpath("//div[@id='sim_block_report_header-body']//img[@class='x-tool-img x-tool-close']")).shouldBe(Condition.visible).click();
        return page(ManagerMainPage.class);
    }

}
