package G2_MiniGame;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayersHandler {
    private static PlayersHandler instance = null;
    private final ArrayList<Player> playersList;
   // private Player currentPlayer;
    public static PlayersHandler getInstance(){
        if (instance == null){
            instance = new PlayersHandler();
        }

        return instance;
    }
    private PlayersHandler(){
        playersList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/G2_MiniGame/TextFiles/players.txt"));
            String playerInfo;

            while((playerInfo = br.readLine()) != null){
                String[] info = playerInfo.split(" / ");
                playersList.add(new Player(info));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No players file.");
        } catch (IOException e) {
            System.out.println("Error loading players file.");
        }
    }


    // tp be upgraded
//    public void sort (){
//        if (playersList.isEmpty()){
//            return;
//        }
//        Collections.sort(playersList);
//    }
    public void sortWordleScore(){
        if (getPlayersList().isEmpty()){
            return;
        }

        getPlayersList().sort(new Player.SortByWordleScore());
    }

    public void sortTakyanScore(){
        if (getPlayersList().isEmpty()){
            return;
        }

        getPlayersList().sort(new Player.SortByTakyanScore());
    }

    public void sortMazeScore(){
        if (getPlayersList().isEmpty()){
            return;
        }

        getPlayersList().sort(new Player.SortByMazeScore());
    }
    public int handleLogIn(String username, char[] password){
        int i = 0;
        for (Player p: playersList){
            if (p.getUsername().equals(username) && Arrays.equals(p.getPassword(),password)){
                return i;
            }

            if (p.getUsername().equals(username)){
                return -1;
            }
            i++;
        }
        return -2;
    }

    public boolean isUsernameTaken(String username){
        for (Player p: playersList){
            if (p.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }
    public void handleCreateUser(String username, char[] password){
        Player p = new Player(username, password);
        playersList.add(p);
        addPlayerToFile(p);
    }
    public void addPlayerToFile(Player p){
        try {
            BufferedWriter bw= new BufferedWriter(new FileWriter("src/G2_MiniGame/TextFiles/players.txt", true));
            bw.append(p.getUsername()).append(" / ").append(String.valueOf(p.getPassword())).append(" / ").append(String.valueOf(p.getWordleScore())).append(" / ").append(String.valueOf(p.getTakyanScore())).append(" / ").append(String.valueOf(p.getMazeScore())).append(" / ").append(String.valueOf(p.isBanned()));
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error adding Player into file.");
        }
    }

    public void updatePlayersFile(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/G2_MiniGame/TextFiles/players.txt"));
            for(Player p: playersList){
                bw.append(p.getUsername()).append(" / ").append(String.valueOf(p.getPassword())).append(" / ").append(String.valueOf(p.getWordleScore())).append(" / ").append(String.valueOf(p.getTakyanScore())).append(" / ").append(String.valueOf(p.getMazeScore())).append(" / ").append(String.valueOf(p.isBanned()));
                bw.newLine();
            }

            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error in updating file.");
        }
    }

//    public void setCurrentPlayer(Player player){
//        currentPlayer = player;
//    }

//    public void unsetCurrentPlayer(){
//        currentPlayer = null;
//    }
//
//    public Player getCurrentPlayer(){
//        return currentPlayer;
//    }

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }
}
