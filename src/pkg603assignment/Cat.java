/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603assignment;

/**
 *
 * @author hughm
 */
public class Cat extends Pet {
    
    public Cat(String name) {
        super(name);
        FavFood = "Kibble";
        species = "cat";
    }
    
    @Override
    public void Happy() {
        System.out.println("/\\_/\\");
        System.out.println("(. . )");
        System.out.println("= v =");
    }
    
    @Override
    public void Sad() {
        System.out.println("/\\_/\\");
        System.out.println("(o o )");
        System.out.println("= ^ =");
    }
    
    @Override
    public void Angry() {
        System.out.println("/\\_/\\");
        System.out.println("(0 o )");
        System.out.println("= M =");
    }
    
    @Override
    public void Asleep() {
        System.out.println("/\\_/\\       z");
        System.out.println("(_ _ )      z");
        System.out.println("= - =     z");
    }
}
