package com.executor.qa.uiautomation.utils;

import com.executor.qa.uiautomation.configurations.Driver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class CommandBase {

    protected static WebDriver webDriver;
    private static WaitHelper waitHelper;
    private Actions action;
    private Logger logger = Logger.getLogger(CommandBase.class);

    public CommandBase() {
        this.webDriver = Driver.getInstance().getWebDriver();
        //this.androidDriver = Driver.getInstance().getAndroidDriver();
        waitHelper = new WaitHelper(webDriver);
    }

    /**
     * This method is used to click web element
     *
     * @param element
     */
    @Step
    protected static void click(WebElement element) {
        waitHelper.waitForElementDisplay(element);
        element.click();
    }

    /**
     * This method is used to click mobile element
     *
     * @param element
     */
    @Step
    protected void click(MobileElement element) {
        waitHelper.waitForElementDisplay(element);
        element.click();
    }

    /**
     * This method is used to clear web field
     *
     * @param element
     */
    @Step
    protected void clear(WebElement element) {
        waitHelper.waitForElementDisplay(element);
        element.click();
    }

    /**
     * This method is used to clear mobile field
     *
     * @param element
     */
    @Step
    protected void clear(MobileElement element) {
        waitHelper.waitForElementDisplay(element);
        element.clear();
    }

    /**
     * This method is used to send text to web element (text box/text field)
     *
     * @param element
     * @param text
     */
    @Step("sendKey {0}, Value:{1}")
    protected void type(WebElement element, String text) {
        waitHelper.waitForElementDisplay(element);
        element.sendKeys(text);
    }

    /**
     * This method is used to send text to mobile element (text box/text field)
     *
     * @param element
     * @param text
     */
    @Step("sendKey {0}, Value:{1}")
    protected void type(AndroidElement element, String text) {
        waitHelper.waitForElementDisplay(element);
        element.sendKeys(text);
    }

    /**
     * This method is used to get element text and append the allure report
     *
     * @param text
     */
    @Step("Value:{1}")
    protected void writeToReport(String text){
        logger.debug("Logged to allure: " +text);
    }

    /**
     * This method is used to get text from web element
     *
     * @param element
     * @return text
     */
    @Step
    protected String getText(WebElement element) {
        waitHelper.waitForElementDisplay(element);
        String text = element.getText();
        return text;
    }

    /**
     * This method is used to get text from mobile element
     *
     * @param element
     * @return text
     */
    @Step
    protected String getText(AndroidElement element) {
        waitHelper.waitForElementDisplay(element);
        String text = element.getText();
        return text;
    }

    /**
     * This method is used to navigate back on web
     */
    @Step
    protected void backWeb() {
        webDriver.navigate().back();
    }

    /**
     * This method is used to check mobile element display or not
     *
     * @param element
     * @return boolean
     */
    @Step
    protected boolean isElementDisplay(MobileElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }



    /**
     * This method is used to check mobile element display or not
     *
     * @param element
     */
    @Step
    protected String getElementText(MobileElement element) {
        waitHelper.waitForElementDisplay(element);
        String text=element.getText();
        return  text;
    }


    /**
     * This method is used to check web element display or not
     *
     * @param element
     * @return boolean
     */
    @Step
    protected static boolean isElementDisplay(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step
    protected static boolean isElementDisplay(String element) {
        try {
          return   Driver.getInstance().getWebDriver().findElement(By.xpath(element)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step
    protected static boolean isElementDisplayListedItems(String element,int i) {
        try {
            boolean status=false;
            String object=element.toString();
            object=object+"["+i+"]";
            status=Driver.getInstance().getWebDriver().findElement(By.xpath(object)).isDisplayed();
            return status;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    /**
     * This method is used to click and hold on specific element
     *
     * @param element
     */
    @Step
    protected void clickAndHold(WebElement element) {
        action.clickAndHold(element);
    }

    /**
     * This method is used to click right button on mouse
     *
     * @param element
     */
    @Step
    protected void contextClick(WebElement element) {
        action.contextClick(element);
    }

    /**
     * This method is used to double click on specific element
     *
     * @param element
     */
    @Step
    protected void doubleClick(WebElement element) {
        action.doubleClick(element);
    }

    /**
     * This method is used to drag and drop from given place to target location on web
     *
     * @param source
     * @param target
     */
    @Step
    protected void dragAndDrop(WebElement source, WebElement target) {
        action.dragAndDrop(source, target);
    }

    /**
     * This method is used to drag and drop from x location to y location
     *
     * @param source
     * @param xOffset
     * @param yOffset
     */
    @Step
    protected void dargAndDropByOffset(WebElement source, int xOffset, int yOffset) {
        action.dragAndDropBy(source, xOffset, yOffset);
    }

    /**
     * This method is used to trigger keyboard events
     *
     * @param target
     * @param key
     */
    @Step
    protected void keyDown(WebElement target, CharSequence key) {
        action.keyDown(target, (Keys) key);
    }

    @Step
    protected void waitForElementDisplay(WebElement webElement){
        waitHelper.waitForElementDisplay(webElement);
    }
    
    @Step
    protected void waitForElementClickable(WebElement webElement){
        waitHelper.waitForElementClickable(webElement);
    }

    @Step
    protected static void openPage(String url){
        webDriver.get(url);
    }
    
    @Step
    protected String getUrl(){
        return webDriver.getCurrentUrl();
    }
    
    @Step
    protected void scrolToElement(WebElement webElement) throws InterruptedException{
    	((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    	Thread.sleep(500);
    }

    @Step
    protected void waitForElementDisappear(WebElement webElement){
        waitHelper.waitForElementDisappear(webElement);
    }


    @Step
    protected static void mouseHover(WebElement webElement){
        Actions actions = new Actions(Driver.getInstance().getWebDriver());
        String webelement=webElement.toString();
        actions.moveToElement(webElement).perform();
    }

    @Step
    protected static void verifyCurrentUrl(String expectedurl){
    String currenturl=Driver.getInstance().getWebDriver().getCurrentUrl();
        String aftercurrenturl = currenturl.substring(0, currenturl.indexOf("?"));
        System.out.println(aftercurrenturl);
        Assert.assertEquals(aftercurrenturl,expectedurl);
    }

    @Step
    protected static int elementlistsize(String element){
        String object=element.toString();
        int elementcount=Driver.getInstance().getWebDriver().findElements(By.xpath(object)).size();
        return elementcount;
    }
}
