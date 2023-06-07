/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603assignment;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hughm
 */
public class JsonHandler {

    Map<String, Map<String, Object>> MainSave;
    Map<String, Object> PlayerSave;
    Map<String, Object> PetSave;

    public JsonHandler() {

        MainSave = new HashMap();
        PlayerSave = new HashMap();
        PetSave = new HashMap();
    }

    public void mainSave(int id) {
        String playerID = "Player " + id;
        Map<String, Object> PlayerSaveCopy = new HashMap();
        PlayerSaveCopy.putAll(PlayerSave);
        Map<String, Object> PetSaveCopy = new HashMap();
        PetSaveCopy.putAll(PetSave);
        MainSave.put(playerID, PlayerSaveCopy);
        MainSave.put("Pet " + id, PetSaveCopy);
    }

    public void playerSave(String name, int coins, int fish, int bones, int kibble) {
        PlayerSave.put("Name", name);
        PlayerSave.put("Coins", coins);
        PlayerSave.put("Fish", fish);
        PlayerSave.put("Bones", bones);
        PlayerSave.put("Kibble", kibble);
    }

    public void objectSave(Player player, Pet pet) {
        PlayerSave.put("Name", player.name);
        PlayerSave.put("Coins", player.coins);
        PlayerSave.put("Fish", player.fish);
        PlayerSave.put("Bones", player.bones);
        PlayerSave.put("Kibble", player.kibble);
        
        PetSave.put("Name", pet.name);
        PetSave.put("Age", pet.age);
        PetSave.put("Happiness", pet.happiness);
        if (pet instanceof Dog)
            PetSave.put("Type", "Dog");
        else if (pet instanceof Cat)
            PetSave.put("Type", "Cat");
    }

    public void petSave(String name, int age, int happiness, String type) {
        PetSave.put("Name", name);
        PetSave.put("Age", age);
        PetSave.put("Happiness", happiness);
        PetSave.put("Type", type);
    }

    public void saveMap() {
        try {
            FileOutputStream fileOut = new FileOutputStream("save.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(MainSave);
            out.close();
            fileOut.close();
        } catch (IOException i) {
        }
    }

    public void loadMap() {
        try {
            FileInputStream fileIn = new FileInputStream("save.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            MainSave = (Map<String, Map<String, Object>>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println("didn't work");
        } catch (ClassNotFoundException c) {
            System.out.println("Not found");
        }
    }

    public Player loadPlayer(int id) {
        String name = (String) MainSave.get("Player " + id).get("Name");
        int coins = (int) MainSave.get("Player " + id).get("Coins");
        int fish = (int) MainSave.get("Player " + id).get("Fish");
        int bones = (int) MainSave.get("Player " + id).get("Bones");
        int kibble = (int) MainSave.get("Player " + id).get("Kibble");
        Player newPlayer = new Player(name, coins, fish, bones, kibble);
        return newPlayer;
    }

    public Pet loadPet(int id) {
        String name = (String) MainSave.get("Pet " + id).get("Name");
        String type = (String) MainSave.get("Pet " + id).get("Type");
        if (type.equals("Dog")) {
            Pet newPet = new Dog(name);
            return newPet;
        } else if (type.equals("Cat")) {
            Pet newPet = new Cat(name);
            return newPet;
        } else {
            Pet newPet = new Pet(name);
            return newPet;
        }

    }

    public static void main(String[] args) {
        JsonHandler test = new JsonHandler();
        int fish = 0;

        test.playerSave("John", 50, fish, 0, 0);
        test.petSave("Sparky", 3, 12, "Dog");
        test.mainSave(0);
        System.out.println("Main test after 1 addition: \n" + test.MainSave + "\n");
        test.playerSave("Jane", 70, fish, 0, 0);
        test.petSave("Spot", 10, 9, "Dog");
        test.mainSave(1);
        System.out.println("Main test after 2 additions: \n" + test.MainSave + "\n");
        test.playerSave("Jane", 90, fish, 0, 0);
        test.petSave("Spot", 11, 10, "Dog");
        test.mainSave(1);
        System.out.println("Main after override of jane: \n" + test.MainSave + "\n");
        test.playerSave("Josh", 30, fish, 9, 2);
        test.petSave("Jake", 10, 9, "Cat");
        test.mainSave(2);
        System.out.println(test.MainSave.get("Player 1").get("Name"));
        System.out.println(test.PlayerSave);
        test.saveMap();
        test.MainSave = null;
        System.out.println(test.MainSave);
        test.loadMap();
        System.out.println(test.MainSave);

        System.out.println(Arrays.toString((int[]) test.PlayerSave.get("Fish")));
        System.out.println(((int[]) test.PlayerSave.get("Fish"))[0]);

    }
}
