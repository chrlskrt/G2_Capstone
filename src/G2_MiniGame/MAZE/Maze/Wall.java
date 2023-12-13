package G2_MiniGame.MAZE.Maze;

import java.awt.*;


public class Wall extends DynamicEntity{

    public Type_Wall type;
    public int index_in_map;

    private Wall(WallBuilder builder) {
        super(builder);
        this.color = builder.color;
        this.type = builder.type;
        this.index_in_map = builder.index_in_map;
    }

    // Builder Class for Wall
    public static class WallBuilder extends DynamicEntityBuilder {

        private Type_Wall type;
        public int index_in_map;

        public WallBuilder() {
            super(); // Call the constructor of the superclass (EntityBuilder)
            type = Type_Wall.SOLIDWALL;
            index_in_map = 1;
        }


        public WallBuilder type(Type_Wall type) {
            this.type = type;
            return this;
        }

        public WallBuilder index_in_map(int index_in_map) {
            this.index_in_map = index_in_map;
            return this;
        }


        @Override
        public Wall build() {
            return new Wall(this);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(pos_x,pos_y, width, height);
    }




}
