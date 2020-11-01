package com.executor.qa.uiautomation.configurations.xmlmappers;

import com.executor.qa.uiautomation.configurations.xmladapters.MapAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to unmarshal xml data to java object
 */
@XmlRootElement
public class AndroidDevice {

    private Map<String, String> capabilities = new HashMap<String, String>();

    @XmlElement(name = "url")
    private String url;

    @XmlJavaTypeAdapter(MapAdapter.class)
    @XmlElement(name = "capabilities")
    public Map<String, String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Map<String, String> capabilities) {
        this.capabilities = capabilities;
    }

    public String getUrl() {
        return url;
    }

}