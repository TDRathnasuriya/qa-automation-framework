package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestNGListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static Map<Long, ExtentTest> testMap = new HashMap<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testMap.put(Thread.currentThread().getId(), test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testMap.get(Thread.currentThread().getId()).log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testMap.get(Thread.currentThread().getId()).log(Status.FAIL, "Test failed: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testMap.get(Thread.currentThread().getId()).log(Status.SKIP, "Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
