package com.company.controler;


import com.company.model.Pet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DOM {

    private String  pathTo;
    private static ArrayList<Pet> pets;
    public  void setPets(ArrayList<Pet> animals, String path){
        pets = animals;
        pathTo = path;
    }

    public static ArrayList<Pet> getPets() {
        return pets;
    }


    private  Node createBookElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));

        return node;
    }

    private  Node createBook(Document document, String petName, Date dateOfBirth, Date dateOfLastAppointment, String nameVet, String diagnosis) {
        Element pet = document.createElement("pet");

        pet.appendChild(createBookElement(document, "petName", petName));
        pet.appendChild(createBookElement(document, "dateOfBirth", dateOfBirth.toString()));
        pet.appendChild(createBookElement(document, "dateOfLastAppointment", dateOfLastAppointment.toString()));
        pet.appendChild(createBookElement(document, "nameVet", nameVet));
        pet.appendChild(createBookElement(document, "diagnosis", diagnosis));
        return pet;
    }

    public  void addBook(Pet pet){
        pets.add(pet);
    }

    public  void setBooks() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = document.createElementNS("", "Pets");
            document.appendChild(root);
            for (Pet pt : pets) {
                root.appendChild(createBook(document, pt.getPetName(),pt.getDateOfBirth(), pt.getDateOfLastAppointment(),pt.getNameVet(),pt.getDiagnosis()));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transf = null;
            try {
                transf = transformerFactory.newTransformer();
                transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transf.setOutputProperty(OutputKeys.INDENT, "yes");
                transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

                DOMSource source = new DOMSource(document);

                File myFile = new File(pathTo);

                StreamResult file = new StreamResult(myFile);

                transf.transform(source, file);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
