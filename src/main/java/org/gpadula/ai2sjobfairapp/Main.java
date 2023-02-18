package org.gpadula.ai2sjobfairapp;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import com.formdev.flatlaf.FlatLightLaf;


public class Main extends JFrame {

    String stringdata;
    String rowdata;
    String columndata;

    Frame frame;

    public Main() throws IOException {
        stringdata = "dati.csv";
        rowdata = "righe.csv";
        columndata = "colonne.csv";
        String[][] data = Utils.readCsv(stringdata);
        String[][] databak = Utils.readCsv(stringdata);
        String[][] column = Utils.readCsv(columndata);
        String[][] row = Utils.readCsv(rowdata);
        BookingTable table = new BookingTable(data, row, column);
        PersonDataField person = new PersonDataField();
        JTextArea endstatus = new JTextArea();
        Font font1 = new Font("SansSerif", Font.BOLD, 25);
        endstatus.setFont(font1);
        endstatus.setEditable(false);
        endstatus.setBounds(0, 550, 700, 200);
        JButton button = new JButton();
        button.setText("Register");
        button.setBounds(50, 500, 95, 30);
        frame = new Frame(person, table, button, databak, stringdata, endstatus);
    }

    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        Main main = new Main();
        main.start();
    }

    void start() {
        frame.setTable();
        frame.setButton();
    }


}
