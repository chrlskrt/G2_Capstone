package G2_MiniGame.MAZE.Maze;

import java.awt.*;

public class GameStatePanel extends DynamicEntity{
    GameStatePanel(GameStatePanelBuilder builder){
        super(builder);
        width = 0;
        height = 0;
    }

    public static class GameStatePanelBuilder extends DynamicEntityBuilder {


        public GameStatePanelBuilder(){
            super();
        }

        @Override
        public GameStatePanel build() {
            return new GameStatePanel(this);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage, 0, 0, width, height, null);
    }

    public void show_panel(){
        width = 800;
        height = 800;
    }

    public void hide_panel(){
        width = 0;
        height = 0;
    }
}
