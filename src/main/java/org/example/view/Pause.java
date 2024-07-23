package org.example.view;

import org.example.models.cursors.CursorOfPause;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Math.abs;

public class Pause extends JPanel {
    CursorOfPause cursorOfPause;
    private int currentStatus;
    private double myPixelWight;
    private double myPixelHeight;
    public Pause(CursorOfPause cursorOfPause, int message, double myPixelWight, double myPixelHeight) {
        this.myPixelWight=myPixelWight;
        this.myPixelHeight=myPixelHeight;
        this.cursorOfPause=cursorOfPause;
        this.setBackground(Color.BLUE);
        currentStatus=message;
    }

    public void paintComponent(@NotNull Graphics g) {
        super.paintComponent(g);

        BufferedImage pauseImage;
        try {
            pauseImage = ImageIO.read(getClass().getResource("/images/pause.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage cursorImage;
        try {
            cursorImage = ImageIO.read(getClass().getResource("/images/cursor.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage resumeImage;
        try {
            resumeImage = ImageIO.read(getClass().getResource("/images/resume.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage restartImage;
        try {
            restartImage = ImageIO.read(getClass().getResource("/images/restart.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage exitImage;
        try {
            exitImage = ImageIO.read(getClass().getResource("/images/pause_exit.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (cursorOfPause.getHeight()/2*myPixelHeight)));
        g.setColor(Color.ORANGE);
        if(currentStatus>0){
            g.drawString("current score : "+currentStatus, 0, (int) (cursorOfPause.getHeight()*2*myPixelHeight));
        }
        int cursorX=cursorOfPause.getX();
        int cursorY=cursorOfPause.getY();
        int cursorWight=cursorOfPause.getWight();
        int cursorHeight=cursorOfPause.getHeight();

        g.drawImage(pauseImage, 0, 0, (int) (cursorWight*4*myPixelWight), (int) (cursorHeight*myPixelHeight), null);
        g.drawImage(cursorImage, (int) (cursorX*myPixelWight), (int) (cursorY*myPixelHeight), (int) (cursorWight*myPixelWight), (int) (cursorHeight*myPixelHeight), null);
        g.drawImage(resumeImage, (int) ((cursorX+2*cursorWight)*myPixelWight), 0, (int) (cursorWight*3*myPixelWight), (int) (cursorHeight*myPixelHeight), null);
        g.drawImage(restartImage, (int) ((cursorX+2*cursorWight)*myPixelWight), (int) ((cursorOfPause.panelHeight/cursorOfPause.amountOfFields)*myPixelHeight), (int) (cursorWight*3*myPixelWight), (int) (cursorHeight*myPixelHeight), null);
        g.drawImage(exitImage, (int) ((cursorX+2*cursorWight)*myPixelWight), (int) ((cursorOfPause.panelHeight/cursorOfPause.amountOfFields*2)*myPixelHeight), (int) (cursorWight*3*myPixelWight), (int) (cursorHeight*myPixelHeight), null);
    }
}
