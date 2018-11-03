package listeners;

import http.HttpRequest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import test.utils.ToolScreenShotCloud;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by Tester3
 */
public class ListenerResultTestsLanding implements ITestListener {

    private WebDriver driver;
    private Map<String, String> resultForSent = new HashMap<>();

    @Override
    public void onTestFailure(ITestResult result) {
        this.driver = getWebDriver();
        if (driver != null) {
            String name = result.getName();
            ToolScreenShotCloud screenShot = new ToolScreenShotCloud();
            screenShot.makeScreenshot(driver, name);
            String pathScreenCloud = screenShot.getUrlToScreenshotCloud();
            resultForSent.put(name, pathScreenCloud);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        StringBuilder buffer = new StringBuilder();
        int count = 1;
        for (Map.Entry<String, String> test : resultForSent.entrySet()) {
            buffer.append("\n").append(count).append(") ").append(test.getKey()).append("=").append(test.getValue());
            count++;
        }
        String sendMessage = buffer.toString();
        System.out.println(sendMessage);

        if (!resultForSent.isEmpty()) {
            new HttpRequest()
                    .sendPostWithParameters(
                            "http://192.168.0.100:55432/skype/message/",
                            ">>>RESULT<<<" + sendMessage);
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }
}
