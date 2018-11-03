package test.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by Tester3
 */
public class UtilForElements {

    public static boolean isElementPresent(By ele) {
        try {
            getWebDriver().findElement(ele);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
