package org.example;

import org.example.test_json.People;
import org.example.test_json.Root;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {
    public List<String> parse(String fileName) {
        Root root = new Root();

        File file = new File(fileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            doc = dbf.newDocumentBuilder().parse(file);
        } catch (Exception e) {
            System.out.println("open parsing error" + e.toString());
            return null;
        }

        Node rootNode = doc.getFirstChild();
        NodeList rootChilds = rootNode.getChildNodes();

        String mainName = null;
        Node peopleNode = null;
        for(int i = 0; i < rootChilds.getLength(); i++) {
            if(rootChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            switch (rootChilds.item(i).getNodeName()) {
                case "name": {
                    mainName = rootChilds.item(i).getTextContent();
                    break;
                }
                case "people": {
                    peopleNode = rootChilds.item(i);
                    break;
                }
            }
        }

        if(peopleNode == null) {
            return null;
        }

        List<People> peopleList = new ArrayList<>();
        NodeList peopleChilds = peopleNode.getChildNodes();
        List<String> expressions = new ArrayList<>();
        for(int i = 0; i < peopleChilds.getLength(); i++) {
            if(peopleChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if(!peopleChilds.item(i).getNodeName().equals("element")) {
                continue;
            }

            String name = "";
            int age = 0;
            NodeList elementChilds = peopleChilds.item(i).getChildNodes();
            for(int j = 0; j < elementChilds.getLength(); j++) {
                if(elementChilds.item(j).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                switch (elementChilds.item(j).getNodeName()) {
                    case "name": {
                        name = elementChilds.item(j).getTextContent();
                        expressions.add(name);
                        break;
                    }
                    case "age": {
                        age = Integer.valueOf(elementChilds.item(j).getTextContent());
                        break;
                    }
                }
            }
            People people = new People(name, age);
            peopleList.add(people);
        }

        root.setName(mainName);
        root.setPeople(peopleList);

        return expressions;
    }
}
