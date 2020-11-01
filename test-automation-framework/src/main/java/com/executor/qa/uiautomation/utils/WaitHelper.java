package com.executor.qa.uiautomation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

    private static final int DEFAULT_TIME_OUT = 10;
    private static final int LONG_TIME_OUT = 30;
    private WebDriver webDriver;

    public WaitHelper(WebDriver webDriver){
       this.webDriver = webDriver;
    }

    public static void sleep(int waitingTime) throws InterruptedException {
        Thread.sleep(waitingTime);

    }

    public void waitForElementDisplay(WebElement webElement){
        waitForElementDisplay(webElement, DEFAULT_TIME_OUT);
    }
    
    public void waitForElementClickable(WebElement webElement){
    	waitForElementClickable(webElement, DEFAULT_TIME_OUT);
    }

    public void waitForElementDisplay(WebElement webElement, int timeout){
        (new WebDriverWait(webDriver, timeout))
                .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(webElement)));
    }
    
    public void waitForElementClickable(WebElement webElement, int timeout){
        (new WebDriverWait(webDriver, timeout))
                .until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(webElement)));
    }

    public void waitForElementDisplay(){
        (new WebDriverWait(webDriver, 30))
                .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Quick Access']"))));
    }

    public void waitForElementDisappear(WebElement webElement){
        waitForElementDisappear(webElement, DEFAULT_TIME_OUT);
    }

    public void waitForElementDisappear(WebElement webElement, int timeout){
        (new WebDriverWait(webDriver, timeout))
                .until(ExpectedConditions.invisibilityOf(webElement));
    }
}
