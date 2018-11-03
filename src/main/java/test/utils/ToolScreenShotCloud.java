package test.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Tester3
 */
public class ToolScreenShotCloud {

    private String urlToScreenshotCloud = "";
    private String cloud_name = "cloud";
    private String api_key = "cloud";
    private String api_secret = "cloud";

    public String makeScreenshot(WebDriver driver, String name) {
        String FolderForScreenShot = "ScreenShot";
        String time = new SimpleDateFormat("dd.MM.yyyy" + "_" + "HH.mm.SS").format(new Date());
        driver.manage().window().setSize(new Dimension(1280, 1024));
        File ScreenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = name + "_Time_" + time + "_.png";
        try {
            File file = new File(FolderForScreenShot, path);
            FileUtils.copyFile(ScreenShot, file);
            urlToScreenshotCloud = pushScreenshotOnTheCloud(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WebDriverException we) {
            System.out.println(" Can't get Screen Shot");
        }
        return path;
    }

    public String pushScreenshotOnTheCloud(File file) {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloud_name,
                "api_key", api_key,
                "api_secret", api_secret));
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String urlToImgCloud = null;
        if (uploadResult != null) {
            urlToImgCloud = (String) uploadResult.get("url");
        }
        return urlToImgCloud;
    }

    public String getUrlToScreenshotCloud() {
        return urlToScreenshotCloud;
    }
}
