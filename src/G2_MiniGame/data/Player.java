package G2_MiniGame.data;

import java.util.Comparator;

public class Player extends User{
    private int wordleScore;
    private int takyanScore;
    private int mazeScore;

    public int getWordleScore() {
        return wordleScore;
    }

    public void setWordleScore(int wordleScore) {
        this.wordleScore = wordleScore;
    }
    public void updateWordleScore(int increase){
        wordleScore += increase;
    }

    public Player(String[] s) {
        super(s[0], s[1].toCharArray());
        setWordleScore(Integer. parseInt(s[2]));
        setTakyanScore(Integer.parseInt(s[3]));
        setMazeScore(Integer.parseInt(s[4]));
    }

    public Player(String username, char[] password) {
        super(username, password);
        this.wordleScore = 0;
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
