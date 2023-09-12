package org.example.reader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IntegerReader {
    String filePath;

    public IntegerReader (String filePath) {
        this.filePath = filePath;
    }

    public Integer readValue (int searchValue) {
        try (FileInputStream fileReader = new FileInputStream(this.filePath)) {
            int data;
            while ((data = fileReader.read()) != -1) {
                if (data == searchValue) {
                    return data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
