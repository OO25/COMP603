/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603assignment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author hughm
 */
public class PetGameGUI implements ItemListener {
    //put where instance variables are declaired
    JPanel cards;
    final static String WELCOMPANEL = "Card with Welcome manu buttons";
    final static String NEWSAVEPANEL = "Card with info for creating new save file";
    final static String MAINPANEL = "Card with the main menu buttons";
    
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
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints con = new GridBagConstraints();
        
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 0;
        //panel.add(Box.createHorizontalGlue(), con);
        
        
        JLabel newSaveLabel = new JLabel("<html><h1>Create new save:</h1></html>",JLabel.CENTER);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 3;
        con.weightx = 0;
        panel.add(newSaveLabel, con);
        
        JLabel nameText = new JLabel("What is your name?", SwingConstants.CENTER);
        con.weightx = .5;
        con.gridx = 0;
        con.gridy = 1;
        con.gridwidth = 1;
        panel.add(nameText, con);
        
        JTextField userName = new JTextField();
        con.weightx = 0;
        con.gridx = 1;
        con.gridy = 1;
        con.gridwidth = 2;
        panel.add(userName, con);
        
        JLabel petText = new JLabel("Which pet do you want?", JLabel.CENTER);
        con.gridwidth = 1;
        con.weightx = 0.5;
        con.gridx = 0;
        con.gridy = 2;
        panel.add(petText, con);
        
        JRadioButton cat = new JRadioButton("Cat", true);
        con.gridx = 1;
        con.gridy = 2;
        cat.setHorizontalAlignment(JLabel.CENTER);
        panel.add(cat, con);
        
        JRadioButton dog = new JRadioButton("Dog", false);
        con.gridx = 2;
        con.gridy = 2;
        dog.setHorizontalAlignment(JLabel.CENTER);
        panel.add(dog, con);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(cat);
        bg.add(dog);
        
        JLabel petNameText = new JLabel("What do you want to name your cat?", JLabel.CENTER);
        con.gridx = 0;
        con.gridy = 3;
        panel.add(petNameText,con);
        
        JTextField petName = new JTextField();
        con.weightx = 0;
        con.gridx = 1;
        con.gridy = 3;
        con.gridwidth = 2;
        panel.add(petName, con);
        
        JButton finishButton = new JButton("Confirm");
        con.gridx = 0;
        con.gridy = 4;
        con.gridwidth = 3;
        panel.add(finishButton, con);
        
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == finishButton){
                    System.out.println("finish setup");
                }
                if(cat.isSelected()){
                    System.out.println("cat");
                    petNameText.setText("What do you want to name your cat?");
                } else if(dog.isSelected()){
                    System.out.println("dog");
                    petNameText.setText("What do you want to name your dog?");
                }
            }
        };
        cat.addActionListener(al);
        dog.addActionListener(al);
        finishButton.addActionListener(al);
        
        
        
        return panel;
    }
    
    private JPanel mainMenu(){
        int ButtonWidth = 300;
        int ButtonHeight = 25;
        JPanel panel = new JPanel();
        //panel.setLayout(null);
        panel.setLayout(new GridLayout(0,2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel welcomeLabel = new JLabel("<html><div style='text-align:center'><h1>This is the main menu!</h1><p>Please Pick One:</html>", SwingConstants.CENTER);
        panel.add(welcomeLabel);
        
        JButton buttonA = new JButton("Show info");
        panel.add(buttonA);
        
        JButton buttonB = new JButton("Shop");
        panel.add(buttonB);
        
        JButton buttonC = new JButton("Pat pet");
        panel.add(buttonC);
        
        JButton buttonD = new JButton("Clean pet");
        panel.add(buttonD);
        
        JButton buttonE = new JButton("Fish");
        panel.add(buttonE);
        
        JButton buttonF = new JButton("Quit game and save");
        panel.add(buttonF);
        
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buttonA) {
                    System.out.println("show info");
                } else if (e.getSource() == buttonB) {
                    System.out.println("show shop");
                } else if (e.getSource() == buttonC){
                    System.out.println("show pat pet");
                } else if (e.getSource() == buttonD){
                    System.out.println("show clean pet");
                } else if (e.getSource() == buttonE){
                    System.out.println("show fishing game");
                } else if (e.getSource() == buttonF){
                    System.out.println("Game Exited");
                    System.exit(0);
                } else {
                    System.out.println("Something went wrong! :(");
                }
            }
        };
        buttonA.addActionListener(al);
        buttonB.addActionListener(al);
        buttonC.addActionListener(al);
        buttonD.addActionListener(al);
        buttonE.addActionListener(al);
        buttonF.addActionListener(al);
        
        return panel;
    }
    
    public void cardCreate(Container pane){
        JPanel comboBoxPane = new JPanel();
        String comboBoxItems[] = {WELCOMPANEL, NEWSAVEPANEL, MAINPANEL};
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        
        // create the cards
        JPanel card1 = welcome();
        JPanel card2 = newSave();
        JPanel card3 = mainMenu();
        
        
        cards = new JPanel(new CardLayout());
        cards.add(card1, WELCOMPANEL);
        cards.add(card2, NEWSAVEPANEL);
        cards.add(card3, MAINPANEL);
        
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