package com.executor.qa.uiautomation.configurations;

import com.executor.qa.uiautomation.configurations.xmlmappers.AndroidDevice;
import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Driver {
    private final static Logger logger = Logger.getLogger(Driver.class);
    private static ThreadLocal<String> urlThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<AndroidDevice> androidDeviceThreadLocal = new ThreadLocal<>();
    public static Set<AndroidDevice> androidDevices = new HashSet<>();
    public static Set<String> nodeUrls = new HashSet<String>();
    private static ThreadLocal<AndroidDriver> androidDriver = new ThreadLocal<AndroidDriver>();
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
    private static Properties uiProp;
    private static Properties testData;
    private static Properties expectedResults;
    private static Driver driver;
    private static Map<Object, Object> cacheMap;

    private Driver() {

    }

    /**
     *  getInstance method to retrieve active driver instance
     * @return
     */
    public synchronized static final Driver getInstance() {
        if (driver == null) {
            driver = new Driver();
        }
        return driver;
    }

    /**
     * This method is used to create either the Selenium WebDriver or AppiumDriver instance
     * @param applicationType
     * @param preferences
     * @throws MalformedURLException
     */
    public void setDriver(ApplicationType applicationType, Map<String, Object>... preferences) throws MalformedURLException {
            DesiredCapabilities desiredCapabilities;
            switch (applicationType) {
                case ANDROID:

                    logger.info("Setup android driver - success ------------>");

                    break;
                case FIRE_FOX:
                    if (nodeUrls.isEmpty()) {
                        WebDriverManager.getInstance(FirefoxDriver.class).setup();
                        webDriverThreadLocal.set(new FirefoxDriver());
                    } else {
                        desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName(Preferences.FF);
                        urlThreadLocal.set(getAvailableUrl());
                        webDriverThreadLocal.set(new RemoteWebDriver(new URL(urlThreadLocal.get()), desiredCapabilities));
                    }
                    break;
                case CHROME:
                    if (nodeUrls.isEmpty()) {
                        WebDriverManager.getInstance(ChromeDriver.class).setup();
                        webDriverThreadLocal.set(new ChromeDriver());
                    } else {
                        desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName(Preferences.CHROME);
                        urlThreadLocal.set(getAvailableUrl());
                        webDriverThreadLocal.set(new RemoteWebDriver(new URL(urlThreadLocal.get()), desiredCapabilities));
                    }
                    break;
            }

    }

    private DesiredCapabilities setCapabilities(Map<String, String> capabilities) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        capabilities.forEach((k, v) -> desiredCapabilities.setCapability(k, v));
        return desiredCapabilities;
    }

    private DesiredCapabilities setCapabilities(DesiredCapabilities desiredCapabilities, Map<String, Object>[] options) {
        if (options.length > 0) {
            for (int i = 0; i < options.length; i++) {
                Object[] keys = options[i].keySet().toArray();
                Object[] values = options[i].values().toArray();
                for (int j = 0; j < keys.length; j++) {
                    desiredCapabilities.setCapability(keys[j].toString(), values[j]);
                }
            }
        }
        return desiredCapabilities;
    }

    private synchronized String getUrl() {
        String url = null;
        if (nodeUrls.iterator().hasNext()) {
            url = nodeUrls.iterator().next();
            nodeUrls.remove(url);
            urlThreadLocal.set(url);
        }
        ;
        return url;
    }

    /**
     * This method is used to release the currently used node
     */
    public synchronized void unlockNode() {
        nodeUrls.add(urlThreadLocal.get());
    }

    /**
     * This method is used to release the currently used device
     */
    public synchronized void unlockDevice() {
        androidDevices.add(androidDeviceThreadLocal.get());
    }

    private String getAvailableUrl() {
        String url = null;
        for (int i = 0; i < 1000; i++) {
            url = getUrl();
            if (url != null) {
                return url;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("Unable to find available url");
    }


    /**
     * This method is used to get the AndroidDriver instance
     * @return
     */
    public synchronized AndroidDriver getAndroidDriver() {
        return androidDriver.get();
    }

    public static String getFrameWorkProp(String key) {
        return uiProp.getProperty(key);
    }

    public static void setCacheMap() {
        cacheMap = new HashMap<Object, Object>();
    }

    public static void putObjCacheMap(Object key, Object value) {
        cacheMap.put(key, value);
    }

    public static Object getObjCacheMap(Object key) {
        return cacheMap.get(key);
    }

    /**
     * This method is used to quite the AndroidDriver instance
     */
    public void quitAndroidDriver() {
        androidDriver.get().closeApp();
        androidDriver.get().quit();
    }

    /**
     * This method is used to get the WebDriver instance
     * @return
     */
    public WebDriver getWebDriver() {
        return webDriverThreadLocal.get();
    }
}
