package G2_MiniGame;

import java.util.Comparator;

public class Player extends User{
    private int wordleScore;
    private int takyanScore;
    private int mazeScore;

    private boolean isBanned;

    public int getWordleScore() {
        return wordleScore;
    }

    public void setWordleScore(int wordleScore) {
        this.wordleScore = wordleScore;
    }
    public void updateWordleScore(int increase){
        wordleScore += increase;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public Player(String[] s) {
        super(s[0], s[1].toCharArray());
        // System.out.println(s[0] + "\n" + s[1] + "\n" + s[2] + "\n" + s[3]);
        setWordleScore(Integer. parseInt(s[2]));
        setTakyanScore(Integer.parseInt(s[3]));
        setMazeScore(Integer.parseInt(s[4]));
        setBanned(Boolean.parseBoolean(s[5]));
    }

    public Player(String username, char[] password) {
        super(username, password);
        this.wordleScore = 0;
        this.isBanned = false;
    }

    public int getMazeScore() {
        return mazeScore;
    }

    public void setMazeScore(int mazeScore) {
        this.mazeScore = mazeScore;
    }

    public int getTakyanScore() {
        return takyanScore;
    }

    public void setTakyanScore(int takyanScore) {
        this.takyanScore = takyanScore;
    }

    public static class SortByWordleScore implements Comparator<Player> {

        @Override
        public int compare(Player o1, Player o2) {
            return Integer.compare(o2.getWordleScore(), o1.getWordleScore());
        }
    }

    public static class SortByTakyanScore implements Comparator<Player>{

        @Override
        public int compare(Player o1, Player o2) {
            return Integer.compare(o2.getTakyanScore(), o1.getTakyanScore());
        }
    }

    public static class SortByMazeScore implements Comparator<Player>{

        @Override
        public int compare(Player o1, Player o2) {
            return Integer.compare(o2.getMazeScore(), o1.getMazeScore());
        }
    }
}
