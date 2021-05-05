package com.executor.qa.uiautomation.configurations;


import com.executor.qa.uiautomation.configurations.xmladapters.MapAdapterTestData;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;
import java.util.List;

@XmlRootElement
public class TestData {

    public HashMap<String, List<HashMap<String,String>>> getTestMethodData() {
        return testMethodData;
    }

    @XmlElement(name = "suite")
    @XmlJavaTypeAdapter(MapAdapterTestData.class)
    private HashMap<String, List<HashMap<String,String>>> testMethodData;
}
