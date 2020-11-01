package com.executor.qa.uiautomation.configurations.xmlmappers;

import com.executor.qa.uiautomation.configurations.User;
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
public class ConfigData {
    public AndroidDevices getAndroidDevices() {
        return androidDevices;
    }

    public void setAndroidDevices(AndroidDevices androidDevices) {
        this.androidDevices = androidDevices;
    }

    private AndroidDevices androidDevices;

    @XmlElement(name = "properties")
    @XmlJavaTypeAdapter(MapAdapter.class)
    private Map<String, String> properties = new HashMap<String, String>();

    @XmlElement(name = "environments")
    @XmlJavaTypeAdapter(MapAdapter.class)
    private Map<String, String> environments = new HashMap<String, String>();

    public String getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public Map<String, String> getEnvironment() {
        return environments;
    }

    @XmlElementWrapper(name = "users")
    @XmlElement(name = "user")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }
}
