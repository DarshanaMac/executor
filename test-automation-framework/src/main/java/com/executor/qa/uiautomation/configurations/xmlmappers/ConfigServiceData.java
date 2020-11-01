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
public class ConfigServiceData {
    
    @XmlElement(name = "properties")
    @XmlJavaTypeAdapter(MapAdapter.class)
    private Map<String, String> properties = new HashMap<String, String>();

    public String getProperty(String propertyName) {
        return properties.get(propertyName);
    }
    
    public Map<String, String> getProperties(){
    	return properties;
    }

}
