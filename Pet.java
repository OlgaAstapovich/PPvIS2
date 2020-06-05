package com.company.model;

import java.util.Date;

public class Pet {
    private String petName;
    private Date dateOfBirth;
    private Date dateOfLastAppointment;
    private String nameVet;
    private String diagnosis;
    public Pet(){}

    public Pet(String petName, Date dateOfBirth, Date dateOfLastAppointment, String nameVet, String diagnosis){
        this.petName = petName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfLastAppointment = dateOfLastAppointment;
        this.nameVet = nameVet;
        this.diagnosis = diagnosis;
    }

    public String getPetName() {
        return petName;
    }
    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Date getDateOfLastAppointment() {
        return dateOfLastAppointment;
    }
    public void setDateOfLastAppointment(Date dateOfLastAppointment) { this.dateOfLastAppointment = dateOfLastAppointment;}

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth;}

    public String getNameVet() { return nameVet; }
    public void setNameVet(String nameVet) {
        this.nameVet = nameVet;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }



}
