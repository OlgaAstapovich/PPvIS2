package com.company.view;

import com.company.controler.DataController;
import com.company.model.Pet;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddFrame extends JFrame {

    private JPanel labelPanel;
    private JPanel textPanel;

    private DataController DC;

    private JTextField petName, dateOfBirth , dateOfLastAppointment, nameVet, diagnosis;
    private JButton add;

    public AddFrame(DataController DC){
        super("Add Window");
        this.DC = DC;
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add = new JButton("Add");
        createFields();
        createLabels();

        add(labelPanel);
        add(textPanel);
        add(add);

        setSize(600, 150);
        setResizable(false);
    }

    public void createLabels(){
        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));

        JLabel namePetLabel = new JLabel("Pet name");
        JLabel datePfBirthLabel = new JLabel("Date of Birth");
        JLabel dateOfLastAppointmentLabel = new JLabel("Date of Last Appointment");
        JLabel nameVetLabel = new JLabel("Name Vet");
        JLabel diagnosisLabel = new JLabel( "Diagnosis");

        labelPanel.add(namePetLabel);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(datePfBirthLabel);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(dateOfLastAppointmentLabel);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(nameVetLabel);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(diagnosisLabel);

    }

    public void createFields(){
        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));

        final int WIDTH = 100;
        final int HEIGHT = 20;

        petName = new JTextField();
        petName.setMaximumSize(new Dimension(WIDTH, HEIGHT));

        dateOfBirth = new JTextField();
        dateOfBirth.setMaximumSize(new Dimension(WIDTH, HEIGHT));

        dateOfLastAppointment = new JTextField();
        dateOfLastAppointment.setMaximumSize(new Dimension(WIDTH, HEIGHT));

        nameVet = new JTextField();
        nameVet.setMaximumSize(new Dimension(WIDTH, HEIGHT));

        diagnosis = new JTextField();
        diagnosis.setMaximumSize(new Dimension(WIDTH, HEIGHT));

        textPanel.add(petName);
        textPanel.add(dateOfBirth);
        textPanel.add(dateOfLastAppointment);
        textPanel.add(nameVet);
        textPanel.add(diagnosis);
    }

    public void addPet() throws ParseException {
        String tempPetName = petName.getText();
        String tempDateOfBirth = dateOfBirth.getText();
        String tempDateOfLastAppointment = dateOfLastAppointment.getText();
        String tempNameVet = nameVet.getText();
        String tempDiagnosis = diagnosis.getText();
        DateFormat format = new SimpleDateFormat("dd:MM:yyyy", Locale.ENGLISH);
        DC.add(new Pet(tempPetName,  format.parse(tempDateOfBirth), format.parse(tempDateOfLastAppointment), tempNameVet, tempDiagnosis));
    }

    public JButton getAdd() {
        return add;
    }
}
