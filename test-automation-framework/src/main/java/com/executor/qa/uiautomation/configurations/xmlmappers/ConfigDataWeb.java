package com.executor.qa.uiautomation.configurations.xmlmappers;

import com.executor.qa.uiautomation.configurations.Browser;
import com.executor.qa.uiautomation.configurations.xmladapters.MapAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to unmarshal xml data to java object
 */
@XmlRootElement
public class ConfigDataWeb {

    @XmlElementWrapper(name = "browsers")
    @XmlElement(name = "browser")
    private List<Browser> browsers;

    @XmlElement(name = "capabilities")
    @XmlJavaTypeAdapter(MapAdapter.class)
    private Map<String, String> capabilities = new HashMap<String, String>();


    @XmlElement(name = "properties")
    @XmlJavaTypeAdapter(MapAdapter.class)
    private Map<String, String> properties = new HashMap<String, String>();

    @XmlElementWrapper(name = "urls")
    @XmlElement(name = "url")
    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public String getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public List<Browser> getBrowsers() {
        return browsers;
    }

    public Map<String, String> getCapabilities() {
        return capabilities;
    }
}
