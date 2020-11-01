package com.executor.qa.automation.uiautomation.base;

import com.executor.qa.uiautomation.configurations.ApplicationType;
import com.executor.qa.uiautomation.configurations.DataExtractor;
import com.executor.qa.uiautomation.configurations.Driver;
import com.executor.qa.uiautomation.configurations.User;
import com.executor.qa.uiautomation.configurations.xmlmappers.ConfigData;
import com.executor.qa.uiautomation.utils.GeneralHelpers;
import com.google.common.collect.ImmutableMap;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class ConfigTest {

    protected Properties properties;
    protected ConfigData configData;
    protected Map<String, Object> preferences;
    private Logger logger = Logger.getLogger(ConfigTest.class);


    @BeforeSuite(alwaysRun = true)
    public void beforeSuiteMethod(ITestContext iTestContext) throws MalformedURLException, Exception {
        try {
            configData = DataExtractor.getData();
            synchronized (this) {
                Driver.androidDevices.addAll(configData.getAndroidDevices().getAndroidDevice());
            }
            System.setProperty("org.uncommons.reportng.escape-output", "false");

            setAllureEnvironment();
        } catch (Exception e) {
            logger.info("Setup test configuration fail------------>" + e.getMessage());
        }
    }


   public void setAllureEnvironment() {

        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder().putAll(configData.getEnvironment()).build(), System.getProperty("user.dir") + "/allure-results/");
    }


    @AfterMethod
    public void tearDown() throws InterruptedException, Exception {
        try {
            Driver.getInstance().quitAndroidDriver();
            logger.info("Kill android driver success------------>");
        } catch (Exception e) {
            logger.info("Kill android driver fail------------>" + e.getMessage());
        }
        finally {
            Driver.getInstance().unlockDevice();
        }
    }

    @BeforeMethod
    public void setup(ITestContext iTestContext) throws MalformedURLException {
        Driver.getInstance().setDriver(ApplicationType.ANDROID);
        iTestContext.setAttribute("driver", Driver.getInstance().getAndroidDriver());
    }

    public void setFrameWorkProp() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(GeneralHelpers.getBasePath()+"/test.modules/src/test/resources/selfcareframework.properties"));
            logger.info("Initialize FrameWork Property file - success ------------>");
        } catch (IOException e) {
            logger.info("Initialize FrameWork Property file - fail ------------>" + e.getMessage());
        }
    }

    @DataProvider(name = "getDefaultUser", parallel = true)
    public Object[][] getDefaultUser(ITestNGMethod iTestNGMethod) throws JAXBException {
        String filePath = configData.getProperty("testDataFilePath")+"\\"+this.getClass().getName()+".xml";
        String methodName = iTestNGMethod.getMethodName();
        List<HashMap<String,String>> testData = null;
        List<User> users = configData.getUsers();
        int testDataSize = 1;
        int usersSize = users.size();
        if(GeneralHelpers.isFileExists(filePath)) {
            testData = DataExtractor.getTestData(methodName, filePath);
            testDataSize = (testData==null ||testData.isEmpty())?1:testData.size();
        }
        Object[][] dataRow = new Object[usersSize * testDataSize][2];
        for(int i=0; i< dataRow.length; i++){
            dataRow[i][0] = users.get(i%usersSize);
            dataRow[i][1] =  (testData==null ||testData.isEmpty())?null:testData.get(i%testDataSize);
        }
        return dataRow;
    }
}