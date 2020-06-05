package com.company.controler;

import com.company.model.Pet;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class SAX {

    DateFormat format = new SimpleDateFormat("dd:MM:yyyy", Locale.ENGLISH);
    private  Pet itemPet = null;
    private static ArrayList<Pet> pets = new ArrayList<>();
    String thisPet = "";
    public ArrayList<Pet> getPets() { return pets; }

    public void parse(String fileName) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                public void startDocument() {
                    System.out.println("Start parse XML...");
                }
                public void startElement(String uri, String localName, String qName,
                                         Attributes attributes) {
                    thisPet = qName;
                    if (thisPet.equals("pet")) {
                       itemPet = new Pet();
                    }
                }
                public void endElement(String uri, String localName,
                                       String qName) {
                    if (qName.equals("pet"))
                        pets.add(itemPet);
                    thisPet = "";

                }
                public void characters(char[] ch, int start, int length) {
                    if (thisPet.equals("petName")) {
                        itemPet.setPetName(new String(ch, start, length));
                    }
                    if (thisPet.equals("dateOfBirth")) {
                        try {
                            itemPet.setDateOfBirth(format.parse(new String(ch, start, length)));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (thisPet.equals("dateOfLastAppointment")) {
                        try {
                            itemPet.setDateOfLastAppointment(format.parse(new String(ch, start, length)));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    if (thisPet.equals("nameVet")) {
                        itemPet.setNameVet(new String(ch, start, length));
                    }
                    if (thisPet.equals("diagnosis")) {
                        itemPet.setDiagnosis(new String(ch, start, length));

                    }
                    }

                public void endDocument() { System.out.println("Stop parse XML..."); }};

            saxParser.parse(fileName, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


