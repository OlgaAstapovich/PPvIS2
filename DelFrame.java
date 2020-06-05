package com.company.view;

import com.company.controler.DataController;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DelFrame extends JFrame {
    private DataController dataController;

    private ChoosePanel choosePanel = new ChoosePanel();

    private JButton delete;

    public DelFrame(DataController dataController){
        super("Delete frame");
        this.dataController = dataController;

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        init();
        setSize(500, 120);
        setResizable(false);
    }

    private void init() {
        delete = new JButton("Delete");
        delete.setMaximumSize(new Dimension(100, 20));

        add(choosePanel);
        add(delete);
    }

    public int deletePets() throws ParseException {
        DateFormat format = new SimpleDateFormat("dd:MM:yyyy", Locale.ENGLISH);
        List<String> list = choosePanel.getPets();
        return  this.dataController.deletePets(list.get(0), (list.get(1)), (list.get(2)), list.get(3), list.get(4));
    }

    public DataController getDataController() {
        return dataController;
    }

    public ChoosePanel getChoosePanel() {
        return choosePanel;
    }

    public JButton getDelete() {
        return delete;
    }
}
