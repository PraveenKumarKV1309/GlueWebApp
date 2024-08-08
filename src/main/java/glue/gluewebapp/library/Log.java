package glue.gluewebapp.library;

import com.aventstack.extentreports.ExtentTest;

public class Log {
    private static ThreadLocal<ExtentTest> threadLocalTest = new ThreadLocal<>();

    // Sets the ExtentTest instance for the current thread
    public static void setTest(ExtentTest extentTest) {
        threadLocalTest.set(extentTest);
    }

    // Gets the ExtentTest instance for the current thread
    public static ExtentTest getTest() {
        return threadLocalTest.get();
    }

    // Logs informational messages to both console and ExtentReport
    public static void info(String message) {
        System.out.println("INFO: " + message);
        ExtentTest test = getTest();
        if (test != null) {
            test.info(message);
        }
    }
    

    // Logs error messages to both console and ExtentReport
    public static void error(String message) {
        System.err.println("ERROR: " + message);
        ExtentTest test = getTest();
        if (test != null) {
            test.fail(message);  // Change from `error` to `fail` for ExtentReports
        }
    }

    // Logs warning messages to both console and ExtentReport
    public static void warn(String message) {
        System.out.println("WARN: " + message);
        ExtentTest test = getTest();
        if (test != null) {
            test.warning(message);
        }
    }

    // Logs the start of a test case
    public static void startTestCase(String testCaseName) {
        System.out.println("START: " + testCaseName);
        ExtentTest test = getTest();
        if (test != null) {
            test.info("START: " + testCaseName);
        }
    }

    // Logs the end of a test case
    public static void endTestCase(String testCaseName) {
        System.out.println("END: " + testCaseName);
        ExtentTest test = getTest();
        if (test != null) {
            test.info("END: " + testCaseName);
        }
    }
}
