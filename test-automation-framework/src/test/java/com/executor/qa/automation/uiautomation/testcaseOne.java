package com.executor.qa.automation.uiautomation;

import com.executor.qa.automation.uiautomation.base.ConfigTestWeb;
import com.executor.qa.uiautomation.configurations.Browser;
import org.testng.annotations.Test;
import javax.xml.bind.JAXBException;
import java.util.Map;

import static com.executor.qa.uiautomation.pages.web.HomePage.*;

/*
Here execute UI automation test case :1

Expected Result:
Users should be taken to the leaf category page and the products pertaining to the category should be displayed in the page.
 */

public class testcaseOne extends ConfigTestWeb {
	
    @Test(testName = "Validate if the user can navigate to the leaf category page and see the products.", dataProvider = "getDefaultUser")
    public static void navigateToHomePage(Browser browser, Map<String, String> testData) throws JAXBException, InterruptedException {
        navigatesToProductPage();
        verifyProductPage(testData.get("expectedurl"));
        verifyProductList();
    }
}