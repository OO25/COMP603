/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603assignment;

import java.util.Scanner;

/**
 *
 * @author Sandra
 */
public class PatPet {
    
public static void Pat(Pet pet) {
        int numPets = 0; 
        
        System.out.println("Do you want to pet " + pet.name + " (y/n)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine().toLowerCase();

        while (numPets < 4) {
            
            System.out.println("Do you want to pet " + pet.name + " again? (y/n)");
            answer = scanner.nextLine().toLowerCase();

            
            if (answer.equals("n")) {
                System.out.println("You stopped playing with " + pet.name + ".");
                break;
            }
            
            numPets++;
            
            if (numPets < 3) {
                System.out.println(pet.name + " is still waiting for more pets.");
            } else if (numPets > 3) {
                System.out.println(pet.name + " is angry!");
                pet.happiness--;
            } else {
                System.out.println(pet.name + " is happy!");
                pet.happiness++;
            }
        }
    }
}