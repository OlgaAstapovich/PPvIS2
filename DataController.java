package com.company.controler;

import com.company.model.Pet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DataController {

    private ArrayList<Pet> tableData;

    private boolean bPetName;
    private boolean bDateOfBirth;
    private boolean bDateOfLastAppointment;
    private boolean bNameVet;
    private boolean bDiagnosis;

    public DataController(){
        tableData = new ArrayList<>();
    }

    private ArrayList<Pet> findTemplate(String petName, String dateOfBirth, String dateOfLastAppointment, String nameVet, String diagnosis) {
        ArrayList<Pet> temp = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("dd:MM:yyyy", Locale.ENGLISH);
        for (Pet tableDatum : tableData) {
            boolean bIsFits = (tableDatum.getPetName().equals(petName) || bPetName)
                    && (format.format(tableDatum.getDateOfBirth()).equals(dateOfBirth) || bDateOfBirth)
                    && (format.format(tableDatum.getDateOfLastAppointment()).equals(dateOfLastAppointment) || bDateOfLastAppointment)
                    && (tableDatum.getNameVet().equals(nameVet) || bNameVet)
                    && (tableDatum.getDiagnosis().equals(diagnosis) || bDiagnosis);
                if(bIsFits){
                temp.add(tableDatum);
            }
        }
        return temp;
    }

    public ArrayList<Pet> findPets(String petName, String dateOfBirth, String dateOfLastAppointment, String nameVet, String diagnosis){

        bPetName = (petName.equals(""));
        bDateOfBirth = (dateOfBirth.equals(""));
        bDateOfLastAppointment = (dateOfLastAppointment.equals(""));
        bNameVet = (nameVet.equals(""));
        bDiagnosis = (diagnosis.equals(""));

        return findTemplate(petName, dateOfBirth, dateOfLastAppointment, nameVet, diagnosis);
    }

    public int deletePets(String petName, String dateOfBirth, String dateOfLastAppointment, String nameVet, String diagnosis){
        bPetName = (petName.equals(""));
        bDateOfBirth = (dateOfBirth.equals(""));
        bDateOfLastAppointment = (dateOfLastAppointment.equals(""));
        bNameVet = (nameVet.equals(""));
        bDiagnosis = (diagnosis.equals(""));

        ArrayList<Pet> temp = findTemplate(petName, dateOfBirth, dateOfLastAppointment, nameVet, diagnosis);
        int amount = temp.size();
        tableData.removeAll(temp);
        return amount;
    }

    public void add(Pet pet){
        tableData.add(pet);
    }

    public void read(String pathToFile) {
        SAX sax = new SAX();
        sax.parse(pathToFile);
        tableData = sax.getPets();
    }

    public void write(String pathToFile){
        DOM dom = new DOM();
        dom.setPets(tableData, pathToFile);
        dom.setBooks();
    }

    public Pet atIndex(int index){
        return tableData.get(index);
    }

    public boolean isExists(int index){
        return index < tableData.size();
    }

    public void setPets(ArrayList<Pet> data){
        tableData = data;
    }
}
