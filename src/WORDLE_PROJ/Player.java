package WORDLE_PROJ;

public class Player extends User implements Comparable<Player>{
    private int score;
    private boolean isBanned;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
        setScore(Integer. parseInt(s[2]));
        setBanned(Boolean.parseBoolean(s[3]));
    }

    public Player(String username, char[] password){
        super(username, password);
        this.score = 0;
        this.isBanned = false;
    }

    @Override
    public int compareTo(Player p) {
        return Integer.compare(p.getScore(), getScore());
    }


}
