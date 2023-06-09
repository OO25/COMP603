/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603assignment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author hughm
 */
public class PetGameGUI implements ItemListener {
    //put where instance variables are declaired
    JPanel cards;
    final static String WELCOMPANEL = "Card with Welcome manu buttons";
    final static String NEWSAVEPANEL = "Card with info for creating new save file";
    final static String LOADSAVEPANEL = "Card with info for loading a save file";
    final static String MAINPANEL = "Card with the main menu buttons";
    final static String SHOWINFOPANEL = "Card that displays game info";
    final static String SHOPPANEL = "Card that displays the shop";
    final static String PATPETPANEL = "Card that allows the player to pat their pet";
    final static String CLEANPETPANEL = "Card that allows the player to clean their pet";
    final static String FISHPANEL = "Card for the fishing minigame";
    
    public static int SCREENWIDTH = 640;
    public static int SCREENHEIGHT = 480;
    
    int selectedSaveSlot;
    Player player;
    Pet pet;
    DB_Operations dboperations = new DB_Operations();
    
    private ImageIcon petEmotion(){
        ImageIcon image;
        String PET = pet.species;
        switch(pet.emotion){
            case "happy":
                image = new ImageIcon("sprites/"+PET+"Happy.png");
                break;
            case "sad":
                image = new ImageIcon("sprites/"+PET+"Sad.png");
                break;
            case "angry":
                image = new ImageIcon("sprites/"+PET+"Angry.png");
                break;
            default:
                image = new ImageIcon("sprites/"+PET+"Sleep.png");
                break;
        }
        return image;
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
                CardLayout cl = (CardLayout)(cards.getLayout());
                if (e.getSource() == buttonA) {
                    //CardLayout cl = (CardLayout)(cards.getLayout());
                    cl.show(cards, NEWSAVEPANEL);
                    //cl.last(cards);
                } else if (e.getSource() == buttonB) {
                    cl.show(cards, LOADSAVEPANEL);
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
        finishButton.setEnabled(false);
        panel.add(finishButton, con);
        
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                if(e.getSource() == finishButton){
                    if(!userName.getText().isEmpty() && !petName.getText().isEmpty() && userName.getText().trim().length() > 0){
                        /*
                        this is where the save to data base needs to go
                        "userName.getText()" is the user name
                        "petName.getText()" is the pet name
                        that if statement is the pet type (see which in print)
                        if we have time probably get username to remove leading white space
                        */
                        player = new Player(userName.getText().trim());
                        if(cat.isSelected()){
                            System.out.println("Pet type: cat");
                            pet = new Cat(petName.getText().trim());
                             
                        }else{
                            pet = new Dog(petName.getText().trim());
                            System.out.println("Pet type: dog");
                            
                        }
                        
                        finalPrep();
                        player.pet = pet;
                        dboperations.insertPlayer(player);
                        cl.show(cards, MAINPANEL);
                    }
                    else {
                        
                    }
                }
                if(cat.isSelected()){
                    petNameText.setText("What do you want to name your cat?");
                } else if(dog.isSelected()){
                    petNameText.setText("What do you want to name your dog?");
                }
            }
        };
        cat.addActionListener(al);
        dog.addActionListener(al);
        finishButton.addActionListener(al);
        DocumentListener dl =  new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }
            
            public void changed(){
                if(!userName.getText().isEmpty() && !petName.getText().isEmpty() && userName.getText().trim().length() > 0)
                    finishButton.setEnabled(true);
                else
                    finishButton.setEnabled(false);
            }
        };
        userName.getDocument().addDocumentListener(dl);
        petName.getDocument().addDocumentListener(dl);
        
        
        return panel;
    }
    
    private JPanel loadSave(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints con = new GridBagConstraints();
        String[] saveStrings = dboperations.List().toArray(new String[0]);
        
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 0;
        //panel.add(Box.createHorizontalGlue(), con);
        
        
        JLabel newSaveLabel = new JLabel("<html><h1>Load Existing save:</h1></html>",JLabel.CENTER);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 3;
        con.weightx = 0;
        panel.add(newSaveLabel, con);
        
        // combo box
        JComboBox saveList = new JComboBox(saveStrings);
        con.gridx = 0;
        con.gridy = 1;
        con.gridwidth = 3;
        panel.add(saveList, con);
        
        JButton finishButton = new JButton("Confirm");
        con.gridx = 0;
        con.gridy = 2;
        con.gridwidth = 3;
        panel.add(finishButton, con);
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                if(e.getSource() == finishButton){
                    /**
                     * to make this work we need to turn the saves into an array and then
                     * use the user inputted choice of save (given by the selectedSaveSlot variable)
                     * then we can load everything and define the player with player name
                     * and pet with pet name and assign all of the values that can change from the start
                     */
                    player = dboperations.loadPlayer(saveList.getSelectedIndex()+1);
                    System.out.println("|" + player.pet.species + "|");
                    System.out.println(player.pet.FavFood);
                    if("cat".equals(player.pet.species)) {
                        pet = new Cat(player.pet.name);
                        pet.emotion = player.pet.emotion;
                        pet.happiness = player.pet.happiness;
                        pet.clean = player.pet.clean;
                        pet.FavFood = "Kibble";
                    }
                    else {
                        pet = new Dog(player.pet.name);
                        pet.emotion = player.pet.emotion;
                        pet.happiness = player.pet.happiness;
                        pet.clean = player.pet.clean;
                        pet.FavFood = "Bones";
                    }
                        
                    
                    
                    System.out.println("Selet File");
                    selectedSaveSlot = saveList.getSelectedIndex();
                    finalPrep();
                    cl.show(cards, MAINPANEL);
                }else if (e.getSource() == saveList){
                    System.out.println(saveList.getSelectedIndex()+" selected");
                }
            }
            //selectedIndex = 1;
        };
        saveList.addActionListener(al);
        finishButton.addActionListener(al);
        
        return panel;
    }
    private JPanel mainMenu(){
        int ButtonWidth = 300;
        int ButtonHeight = 25;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,2));//, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel welcomeLabel = new JLabel("<html><div style='text-align:center'><h1>This is the main menu!</h1><p>Please Pick One:</html>", SwingConstants.CENTER);
        panel.add(welcomeLabel);
        
        JLabel petImage = new JLabel(petEmotion());
        panel.add(petImage);
        
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
                CardLayout cl = (CardLayout)(cards.getLayout());
                if (e.getSource() == buttonA) {
                    System.out.println("show info");
                    cl.show(cards, SHOWINFOPANEL);
                } else if (e.getSource() == buttonB) {
                    System.out.println("show shop");
                    cl.show(cards, SHOPPANEL);
                } else if (e.getSource() == buttonC){
                    System.out.println("show pat pet");
                    cl.show(cards, PATPETPANEL);
                } else if (e.getSource() == buttonD){
                    System.out.println("show clean pet");
                    cl.show(cards, CLEANPETPANEL);
                } else if (e.getSource() == buttonE){
                    cl.show(cards, FISHPANEL);
                    System.out.println("show fishing game");
                } else if (e.getSource() == buttonF){
                    
                    player.pet = pet;
                    
                    dboperations.playerSave(player);
                     
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
        
        ComponentAdapter ca = new ComponentAdapter(){
            @Override
            public void componentShown(ComponentEvent e){
                petImage.setIcon(petEmotion());
            }
        };
        panel.addComponentListener(ca);
        
        return panel;
    }
    
    private JPanel showInfo(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints con = new GridBagConstraints();
        
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 0;
        
        
        JLabel playerInfoHeaderLabel = new JLabel("<html><h1>Player Info:</h1></html>",JLabel.CENTER);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 3;
        con.weightx = 0;
        panel.add(playerInfoHeaderLabel, con);
        
        JLabel playerInfoLabel = new JLabel("<html><div style='text-align:center'>"+player.toString().replaceAll("\n", "<p>")+"</html>",JLabel.CENTER);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 1;
        con.gridwidth = 3;
        con.weightx = 0;
        panel.add(playerInfoLabel, con);
        
        JLabel petInfoHeaderLabel = new JLabel("<html><h1>Pet Info:</h1></html>",JLabel.CENTER);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 2;
        con.gridwidth = 3;
        con.weightx = 0;
        panel.add(petInfoHeaderLabel, con);
        
        JLabel petInfoLabel = new JLabel("<html><div style='text-align:center'>"+pet.toString().replaceAll("\n", "<p>")+"</html>",JLabel.CENTER);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 3;
        con.gridwidth = 3;
        con.weightx = 0;
        panel.add(petInfoLabel, con);
        
        JButton backButton = new JButton("Back to Main Menu");
        con.gridx = 0;
        con.gridy = 4;
        con.gridwidth = 3;
        panel.add(backButton, con);
        
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                if(e.getSource() == backButton){
                    System.out.println("Returning to main menu");
                    cl.show(cards, MAINPANEL);
                }
            }
        };
        backButton.addActionListener(al);
        
        ComponentAdapter ca = new ComponentAdapter(){
            @Override
            public void componentShown(ComponentEvent e){
                playerInfoLabel.setText("<html><div style='text-align:center'>"+player.toString().replaceAll("\n", "<p>")+"</html>");
                petInfoLabel.setText("<html><div style='text-align:center'>"+pet.toString().replaceAll("\n", "<p>")+"</html>");
            }
        };
        panel.addComponentListener(ca);
        
        return panel;
    }
    
    private JPanel shopMenu(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints con = new GridBagConstraints();
        
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 0;
        
        JLabel shopHeader = new JLabel("<html><div style='text-align:center;'><h1>Welcome to the pet shop!</h1>"
                + "<h3>You have "+player.coins+" coins.<br/>What would you like to do?</h3></html>",JLabel.CENTER);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 3;
        con.weightx = 3;
        panel.add(shopHeader, con);
        
        JButton buyBones = new JButton("Buy bones (Costs 5 coins)");
        con.gridx = 0;
        con.gridy = 1;
        con.gridwidth = 3;
        panel.add(buyBones, con);
        
        JButton buyKibble = new JButton("Buy kibble (Costs 2 coins)");
        con.gridx = 0;
        con.gridy = 2;
        con.gridwidth = 3;
        panel.add(buyKibble, con);
        
        JButton sellFish = new JButton("Sell fish (Worth 2 coins each)");
        con.gridx = 0;
        con.gridy = 3;
        con.gridwidth = 3;
        panel.add(sellFish, con);
        
        JButton quitButton = new JButton("Quit");
        con.gridx = 0;
        con.gridy = 4;
        con.gridwidth = 3;
        panel.add(quitButton, con);
        
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                if(e.getSource() == buyBones){
                    if (player.coins >= 5) {
                        player.coins = player.coins - 5;
                        player.bones++;
                        if(player.coins < 5)
                            buyBones.setEnabled(false);
                        if(player.coins < 2)
                            buyKibble.setEnabled(false);
                    } else {
                        buyBones.setEnabled(false);
                    }
                    shopHeader.setText("<html><div style='text-align:center;'><h1>Welcome to the pet shop!</h1>"
                            + "<h3>You have " + player.coins+" coins.<br/>What would you like to do?</h3></html>");
                    
                }else if(e.getSource() == buyKibble){
                    if (player.coins >= 2) {
                        player.coins -= 2;
                        player.kibble++;
                        if(player.coins < 5)
                            buyBones.setEnabled(false);
                        if(player.coins < 2)
                            buyKibble.setEnabled(false);
                    } else {
                        buyKibble.setEnabled(false);
                    }
                    shopHeader.setText("<html><div style='text-align:center;'><h1>Welcome to the pet shop!</h1>"
                            + "<h3>You have " + player.coins + " coins.<br/>What would you like to do?</h3></html>");
                }else if(e.getSource() == sellFish){
                    if(player.fish > 0){
                        player.fish--;
                        if(player.fish < 1)
                            sellFish.setEnabled(false);
                        player.coins+=2;
                    }else
                        sellFish.setEnabled(false);
                    shopHeader.setText("<html><div style='text-align:center;'><h1>Welcome to the pet shop!</h1>"
                            + "<h3>You have " + player.coins + " coins.<br/>What would you like to do?</h3></html>");
                    if (player.coins >= 5) {
                        buyBones.setEnabled(true);
                    } else if(player.coins >= 2){
                        buyKibble.setEnabled(true);
                    }
                }else if(e.getSource() == quitButton){
                    cl.show(cards, MAINPANEL);
                }
            }
        };
        buyBones.addActionListener(al);
        buyKibble.addActionListener(al);
        sellFish.addActionListener(al);
        quitButton.addActionListener(al);
        
        ComponentAdapter ca = new ComponentAdapter(){
            @Override
            public void componentShown(ComponentEvent e){
                if(player.coins < 5){
                    buyBones.setEnabled(false);
                }else{
                    buyBones.setEnabled(true);
                }
                if(player.coins < 2){
                    buyKibble.setEnabled(false);
                }else{
                    buyKibble.setEnabled(true);
                }
                if(player.fish <= 0)
                    sellFish.setEnabled(false);
                else
                    sellFish.setEnabled(true);
            }
        };
        panel.addComponentListener(ca);
        
        return panel;
    }
    
    private JPanel patPet(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints con = new GridBagConstraints();
        
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 0;
        
        JLabel playerInfoHeaderLabel = new JLabel("<html><h1>Pat pet:</h1></html>",JLabel.CENTER);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 3;
        con.weightx = 0;
        panel.add(playerInfoHeaderLabel, con);
        
        JLabel emotionString = new JLabel("<html><h2>"+pet.name+" is felling "+pet.emotion+"</h2></html>",JLabel.CENTER);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 1;
        con.gridwidth = 3;
        con.weightx = 0;
        panel.add(emotionString, con);
        
        JLabel emotion = new JLabel(petEmotion());
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 2;
        con.gridwidth = 3;
        con.weightx = 0;
        panel.add(emotion, con);
        
        JLabel patOutput = new JLabel("<html><h3>"+pet.name+" looks very pettable</h3><html>",JLabel.CENTER);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 3;
        con.gridwidth = 3;
        con.weightx = 0;
        panel.add(patOutput, con);
        
        JButton patPetButton = new JButton("Pat "+pet.name);
        con.gridx = 1;
        con.gridy = 4;
        con.gridwidth = 1;
        con.weightx = 1;
        panel.add(patPetButton, con);
        
        JButton feedPetButton = new JButton("Feed "+pet.name+ "");
        con.gridx = 1;
        con.gridy = 5;
        con.gridwidth = 1;
        con.weightx = 1;
        panel.add(feedPetButton, con);
        
        JButton backButton = new JButton("Back to Main Menu");
        con.gridx = 1;
        con.gridy = 6;
        con.gridwidth = 1;
        con.weightx = 1;
        panel.add(backButton, con);
        
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                if(e.getSource() == patPetButton){
                    int rand = (int) (Math.random() * 10);
                    if(rand >= 8){
                        if(pet.happiness >= 2){
                            pet.happiness-=2;
                            pet.changeHappiness();
                            emotion.setIcon(petEmotion());
                            patOutput.setText("<html><h3>"+pet.name+" didn't like that! They grow annoyed.</h3><html>");
                        }
                    }else{
                        if(pet.happiness < 10){
                            pet.happiness++;
                            pet.changeHappiness();
                            emotion.setIcon(petEmotion());
                            patOutput.setText("<html><h3>"+pet.name+" liked that! They feel a bit better.</h3><html>");
                        }else{
                            pet.happiness++;
                            pet.changeHappiness();
                            emotion.setIcon(petEmotion());
                            patOutput.setText("<html><h3>"+pet.name+" doesn't want pets but will let you because it makes you happy.</h3><html>");
                        }
                    }
                } else if(e.getSource() == feedPetButton){
                    String food = pet.FavFood;
                    if(pet.happiness <= 8){
                        switch(food){
                            case "Kibble":
                                if(player.kibble>0){
                                    player.kibble--;
                                    pet.happiness+=2;
                                    pet.changeHappiness();
                                    emotion.setIcon(petEmotion());
                                    patOutput.setText("<html><h3>Kibble is "+pet.name+"'s favourite food, they really liked that!</h3><html>");
                                    if(player.kibble<1)
                                        feedPetButton.setEnabled(false);
                                    System.out.println("used kibbel");
                                }
                                break;
                            case "Bone":
                                if(player.bones>0){
                                    player.bones--;
                                    pet.happiness+=2;
                                    pet.changeHappiness();
                                    emotion.setIcon(petEmotion());
                                    patOutput.setText("<html><h3>Bones are "+pet.name+"'s favourite food, they really liked that!</h3><html>");
                                    if(player.bones<1)
                                        feedPetButton.setEnabled(false);
                                }
                                break;
                        }
                    }
                }else if (e.getSource() == backButton) {
                    System.out.println("Returning to main menu");
                    cl.show(cards, MAINPANEL);
                }
            }
        };
        patPetButton.addActionListener(al);
        feedPetButton.addActionListener(al);
        backButton.addActionListener(al);
        ComponentAdapter ca = new ComponentAdapter(){
            @Override
            public void componentShown(ComponentEvent e){
                emotion.setIcon(petEmotion());
                patOutput.setText("<html><h3>"+pet.name+" looks very pettable</h3><html>");
                if("cat".equals(pet.species)){
                    feedPetButton.setText("Feed "+pet.name+ " (they want kibble)");
                    if(player.kibble<1)
                        feedPetButton.setEnabled(false);
                    else
                        feedPetButton.setEnabled(true);
                }else if("dog".equals(pet.species)){
                    feedPetButton.setText("Feed "+pet.name+ " (they want bones)");
                    if(player.bones<1)
                        feedPetButton.setEnabled(false);
                    else
                        feedPetButton.setEnabled(true);
                }
            }
        };
        panel.addComponentListener(ca);
        
        return panel;
    }
    
    private JPanel cleanPet(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints con = new GridBagConstraints();
        
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 0;
        
        JLabel cleanPetHeader = new JLabel("<html><h1>Clean Pet:</h1></html>",JLabel.CENTER);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 3;
        con.weightx = 0;
        panel.add(cleanPetHeader, con);
        
        JLabel emotion = new JLabel(petEmotion());
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 1;
        con.gridwidth = 3;
        con.weightx = 0;
        panel.add(emotion, con);
        
        JLabel petEmotionMessage = new JLabel("<html><h3>Clean Me</h3></html>",JLabel.CENTER);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 2;
        con.gridwidth = 3;
        con.weightx = 0;
        panel.add(petEmotionMessage, con);
        
        JButton cleanPet = new JButton("Clean "+pet.name);
        con.gridx = 0;
        con.gridy = 3;
        con.gridwidth = 3;
        panel.add(cleanPet, con);
        
        JButton backButton = new JButton("Back to Main Menu");
        con.gridx = 0;
        con.gridy = 4;
        con.gridwidth = 3;
        panel.add(backButton, con);
        
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                if(e.getSource() == cleanPet){
                    if(pet.clean < 10){
                        if(pet.happiness >0){
                            pet.happiness--;
                            pet.clean++;
                            pet.changeHappiness();
                            emotion.setIcon(petEmotion());
                            petEmotionMessage.setText("<html><h3>" + pet.name + " gets cleaner, but isn't happy about it.</h3><html>");
                        }else{
                            petEmotionMessage.setText("<html><h3>" + pet.name + " is too unhappy to cooperate with a clean.</h3><html>");
                            cleanPet.setEnabled(false);
                        }
                    }else{
                        petEmotionMessage.setText("<html><h3>" + pet.name + " is clean, Washing them again would be mean.</h3><html>");
                        cleanPet.setEnabled(false);
                    }
                    } else if (e.getSource() == backButton) {
                    System.out.println("Returning to main menu");
                    cl.show(cards, MAINPANEL);
                }
            }
        };
        cleanPet.addActionListener(al);
        backButton.addActionListener(al);
        ComponentAdapter ca = new ComponentAdapter(){
            @Override
            public void componentShown(ComponentEvent e){
                emotion.setIcon(petEmotion());
                if(pet.happiness > 0)
                    cleanPet.setEnabled(true);
                if(pet.clean > 0 && pet.happiness > 0){
                    petEmotionMessage.setText("<html><h3>" + pet.name + " looks a little dirty, give them a clean!</h3><html>");
                    cleanPet.setEnabled(true);
                }else if(pet.clean < 10 && pet.happiness < 1){
                    petEmotionMessage.setText("<html><h3>" + pet.name + " looks a little dirty, but isn't in the mood for a clean!</h3><html>");
                    cleanPet.setEnabled(false);
                }else if(pet.clean >= 10){
                    petEmotionMessage.setText("<html><h3>" + pet.name + " doesn't look dirty!</h3><html>");
                    cleanPet.setEnabled(false);
                }
            }
        };
        panel.addComponentListener(ca);
        
        return panel;
    }
    
    private JPanel fishGame(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints con = new GridBagConstraints();
        
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 0;
        
        JLabel fishingGameHeader = new JLabel("<html><h1>Fishing Game</h1></html>",JLabel.CENTER);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 3;
        con.weightx = 0;
        panel.add(fishingGameHeader, con);
        
        JLabel fishingRules = new JLabel("<html><div style='text-align:center;'><h3>This is the fishing game. Send "+pet.name+" into the pond to fish!<br/>"
                + "They won't get any fish when they want a bath"+"</h3></html>",JLabel.CENTER);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 1;
        con.gridwidth = 3;
        con.weightx = 0;
        panel.add(fishingRules, con);
        
        JLabel fishImage = new JLabel(new ImageIcon("sprites/clear.png"),JLabel.CENTER);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 2;
        con.gridwidth = 3;
        con.weightx = 0;
        panel.add(fishImage, con);
        JLabel fishingPrompt = new JLabel("Lets go fishing!",JLabel.CENTER);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 3;
        con.gridwidth = 3;
        con.weightx = 0;
        panel.add(fishingPrompt, con);
        
        JButton fishButton = new JButton("Send "+pet.name+" fishing!");
        con.gridx = 0;
        con.gridy = 4;
        con.gridwidth = 3;
        panel.add(fishButton, con);
        
        JButton backButton = new JButton("Back to Main Menu");
        con.gridx = 0;
        con.gridy = 5;
        con.gridwidth = 3;
        panel.add(backButton, con);
    
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                if(e.getSource() == fishButton){
                    if(pet.clean >0){
                        int rand = (int) (Math.random() * 10);
                        int fishSize = (int) ((Math.random()*10) % 3)+1;
                        if (rand <= 4){
                            switch(fishSize){
                                case 1:
                                    player.fish++;
                                    pet.clean-=2;
                                    System.out.println("small");
                                    fishImage.setIcon(new ImageIcon("sprites/fishSmall.png"));
                                    fishingPrompt.setText("You caught a small fish! Brining your total fish count to "+player.fish);
                                    break;
                                case 2:
                                    player.fish+=2;
                                    pet.clean-=2;
                                    System.out.println("medium");
                                    fishImage.setIcon(new ImageIcon("sprites/fishMedium.png"));
                                    fishingPrompt.setText("You caught a medium fish! Brining your total fish count to "+player.fish);
                                    break;
                                case 3:
                                    player.fish+=3;
                                    pet.clean-=2;
                                    System.out.println("large");
                                    fishImage.setIcon(new ImageIcon("sprites/fishLarge.png"));
                                    fishingPrompt.setText("You caught a large fish! Brining your total fish count to "+player.fish);
                                    break;
                                default:
                                    System.out.println(fishSize);
                                    fishImage.setIcon(new ImageIcon("sprites/clear.png"));
                                    break;
                            }
                        }else{
                            pet.clean--;
                            System.out.println("found noting");
                            fishingPrompt.setText("You didn't catch any fish :(");
                            fishImage.setIcon(new ImageIcon("sprites/clear.png"));
                            
                        }
                        
                    }else{
                        fishImage.setIcon(new ImageIcon("sprites/clear.png"));
                        fishingPrompt.setText(pet.name+" is too dirty and doesn't want to go fishing!");
                        System.out.println(pet.name+" is too dirty and doesn't want to go fishing!");
                        fishButton.setEnabled(false);
                    }
                } else if (e.getSource() == backButton) {
                    System.out.println("Returning to main menu");
                    cl.show(cards, MAINPANEL);
                }
            }
        };
        fishButton.addActionListener(al);
        backButton.addActionListener(al);
        ComponentAdapter ca = new ComponentAdapter(){
            @Override
            public void componentShown(ComponentEvent e){
                fishImage.setIcon(new ImageIcon("sprites/clear.png"));
                fishingPrompt.setText("Lets go fishing!");
                if(pet.clean>0)
                    fishButton.setEnabled(true);
                else
                    fishButton.setEnabled(false);
            }
        };
        panel.addComponentListener(ca);
        
        return panel;
    }
    
    public void finalPrep(){
        JPanel card4 = mainMenu();
        JPanel card5 = showInfo();
        JPanel card6 = shopMenu();
        JPanel card7 = patPet();
        JPanel card8 = cleanPet();
        JPanel card9 = fishGame();
        
        cards.add(card4, MAINPANEL);
        cards.add(card5, SHOWINFOPANEL);
        cards.add(card6, SHOPPANEL);
        cards.add(card7, PATPETPANEL);
        cards.add(card8, CLEANPETPANEL);
        cards.add(card9, FISHPANEL);
        
    }
    
    public void cardCreate(Container pane){
        JPanel comboBoxPane = new JPanel();
        String comboBoxItems[] = {WELCOMPANEL, NEWSAVEPANEL, MAINPANEL, SHOWINFOPANEL, SHOPPANEL, PATPETPANEL, CLEANPETPANEL, FISHPANEL};
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        // create the cards for welcome and save handling
        JPanel card1 = welcome();
        JPanel card2 = newSave();
        JPanel card3 = loadSave();
        
        
        cards = new JPanel(new CardLayout());
        cards.add(card1, WELCOMPANEL);
        cards.add(card2, NEWSAVEPANEL);
        cards.add(card3, LOADSAVEPANEL);
        
        //pane.add(comboBoxPane,BorderLayout.PAGE_END); dev tools
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