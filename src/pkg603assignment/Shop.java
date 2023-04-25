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
public class Shop {
    private Player player;
    private Scanner scanner;
    
    public Shop(Player player) {
        this.player = player;
        this.scanner = new Scanner(System.in);
    }
    
    public void run() {
        
        System.out.println("Welcome to the pet shop!");
        boolean running = true;
        
        while (running) {
            System.out.println("You have " + player.coins + " coins.");
            System.out.println("What would you like to do?");
            System.out.println("1. Buy bones (5 coins)");
            System.out.println("2. Buy kibble (2 coins)");
            System.out.println("3. Sell fish");
            System.out.println("4. Quit");
            
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    buyBones();
                    break;
                case 2:
                    buyKibble();
                    break;
                case 3:
                    sellFish();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        System.out.println("Goodbye!");
    }
    
    private void buyBones() {
        if (player.coins >= 5) {
            
            player.coins = player.coins - 5;
            player.bones++;
            System.out.println("You bought a bone!");
        } else {
            System.out.println("You don't have enough coins!");
        }
    }
    
    private void buyKibble() {
        if (player.coins >= 2) {
           player.coins = player.coins - 2;
            player.kibble++;
            System.out.println("You bought a kibble!");
        } else {
            System.out.println("You don't have enough coins!");
        }
    }
    
    private void sellFish() {
        System.out.println("What size is the fish? (1 = small, 2 = medium, 3 = big)");
        int input = scanner.nextInt();
        int value;
        switch (input) {
            case 1:
                value = 2;
                break;
            case 2:
                value = 3;
                break;
            case 3:
                value = 5;
                break;
            default:
                System.out.println("Invalid size!");
                return;
        }
        player.coins = player.coins + value;
        System.out.println("You sold a fish for " + value + " coins!");
    }
}