package G2_Capstone.WORDLE;

public class TilePositionTracker {
    // STATIC CLASS
    // Keeps track of which tile is being used

    private static int ROW = 0;
    private static int COL = 0;
    public static void setROW(int row){
        ROW = row;
    }

    public static void setCOL(int col) {
        COL = col;
    }

    public static int getROW(){
        return ROW;
    }

    public static int getCOL(){
        return COL;
    }
}
