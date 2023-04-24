package src.API;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import src.Entity.Food;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NutritionAPI {

    private String url = "http://www7.slv.se/apilivsmedel/LivsmedelService.svc/Livsmedel/Naringsvarde/";



        public static String getCurrentDate() {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            return today.format(formatter);
        }



    public void updateXmlDocument() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        URL xmlUrl = new URL(url+getCurrentDate());

        // Create a new file at the specified path
        File xmlFile = new File("src/livsmedelsverket");

        // Download the XML file and write it to the file
        try (InputStream inputStream = xmlUrl.openStream()) {
            Files.copy(inputStream, xmlFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }


    public Document getXmlDocument() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        File xmlFile;
        try {
            xmlFile = new File("src/livsmedelsverket");
        }catch (Exception e){
            xmlFile = new File("src/livsmedelsverket");
        }
        return builder.parse(xmlFile);
    }


    public void printFromDoc() throws ParserConfigurationException, IOException, SAXException {
        Document doc = getXmlDocument();
        Element root = doc.getDocumentElement();
        NodeList nodeList = doc.getElementsByTagName("Livsmedel");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = element.getElementsByTagName("Namn").item(0).getTextContent();
                String protein = "";
                String kolhydrater = "";
                String fett = "";
                String energi = "";

                NodeList naringsvardeList = element.getElementsByTagName("Naringsvarde");
                for (int j = 0; j < naringsvardeList.getLength(); j++) {
                    Node naringsvardeNode = naringsvardeList.item(j);
                    if (naringsvardeNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element naringsvardeElement = (Element) naringsvardeNode;
                        String namn = naringsvardeElement.getElementsByTagName("Namn").item(0).getTextContent();
                        if (namn.equals("Protein")) {
                            protein = naringsvardeElement.getElementsByTagName("Varde").item(0).getTextContent();
                        }
                        if(namn.equals("Kolhydrater")){
                            kolhydrater = naringsvardeElement.getElementsByTagName("Varde").item(0).getTextContent();
                        }
                        if(namn.equals("Fett")){
                            kolhydrater = naringsvardeElement.getElementsByTagName("Varde").item(0).getTextContent();
                        }
                    }
                }
                System.out.println(name + " - "  + "Energi: " + energi + "Kcals Protein: " + protein + "g Kolhydrater: " + kolhydrater + "g Fett: " + fett + "g");
            }
        }
    }

    public String getProteinValue(String searchTerm) throws ParserConfigurationException, IOException, SAXException {
        Document doc = getXmlDocument();
        NodeList nodeList = doc.getElementsByTagName("Livsmedel");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = element.getElementsByTagName("Namn").item(0).getTextContent();
                if (name.toLowerCase().contains(searchTerm.toLowerCase())) {
                    NodeList naringsvardeList = element.getElementsByTagName("Naringsvarde");
                    for (int j = 0; j < naringsvardeList.getLength(); j++) {
                        Node naringsvardeNode = naringsvardeList.item(j);
                        if (naringsvardeNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element naringsvardeElement = (Element) naringsvardeNode;
                            String namn = naringsvardeElement.getElementsByTagName("Namn").item(0).getTextContent();
                            if (namn.equals("Protein")) {
                                return naringsvardeElement.getElementsByTagName("Varde").item(0).getTextContent();
                            }
                        }
                    }
                }
            }
        }
        return "No matching food found.";
    }

    public String getCarbsValue(String searchTerm) throws ParserConfigurationException, IOException, SAXException {
        Document doc = getXmlDocument();
        NodeList nodeList = doc.getElementsByTagName("Livsmedel");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = element.getElementsByTagName("Namn").item(0).getTextContent();
                if (name.toLowerCase().contains(searchTerm.toLowerCase())) {
                    NodeList naringsvardeList = element.getElementsByTagName("Naringsvarde");
                    for (int j = 0; j < naringsvardeList.getLength(); j++) {
                        Node naringsvardeNode = naringsvardeList.item(j);
                        if (naringsvardeNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element naringsvardeElement = (Element) naringsvardeNode;
                            String namn = naringsvardeElement.getElementsByTagName("Namn").item(0).getTextContent();
                            if (namn.equals("Kolhydrater")) {
                                return naringsvardeElement.getElementsByTagName("Varde").item(0).getTextContent();
                            }
                        }
                    }
                }
            }
        }
        return "No matching food found.";
    }

    public String getFatValue(String searchTerm) throws ParserConfigurationException, IOException, SAXException {
        Document doc = getXmlDocument();
        NodeList nodeList = doc.getElementsByTagName("Livsmedel");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = element.getElementsByTagName("Namn").item(0).getTextContent();
                if (name.toLowerCase().contains(searchTerm.toLowerCase())) {
                    NodeList naringsvardeList = element.getElementsByTagName("Naringsvarde");
                    for (int j = 0; j < naringsvardeList.getLength(); j++) {
                        Node naringsvardeNode = naringsvardeList.item(j);
                        if (naringsvardeNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element naringsvardeElement = (Element) naringsvardeNode;
                            String namn = naringsvardeElement.getElementsByTagName("Namn").item(0).getTextContent();
                            if (namn.equals("Fett")) {
                                return naringsvardeElement.getElementsByTagName("Varde").item(0).getTextContent();
                            }
                        }
                    }
                }
            }
        }
        return "No matching food found.";
    }
    public String getCalorieValue(String searchTerm) throws ParserConfigurationException, IOException, SAXException {
        Document doc = getXmlDocument();
        NodeList nodeList = doc.getElementsByTagName("Livsmedel");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = element.getElementsByTagName("Namn").item(0).getTextContent();
                if (name.toLowerCase().contains(searchTerm.toLowerCase())) {
                    NodeList naringsvardeList = element.getElementsByTagName("Naringsvarde");
                    for (int j = 0; j < naringsvardeList.getLength(); j++) {
                        Node naringsvardeNode = naringsvardeList.item(j);
                        if (naringsvardeNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element naringsvardeElement = (Element) naringsvardeNode;
                            String namn = naringsvardeElement.getElementsByTagName("Namn").item(0).getTextContent();
                            if (namn.equals("Energi (kcal)")) {
                                return naringsvardeElement.getElementsByTagName("Varde").item(0).getTextContent();
                            }
                        }
                    }
                }
            }
        }
        return "No matching food found.";
    }



    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        NutritionAPI api = new NutritionAPI();
        String searchValue = "Yoghurtsås";
        System.out.println("Energi för " + searchValue + " " + api.getCalorieValue(searchValue) + "Kcals");
        System.out.println("Protein för "+ searchValue + ": " + api.getProteinValue(searchValue) + "g");
        System.out.println("Kolhydrater för " +  searchValue + " " + api.getCarbsValue(searchValue) + "g");
        System.out.println("Fett för " + searchValue + " " + api.getFatValue(searchValue) + "g");
        //api.updateXmlDocument();
    }

    // Add other methods to extract data from the XML document as per your requirement

}

