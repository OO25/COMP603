/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603assignment;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author hughm
 */
/*\
This page is no good just need it for referance
*/
public class PetGame implements ActionListener {
    
    public PetGame(){
        int clicks = 0;
    }
    
    public static int welcome(){
        int choice;
        JButton buttonB = new JButton(new AbstractAction("choice B"){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("test action B");
            }
        });
        buttonB.setBounds(10, 240, 300, 25);
        //panel.add(buttonB);
        
        return 1;
    }
    
    public static void main(String[] args) {
        int clicks = 0;
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(640,480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Pet Game");
        frame.add(panel, BorderLayout.CENTER);
        
        //panel.setLayout(new GridLayout(0, 1));
        panel.setLayout(null);
        //panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel userLable = new JLabel("Test");
        userLable.setBounds(10, 20, 80, 25);
        panel.add(userLable);
        
        JTextField userText = new JTextField();
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);
        
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);
        
        JTextField passwordText = new JTextField();
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);
        
        JButton button = new JButton("Login");
        button.setBounds(10, 80, 80, 25);
        button.addActionListener(new PetGame());
        panel.add(button);
        
        JLabel success = new JLabel("");
        success.setBounds(10, 120, 300, 25);
        panel.add(success);
        
        
        String choiceA = "choice A";
        JButton buttonA = new JButton(new AbstractAction(choiceA){
            int clicks = 0;
            @Override
            public void actionPerformed(ActionEvent e){
                clicks ++;
                System.out.println("Button clicked "+clicks);
                
            }
        });
        buttonA.setBounds(10, 200, 300, 25);
        panel.add(buttonA);
        JButton buttonB = new JButton(new AbstractAction("choice B"){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("test action B");
            }
        });
        buttonB.setBounds(10, 240, 300, 25);
        panel.add(buttonB);
        
        System.out.println(welcome());
        JOptionPane.showMessageDialog(frame, "test");
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //the way this will work is with a switch ststement that reads a variable that changes depending on which section of the game the player is because i dont know how to differenciate different buttons
        System.out.println("hi");
        System.out.println(e);
    }
}
