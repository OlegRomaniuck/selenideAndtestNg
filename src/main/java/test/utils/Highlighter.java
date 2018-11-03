package test.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Created by Tester3
 */
public class Highlighter extends AbstractWebDriverEventListener {

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        highlight(element, "orange");
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        highlight(element, "red");
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        highlight(element, "green");
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver) {
        highlight(element, "orange");
    }

    public static <T extends WebElement> T highlight(T element) {
        return highlight(element, "orange");
    }

    public static <T extends WebElement> T highlight(T element, final String color) {
        if (element != null && element.getAttribute("__selenideHighlighting") == null) {
            for (int i = 1; i < 4; i++) {
                transform(element, color, i);
                sleep(50);
            }
            for (int i = 4; i > 0; i--) {
                transform(element, color, i);
                sleep(50);
            }
        }
        return element;
    }

    private static void transform(WebElement element, String color, int i) {
        executeJavaScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 5px solid red;");
    }
}
