/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603assignment;

import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.util.Random;

/**
 *
 * @author Sandra
 */
public class Fishing {
    
    int smallFish;
    int medFish;
    int largeFish;
    private int hookDurability;
    private Random rand;

    public Fishing() {
        
        this.smallFish = 0;
        this.medFish = 0;
        this.largeFish = 0;
        this.hookDurability = 10;
        this.rand = new Random();
    }

    public void play() throws InterruptedException {
        System.out.println("==========================================================================");
        System.out.println("Welcome to the fishing mini-game!");
        System.out.println("Your goal is to catch as many fish as possible before your hook breaks.");
        System.out.println("You start with a hook durability of " + this.hookDurability + ".");
        System.out.println("==========================================================================");
        Thread.sleep(1000);

        while (this.hookDurability > 0) {
            
            int roll = this.rand.nextInt((10) +1);

            if (roll <= 3) {
                System.out.println("..........");
                System.out.println("..........");
                System.out.println("..........");
                Thread.sleep(2000);
                System.out.println("You didn't catch anything this time.");
               
                Thread.sleep(1000);
                
            } else if (roll <= 9) {
                int fishSize = this.rand.nextInt(5) + 1;
                if(fishSize <= 2) {
                    
                    System.out.println("..........");
                    System.out.println("..........");
                    System.out.println("..........");
                    Thread.sleep(2000);
                    
                    System.out.println("You caught a small fish " + fishSize + "kgs !\n\n");
                    smallFish++;
                    System.out.println("  _");
                    System.out.println("><_>");
                    
                    Thread.sleep(1000);
                    
                }
                else if(fishSize <= 4 && fishSize >= 3) {
                    
                    System.out.println("..........");
                    System.out.println("..........");
                    System.out.println("..........");
                    Thread.sleep(2000);
                    System.out.println("You caught a medium fish " + fishSize + "kgs !\n\n");
                    medFish++;
                    System.out.println("/\\\n" +
                                "      _/./\n" +
                                "   ,-'    `-:.,-'/\n" +
                                "  > O )<)    _  (\n" +
                                "   `-._  _.:' `-.\\\n" +
                                "       `` \\;");
                    
                    Thread.sleep(1000);
                }
                else if(fishSize  == 5) {
                    
                    System.out.println("..........");
                    System.out.println("..........");
                    System.out.println("..........");
                    Thread.sleep(2000);
                    System.out.println("You caught a large fish " + fishSize + "kgs !\n\n");
                    largeFish++;
                    System.out.println(" \\.          |\\                        =\n" +
                                       "  =       \\`.___---~~  ~~~--__       __         =\n" +
                                       " =        //~~----___  (_c_nn~~~   ><_o>   __    =\n" +
                                       "=        '           |/'");
                   
                    Thread.sleep(1000);
                }
                             
            } else {
                
                System.out.println("Your hook got stuck on something and broke!");
                System.out.println("Game over");
                this.hookDurability = 0;
            }
            this.hookDurability--;
        }
    }

  

  
}
