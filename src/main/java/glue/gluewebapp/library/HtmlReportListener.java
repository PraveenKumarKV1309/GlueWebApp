package glue.gluewebapp.library;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class HtmlReportListener implements ITestListener {

    public static String[] sDataGuest = null;
    public static ExtentSparkReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    public void onTestStart(ITestResult result) {
        try {
            sDataGuest = GenericLibrary.toReadExcelData("GuestLogin_TestData", result.getName());
        } catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSuccess(ITestResult result) {
        test.pass(MarkupHelper.createLabel(result.getName() + " has Passed", ExtentColor.GREEN));
    }

    public void onTestFailure(ITestResult result)   {
        test.fail(MarkupHelper.createLabel(result.getName() + " has failed due to the following issue", ExtentColor.RED));
        test.fail(result.getThrowable());
        test.fail("Test Case failure screenshot is below");
        
        try {
        	test.addScreenCaptureFromPath(GenericLibrary.getVisibleAreaScreenshot(BaseClass.driver, result.getName()));
        }
        catch  (Exception e) {
        	e.printStackTrace();
			// TODO: handle exception
		}
    }

    public void onTestSkipped(ITestResult result) {
        test.skip(MarkupHelper.createLabel(result.getName() + " has skipped due to the following issues", ExtentColor.ORANGE));
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not implemented
    }

    public void onStart(ITestContext context) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_hh_mm_ss");
        String sDate = sdf.format(date);

        String reportPath= "BaseClass.sDirPath + \"\\\\Reports\\\\\" + sDate + \"HtmlReport.html\")";
        htmlReporter = new ExtentSparkReporter(reportPath);
        
        //htmlReporter.setAppendExisting(true);
        htmlReporter.config().setReporter(htmlReporter);
        htmlReporter.config().setTheme(Theme.STANDARD);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // System information
        extent.setSystemInfo("AuthorName", "Praveen");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("PlatformName", "Android");
        extent.setSystemInfo("PlatformVersion", "10");
        extent.setSystemInfo("Build Name", "1.0");
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
