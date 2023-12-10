package G2_Capstone;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HandlePlayers {
    private static HandlePlayers instance = null;
    public ArrayList<Player> playerslist = null;
    public static HandlePlayers getInstance(){
        if (instance == null){
            instance = new HandlePlayers();
        }

        return instance;
    }
    private HandlePlayers(){
        playerslist = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/G2_Capstone/TextFiles/players.txt"));
            String playerInfo;

            while((playerInfo = br.readLine()) != null){
                String[] info = playerInfo.split(" / ");
                playerslist.add(new Player(info));
            }
        } catch (FileNotFoundException e) {
            System.out.println("no file. cannot continue program run.");
        } catch (IOException e) {
            System.out.println("incorrect");
        }
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
                return -1;
            }
            i++;
        }
        return -2;
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
            BufferedWriter bw= new BufferedWriter(new FileWriter("src/G2_Capstone/TextFiles/players.txt", true));
            bw.append(p.getUsername() + " / " + String.valueOf(p.getPassword()) + " / " + p.getWordleScore() + " / " + p.isBanned());
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error adding Player into file.");
        }
    }

    public void updatePlayersFile(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/G2_Capstone/TextFiles/players.txt"));
            for(Player p: playerslist){
                bw.append(p.getUsername() + " / " + String.valueOf(p.getPassword()) + " / " + p.getWordleScore() + " / " + p.isBanned());
                bw.newLine();
            }

            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error in updating file.");
        }
    }
}
