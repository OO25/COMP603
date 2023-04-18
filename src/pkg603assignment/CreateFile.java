/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603assignment;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Sandra
 */

public class CreateFile {
    
    String path;
    
    public CreateFile() { 
        try {
            File file = new File("SaveFile.txt");
            path = file.getAbsolutePath();
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    
    public String getPath() {
        return this.path;
    }
}