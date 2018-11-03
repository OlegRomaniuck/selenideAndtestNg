package listeners;

import in.ashwanthkumar.slack.webhook.Slack;
import in.ashwanthkumar.slack.webhook.SlackMessage;
import logic.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import test.utils.ToolScreenShotCloud;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by Tester3
 */
public class ListenerResultTestsManager implements ITestListener {

    private WebDriver driver;
    private Map<String, String> resultTestFails = new HashMap<>();

    private String webHookUrl="https://hooks.slack.com/services/sdshriHIIUHDldjskldjskldksjdsjdsjdksj";
    private String developChannel = "tests_reports";
    private String testerChannel = "broken_tests_report";
    private String displayName = "Automation tests";

    @Override
    public void onTestFailure(ITestResult result) {
        this.driver = getWebDriver();
        // Making screenshot and load on cloud if test is failure
        // Save Test name and link to screenshot on cloud in map (@resultTestFails)
        if (driver != null) {
            String name = result.getName();
            ToolScreenShotCloud screenShot = new ToolScreenShotCloud();
            screenShot.makeScreenshot(driver, name);
            String pathScreenCloud = screenShot.getUrlToScreenshotCloud();
            resultTestFails.put(name, pathScreenCloud);
        }
    }


    @Override
    public void onFinish(ITestContext context) {
        if (resultTestFails.isEmpty()) {
            String xml = context.getCurrentXmlTest().getSuite().getName();
            System.out.println(context.getCurrentXmlTest().getParallel());
            try {
                new Slack(webHookUrl)
                        .icon(":smiling_imp:")
                        .sendToChannel(testerChannel)
                        .displayName(displayName)
                        .push(new SlackMessage().bold("Regression Smoke tests successfully passed!").text("\nName of suite is " + xml));

                // Если тесты будут запускаться на stage - то результаты нужны и в develop channal Slack
                if(TestBase.Base_URL.equals("https://stage.g.com/manager/")){
                    new Slack(webHookUrl)
                            .icon(":smiling_imp:")
                            .sendToChannel(developChannel)
                            .displayName(displayName)
                            .push(new SlackMessage().bold("Regression Smoke tests on >>>STAGE<<< successfully passed!").text("\nName of suite is " + xml));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        // If tests were failed send result by skype to developers
        int countOfTests = context.getAllTestMethods().length;
        int failedTests = context.getFailedTests().size();
        double myPercentage = (failedTests * 1.0 / countOfTests) * 100;
        String myPercentageRound = roundDouble(myPercentage);
        SlackMessage slackMessage = new SlackMessage();
        slackMessage.bold("Regression Smoke test Result: ");
        for (Map.Entry<String, String> test : resultTestFails.entrySet()) {
            slackMessage.text("\nTest name: ").italic(test.getKey()).text(", link screenshot - ").link(test.getValue());
        }
        slackMessage.text("____________________________RESULT____________________________");
        slackMessage.text("\nUsername: " + TestBase.Username + ", password: " + TestBase.Password + ", basic url: " + TestBase.Base_URL);
        slackMessage.text("\nPercentage of test failed is " + myPercentageRound + ". From " + countOfTests + " tests in suite, failed - " + failedTests);

        //Если больше 50% тестов с сьюта провалено, то отправляем сообщение тестировщикам, проверить действительно ли это так
        String channelToSent = "";
        if (myPercentage > 50.0) {
            channelToSent = testerChannel;
        } else {
            channelToSent = developChannel;
        }
        try {
            new Slack(webHookUrl)
                    .icon(":smiling_imp:")
                    .sendToChannel(channelToSent)
                    .displayName(displayName)
                    .push(slackMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String roundDouble(double myPercentage) {
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(myPercentage);
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
