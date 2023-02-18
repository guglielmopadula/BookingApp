package org.gpadula.AI2SJobFairApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

class Button extends JButton{
    BookingTable table;
    public Button(PersonDataField person, BookingTable table, String[][] data, String stringdata, JTextArea endstatus){
        this.table=table;
        this.setText("Register");
        this.setBounds(50,500,95,30);

    }

}
