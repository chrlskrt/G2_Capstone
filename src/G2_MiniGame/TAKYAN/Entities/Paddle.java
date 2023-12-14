package G2_MiniGame.TAKYAN.Entities;
import G2_MiniGame.TAKYAN.GameScreens.TakyanGameScreen;
import G2_MiniGame.TAKYAN.GameUtils.Mouse;
import G2_MiniGame.TAKYAN.GameUtils.RenderObj;
import G2_MiniGame.TAKYAN.GameUtils.Updater;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Paddle extends RenderObj implements Updater {

    public static int xCoordinate;
    public static int yCoordinate;
    public static int width = 100;
    public static int height = 30;
    private BufferedImage paddleImage;

    public Paddle(){
        try {
            paddleImage = ImageIO.read(new File("src/G2_MiniGame/TAKYAN/Assets/tsinelas.png"));
        } catch (IOException e) {
            System.out.println("Image of Tsinelas can't be found.");
        }

        xCoordinate = TakyanGameScreen.WINDOW_SIZE_X / 2;
        yCoordinate = TakyanGameScreen.WINDOW_SIZE_Y - 40;
    }
    @Override
    public void paintComponent(Graphics2D g) {
        if(paddleImage != null){
            g.drawImage(paddleImage, xCoordinate - width / 2, yCoordinate - height / 2, null);
        }else{
            g.setColor(Color.YELLOW);
            g.fillRect(xCoordinate - width / 2, yCoordinate - height / 2, width, height);
        }
    }

    @Override
    public void update() {
        Mouse mouse = game.mouse;
        xCoordinate = mouse.x;

        yCoordinate = TakyanGameScreen.WINDOW_SIZE_Y - 20;
        if(yCoordinate >= TakyanGameScreen.WINDOW_SIZE_Y - height){
            yCoordinate = TakyanGameScreen.WINDOW_SIZE_Y - height;
        }

        if(xCoordinate + width / 2 >= TakyanGameScreen.WINDOW_SIZE_X){
            xCoordinate = TakyanGameScreen.WINDOW_SIZE_X - width / 2;
        }

        if(xCoordinate - width / 2  <= 0){
            xCoordinate = width / 2;
        }
    }
}
