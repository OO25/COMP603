/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg603assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 *
 * @author Sandra
 */
public class Main {

    public static void welcome() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("""
                           Welcome to pet game!
                           It is a game where you pet
                           1. Select save 2. Quit""");
        int response = input.nextInt();
        if (response == 1) {
            System.out.println("LETS GOOOOO");
            Thread.sleep(10000);
        } else if (response == 2) {
            System.exit(0);
        }
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {
        CreateFile NewSave = new CreateFile();
        String path = NewSave.getPath();
        //welcome();
        while (true) {
            break;
        }
        System.out.println("TESTING");
        System.out.println("testing 2");/*
        System.out.println("AMONG US");
        for (int i = 0; i < 100; i++) { // console clear that works in IDE
            System.out.println("\b");
        }*/
 
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        System.out.println("ENTER NAME");
        Scanner input = new Scanner(System.in);
        String keyboard = input.nextLine();
        Pet cat1 = new Cat(keyboard);
        System.out.println("ENTER AGE");
        cat1.age = input.nextInt();
        
        writeToFile(cat1, path);

}
    
public static void writeToFile(Pet pet, String path) throws IOException {
    
        PrintWriter writer = new PrintWriter(new FileWriter(path, true));
        writer.println("Name: " + pet.name);
        writer.println("Age: " + pet.age);
        writer.close();

        
        
        
 }
}
    

      
    


