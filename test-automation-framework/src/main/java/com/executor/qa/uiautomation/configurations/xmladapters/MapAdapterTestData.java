package com.executor.qa.uiautomation.configurations.xmladapters;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is a customize adapter class
 */
public class MapAdapterTestData extends XmlAdapter<MapAdapterTestData.AdaptedMapTestData, HashMap<String, List<HashMap<String, String>>>> {

    private DocumentBuilder documentBuilder;

    public MapAdapterTestData() throws Exception {
        documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    public static class AdaptedMapTestData {
        @XmlAnyElement
        public List<Element> elements = new ArrayList<Element>();
    }

    @Override
    public AdaptedMapTestData marshal(HashMap<String, List<HashMap<String, String>>> map) throws Exception {
        Document document = documentBuilder.newDocument();
        AdaptedMapTestData adaptedMap = new AdaptedMapTestData();
        return adaptedMap;
    }

    @Override
    public HashMap<String, List<HashMap<String, String>>> unmarshal(AdaptedMapTestData adaptedMap) throws Exception {
        HashMap<String, List<HashMap<String, String>>> map = new HashMap<String, List<HashMap<String, String>>>();
        for (Element element : adaptedMap.elements) {
            List<HashMap<String, String>> tempList = new ArrayList<>();
            String name = element.getAttribute("name");
            NodeList nodeList = element.getElementsByTagName("property");
            HashMap<String, String> tempMap = new HashMap<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element tempElement = (Element) nodeList.item(i);
                tempMap.put(tempElement.getAttribute("name"), tempElement.getTextContent());
            }
            if (map.isEmpty() || map.get(name) == null) {
                tempList.add(tempMap);
                map.put(name, tempList);
            } else {
                map.get(name).add(tempMap);
            }
        }
        return map;
    }
}