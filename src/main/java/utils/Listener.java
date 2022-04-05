package utils;

import baseEntities.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class Listener implements ITestListener {

//    @Override
//    public void onTestFailure(ITestResult tr) {
//        Object currentClass = tr.getInstance();
//        System.out.println("Делаю скриншот!!!!!!");
//        try {
//            WebDriver driver = ((BaseTest) currentClass).driver;
//            byte[] srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//            saveScreenshot(srcFile);
//        } catch (Exception ex) {
//
//        }
//    }

    @Override
    public void onTestFailure(ITestResult result) {

        Allure.addAttachment("screenShot", new ByteArrayInputStream(((TakesScreenshot)result.getTestContext().getAttribute("WebDriver")).getScreenshotAs(OutputType.BYTES)));

        Object webDriverAttribute = result.getTestContext().getAttribute("WebDriver");
        captureScreenshot((WebDriver) webDriverAttribute);

    }


    @Attachment
    public byte[] captureScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

//    @Attachment(value = "Page screenshot", type = "image/png")
//    private byte[] saveScreenshot(byte[] screenshot) {
//        return screenshot;
//    }
}
