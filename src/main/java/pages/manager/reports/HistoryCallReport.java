package pages.manager.reports;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import pages.manager.ManagerMainPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by Tester3
 */
public class HistoryCallReport extends ManagerMainPage {

    @FindBy(xpath = "//input[@name='intercall']")
    private SelenideElement generated;

    @FindBy(xpath = "//table[starts-with(@id,'radiogroup-')]//td[2]//table[2]//input")
    private SelenideElement radioBTogrid;

    @FindBy(xpath = "//div[starts-with(@id,'container')]//a[2]")
    private SelenideElement render;

    @FindBy(xpath = "//div[starts-with(@id,'pagingtoolbar')]//a[5]")
    private SelenideElement refreshGrid;

    @FindBy(xpath = "//div[starts-with(@id,'grid-')]//table[starts-with(@id,'gridview-')]")
    private SelenideElement grid;

    @FindBy(xpath = "//div[starts-with(@id,'CallHistory')]//img[@class='x-tool-img x-tool-close']")
    private SelenideElement close;

    @FindBy(xpath = "//table[starts-with(@id,'datetimefield-')][1]//td/div/div//table//input[starts-with(@name,'datefield-')]")
    private SelenideElement fromTime;

    @FindBy(xpath = "//table[starts-with(@id,'datetimefield-')][2]//td/div/div//table//input[starts-with(@name,'datefield-')]")
    private SelenideElement toTime;

    @FindBy(name = "billsec__gte")
    private SelenideElement fromDuration;

    @FindBy(name = "billsec__lte")
    private SelenideElement toDuration;

    @FindBy(xpath = "//div[starts-with(@id,'container')]//a[1]")
    private SelenideElement refreshFilter;


    public void chooseGenerated(String ch) {
        generated.click();
        if (ch.equals("yes")) {
            $$(byXpath("//div[starts-with(@id,'boundlist-')]//li")).shouldBe(CollectionCondition.size(3)).get(2).click();
        }
        if (ch.equals("clear")) {
            $$(byXpath("//div[starts-with(@id,'boundlist-')]//li")).shouldBe(CollectionCondition.size(3)).get(0).click();
        }
        if (ch.equals("no")) {
            $$(byXpath("//div[starts-with(@id,'boundlist-')]//li")).shouldBe(CollectionCondition.size(3)).get(1).click();
        }
    }

    public void showOnGrid() {
        radioBTogrid.click();
    }

    public void show() {
        render.click();
    }

    public void refresh() {
        $(refreshGrid).waitUntil(Condition.enabled, 3000).shouldBe(visible).click();
    }

    public int getRowSize() {
        refresh();
        grid.shouldBe(visible);
        return $$(byXpath("//div[starts-with(@id,'grid-')]//table[starts-with(@id,'gridview-')]//tbody//tr")).size();
    }


    public void checkNumberTelephone(String tel, String src) {
        grid.shouldBe(visible);
        $$(byXpath("//div[starts-with(@id,'grid-')]//table[starts-with(@id,'gridview-')]//tr/td[5]")).filter(text(tel));
        //$$(byXpath("//div[starts-with(@id,'grid-')]//table[starts-with(@id,'gridview-')]//tr/td[5]")).shouldHave(CollectionCondition.texts(tel)).filter(v);
        $$(byXpath("//div[starts-with(@id,'grid-')]//table[starts-with(@id,'gridview-')]//tr/td[10]")).shouldHave(CollectionCondition.texts(src));
    }

    @Override
    public ManagerMainPage close() {
        close.click();
        return page(ManagerMainPage.class);
    }

    public void from(String data_to, String s) {
        fromTime.shouldBe(visible).setValue(data_to);
        if (s != null) {
            $(byXpath("//table[starts-with(@id,'datetimefield-')][1]//td/div/div//table//tr/td[1][@class='x-form-trigger-input-cell']/input[starts-with(@id,'timefield')]")).setValue(s);
        }
    }

    public void to(String data_to, String s) {
        toTime.setValue(data_to);
        $(byXpath("//table[starts-with(@id,'datetimefield-')][2]//td/div/div//table//tr/td[1][@class='x-form-trigger-input-cell']/input[starts-with(@id,'timefield')]")).setValue(s);
    }

    public void duration(String duration_from, String duration_to) {
        fromDuration.setValue(duration_from);
        toDuration.setValue(duration_to);

    }

    public void chooseTariff(String testHard) {
        $(byXpath("//div[starts-with(@id,'CallHistory')]//fieldset/div//div/div[2]/span/div/table[1]//table//td[2]/div")).should(visible).click();
        $$(byXpath("//ul[@class='x-list-plain']/li/div/div[@class='map_location_name']")).findBy(text(testHard)).shouldBe(visible).click();
        $(byXpath("//div[starts-with(@id,'CallHistory')]//fieldset/div//div/div[2]/span/div/table[1]//table//td[2]/div")).click();
    }

    public void chooseOriginator(String text) {
        $(byXpath("//div[starts-with(@id,'CallHistory')]//fieldset/div//div/div[2]/span/div/table[2]//table//td[2]/div")).shouldBe(visible).click();
        $$(byXpath("//div[starts-with(@id,'boundlist') and @class='x-boundlist combo_group x-boundlist-floating x-layer x-boundlist-default x-border-box'][2]//li/div"))
                .findBy(text(text))
                .shouldBe(visible, Condition.enabled).click();
        $(byXpath("//div[starts-with(@id,'CallHistory')]//fieldset/div//div/div[2]/span/div/table[2]//table//td[2]/div")).click();


    }

    public void chooseIpOruginator(String s) {
        $(byXpath("//div[starts-with(@id,'CallHistory')]//fieldset/div//div/div[2]/span/div/table[3]//table//td[2]/div")).shouldBe(visible).click();
        $$(byXpath("//div[starts-with(@id,'boundlist') and @class='x-boundlist combo_group x-boundlist-floating x-layer x-boundlist-default x-border-box'][3]//li/div"))
                .findBy(text(s))
                .shouldBe(visible, Condition.enabled).click();
        $(byXpath("//div[starts-with(@id,'CallHistory')]//fieldset/div//div/div[2]/span/div/table[3]//table//td[2]/div")).click();
    }

    public void dstTel(String telf2) {
        $(byXpath("//div[starts-with(@id,'CallHistory')]//fieldset/div//div/div[2]/span/div/table[4]//input")).setValue(telf2);
    }


    public void srcTel(String telf3) {
        $(byXpath("//div[starts-with(@id,'CallHistory')]//fieldset/div//div/div[2]/span/div/table[6]//input")).setValue(telf3);
    }

    public void refreshFilters() {
        refreshFilter.click();

    }

    public void chooseSizeGrid(String s) {
        $(byXpath("//div[starts-with(@id,'pagingtoolbar')]//table[starts-with(@id, 'combobox')]//td[starts-with(@id,'ext')]/div[@class='x-trigger-index-0 x-form-trigger x-form-arrow-trigger x-form-trigger-first']"))
                .shouldBe(visible, enabled).click();
        $$(byXpath("//ul[@class='x-list-plain']/li")).findBy(text(s)).shouldBe(visible).click();
    }

    public void clickOnPaginator() {
        $(byXpath("//div[starts-with(@id,'pagingtoolbar')]//a[3]")).shouldBe(visible).click();
    }

    public HistoryCallReport open() {
        return page(HistoryCallReport.class);
    }
}
