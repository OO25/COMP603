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
    int age;
    String name;
    String FavFood;
    int happiness;
    String emotion;
    int clean;
    String species;
    int fish;
    
    public Pet(String name){
        this.age = 0;
        this.name = name;
        this.emotion = "happy";
        this.happiness = 5;
        this.clean = 5;
        this.fish = 0;
        
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
    @Override
    public String toString(){
        return "Name: "+ this.name
                +"\nAge: "+this.age
                +"\nEmotion: "+this.emotion
                +"\nFav food: "+this.FavFood
                +"\nHappiness: "+this.happiness
                +"\nClean: "+this.clean;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }
    
    public void changeHappiness(){
        if(this.happiness >= 5) {
            this.emotion = "happy";
        }else if(this.happiness < 5 && this.happiness > 2) {
            this.emotion = "sad";
        }else if(this.happiness <= 2) {
            this.emotion = "angry";
        }
    }
    
}
