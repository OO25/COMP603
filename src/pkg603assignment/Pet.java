/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603assignment;

/**
 *
 * @author hughm
 */
public class Pet {
    float age;
    String name;
    String FavFood;
    int happiness;
    String emotion;
    int clean;
    
    public Pet(String name){
        this.age = 0;
        this.name = name;
        this.emotion = "happy";
        this.happiness = 5;
        this.clean = 5;
        
        if(this.happiness >= 5) {
            this.emotion = "happy";
        }
        
        if(this.happiness < 5 && this.happiness > 2) {
            this.emotion = "sad";
        }
        
        if(this.happiness <= 2) {
            this.emotion = "angry";
        }
        
    }
    
    public void Happy() {
        
    }
    
    public void Sad() {
        
    }
    
    public void Angry() {
        
    }
    
    public void Asleep() {
        
    }
    
    
}
