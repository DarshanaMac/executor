package com.executor.qa.uiautomation.pages.web;

import com.executor.qa.uiautomation.configurations.DataExtractor;
import com.executor.qa.uiautomation.configurations.Driver;
import com.executor.qa.uiautomation.utils.CommandBase;
import com.executor.qa.uiautomation.utils.WaitHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import javax.xml.bind.JAXBException;

public class HomePage extends CommandBase {
    private static String URL;

    @FindBy(xpath = "//input[@id='user-name']")
    WebElement txtUsername;

    @FindBy(xpath = "//ul[@class='lzd-site-menu-root']")
    WebElement btnMainMenu;

    @FindBy(how = How.ID, using = "Level_1_Category_No1")
    static WebElement lnkElectronicDevice;

    @FindBy(how = How.XPATH, using = "//li[@class='lzd-site-menu-sub-item']/a/span[text()='Laptops']")
    static WebElement secondsublevelcategory;

    @FindBy(how = How.XPATH, using = "//span[text()='2-in-1s']")
    static WebElement thirdsublevel;

    @FindBy(how = How.XPATH, using = "//span[@class='breadcrumb_item_anchor breadcrumb_item_anchor_last' and text()='2-in-1s']")
    static WebElement leafcategory;

    static String productlist="//div[@class='c2prKC']";

    static String subcategoryitems="//ul[@class='lzd-site-menu-sub Level_1_Category_No1']/li";

    @FindBy(how = How.XPATH, using = "//li[@class='lzd-site-menu-sub-item']/a/span[text()='Desktops Computers']")
    static WebElement desktopscomputerssublevel;

    public HomePage() throws JAXBException {
        URL = DataExtractor.getConfigDataWeb().getProperty("mainPage");
        webDriver = Driver.getInstance().getWebDriver();
        PageFactory.initElements(webDriver, this);
        openPage(URL);
    }


     //In this method mouse hover to product and mouse hover its child category then click on specific product page.
    public static void navigatesToProductPage() throws JAXBException, InterruptedException {
        HomePage homePage=new HomePage();
        WaitHelper waitHelper=new WaitHelper(Driver.getInstance().getWebDriver());
        waitHelper.sleep(6000);
        mouseHover(lnkElectronicDevice);
        mouseHover(secondsublevelcategory);
        click(thirdsublevel);
    }

    //Verify navigated page url and its products.
    public static void verifyProductPage(String expectedurl){
        verifyCurrentUrl(expectedurl);
        isElementDisplay(leafcategory);
    }

    //Verify product list
    public static void verifyProductList(){
        int noofelements=elementlistsize(productlist);
        for (int element=1;element<=noofelements;element++){
        isElementDisplay(productlist);
        }
    }

    //Mouse hover to sub level product category
    public static void navigateToLeafCategory() throws JAXBException, InterruptedException {
        HomePage homePage=new HomePage();
        //wait using application loading time
        Thread.sleep(7000);
        mouseHover(lnkElectronicDevice);
        mouseHover(secondsublevelcategory);
    }

    // Verify all sub products of main category
    public static void verifyChildCategoryProducts(String products){
        int count=0;
        String str=products;
        for(String subString: str.split(",")){
           System.out.println(subString);
           count++;
        }
        for (int i=1;i<=count;i++){
            isElementDisplayListedItems(subcategoryitems,i);
        }
    }

    //Mouse hover to desktop computer
    public static void navigateToDesktopComputersSubLevel() throws JAXBException, InterruptedException {
        mouseHover(lnkElectronicDevice);
        mouseHover(desktopscomputerssublevel);
    }

    //Verify all sub products of desktop computers
    public static void verifyDesktopComputersLeafLevelItems(String products){
        int count=0;
        String product=products;
        for(String subString: product.split(",")){
            System.out.println(subString);
            count++;
        }
        for (int i=1;i<=count;i++){
            isElementDisplayListedItems(subcategoryitems,i);
        }
    }
}
