package com.executor.qa.uiautomation.configurations;

import com.executor.qa.uiautomation.configurations.xmlmappers.ConfigData;
import com.executor.qa.uiautomation.configurations.xmlmappers.ConfigDataWeb;
import com.executor.qa.uiautomation.configurations.xmlmappers.ConfigServiceData;
import com.executor.qa.uiautomation.global.GLOBAL_VARS;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * This class is used to get the test data from xml file.
 */
public class DataExtractor {
    /**
     * This method is used to get the android related configuration data
     * @return
     * @throws JAXBException
     */
    public static ConfigData getData() throws JAXBException {
        File file = new File(GLOBAL_VARS.baseDir + "\\src\\main\\resources\\testData.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(ConfigData.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (ConfigData) jaxbUnmarshaller.unmarshal(file);
    }

    /**
     * This method is used to test method related test data.
     * @param methodName
     * @param filePath
     * @return
     * @throws JAXBException
     */
    public static List<HashMap<String, String>> getTestData(String methodName, String filePath) throws JAXBException {
        File file = new File(filePath);
        JAXBContext jaxbContext = JAXBContext.newInstance(TestData.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        TestData testData = (TestData) jaxbUnmarshaller.unmarshal(file);
        return testData.getTestMethodData().get(methodName);
    }

    /**
     * This method is used to the web related configuration data.
     * @return
     * @throws JAXBException
     */
    public static ConfigDataWeb getConfigDataWeb() throws JAXBException {
        File file = new File(GLOBAL_VARS.baseDir + "\\src\\main\\resources\\webConfigData.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(ConfigDataWeb.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (ConfigDataWeb) jaxbUnmarshaller.unmarshal(file);
    }

    /**
     * This method is used to the service related configuration data.
     * @return
     * @throws JAXBException
     */
    public static ConfigServiceData getConfigDataService() throws JAXBException {
        File file = new File(GLOBAL_VARS.baseDir + "\\src\\main\\resources\\serviceConfigData.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(ConfigServiceData.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (ConfigServiceData) jaxbUnmarshaller.unmarshal(file);
    }
}
