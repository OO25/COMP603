/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603assignment;

/**
 *
 * @author Sandra
 */
public class Dog extends Pet {
    
    public Dog(String name) {
        super(name);
        FavFood = "Bone";
    }
    
    @Override
    public void Happy() {
        System.out.println("/\\___/\\");
        System.out.println("(^   ^)");
        System.out.println("(  V  ) ");
        System.out.println("  U  ");
    }
    
    @Override
    public void Sad() {
        System.out.println("/\\___/\\");
        System.out.println("(=   =)");
        System.out.println("(  V  )           *whimper*");
        System.out.println("   ^");
    }
    
    @Override
    public void Angry() {
        System.out.println("/\\___/\\");
        System.out.println("(O   O)");
        System.out.println("(  V  )      *GROWL*");
        System.out.println(" v   v  ");
    }
    
    @Override
    public void Asleep() {
        System.out.println("/\\___/\\");
        System.out.println("(-   -)");
        System.out.println("(  V  ) ");
        System.out.println("    ");
    }
}
