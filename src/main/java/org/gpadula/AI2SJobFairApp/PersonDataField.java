package org.gpadula.AI2SJobFairApp;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PersonDataField
{
    final JTextField namefield;
    final JLabel namelabel;

    final JTextField emailfield;
    final JLabel emaillabel;

    final JTextField surnamefield;
    final JLabel surnamelabel;

    public PersonDataField() {
        namefield = new JTextField();
        namelabel = new JLabel("Name");
        namelabel.setBounds(50, 50, 80, 20);
        namefield.setBounds(130, 50, 150, 20);
        surnamefield = new JTextField();
        surnamelabel = new JLabel("Surname");
        surnamelabel.setBounds(50, 80, 80, 20);
        surnamefield.setBounds(130, 80, 150, 20);
        emailfield = new JTextField();
        emaillabel = new JLabel("Email");
        emaillabel.setBounds(50, 110, 80, 20);
        emailfield.setBounds(130, 110, 150, 20);



    }

};

