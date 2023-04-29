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
        
        Player Andrew = new Player();

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



       Pet naz = new Dog("NAZ");

       CleanPet game = new CleanPet();
       game.run(naz);

}

    
}
