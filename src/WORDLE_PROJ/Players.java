package WORDLE_PROJ;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Players {
    ArrayList <Player> playerslist = null;
    public Players(){
        playerslist = new ArrayList<>();
    }
    public void sort (){
        if (playerslist.isEmpty()){
            return;
        }

        Collections.sort(playerslist);
    }

    public int handleLogIn(String name, char[] password){
        int i = 0;
        for (Player p: playerslist){
            if (p.getUsername().equals(name) && Arrays.equals(p.getPassword(),password)){
                return i;
            }

            if (p.getUsername().equals(name)){
                JOptionPane.showMessageDialog(null, "Wrong password");
                return -1;
            }
            i++;
        }

        JOptionPane.showMessageDialog(null,"Account does not exist. Invalid username/password.");
        return -1;
    }

    public boolean isUsernameTaken(String name){
        for (Player p: playerslist){
            if (p.getUsername().equals(name)) {
                return true;
            }
        }

        return false;
    }
    public void handleCreateUser(String name, char[] password){
        Player p = new Player(name, password);
        playerslist.add(p);
        addtoFile(p);
    }
    public void addtoFile(Player p){
        try {
            BufferedWriter bw= new BufferedWriter(new FileWriter("C:\\Users\\Acer\\Desktop\\capstone\\src\\WORDLE_PROJ\\players.txt", true));
            bw.append(p.getUsername() + " / " + String.valueOf(p.getPassword()) + " / " + p.getScore() + " / " + p.isBanned());
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
