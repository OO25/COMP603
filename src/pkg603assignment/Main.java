/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg603assignment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author hughm
 */
public class Main {
    public static int welcome() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 10; i++) { // console clear that works in IDE
            System.out.println("\b");
        }
        System.out.println("""
                           ======================================
                           |      Welcome to The Pet Game!      |
                           |          Please Pick One:          |
                           ======================================
                           |                                    |
                           |          1. New Game Save          |
                           |         2. Load Game Save          |
                           |            3. Quit Game            |
                           |                                    |
                           ======================================""");
        while (true) {
            int response = input.nextInt();
            switch (response) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    System.out.println("Exiting Programe");
                    System.exit(0);
                default:
                    System.out.println("Invalid Input Please Try Again: ");
                    break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {
        JsonHandler jsonHandler = new JsonHandler();
        jsonHandler.loadMap();
        Player player;
        Pet pet;
        int saveID;
        Scanner input = new Scanner(System.in);
        int welcomeResult = welcome();

        if (welcomeResult == 1) { // Make a new Save
            saveID = (jsonHandler.MainSave.size() - 2) / 2 + 1;
            System.out.println("Create new save: \n");
            System.out.println("What is your name?\n");
            String name = input.nextLine();
            player = new Player(name);
            System.out.println("Do you want a 1. cat or 2. dog?");
            int answer = input.nextInt();
            input.nextLine();
            if (answer == 1) {
                System.out.println("What do you want to name your dog?\n");
                String petName = input.nextLine();
                pet = new Dog(petName);
            } else {
                System.out.println("What do you want to name your cat?\n");
                String petName = input.nextLine();
                pet = new Cat(petName);
            }
        } else { // Load an existing save
            System.out.println("\nLoading game save...\n");
            System.out.println("Which save to load?\n");
            int options = (jsonHandler.MainSave.size() - 2) / 2;
            for (int i = 0; i <= options; i++) { // display names of all saved profiles
                String playerName = (String) jsonHandler.MainSave.get("Player " + i).get("Name");
                int playerCoins = (int) jsonHandler.MainSave.get("Player " + i).get("Coins");
                String petName = (String) jsonHandler.MainSave.get("Pet " + i).get("Name");
                String petType = (String) jsonHandler.MainSave.get("Pet " + i).get("Type");
                System.out.print(i + 1 + ": Player: " + playerName + " Coins: " + playerCoins
                        + "\n   Pet : " + petName + " Type: " + petType + "\n\n");
            }
            while (true) {
                int response = input.nextInt() - 1;
                if ((response >= 0) && (response <= options)) {
                    saveID = response;
                    player = jsonHandler.loadPlayer(response); // load the player
                    pet = jsonHandler.loadPet(response);
                    break;
                } else {
                    System.out.println("Invalid input, try again!\n");
                }
            }
        }
        Shop shop = new Shop(player);
        CleanPet cleanPet = new CleanPet();
        while (true) {
            for (int i = 0; i < 10; i++) { // console clear that works in IDE
                System.out.println("\b");
            }
            pet.Happy();
            System.out.println("""
                           ======================================
                           |       This is the main menu!       |
                           |          Please Pick One:          |
                           ======================================
                           |                                    |
                           |            1. Show info            |
                           |              2. Shop               |
                           |             3. Pat pet             |
                           |            4. Clean pet            |
                           |              5. Fish               |
                           |        6. Quit Game and Save       |
                           |                                    |
                           ======================================""");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Player Info:");
                    System.out.println(player.toString());
                    System.out.println("\nPet Info:");
                    System.out.println(pet.toString());
                    System.out.println("\nPress anything to continue...");
                    Scanner wait = new Scanner(System.in);
                    String next = wait.nextLine();
                    break;
                case 2:
                    shop.run();
                    break;
                case 3:
                    PatPet.Pat(pet);
                    break;
                case 4:
                    cleanPet.run(pet);
                    break;
                case 5:
                    Fishing fishing = new Fishing();
                    fishing.play();
                    player.fish[0] += fishing.smallFish;
                    player.fish[1] += fishing.medFish;
                    player.fish[2] += fishing.largeFish;
                    break;
                case 6:
                    jsonHandler.objectSave(player, pet);
                    jsonHandler.mainSave(saveID);
                    jsonHandler.saveMap();
                    System.exit(0);
                default:
                    System.out.println("Invalid input!");
                    Thread.sleep(1000);
                    break;
            }
        }
    }
}
