package com.company.view;

import com.company.controler.DataController;

import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class FindFrame extends JFrame {
    private DataController DC;

    private JButton findButton;

        private TablePanel tablePanel;

        private ChoosePanel choosePanel = new ChoosePanel();
        public FindFrame(DataController DC){
            super("Find Pet");
            this.DC = DC;
            setSize(500, 320);
            setResizable(false);
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
            DateFormat format = new SimpleDateFormat("dd:MM:yyyy", Locale.ENGLISH);
            findButton = new JButton("Find");
            findButton.addActionListener(actionEvent -> {
                List<String> list = choosePanel.getPets();
                DataController temp = new DataController();
               // try {
                    temp.setPets(this.DC.findPets(list.get(0), (list.get(1)), (list.get(2)), list.get(3),list.get(4)));
                //} catch (ParseException e) {
                //    e.printStackTrace();
                //}
                tablePanel.setPets(temp);
                tablePanel.showTable(temp);
            });

        tablePanel = new TablePanel(DC);
        JPanel pan = new JPanel();
        pan.add(findButton);

        add(choosePanel);
        add(pan);
        add(tablePanel);
    }

    public DataController getDC() {
        return DC;
    }

    public JButton getFindButton() {
        return findButton;
    }

    public TablePanel getTablePanel() {
        return tablePanel;
    }

    public ChoosePanel getChoosePanel() {
        return choosePanel;
    }
}
