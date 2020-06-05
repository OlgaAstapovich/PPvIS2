package com.company.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChoosePanel extends JPanel {

    private JPanel textPanel;


    private JTextField petName, dateOfBirth , dateOfLastAppointment, nameVet, diagnosis;

    ChoosePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        createLabels();
        createText();
    }

    private void createLabels() {
        JPanel labelPanel = new JPanel();
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


        add(labelPanel);
    }

    private void createText() {
        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));

        final int WIDTH = 100;
        final int HEIGHT = 20;


        petName = new JTextField();
        petName.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        petName.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        dateOfBirth = new JTextField();
        dateOfBirth.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        dateOfBirth.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        dateOfLastAppointment = new JTextField();
        dateOfLastAppointment.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        dateOfLastAppointment.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        nameVet = new JTextField();
        nameVet.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        nameVet.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        diagnosis = new JTextField();
        diagnosis.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        diagnosis.setMinimumSize(new Dimension(WIDTH, HEIGHT));



        textPanel.add(petName);
        textPanel.add(dateOfBirth);
        textPanel.add(dateOfLastAppointment);
        textPanel.add(nameVet);
        textPanel.add(diagnosis);

        add(textPanel);
    }

    public List<String> getPets() {
        List<String> result = new ArrayList<>();
        result.add(getFieldName());
//        int min = 0;
//        int max = 0;
//        if(!getTeam().equals("")){
//            min = Integer.parseInt(getTeam());
//        }
//        if(!getPosition().equals("")){
//            max = Integer.parseInt(getPosition());
//        }
        String min = "";
        String max = "";
        if (!getTeam().equals("")) {
            min = getTeam();
        }

        result.add(getDate());
        result.add(min);
        result.add(getCity());
        result.add(getStructure());
        result.add(max);
        return result;
    }

    public String getFieldName() {
        return petName.getText();
    }

    public String getDate() {
        return dateOfBirth.getText();
    }

    public String getTeam() {
        return dateOfLastAppointment.getText();
    }

    public String getCity() {
        return nameVet.getText();
    }

    public String getStructure() {
        return diagnosis.getText();
    }
}
