package WordlePackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Players {
    HashMap<String, String> playerslist = new HashMap<>();
    HashMap<String, Integer> playerScores = new HashMap<>();
    public int addtoList(String name, String password){
        if (playerslist.containsKey(name)) {
            if (playerslist.get(name).equals(password)) {
                JOptionPane.showMessageDialog(null, "Account already exists");
            } else {
                JOptionPane.showMessageDialog(null, "Wrong password");
            }
            return 0;
        } else {
            playerslist.put(name, password);
            addtoFile(name);
            return 1;
        }
    }
    public void addtoFile(String name){
        try {
            BufferedWriter bw= new BufferedWriter(new FileWriter("C:\\Users\\Acer\\Downloads\\WORDLE\\WORDLE\\players.txt", true));
            bw.append(name);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //If naa na tay Score2x, try daw call this method
    public void updateScore(String name, int score) {
        if (playerScores.containsKey(name)) {
            int currentScore = playerScores.get(name);
            playerScores.put(name, currentScore + score);
        } else {
            JOptionPane.showMessageDialog(null, "Player not found");
        }
    }
    Players(){

    }

}
