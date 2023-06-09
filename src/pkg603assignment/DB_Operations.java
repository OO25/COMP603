/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sandra
 */
public class DB_Operations {
    
    private DB_Manager dbManager;

    public DB_Operations() {
        dbManager = new DB_Manager();
    }

    //Creates a table, just for testing and to make things simple so i dont have to type in SQL
    public void createTable() {
        try {
            Statement statement = dbManager.getConnection().createStatement();
            String newTableName = "PlayerData";

            String sqlCreate = "create table " + newTableName + " (" 
                    + "ID int, PlayerName varchar(20), Coins int, Fish int, Bones int,  Kibble int, Species varchar(3),"
                    + "Age int, PetName varchar(20), Happiness int,  PRIMARY KEY (ID))";
            statement.executeUpdate(sqlCreate);


            System.out.println("Table created");

        } catch (SQLException ex) {
            Logger.getLogger(DB_Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //Used when creating an inital save, creates a new save for the player
    public void insertPlayer(Player player) {
        try {
            // Prepare SQL statement for insertion
            String sql = "INSERT INTO PlayerData (ID, Playername, coins, fish, bones, kibble, species, age, petname, happiness, clean) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = dbManager.getConnection().prepareStatement(sql);
            Statement stmt = dbManager.getConnection().createStatement();

            
            String query = "select count(*) from PLAYERDATA";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            int count = rs.getInt(1);
            System.out.println("Number of records in the PLAYERDATA table: "+count);
            
            statement.setInt(1, count + 1);
            statement.setString(2, player.name);
            statement.setInt(3, player.coins);
            statement.setInt(4, player.fish);
            statement.setInt(5, player.bones);
            statement.setInt(6, player.kibble);
            statement.setString(7, player.pet.species.toLowerCase());
            statement.setInt(8, player.pet.age);
            statement.setString(9, player.pet.name);
            statement.setInt(10, player.pet.happiness);
            statement.setInt(11, player.pet.clean);
            
            statement.executeUpdate();
            System.out.println("Data inserted successfully.");

            rs.close();
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    //Saves a player into previously created row
     public void playerSave(Player player) {
        
        try {
            Statement statement = dbManager.getConnection().createStatement();

            String sql = "UPDATE PlayerData "
                    + "SET Coins = " + player.coins+ ","
                    + "FISH = " + player.fish + "," 
                    + "BONES =" + player.bones + ","
                    + "KIBBLE =" + player.kibble + ","
                    + "SPECIES = '"+ player.pet.species + "' ,"
                    + "AGE ="+ player.pet.age + ","
                    + "PETNAME = '"+ player.pet.name + "' ,"
                    + "HAPPINESS =" + player.pet.happiness + ", "
                    + "CLEAN =" + player.pet.clean + " "
                    + "WHERE PLAYERNAME = '"+ player.name +"'";

            
            int rowsAffected = statement.executeUpdate(sql);
            
            
            System.out.println("Table updated successfully." + rowsAffected);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }
     
    public Player loadPlayer(int ID) {
        Player player = new Player("");
        player.pet = new Pet(null);
        ResultSet rs = dbManager.myQuery("select * from PLAYERDATA where ID = " + ID + "");
        if (rs == null) {
            return null;
        }
        try {
            while (rs.next()) {
                player.setName(rs.getString("PLAYERNAME"));
                player.setCoins(rs.getInt("COINS"));
                player.setFish(rs.getInt("FISH"));  
                player.setBones(rs.getInt("BONES"));
                player.setKibble(rs.getInt("BONES"));
                player.pet = new Pet(rs.getString("PETNAME"));
                player.pet.setSpecies(rs.getString("SPECIES"));
                player.pet.setAge(rs.getInt("AGE"));
                player.pet.setHappiness(rs.getInt("HAPPINESS")); 
                player.pet.setClean(rs.getInt("CLEAN"));
                
            }
            rs.close();
            
        } catch (SQLException ex) {
            return null;
        }
        
        return player;
    }
    
    public ArrayList<String> List() {
        
        ArrayList<String> playerList = new ArrayList<String>();
        
        try {
            
        Connection connection = dbManager.getConnection();
        Statement statement = connection.createStatement();

        
        ResultSet resultSet = statement.executeQuery("SELECT PLAYERNAME, COINS, PETNAME FROM PLAYERDATA");

        while (resultSet.next()) {
            String playerName = resultSet.getString("PLAYERNAME");
            int coins = resultSet.getInt("COINS");
            String petName = resultSet.getString("PETNAME");
            playerList.add("NAME: " + playerName + " COINS: " + coins + " PET NAME: " + petName);
        }

        resultSet.close();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
        
    return playerList;
    }
     
     
    public static void main(String[] args) {
        
        Player player = new Player("andrew");
        player.pet = new Cat("Billy");
        player.coins = 77;
        player.pet.species = "CAT";
        
        
       

        DB_Operations dboperations = new DB_Operations();
        
        
       
        
        player.pet.fish = 12;
        
        dboperations.playerSave(player);
        
        
        
        
        
       

        dboperations.dbManager.closeConnections();

    }
}

