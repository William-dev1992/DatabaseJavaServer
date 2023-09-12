package org.example.writers;

import java.io.FileOutputStream;
import java.io.IOException;

public class IntegerWriter {

    String filePath;

    public IntegerWriter (String filePath) {
        this.filePath = filePath;
    }

    public void writeValue (Integer value) {
        try (FileOutputStream valuesWriter = new FileOutputStream(this.filePath)) {
            valuesWriter.write(value);
            System.out.println("Successfully written.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
