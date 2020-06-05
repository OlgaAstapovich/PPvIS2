package com.company.view;

import com.company.controler.DataController;
import com.company.model.Pet;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TablePanel extends JPanel {
    private DataController DC;

    private JTable table;
    private JPanel controlPane;
    private JLabel count;
    private JLabel page;

    int left;
    int right;
    int pageCounter;

    public TablePanel(DataController DC){
        this.DC = DC;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        table = new JTable(10,5);

        makeControlButtons();
        createLabels();
        add(table);
        add(controlPane);
    }

    public void createLabels(){
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

    void makeControlButtons(){
        left = 1;
        right = 10;
        pageCounter = 1;

        controlPane = new JPanel();
        controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.LINE_AXIS));
        count = new JLabel("Pets " + left + " - " + right);
        page = new JLabel("Page: " + pageCounter);

        JButton nextPage = new JButton("Next");
        nextPage.addActionListener(actionEvent -> {
            if(DC.isExists(right+1)) {
                left += 10;
                right += 10;
                pageCounter++;
                count.setText("Pets " + left + " - " + right);
                page.setText("Page: " + pageCounter);
                showTable(DC);
            }
        });

        JButton prevPage = new JButton("Previous");
        prevPage.addActionListener(actionEvent -> {
            if(pageCounter > 1) {
                left -= 10;
                right -= 10;
                pageCounter--;
                count.setText("Pets " + left + " - " + right);
                page.setText("Page: " + pageCounter);
                showTable(DC);
            }
        });

        controlPane.add(count);
        controlPane.add(Box.createHorizontalGlue());
        controlPane.add(page);
        controlPane.add(Box.createHorizontalGlue());
        controlPane.add(prevPage);
        controlPane.add(nextPage);
    }

    private void addPet(int row, Pet pet){
        table.setValueAt(pet.getPetName(), row, 0);
        DateFormat format = new SimpleDateFormat("dd:MM:yyyy", Locale.ENGLISH);
        table.setValueAt(format.format(pet.getDateOfBirth()), row, 1);
        table.setValueAt(format.format(pet.getDateOfLastAppointment()), row, 2);
        table.setValueAt(pet.getNameVet(), row, 3);
        table.setValueAt(pet.getDiagnosis(), row, 4);
    }

    private void addEmpty(int row){
        table.setValueAt(" ", row, 0);
        table.setValueAt(" ", row, 1);
        table.setValueAt(" ", row, 2);
        table.setValueAt(" ", row, 3);
        table.setValueAt(" ", row, 4);
    }

    public void setPets(DataController data){
        DC = data;
    }
    
    public  void showTable(DataController DC){
        int index = 0;
        for(int i = left-1; i < right; i++){
            if(DC.isExists(i)) {
                    addPet(index, DC.atIndex(i));
            }else{
                addEmpty(index);
            }
            index++;
        }
    }
}
