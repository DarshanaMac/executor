package com.executor.qa.uiautomation.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptExecutors {
    private WebDriver webDriver;

    public JavaScriptExecutors(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void scrollPage(int x, int y) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(" + x + "," + y + ")", "");
    }
}
