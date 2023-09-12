package org.example.utils;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class CreateFile {


    public static void main(String[] args) {
        try {
            File myObj = new File("/home/williamf/Github/personal/data/insert.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void WriteValues() {
        try {
            FileWriter Writer
                    = new FileWriter("myfile.txt");
            Writer.write(
                    "Files in Java are seriously good!!");
            Writer.close();
            System.out.println("Successfully written.");
        }
        catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }
}