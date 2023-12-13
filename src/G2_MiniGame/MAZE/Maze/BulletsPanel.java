package G2_MiniGame.MAZE.Maze;

import java.awt.*;

public class BulletsPanel extends DynamicEntity{
    BulletsPanel(BulletsPanelBuilder builder){
        super(builder);
    }

    public static class BulletsPanelBuilder extends DynamicEntityBuilder {


        public BulletsPanelBuilder(){
            super();
        }

        @Override
        public BulletsPanel build() {
            return new BulletsPanel(this);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage, 20, 20, 200, 100, null);
    }
}
