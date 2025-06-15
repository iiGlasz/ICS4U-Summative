package myfirstsketch;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ljphi
 */
public class GameSaver {
    //variables
    int discipline;
    int currentChapter;
    boolean hasPigsy;
    boolean hasSandy;
    int damage;
    
    // arrayList to read from the save files
    ArrayList<String> lines = new ArrayList<String>();
    
    // constructor with the game's current info
    public GameSaver(int chapter, boolean hasPigsy, boolean hasSandy, int damage, int discipline){
        this.currentChapter = chapter;
        this.hasPigsy = hasPigsy;
        this.hasSandy = hasSandy;
        this.damage = damage;
        this.discipline = discipline;
    }
    
    /**
     * saves the current game by writing the info to a text file
     * @param slot the save file slot which will be the line on the text file
     */
    public void saveGame(int slot){
        // try to write to the file
        try{
            String fileName = "text/playerSaves.txt";
            fileTextReader(fileName);
               
            FileWriter gameData = new FileWriter(fileName, false);
            PrintWriter writeFile = new PrintWriter (gameData);
            
            // loop through each line and rewrite the previous info 
            for (int i = 0; i < 4; i++){
                // if the line is equal to the save slot, update it to the new save
                if (i == slot - 1){
                    writeFile.print("" + this.currentChapter + ',' + this.hasPigsy + ',' + this.hasSandy + ',' + this.damage + ',' + this.discipline);
                }
                else{
                    writeFile.print(lines.get(i));
                }
                // only add a new line to the file if its not the last line
                if (i < 3) {
                    writeFile.print("\n");
                } 
            }
            // close the file to save it
            writeFile.close();
            // print a message
            System.out.println("Game Saved!");
            // return to the main menu
            MySketch.mainMenu = true;
            MySketch.gameState = 0;
        }
        catch(IOException e){
            System.out.println("Failed to save game!");
        }
    }
    
    /**
     * load the save file
     * @param slot the file's line, where the save data is 
     */
    public void loadGame(int slot){
        String fileName = "text/playerSaves.txt";
        fileTextReader(fileName);
        
        //get the save file's data 
        String tempLine = lines.get(slot - 1);
        String [] arrayLine = tempLine.split(",");
        
        // set each part of the game variables to the ones in the save file
        MySketch.gameState = Integer.parseInt(arrayLine[0]);
        Scene.chapterScreen = Integer.parseInt(arrayLine[0]);
        TeamMembers.pigsy = Boolean.valueOf(arrayLine[1]);
        TeamMembers.sandy = Boolean.valueOf(arrayLine[2]);
        Player.damage = Integer.parseInt(arrayLine[3]);
        Player.discipline = Integer.parseInt(arrayLine[4]);
        
        // print a message to show that the save file was loaded
        System.out.println("Loaded Save File " + slot + "!");
    }
    
    /**
     * reads files and adds each line to an arraylist
     * @param fileName the name of the file being read (file location)
     */
    public void fileTextReader(String fileName){
        // clear out the array before adding to it
        lines.clear();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
