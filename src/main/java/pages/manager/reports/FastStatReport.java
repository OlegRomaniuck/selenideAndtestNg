package pages.manager.reports;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import http.pogoclass.FastStatObj;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import pages.manager.ManagerMainPage;

import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by
 */
public class FastStatReport extends ManagerMainPage {

    @FindBy(xpath = "//div[@id='fast_stat_panel']//input[@name='day']")
    private SelenideElement editTextDay;

    @FindBy(xpath = "//div[@id='fast_stat_panel']//input[@name='hour']")
    private SelenideElement editTextHour;

    @FindBy(xpath = "//div[@id='fast_stat_panel']//input[@name='minute']")
    private SelenideElement editTextMinute;

    @FindBy(xpath = "//div[contains(@id,'form-')]/a")
    private SelenideElement buttonShow;

    @FindBy(xpath = "//div[@id='fast_stat_panel']//a[contains(@class,'button_settings')]")
    private SelenideElement buttonSettings;

    @FindBy(xpath = "//div[@id='fast_stat_panel']//div[contains(@id,'-innerCt')]/a[1]")
    private SelenideElement button15minutes;

    @FindBy(xpath = "//div[@id='fast_stat_panel']//div[contains(@id,'-innerCt')]/a[2]")
    private SelenideElement button30minutes;

    @FindBy(xpath = "//div[@id='fast_stat_panel']//div[contains(@id,'-innerCt')]/a[3]")
    private SelenideElement button60minutes;

    @Override
    public ManagerMainPage close() {
        $(byXpath("//div[starts-with(@id,'WindowTab-')]//img[contains(@class, 'x-tool-close')]")).shouldBe(visible).click();
        return page(ManagerMainPage.class);
    }

    public void chooseFilter(String filterName, Integer days, Integer hours, Integer minutes) {
        $(byXpath("//div[@id='fast_stat_panel']//table[contains(@id,'combobox-')]//input")).should(enabled).click();
        $(byXpath("//ul[@class='x-list-plain']/li[text()='" + filterName + "']")).should(enabled).click();
        if (filterName.equals("День/Час/Минуты")) {
            editTextDay.shouldBe(enabled).setValue(String.valueOf(days));
            editTextHour.shouldBe(enabled).setValue(String.valueOf(hours));
            editTextMinute.shouldBe(enabled).setValue(String.valueOf(minutes));
            buttonShow.shouldBe(enabled).click();
        } else if (filterName.equals("15/30/60")) {
            switch (minutes) {
                case 15:
                    button15minutes.shouldBe(enabled).click();
                    break;
                case 30:
                    button30minutes.shouldBe(enabled).click();
                    break;
                case 60:
                    button60minutes.shouldBe(enabled).click();
                    break;
                default:
                    throw new NoSuchElementException("This button by minute doesn't exist in this filter (" + filterName + ") - " + minutes);
            }
        }
    }

    public String[] getDataFromTableByColon(int colonNumber) {
        return $$(byXpath(".//tbody[contains(@id,'treeview-')]/tr/td[" + colonNumber + "]")).shouldBe(CollectionCondition.sizeGreaterThan(3)).getTexts();
    }

    public FastStatObj getObjectDataByNumberRow(int i) {
        List<SelenideElement> rowByNumber = $$(byXpath("//div[starts-with(@id,'fastStat-grid')]//tbody/tr[" + i + "]/td"));
        FastStatObj obj = new FastStatObj();
        obj.setName(rowByNumber.get(0).getText());
        obj.setACD(rowByNumber.get(2).getText());
        obj.setASR(rowByNumber.get(3).getText());
        obj.setCalls(rowByNumber.get(4).getText());
        obj.setMinutes(rowByNumber.get(5).getText());
        obj.setSuccess(rowByNumber.get(6).getText());
        obj.setClosed(rowByNumber.get(7).getText());
        obj.setInUse(rowByNumber.get(9).getText());
        obj.setInCall(rowByNumber.get(8).getText());
        obj.setLoading(rowByNumber.get(10).getText());
        obj.setChannels(rowByNumber.get(11).getText());
        return obj;
    }

    public String getColorByRowCell(int row, int cell) {
        List<SelenideElement> el = $$(byXpath("//div[starts-with(@id,'fastStat-grid')]//tbody/tr[" + row + "]/td/div"));
        return el.get(cell).getCssValue("background-color");
    }

    public void changeSettings(Integer ASRmin, Integer ASRmax, Integer ACDmin, Integer ACDmax) {
        buttonSettings.click();

        if (ASRmin != null)
            $(byXpath("//div[@id='fast_stat_settings']//table//input[@name='asr_min']")).shouldBe(visible).setValue(String.valueOf(ASRmin));
        if (ASRmax != null)
            $(byXpath("//div[@id='fast_stat_settings']//table//input[@name='asr_max']")).shouldBe(visible).setValue(String.valueOf(ASRmax));
        if (ACDmin != null)
            $(byXpath("//div[@id='fast_stat_settings']//table//input[@name='acd_min']")).shouldBe(visible).setValue(String.valueOf(ACDmin));
        if (ACDmax != null)
            $(byXpath("//div[@id='fast_stat_settings']//table//input[@name='acd_max']")).shouldBe(visible).setValue(String.valueOf(ACDmax));

        $(byXpath("//div[@id='fast_stat_settings']//a[1]")).shouldBe(visible).click();
    }

    public void clickShowButton() {
        buttonShow.click();
    }
}
