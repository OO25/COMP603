/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603assignment;

/**
 *
 * @author hughm
 */
public class Player {

    String name;
    int coins;
    Pet pet;
    int bones;
    int kibble;
    int fish;

    public Player(String name) {
        this.name = name;
        this.coins = 0;
        this.fish = 0;
        this.bones = 0;
        this.kibble = 0;
    }

    public Player(String name, int coins, int fish, int bones, int kibble) {
        this.name = name;
        this.coins = coins;
        this.fish = fish;
        this.bones = bones;
        this.kibble = kibble;
    }

    @Override
    public String toString() {
        return "Name: " + this.name
                + "\nCoins: " + this.coins
                + "\nSmall fish: " + this.fish
                + "\nBones: " + this.bones
                + "\nKibble: " + this.kibble;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setFish(int fish) {
        this.fish = fish;
    }

    public void setBones(int bones) {
        this.bones = bones;
    }

    public void setKibble(int kibble) {
        this.kibble = kibble;
    }
}
