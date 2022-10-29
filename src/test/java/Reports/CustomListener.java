package Reports;

import TestClasses.SearchSizeTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;

public class CustomListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result){
        try {
            if (!result.isSuccess()) {
                String filePath = "src\\test\\test-output\\failureScreenshots\\";
                File scrFile = ((TakesScreenshot) SearchSizeTest.webDriver.getWebDriver()).getScreenshotAs(OutputType.FILE);
                String fullPath = filePath + result.getName() + ".jpg";
                File dest = new File(fullPath);
                FileUtils.copyFile(scrFile, dest);
                System.out.println(dest.getAbsolutePath());
                Reporter.log("<br><br>screenshot of the failure : <br><br>");
                Reporter.log("<img src='failureScreenshots\\"+ result.getName() + ".jpg' height='400' width='400'/>");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
