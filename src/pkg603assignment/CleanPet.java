/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603assignment;

/**
 *
 * @author Sandra
 */
import java.util.Scanner;

public class CleanPet {
    
   
    Scanner scanner = new Scanner(System.in);
    
    public void run(Pet pet) {
        System.out.println("Welcome to the Pet Cleaning Game!");
        System.out.println("Your pet is dirty and needs a bath.");
        System.out.println("Let's get started!\n");
        
        while (pet.clean < 5) {
            System.out.println("What would you like to do?");
            System.out.println("1. Brush your pet");
            System.out.println("2. Give your pet a bath");
            System.out.println("3. Quit game\n");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    pet.clean++;
                    System.out.println("You brushed your pet.");
                    break;
                case 2:
                    pet.clean++;
                    System.out.println("You gave your pet a bath.");
                    break;
                case 3:
                    System.out.println("Thanks for playing!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
           if(pet.clean < 5) {
               System.out.println(pet.name + " is still dirty!");
           }
           else if(pet.clean >= 5) {
               System.out.println(pet.name + " is clean!");
           }          
        }
    }
}