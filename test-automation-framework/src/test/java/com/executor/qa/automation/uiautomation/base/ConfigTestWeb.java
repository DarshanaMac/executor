package com.executor.qa.automation.uiautomation.base;

import com.executor.qa.uiautomation.configurations.*;
import com.executor.qa.uiautomation.configurations.xmlmappers.ConfigDataWeb;
import com.executor.qa.uiautomation.utils.GeneralHelpers;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ConfigTestWeb {
    private ConfigDataWeb configDataWeb;
    protected WebDriver webDriver;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuiteMethod(ITestContext iTestContext) throws JAXBException {
        configDataWeb = DataExtractor.getConfigDataWeb();
        synchronized (this) {
            Driver.nodeUrls.addAll(configDataWeb.getUrls());
        }

    }

    @DataProvider(name = "getDefaultUser",parallel = true)
    public Object[][] getDefaultUser(ITestNGMethod iTestNGMethod) throws JAXBException {
        String filePath = configDataWeb.getProperty("testDataFilePath")+"\\"+this.getClass().getName()+".xml";
        String methodName = iTestNGMethod.getMethodName();
        List<Browser> browsers = configDataWeb.getBrowsers();
        List<HashMap<String,String>> testData = null;
        /*if(GeneralHelpers.isFileExists(filePath)){
            testData = DataExtractor.getTestData(methodName,filePath);
        }
        int testDataSize = 1;
        if(testData != null){
            testDataSize = testData.size();
        }
        Object[][] dataRow = new Object[browsers.size() * testDataSize][2];
        for(int i=0; i< dataRow.length; i++){
            dataRow[i][0] = browsers.get(i);
            for(int j=0; j< testDataSize; j++){
                if(testData == null){
                    dataRow[i][j] = null;
                }
                else {
                    dataRow[i][j] = testData.get(j);
                }
            }
        }*/
        int testDataSize = 1;
        int browsersSize = browsers.size();
        if(GeneralHelpers.isFileExists(filePath)) {
            testData = DataExtractor.getTestData(methodName, filePath);
            testDataSize = (testData==null ||testData.isEmpty())?1:testData.size();
        }
        Object[][] dataRow = new Object[browsersSize * testDataSize][2];
        for(int i=0; i< dataRow.length; i++){
            dataRow[i][0] = browsers.get(i%browsersSize);
            dataRow[i][1] =  (testData==null ||testData.isEmpty())?null:testData.get(i%testDataSize);
        }
        return dataRow;
    }


    public void createDriver(Browser browser, Map<String, Object> preferences) throws MalformedURLException {
       switch (browser) {
            case CHROME:
                Driver.getInstance().setDriver(ApplicationType.CHROME,preferences);
                //return  Driver.getInstance().getWebDriver();
                break;
            case FIREFOX:
                Driver.getInstance().setDriver(ApplicationType.FIRE_FOX,preferences);
                //return  Driver.getInstance().getWebDriver();
                break;
            default:
                break;
                //return null;
       }
       Driver.getInstance().getWebDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        Driver.getInstance().getWebDriver().quit();
        Driver.getInstance().unlockNode();
    }

    @BeforeMethod
    public void setDriver(Object[] data) throws MalformedURLException {
        Map<String,Object> preferences = new HashMap<>();
        Browser browser = (Browser)data[0];
        preferences.put(Preferences.URL,configDataWeb.getUrls().isEmpty());
        createDriver(browser,preferences);
    }
}
