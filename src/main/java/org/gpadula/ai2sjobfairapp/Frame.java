package org.gpadula.ai2sjobfairapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class Frame extends JFrame {
    BookingTable table;
    PersonDataField person;
    String stringdata;
    JTextArea endstatus;
    String[][] data;
    JButton button;
    public Frame(PersonDataField person, BookingTable table, JButton button, String[][] data, String stringdata, JTextArea endstatus) {
        this.table = table;
        this.person=person;
        this.endstatus=endstatus;
        this.button=button;
        this.data=data;
        this.stringdata=stringdata;
        this.add(this.button);
        this.add(this.person.namefield);
        this.add(this.person.namelabel);
        this.add(this.person.surnamefield);
        this.add(this.person.surnamelabel);
        this.add(this.person.emailfield);
        this.add(this.person.emaillabel);
        this.add(this.table);
        this.add(this.table.rows);
        this.add(this.table.columns);
        this.setSize(1000, 1000);
        this.setLayout(null);
        this.setVisible(true);
        this.add(endstatus);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setTable();
        this.setButton();

    }


    private void setTable(){
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (table.statuses[row][col] == Status.MODIFIABLE) {
                    table.statuses[row][col] = Status.SELECTED;
                    set_unmodifiable_inner(row, col);
                } else {
                    if (table.statuses[row][col] == Status.SELECTED) {
                        table.statuses[row][col] = Status.MODIFIABLE;
                        set_modifiable_inner(row,col);
                    }
                    for (int i = 0; i < table.getRowCount(); i++) {
                        for (int j = 0; j < table.getColumnCount(); j++) {
                            if (table.statuses[i][j] == Status.SELECTED) {
                                set_unmodifiable_inner(i, j);
                            }
                        }
                    }

                }

                myrepaint();
            }
        });

    }

    private void myrepaint() {
        this.repaint();
    }

    private void mydispose() {
        this.setVisible(false);
        this.dispose();
    }

    private void set_unmodifiable_inner(int row, int col) {
        for (int i = 0; i < table.getRowCount(); i++) {
            if (table.statuses[i][col] == Status.MODIFIABLE) {
                table.statuses[i][col] = Status.NONMODIFIABLE;
            }
        }
        for (int j = 0; j < table.getColumnCount(); j++) {
            if (table.statuses[row][j] == Status.MODIFIABLE) {
                table.statuses[row][j] = Status.NONMODIFIABLE;
            }
        }

    }
    private void set_modifiable_inner(int row, int col) {
        for (int i = 0; i < table.getRowCount(); i++) {
            if (table.statuses[i][col] == Status.NONMODIFIABLE) {
                table.statuses[i][col] = Status.MODIFIABLE;
            }
        }
        for (int j = 0; j < table.getColumnCount(); j++) {
            if (table.statuses[row][j] == Status.NONMODIFIABLE) {
                table.statuses[row][j] = Status.MODIFIABLE;
            }
        }
        }

    private void setButton(){
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = person.namefield.getText().replaceAll("\\s+","");
                String surname = person.surnamefield.getText().replaceAll("\\s+","");
                String email= person.emailfield.getText().replaceAll("\\s+","");
                int flag = 0;

                try {
                    String[][] data2 = Utils.readCsv(stringdata);
                    if (Arrays.deepEquals(data, data2)) {
                        if (!(name.equals("") || surname.equals("") || email.equals(""))) {
                            for (int i = 0; i < table.getRowCount(); i++) {
                                for (int j = 0; j < table.getColumnCount(); j++) {
                                    if (table.statuses[i][j] == Status.SELECTED) {
                                        data2[i][j] = name + " " + surname+" "+email;
                                        flag = 1;
                                    }
                                }
                            }

                            if (flag == 1) {
                                Utils.writeCSV(data2, "dati.csv", table.getRowCount(), table.getColumnCount());
                                endstatus.setForeground(Color.green);
                                endstatus.setText("You have successfully registered \n The application will close in 10s");
                                Timer timer = new Timer(10000, new ActionListener() {
                                    public void actionPerformed(ActionEvent evt) {
                                        mydispose();
                                    }
                                });
                                timer.setRepeats(false);
                                timer.start();
                            } else {
                                endstatus.setForeground(Color.orange);
                                endstatus.setText("You have not selected any slot");

                            }
                        } else {
                            endstatus.setForeground(Color.orange);
                            endstatus.setText("Please enter your name/surname/email");

                        }

                    } else {
                        endstatus.setForeground(Color.red);
                        endstatus.setText("You have been too slow, please reopen the page");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }


    }
