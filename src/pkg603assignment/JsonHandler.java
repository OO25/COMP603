/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603assignment;

import java.io.*;
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
    
    public JsonHandler(){
        
        MainSave = new HashMap();
        PlayerSave = new HashMap();
        PetSave = new HashMap();
    }
    
    public void mainSave(int id){
        String playerID = "Player "+id;
        Map<String, Object> PlayerSaveCopy = new HashMap();
        PlayerSaveCopy.putAll(PlayerSave);
        Map<String, Object> PetSaveCopy = new HashMap();
        PetSaveCopy.putAll(PetSave);
        MainSave.put(playerID, PlayerSaveCopy);
        MainSave.put("Pet "+id, PetSaveCopy);
    }
    
    public void playerSave(String name, int coins){
        PlayerSave.put("Name", name);
        PlayerSave.put("Coins", coins);
    }
    
    public void petSave(String name, int age, int happiness){
        PetSave.put("Name", name);
        PetSave.put("Age", age);
        PetSave.put("Happiness", happiness);
    }
    
    public void saveMap(){
        try {
            FileOutputStream fileOut = new FileOutputStream("save.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(MainSave);
            out.close();
            fileOut.close();
        } catch (IOException i){
        }
    }
    
    public void loadMap(){
        try{
            FileInputStream fileIn = new FileInputStream("save.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            MainSave = (Map<String, Map<String, Object>>) in.readObject();
            in.close();
            fileIn.close();
        }catch (IOException i){
        }catch (ClassNotFoundException c){
            System.out.println("Not found");
        }
    }
    
    public static void main(String[] args) {
        JsonHandler test = new JsonHandler();
        test.playerSave("John", 50);
        test.petSave("Sparky", 3, 12);
        test.mainSave(0);
        System.out.println("Main test after 1 addition: \n" + test.MainSave+"\n");
        test.playerSave("Jane", 70);
        test.petSave("Spot", 10, 9);
        test.mainSave(1);
        System.out.println("Main test after 2 additions: \n" + test.MainSave+"\n");
        test.playerSave("Jane", 90);
        test.petSave("Spot", 11, 10);
        test.mainSave(1);
        System.out.println("Main after override of jane: \n" + test.MainSave+"\n");
        System.out.println(test.MainSave.get("Player 1").get("Name"));
        System.out.println(test.PlayerSave);
        test.saveMap();
        test.MainSave = null;
        System.out.println(test.MainSave);
        test.loadMap();
        System.out.println(test.MainSave);
        
    }
}
