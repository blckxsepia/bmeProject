package com.bmeproject.game.bmeProject.dataAccess;

import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.gameObjects.Effect;
import com.bmeproject.game.bmeProject.gameObjects.Type;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Die Klasse hat den Zweck, aus einem XML-File, dessen Pfad sie übergeben bekommt anhand zu lesen und daraus
 * eine Card zu bauen.
 */
public class XMLReader {

    String path;

    public XMLReader(String filepath) {
        path = filepath;
    }

    public List<Card> initCards() {
        Document document = generateDocFromXML();
        return readCardsFromXML(document);
    }

    public HashMap initEffects() {
        Document document = generateDocFromXML();
        return readEffectsFromXML(document) ;

    }
    
    private Document readEffectsXML() {
        try {
            File xmlFile = new File("core/src/com/bmeproject/game/bmeProject/dataAccess/EffectsXML.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            return doc;
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    //Builds document from XML and returns it
    private Document generateDocFromXML() {
        try {
            File xmlFile = new File(path);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            return doc;
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    public HashMap readEffectsFromXML(Document doc){
        System.out.println(doc.getDocumentURI());
        //System.out.println("Root element:" + doc.getDocumentElement().getNodeName());
        NodeList nodeListEffect = doc.getElementsByTagName("effect");
        HashMap<Integer, Effect> EffectList = new HashMap<Integer, Effect>();
        Effect tempEffect;


        for (int temp = 0; temp < nodeListEffect.getLength(); temp++) {
            Node nNode = nodeListEffect.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                tempEffect= new Effect(
                        Integer.parseInt(eElement.getAttribute("id")),
                        eElement.getElementsByTagName("effectDescription").item(0).getTextContent()

                        );

                EffectList.put(temp, tempEffect);
            }
        }
        return EffectList;

    }

    private List<Card> readCardsFromXML(Document doc) {
        NodeList nodeListCards = doc.getElementsByTagName("card");
        List<Card> cardList = new ArrayList<Card>();
        Card tempCard;


        for (int temp = 0; temp < nodeListCards.getLength(); temp++) {
            Node nNode = nodeListCards.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                tempCard = new Card(
                        Integer.parseInt(eElement.getAttribute("id")),
                        eElement.getElementsByTagName("cardName").item(0).getTextContent(),
                        getType(eElement.getElementsByTagName("cardType").item(0).getTextContent()),
                        eElement.getElementsByTagName("cardEffect").item(0).getTextContent(),
                        eElement.getElementsByTagName("cardDescription").item(0).getTextContent()
                        );
                cardList.add(tempCard);
            }
        }
        return cardList;
    }

    private Type getType(String type) {
        if (type.equals("Quartier")) {
            return Type.QUARTIER;
        } else if (type.equals("FIGURE")) {
            return Type.KREATUR;
        } else if (type.equals("MANIPULATION")) {
            return Type.PHAENOMEN;
        } else return null;
    }


}
