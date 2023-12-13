package G2_MiniGame.MAZE.Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DynamicEntity extends Entity{
    public int animation_speed = 5;
    public int TOTAL_ANIMATION_PICTURES = 15;
    public int current_animation_frame = 0;
    public int animation_counter = 0;
    private boolean idleState = true;
    public BufferedImage backgroundImage;
    public boolean state = true;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage, 255, 485, 300, 300, null);
    }

    DynamicEntity(DynamicEntityBuilder builder) {
        super(builder);
        animation_speed = builder.animation_speed;
        TOTAL_ANIMATION_PICTURES = builder.TOTAL_ANIMATION_PICTURES;
        current_animation_frame = builder.current_animation_frame;
        animation_counter = builder.animation_counter;
        idleState = builder.idleState;
        backgroundImage = builder.backgroundImage;
        state = builder.state;
    }

    public static class DynamicEntityBuilder extends EntityBuilder {
        public int animation_speed;
        public int TOTAL_ANIMATION_PICTURES;
        public int current_animation_frame;
        public int animation_counter;
        private boolean idleState;
        public boolean state;


        private BufferedImage backgroundImage;


        public DynamicEntityBuilder(){
            super();
            animation_speed = 5;
            TOTAL_ANIMATION_PICTURES = 15;
            current_animation_frame = 0;
            animation_counter = 0;
            idleState = true;
            state = true;
        }


        public DynamicEntityBuilder backgroundImage(BufferedImage backgroundImage) {
            this.backgroundImage = backgroundImage;
            return this;
        }

        public DynamicEntityBuilder animation_speed(int animation_speed) {
            this.animation_speed = animation_speed;
            return this;
        }

        public DynamicEntityBuilder TOTAL_ANIMATION_PICTURES(int TOTAL_ANIMATION_PICTURES) {
            this.TOTAL_ANIMATION_PICTURES = TOTAL_ANIMATION_PICTURES;
            return this;
        }

        public DynamicEntityBuilder current_animation_frame(int current_animation_frame) {
            this.current_animation_frame = current_animation_frame;
            return this;
        }

        public DynamicEntityBuilder animation_counter(int animation_counter) {
            this.animation_counter = animation_counter;
            return this;
        }

        public DynamicEntityBuilder state(boolean state) {
            this.state = state;
            return this;
        }

        @Override
        public DynamicEntity build() {
            return new DynamicEntity(this);
        }
    }

    public void setBackgroundImage(String path_image) throws IOException {
        try {
            backgroundImage = ImageIO.read(new File(path_image));
        } catch (IOException e) {
            throw new IOException("Failed to set background image: " + e.getMessage(), e);
        }
    }

    public void setIdleState(boolean idleState) {
        this.idleState = idleState;
    }

    public boolean getIdleState(){
        return idleState;
    }
}
