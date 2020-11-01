package com.executor.qa.uiautomation.listeners;

import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    final static Logger logger = Logger.getLogger(TestListener.class);
    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.info("===================================Start Test " + iTestResult.getMethod().getMethodName() + "===================================");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.info("===================================Test Success " + iTestResult.getMethod().getMethodName() + "===================================");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.info("===================================Test Failed " + iTestResult.getMethod().getMethodName() + "===================================");
        ITestContext context = iTestResult.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        saveFailureScreenShot(driver);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("===============================================================================================");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("===============================================================================================@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

    }
    @Attachment
    public byte[] saveFailureScreenShot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
