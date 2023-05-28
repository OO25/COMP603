/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603assignment;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 *
 * @author hughm
 */
public class PetGameGUI implements ItemListener {
    //put where instance variables are declaired
    JPanel cards;
    final static String WELCOMPANEL = "Card with Welcome manu buttons";
    final static String NEWSAVEPANEL = "Card with info for creating new save file";
    
    public static int SCREENWIDTH = 640;
    public static int SCREENHEIGHT = 480;
    
    private JButton newButton(String lable){
        JButton button = new JButton(lable);
        return button;
    }
    
    private JPanel welcome(){
        int ButtonWidth = 300;
        int ButtonHeight = 25;
        JPanel panel = new JPanel();
        //panel.setLayout(null);
        panel.setLayout(new GridLayout(0,1, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel welcomeLabel = new JLabel("<html><div style='text-align:center'><h1>Welcome to the Pet Game!<p></h1>"+"Please Pick One:</html>", SwingConstants.CENTER);
        panel.add(welcomeLabel);
        
        JButton buttonA = new JButton("New Game Save");
        buttonA.setBounds((SCREENWIDTH-ButtonWidth)/2, 210, ButtonWidth, ButtonHeight);
        panel.add(buttonA);
        
        JButton buttonB = new JButton("Load Game Save");
        buttonB.setBounds((SCREENWIDTH-ButtonWidth)/2, 240, ButtonWidth, ButtonHeight);
        panel.add(buttonB);
        
        JButton buttonC = new JButton("Quit Game");
        buttonC.setBounds((SCREENWIDTH-ButtonWidth)/2, 270, ButtonWidth, ButtonHeight);
        panel.add(buttonC);
        
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buttonA) {
                    CardLayout cl = (CardLayout)(cards.getLayout());
                    cl.show(cards, NEWSAVEPANEL);
                    //cl.last(cards);
                } else if (e.getSource() == buttonB) {
                    System.out.println("B");
                } else if (e.getSource() == buttonC){
                    System.out.println("Program closed");
                    System.exit(0);
                }
            }
        };
        buttonA.addActionListener(al);
        buttonB.addActionListener(al);
        buttonC.addActionListener(al);
        
        return panel;
    }
    
    private JPanel newSave(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        //panel.setLayout(null);
        //panel.setLayout(new GridLayout(0,2));
        //panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints con = new GridBagConstraints();
        
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 0;
        //panel.add(Box.createHorizontalGlue(), con);
        
        
        JLabel newSaveLabel = new JLabel("<html><h1>Create new save:</h1></html>",JLabel.CENTER);
        
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 2;
        con.weightx = 0;
        panel.add(newSaveLabel, con);
        
        JLabel nameText = new JLabel("Save Name:", SwingConstants.CENTER);
        con.weightx = 0.1;
        con.gridx = 0;
        con.gridy = 1;
        con.gridwidth = 1;
        panel.add(nameText, con);
        
        JTextField userName = new JTextField();
        con.weightx = 0.5;
        con.gridx = 1;
        con.gridy = 1;
        panel.add(userName, con);
        
        
        return panel;
    }
    
    public void cardCreate(Container pane){
        JPanel comboBoxPane = new JPanel();
        String comboBoxItems[] = {WELCOMPANEL, NEWSAVEPANEL};
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        
        // create the cards
        JPanel card1 = welcome();
        JPanel card2 = newSave();
        
        //JPanel card3 = welcome();
        
        cards = new JPanel(new CardLayout());
        cards.add(card1, WELCOMPANEL);
        cards.add(card2, NEWSAVEPANEL);
        
        pane.add(comboBoxPane,BorderLayout.PAGE_END);
        pane.add(cards,BorderLayout.CENTER);
        
    }
    
    @Override
    public void itemStateChanged(ItemEvent evt){
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
        System.out.println(evt);
    }
    
    public static void createAndShowGUI(){
        JFrame frame = new JFrame("test cards");
        frame.setSize(SCREENWIDTH,SCREENHEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        PetGameGUI panel = new PetGameGUI();
        panel.cardCreate(frame.getContentPane());
        //frame.add(welcomePanel, BorderLayout.CENTER);
        
        //JFrame.
        frame.setVisible(true);
        System.out.println("create test");
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                createAndShowGUI();
            }
        });
        
    }
}

/*JButton buttonB = new JButton(new AbstractAction("choice B"){ // start of button
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("test action B");
                CardLayout cl = (CardLayout)(cards.getLayout());
                //cl.show(cards, (String)evt.getItem());
                cl.first(cards);
            }
        }); // end of button */