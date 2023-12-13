package G2_MiniGame.MAZE.Maze;

import G2_MiniGame.MAZE.Helpers.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gun extends DynamicEntity{
    private double damage;
    private int bullets ;
    Sound sd;



    Gun(GunBuilder builder){
        super(builder);
        damage = builder.damage;
        bullets = builder.bullets;
    }




    public static class GunBuilder extends DynamicEntityBuilder {
        private double damage;
        private int bullets;


        public GunBuilder(){
            super();
            damage = 10;
            bullets = 5;
        }

        public GunBuilder damage(double damage) {
            this.damage = damage;
            return this;
        }


        public GunBuilder bullets(int bullets) {
            this.bullets = bullets;
            return this;
        }

        @Override
        public Gun build() {
            return new Gun(this);
        }
    }

    public boolean shoot(boolean hit, Wall w){
        if(current_animation_frame == 1 && bullets > 0){
            if(hit){
                sd.play(new File("src/G2_MiniGame/MAZE/Audio/hit.wav"), 5f);
                w.state = true;
            }
            setIdleState(false);
            bullets -=1;
            sd.play(new File("src/G2_MiniGame/MAZE/Audio/shoot_reload.wav"), 5f);
            return true;
        }

        if(bullets < 1){
            sd.play(new File("src/G2_MiniGame/MAZE/Audio/no_ammo.wav"), 5f);
        }

        return false;
    }

    public int getBullets() {
        return bullets;
    }

    public void setBullets(int bullets){
        this.bullets = bullets;
    }
}
