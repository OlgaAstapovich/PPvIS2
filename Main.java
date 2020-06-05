package com.company;

import com.company.controler.DataController;
import com.company.controler.MenuPanel;
import com.company.view.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

public class Main extends JFrame {

    private DataController dataController;

    private AddFrame addFrame;
    private FindFrame findFrame;
    private DelFrame delFrame;

    private TablePanel tablePanel;

    JButton add;
    JButton delete;
    JButton find;


    Main(DataController dataController) {
        super("Second lab");
        this.dataController = dataController;
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);

        panel.setLayout(layout);

        addFrame = new AddFrame(this.dataController);
        delFrame = new DelFrame(this.dataController);
        findFrame = new FindFrame(this.dataController);

        FindListener findListener = new FindListener();
        AddListener addListener = new AddListener();
        DelListener delListener = new DelListener();

        MenuPanel menuPanel = new MenuPanel();
        menuPanel.getFind().addActionListener(findListener);
        menuPanel.getAddInfo().addActionListener(addListener);
        menuPanel.getRemoveInfo().addActionListener(delListener);
        menuPanel.getSave().addActionListener(new SaveListener());
        menuPanel.getLoadFile().addActionListener(new LoadListener());
        panel.add(menuPanel);

        tablePanel = new TablePanel(this.dataController);
        panel.add(tablePanel);

        ControlPanel controlPanel = new ControlPanel();
        controlPanel.getDelete().addActionListener(delListener);
        controlPanel.getFind().addActionListener(findListener);
        controlPanel.getAdd().addActionListener(addListener);
        panel.add(controlPanel);

        delFrame.getDelete().addActionListener(actionEvent -> {
            int amount = 0;
            try {
                amount = delFrame.deletePets();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Deleted: " + amount);
            tablePanel.showTable(this.dataController);
        });

        addFrame.getAdd().addActionListener(actionEvent -> {
            try {
                addFrame.addPet();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tablePanel.showTable(this.dataController);
        });

        add(panel);
        setSize(500, 300);

        setVisible(true);
    }

    private class FindListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            findFrame.setVisible(!findFrame.isVisible());
        }
    }

    private class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            addFrame.setVisible(!addFrame.isVisible());
        }
    }

    private class DelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            delFrame.setVisible(!delFrame.isVisible());
        }
    }

    private class SaveListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Special xml file", "xml");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showSaveDialog(chooser);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                dataController.write(path);
            }
        }
    }

    private class LoadListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Special xml file", "xml");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(chooser);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                dataController.read(path);
                tablePanel.showTable(dataController);
            }
        }
    }

    public static void main(String[] args) {
        DataController dataBase = new DataController();
        new Main(dataBase);

    }
}
