package org.example.view;

import org.example.models.cursors.CursorOfGameOver;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class GameOver extends JPanel {
    CursorOfGameOver cursorOfGameOver;
    private double myPixelWight;
    private double myPixelHeight;
    private int currentStatus;
    public GameOver(CursorOfGameOver cursorOfGameOver, int message, double myPixelWight, double myPixelHeight) {
        this.myPixelWight=myPixelWight;
        this.myPixelHeight=myPixelHeight;
        this.cursorOfGameOver=cursorOfGameOver;
        this.setBackground(Color.BLUE);
        currentStatus=message;
    }

    public void paintComponent(@NotNull Graphics g) {
        super.paintComponent(g);

        BufferedImage gameOverImage;
        try {
            gameOverImage = ImageIO.read(getClass().getResource("/images/game_over.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage cursorImage;
        try {
            cursorImage = ImageIO.read(getClass().getResource("/images/cursor.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage restartImage;
        try {
            restartImage = ImageIO.read(getClass().getResource("/images/restart.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage quitImage;
        try {
            quitImage = ImageIO.read(getClass().getResource("/images/pause_exit.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (cursorOfGameOver.getHeight()/2*myPixelHeight)));
        g.setColor(Color.ORANGE);
        if(currentStatus>0){
            g.drawString("total score : "+currentStatus, 0, (int) (cursorOfGameOver.getHeight()*2*myPixelHeight));
        } else if (currentStatus<0) {
            switch (currentStatus){
                case -1:
                    g.drawString("player two is winner", 0, (int) (cursorOfGameOver.getHeight()*2*myPixelHeight));
                    break;
                case -2:
                    g.drawString("player one is winner", 0, (int) (cursorOfGameOver.getHeight()*2*myPixelHeight));
                    break;
            }
        }
        int cursorX=cursorOfGameOver.getX();
        int cursorY=cursorOfGameOver.getY();
        int cursorWight=cursorOfGameOver.getWight();
        int cursorHeight=cursorOfGameOver.getHeight();

        g.drawImage(gameOverImage, 0, 0, (int) (cursorWight*2*myPixelWight), (int) (cursorHeight*myPixelHeight), null);
        g.drawImage(cursorImage, (int) (cursorX*myPixelWight), (int) (cursorY*myPixelHeight), (int) (cursorWight*myPixelWight), (int) (cursorHeight*myPixelHeight), null);
        g.drawImage(restartImage, (int) ((cursorX+2*cursorWight)*myPixelWight), 0, (int) (cursorWight*3*myPixelWight), (int) (cursorHeight*myPixelHeight), null);
        g.drawImage(quitImage, (int) ((cursorX+2*cursorWight)*myPixelWight), (int) ((cursorOfGameOver.panelHeight/cursorOfGameOver.amountOfFields)*myPixelHeight), (int) (cursorWight*3*myPixelWight), (int) (cursorHeight*myPixelHeight), null);
    }
}
