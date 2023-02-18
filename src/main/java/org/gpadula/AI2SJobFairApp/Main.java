package org.gpadula.AI2SJobFairApp;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.formdev.flatlaf.FlatLightLaf;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;


public class Main extends JFrame {

    String stringdata;
    String rowdata;
    String columndata;

    public Main() throws IOException {
        stringdata="dati.csv";
        rowdata="righe.csv";
        columndata="colonne.csv";
        String[][] data=Utils.readCsv(stringdata);
        String[][] data_bak=Utils.readCsv(stringdata);
        String[][] column=Utils.readCsv(columndata);
        String[][] row=Utils.readCsv(rowdata);
        BookingTable table= new BookingTable(data,row,column);
        PersonDataField person= new PersonDataField();
        JTextArea endstatus= new JTextArea();
        Font font1 = new Font("SansSerif", Font.BOLD, 25);
        endstatus.setFont(font1);
        endstatus.setEditable(false);
        endstatus.setBounds(0,550,700,200);
        Button button= new Button(person, table, data_bak, stringdata,endstatus);
        button.setText("Register");
        button.setBounds(50,500,95,30);

        Frame frame= new Frame(person,table,button,data_bak, stringdata,endstatus);
    }
    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        Main main=new Main();
    }
}
