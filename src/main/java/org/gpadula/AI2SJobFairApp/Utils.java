package org.gpadula.AI2SJobFairApp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    FileWriter myWriter;
    static String[][] readCsv(String file) throws IOException {
        List<String[]> lines = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = r.readLine()) != null)
                lines.add(line.split(",",-1));
        }
        return lines.toArray(new String[lines.size()][]);
    }

    static void writeCSV(String[][] data,String file, int rows, int columns) throws IOException {
        String s = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                s = s + data[i][j];
                if (j != columns - 1) {
                    s = s + ",";
                }
            }
            if (i != rows - 1) {
                s = s + "\n";
            }

        }
        FileWriter myWriter=null;
        try {
            myWriter = new FileWriter(file);
            myWriter.write(s);
        }
        finally {
            myWriter.close();
        }
    }

}
