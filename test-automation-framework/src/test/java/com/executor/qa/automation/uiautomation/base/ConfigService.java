package com.executor.qa.automation.uiautomation.base;

import com.executor.qa.uiautomation.configurations.DataExtractor;
import com.executor.qa.uiautomation.configurations.xmlmappers.ConfigServiceData;
import com.executor.qa.uiautomation.utils.GeneralHelpers;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;


public class ConfigService {

    protected Properties properties;
    protected ConfigServiceData configData;
    protected Map<String, Object> preferences;
    private Logger logger = Logger.getLogger(ConfigService.class);


    @BeforeSuite(alwaysRun = true)
    public void beforeSuiteMethod(ITestContext iTestContext) throws MalformedURLException, Exception {
        try {
            configData = DataExtractor.getConfigDataService();
            System.setProperty("org.uncommons.reportng.escape-output", "false");

            setAllureEnvironment();
        } catch (Exception e) {
            logger.info("Setup test configuration fail------------>" + e.getMessage());
        }
    }


   public void setAllureEnvironment() {

		/*
		  allureEnvironmentWriter( ImmutableMap.<String,
		  String>builder().putAll(configData.getEnvironment()).build(),
		  System.getProperty("user.dir") + "/allure-results/");
		 */
    }

    @DataProvider(name = "getDefaultUser", parallel = true)
    public Object[][] getDefaultUser(ITestNGMethod iTestNGMethod) throws JAXBException {
        String filePath = configData.getProperty("testDataFilePath")+"\\"+this.getClass().getName()+".xml";
        String methodName = iTestNGMethod.getMethodName();
        List<HashMap<String,String>> testData = new ArrayList<>();
        int testDataSize = 1;
        if(GeneralHelpers.isFileExists(filePath)) {
        	List<HashMap<String,String>> tempData = DataExtractor.getTestData(methodName, filePath);
            if(tempData != null) {
            	tempData.forEach(a -> a.putAll(configData.getProperties()));
            	testData = tempData;
            }
            else {
            	testData.add((HashMap<String, String>) configData.getProperties());
            }
        	testDataSize = (testData==null ||testData.isEmpty())?1:testData.size();
        }
        else{
        	testData.add((HashMap<String, String>) configData.getProperties());
        }
        Object[][] dataRow = new Object[testDataSize][1];
        for(int i=0; i< dataRow.length; i++){

            dataRow[i][0] =  (testData==null ||testData.isEmpty())?null:testData.get(i%testDataSize);
        }
        return dataRow;
    }
}