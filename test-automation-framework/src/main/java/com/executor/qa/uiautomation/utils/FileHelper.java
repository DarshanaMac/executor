package com.executor.qa.uiautomation.utils;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FileHelper {
    public static final String COMMA_SEPARATOR = ",";

    public static List<String> stringToList(String text, String separator){
        return Arrays.asList(text.split(separator));
    }

    public static void captureScreenShots(AppiumDriver<WebElement> driver) throws IOException {
        String folder_name;
        DateFormat df;
        folder_name="screenshot";
        File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Date format for screenshot file name
        df=new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        //create dir with given folder name
        new File(folder_name).mkdir();
        //Setting file name
        String file_name=df.format(new Date())+".png";
        //copy screenshot file into screenshot folder.
        FileUtils.copyFile(f, new File(folder_name + "/" + file_name));
    }

    public static PropertiesConfiguration readPropertyFile(String filePath) throws ConfigurationException, org.apache.commons.configuration2.ex.ConfigurationException {
        Configurations configurations = new Configurations();
        PropertiesConfiguration propertiesConfiguration = configurations.properties(getFileFromPath(filePath));
        return propertiesConfiguration;
    }

    public static File getFileFromPath(String path){
        return new File(path);
    }

    public static String getStringFromFile(String filePath) {
        String content = "";
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }
}
