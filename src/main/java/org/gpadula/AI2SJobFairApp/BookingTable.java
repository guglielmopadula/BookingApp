package org.gpadula.AI2SJobFairApp;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class BookingTable extends JTable {
    JTable rows;
    JTable columns;

    Status[][] statuses;

    public BookingTable(String[][] data, String[][] row,  String[][] column) throws IOException {
        super(data,column[0]);
        this.setBounds(60, 200, 500, 300);
        String[] column_row= new String[1];
        String[] column_column= new String[column[0].length];
        Arrays.fill(column_column,"");
        Arrays.fill(column_row,"");
        rows= new JTable(row,column_row);
        columns=new JTable(column,column_column);
        columns.setBounds(60,150,500,50);
        columns.setRowHeight(50);
        rows.setRowHeight(50);
        rows.setBounds(0,200,60,300);
        this.setRowHeight(50);
        this.setDefaultEditor(Object.class, null);
        rows.setDefaultEditor(Object.class, null);
        columns.setDefaultEditor(Object.class, null);
        statuses=new Status[this.getRowCount()][this.getColumnCount()];
        for(int i=0; i<this.getRowCount(); i++) {
            for (int j = 0; j < this.getColumnCount(); j++) {
                if (this.getValueAt(i, j) == "") {
                    this.statuses[i][j] = Status.MODIFIABLE;
                } else {
                    this.statuses[i][j] = Status.BLOCKED;
                }
            }
        }



        for(int i=0; i<this.getRowCount(); i++){
            for(int j=0; j<this.getColumnCount();j++){
                this.setValueAt("",i,j);
            }
        }

    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
        Component comp = super.prepareRenderer(renderer, row, col);
        Status status = statuses[row][col];
        if (status.equals(Status.MODIFIABLE)) {
            comp.setBackground(Color.white);
        }
        if (status.equals(Status.SELECTED)) {
            comp.setBackground(Color.green);
        }

        if (status.equals(Status.BLOCKED)) {
                comp.setBackground(Color.red);
            }
        if (status.equals(Status.NONMODIFIABLE)) {
            comp.setBackground(Color.black);
        }

        return comp;
    }


}
