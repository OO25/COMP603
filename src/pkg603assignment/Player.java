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
    int[] fish;
    int bones;
    int kibble;

    public Player(String name) {
        this.name = name;
        this.coins = 0;
        this.fish = new int[]{0, 0, 0};
        this.bones = 0;
        this.kibble = 0;
    }

    public Player(String name, int coins, int[] fish, int bones, int kibble) {
        this.name = name;
        this.coins = coins;
        this.fish = new int[]{fish[0], fish[1], fish[2]};
        this.bones = bones;
        this.kibble = kibble;
    }

    @Override
    public String toString() {
        return "Name: " + this.name
                + "\nCoins: " + this.coins
                + "\nSmall fish: " + this.fish[0]
                + " | Medium fish: " + this.fish[1]
                + " | Large fish: " + this.fish[2]
                + "\nBones: " + this.bones
                + "\nKibble: " + this.kibble;
    }
}
