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

    public void createTable() {
        try {
            Statement statement = dbManager.getConnection().createStatement();
            String newTableName = "PlayerData";

            String sqlCreate = "create table " + newTableName + " ("
                    + "PlayerName varchar(20), Coins int, Fish int, Bones int,  Kibble int, Species varchar(3),"
                    + "Age int, PetName varchar(20), Happiness int,  PRIMARY KEY (PlayerName))";
            statement.executeUpdate(sqlCreate);


            System.out.println("Table created");

        } catch (SQLException ex) {
            Logger.getLogger(DB_Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertPlayer(Player player) {
        try {
            // Prepare SQL statement for insertion
            String sql = "INSERT INTO PlayerData (Playername, coins, fish, bones, kibble) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = dbManager.getConnection().prepareStatement(sql);

            // Set the values for the parameters from the Player object
            statement.setString(1, player.name);
            statement.setInt(2, player.coins);
            statement.setInt(3, player.fish);
            statement.setInt(4, player.bones);
            statement.setInt(5, player.kibble);

            // Execute the insertion
            statement.executeUpdate();
            System.out.println("Data inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
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
                    + "HAPPINESS =" + player.pet.happiness + " "
                    + "WHERE PLAYERNAME = '"+ player.name +"'";

            
            int rowsAffected = statement.executeUpdate(sql);
            
            System.out.println("Rows affected: " + rowsAffected);
            System.out.println("Table updated successfully.");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
     
    public Player loadPlayer(String playerName) {
        Player player = new Player(playerName);
        player.pet = new Pet(null);
        ResultSet rs = dbManager.myQuery("select * from PLAYERDATA where PLAYERNAME = '" + playerName + "'");

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
            }

        } catch (SQLException ex) {
            return null;
        }

        return player;
    }
     
     
    public static void main(String[] args) {
        
        Player andrew = new Player("Andrew");
        andrew.pet = new Dog("Billy");
        
       

        DB_Operations dboperations = new DB_Operations();
        dboperations.playerSave(andrew);
       

        dboperations.dbManager.closeConnections();

    }
}

