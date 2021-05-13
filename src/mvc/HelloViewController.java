package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HelloViewController implements HelloWorld, SaveFile {
    private HelloModel model;
    private File save;
    private ImageIcon icon;

    private JFrame view;
    private JButton hello, max, add, subtract, min;
    private JRadioButton line, square;
    private ButtonGroup buttonGroup;
    private JLabel value;
    private JPanel valuePanel, buttonPanel;

    private int val1, val2;

    public HelloViewController(HelloModel hwModel, File save) {
        this.save = save;
        icon = new ImageIcon(getClass().getResource("/assets/icon.png"));

        try {
            readSaveFile();
            //corrects invalid inputs from save file, swapping them with defaults
            if(val1 < FIRST_MIN || val1 > FIRST_MAX) {
                val1 = 0;
            }
            if(val2 < SECOND_MIN || val2 > SECOND_MAX) {
                val2 = 1;
            }
        } catch (IOException e) {
            defaultSettings();
        }
        model = hwModel;
        model.setValue(val2);
        setupGUI();
    }

    public void readSaveFile() throws IOException {
        Scanner saveReader = new Scanner(save);

        if(saveReader.hasNextLine()) {
            val1 = Integer.parseInt(saveReader.nextLine());

            if(saveReader.hasNextLine()) {
                val2 = Integer.parseInt(saveReader.nextLine());
            } else {
                throw new IOException("Invalid Save File!");
            }
        } else {
            throw new IOException("Invalid Save File!");
        }

        saveReader.close();
    }

    public void defaultSettings() {
        val1 = 0;
        val2 = 1;
    }

    public void setupGUI(){
        view = new JFrame();
        view.setTitle("Hello World 2!");
        view.setIconImage(icon.getImage());

        view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        view.setLayout(new GridLayout(3,1));

        view.setSize(640,480);
        view.setVisible(true);

        hello = new JButton("Hello, World!");

        buttonPanel = new JPanel(new FlowLayout());
        line = new JRadioButton("Line Print");
        square = new JRadioButton("Square Print");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(line);
        buttonGroup.add(square);
        buttonPanel.add(line);
        buttonPanel.add(square);

        if(val1 == 0) {
            buttonGroup.setSelected(line.getModel(), true);
        } else {
            buttonGroup.setSelected(square.getModel(), true);
        }

        valuePanel = new JPanel(new FlowLayout());
        value = new JLabel(String.valueOf(model.getValue()));
        max = new JButton("Max");
        add = new JButton("+ 1");
        subtract = new JButton("- 1");
        min = new JButton("Min");
        valuePanel.add(max);
        valuePanel.add(add);
        valuePanel.add(value);
        valuePanel.add(subtract);
        valuePanel.add(min);

        addComponentsToGUI();
        addActionListeners();
        buttonCheck();
    }

    public void addComponentsToGUI() {
        view.add(hello);
        view.add(buttonPanel);
        view.add(valuePanel);
    }

    public void addActionListeners() {
        hello.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (square.isSelected()) {
                    model.printMessageSquared(model.getValue());
                } else if (line.isSelected()) {
                    model.printMessageMulti(model.getValue());
                } else {
                    model.printMessageLine();
                }
            }
        });

        max.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setValueMax();
                value.setText(String.valueOf(model.getValue()));
                buttonCheck();
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.incrementValue();
                value.setText(String.valueOf(model.getValue()));
                buttonCheck();
            }
        });

        subtract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.decrementValue();
                value.setText(String.valueOf(model.getValue()));
                buttonCheck();
            }
        });

        min.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setValueMin();
                value.setText(String.valueOf(model.getValue()));
                buttonCheck();
            }
        });

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveState();
            }
        });
    }

    public void buttonCheck() {
        int value = model.getValue();

        if(value == 1) {
            min.setEnabled(false);
            subtract.setEnabled(false);
        } else {
            min.setEnabled(true);
            subtract.setEnabled(true);
        }

        if(value == 6) {
            max.setEnabled(false);
            add.setEnabled(false);
        } else {
            max.setEnabled(true);
            add.setEnabled(true);
        }
    }

    public void saveState() {
        fileExistCheck();

        System.out.println("Saving...");
        modifySaveFile();
    }

    public void modifySaveFile(){
        try {
            FileWriter saveWrite = new FileWriter("save.txt");
            int val1 = buttonGroup.getSelection() == line.getModel() ? 0 : 1, val2 = model.getValue();

            saveWrite.write(val1 + "\n" + val2);
            saveWrite.close();
            System.out.println("File saved successfully!");
        } catch (IOException e) {
            System.out.println("File saved failed!");
            e.printStackTrace();
        }
    }

    public void fileExistCheck() {
        try {
            if(save.createNewFile()) {
                System.out.println("Save file created!");
            }//checks if file exists, creates it if it doesn't
        } catch (IOException e) {
            System.out.println("An error has occurred while trying to access save file.");
            e.printStackTrace();
        }//catch
    }//method end
}//class end
