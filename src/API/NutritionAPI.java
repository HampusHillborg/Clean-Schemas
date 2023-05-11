package src.API;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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


    /**
     Updates an XML document by downloading a new version from a URL and saving it to a file.
     @throws ParserConfigurationException if a DocumentBuilder cannot be created
     @throws SAXException if there is an error parsing the XML document
     @throws IOException if there is an error downloading or writing the file
     */
    public void updateXmlDocument() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        URL xmlUrl = new URL(url + getCurrentDate());
        System.out.println("Updating file...");

        // Create a new file at the specified path
        String projectDir = new File("").getAbsolutePath();
        File xmlFile = new File(projectDir + "/Files/livsmedelsverket");


        // Check if the file already exists and was last modified today
        if (xmlFile.exists() && isToday(xmlFile.lastModified())) {
            System.out.println("File already up-to-date");
            return;
        }

        // Download the XML file and write it to the file
        try (InputStream inputStream = xmlUrl.openStream()) {
            Files.copy(inputStream, xmlFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File updated");
        }
    }

    private boolean isToday(long timestamp) {
        LocalDate today = LocalDate.now();
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, zoneId);
        return today.equals(dateTime.toLocalDate());
    }



    /**
     Retrieves an XML document from a file and returns it as a Document object.
     If the file does not exist, a default file is used instead.
     @return the XML document as a Document object
     @throws ParserConfigurationException if a DocumentBuilder cannot be created
     @throws SAXException if there is an error parsing the XML file
     @throws IOException if there is an error reading the file
     */
    public Document getXmlDocument() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        String projectDir = new File("").getAbsolutePath();

        File xmlFile;
        try {
            xmlFile = new File(projectDir + "/Files/livsmedelsverket");
        }catch (Exception e){
            xmlFile = new File(projectDir + "/Files/livsmedelsverket");
        }
        return builder.parse(xmlFile);
    }

    /**
     * Creates a food objekt from a searchterm in the xml file
     * @param searchTerm
     * @return Food
     */
    public Food createFood(String searchTerm){
        String meal;
        String protein;
        String kcal;
        String carbs;
        String fat;
        try {
            meal = getMealName(searchTerm);
            kcal = getCalorieValue(searchTerm);
            protein = getProteinValue(searchTerm);
            carbs = getCarbsValue(searchTerm);
            fat = getFatValue(searchTerm);


        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        return new Food(meal, kcal, carbs, protein, fat);
    }


    /**

     This method searches for a food in the XML document based on a given search term,
     and returns the protein value for the first matching food. If no matching food is found,
     it returns a message indicating so.
     @param searchTerm The term to search for in the food names
     @return A string representing the protein value for the first matching food, or a message indicating no matching food was found
     @throws ParserConfigurationException if there is a configuration error
     @throws IOException if an I/O error occurs
     @throws SAXException if there is an error parsing the XML document
     */
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

    /**
     This method searches for a food in the XML document based on a given search term,
     and returns the carbs value for the first matching food. If no matching food is found,
     it returns a message indicating so.
     @param searchTerm The term to search for in the food names
     @return A string representing the protein value for the first matching food, or a message indicating no matching food was found
     @throws ParserConfigurationException if there is a configuration error
     @throws IOException if an I/O error occurs
     @throws SAXException if there is an error parsing the XML document
     */
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

    /**
     This method searches for a food in the XML document based on a given search term,
     and returns the fat value for the first matching food. If no matching food is found,
     it returns a message indicating so.
     @param searchTerm The term to search for in the food names
     @return A string representing the protein value for the first matching food, or a message indicating no matching food was found
     @throws ParserConfigurationException if there is a configuration error
     @throws IOException if an I/O error occurs
     @throws SAXException if there is an error parsing the XML document
     */
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


    /**
     * Method that finds a dish int the xml file
     * that contains the searchterm and returns its full name
     * @param searchTerm
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public String getMealName(String searchTerm) throws ParserConfigurationException, IOException, SAXException {
        Document doc = getXmlDocument();
        NodeList nodeList = doc.getElementsByTagName("Livsmedel");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = element.getElementsByTagName("Namn").item(0).getTextContent();
                if (name.toLowerCase().contains(searchTerm.toLowerCase()))
                return name;
            }
        }
        return "No matching food found.";
    }

    /**
     This method searches for a food in the XML document based on a given search term,
     and returns the calorie value for the first matching food. If no matching food is found,
     it returns a message indicating so.
     @param searchTerm The term to search for in the food names
     @return A string representing the protein value for the first matching food, or a message indicating no matching food was found
     @throws ParserConfigurationException if there is a configuration error
     @throws IOException if an I/O error occurs
     @throws SAXException if there is an error parsing the XML document
     */
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
        //System.out.println("Energi för " + searchValue + " " + api.getCalorieValue(searchValue) + "Kcals");
        //System.out.println("Protein för "+ searchValue + ": " + api.getProteinValue(searchValue) + "g");
        //System.out.println("Kolhydrater för " +  searchValue + " " + api.getCarbsValue(searchValue) + "g");
        System.out.println("Fett för " + api.getMealName(searchValue) + " " + api.getFatValue(searchValue) + "g");
        System.out.println();
        //api.updateXmlDocument();
    }

    // Add other methods to extract data from the XML document as per your requirement

}

