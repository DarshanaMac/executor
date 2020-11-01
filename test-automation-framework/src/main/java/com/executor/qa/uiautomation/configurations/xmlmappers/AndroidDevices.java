package com.executor.qa.uiautomation.configurations.xmlmappers;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * This class is used to unmarshal xml data to java object
 */
@XmlRootElement
public class AndroidDevices {
    public List<AndroidDevice> getAndroidDevice() {
        return androidDevices;
    }

    public void setAndroidDevice(List<AndroidDevice> androidDevices) {
        this.androidDevices = androidDevices;
    }

    private List<AndroidDevice> androidDevices;
}
