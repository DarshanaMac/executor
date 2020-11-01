package com.executor.qa.automation.uiautomation;

import com.executor.qa.automation.uiautomation.base.ConfigTestWeb;
import com.executor.qa.uiautomation.configurations.Browser;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;
import java.util.Map;

import static com.executor.qa.uiautomation.pages.web.HomePage.*;
import static com.executor.qa.uiautomation.pages.web.HomePage.navigateToDesktopComputersSubLevel;

/*
  Mouse hover to desktop computers category and verify its sub product categories
 */

public class testcase_2 extends ConfigTestWeb {
    @Test(testName = "NavigateToLeafCategory", dataProvider = "getDefaultUser")
    public static void NavigateToLeafCategory(Browser browser, Map<String, String> testData) throws JAXBException, InterruptedException {
        navigateToLeafCategory();
        verifyChildCategoryProducts(testData.get("products"));
        navigateToDesktopComputersSubLevel();
        verifyDesktopComputersLeafLevelItems(testData.get("desktopcomputersproducts"));
    }
}
