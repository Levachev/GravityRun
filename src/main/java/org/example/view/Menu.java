package org.example.view;

import org.example.models.cursors.CursorOfMenu;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Menu extends JPanel {
    CursorOfMenu cursorOfMenu;
    private double myPixelWight;
    private double myPixelHeight;
    public Menu(CursorOfMenu cursorOfMenu, double myPixelWight, double myPixelHeight) {
        this.myPixelWight=myPixelWight;
        this.myPixelHeight=myPixelHeight;
        this.cursorOfMenu=cursorOfMenu;
        this.setBackground(Color.CYAN);
    }

    public void paintComponent(@NotNull Graphics g) {
        super.paintComponent(g);

        BufferedImage menuImage;
        try {
            menuImage = ImageIO.read(getClass().getResource("/images/menu.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage cursorImage;
        try {
            cursorImage = ImageIO.read(getClass().getResource("/images/cursor.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage singleImage;
        try {
            singleImage = ImageIO.read(getClass().getResource("/images/singleplayer.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage pvpImage;
        try {
            pvpImage = ImageIO.read(getClass().getResource("/images/multiplayer.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage helpImage;
        try {
            helpImage = ImageIO.read(getClass().getResource("/images/help.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage quitImage;
        try {
            quitImage = ImageIO.read(getClass().getResource("/images/quit.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int cursorX=cursorOfMenu.getX();
        int cursorY=cursorOfMenu.getY();
        int cursorWight=cursorOfMenu.getWight();
        int cursorHeight=cursorOfMenu.getHeight();

        g.drawImage(menuImage, 0, 0, (int) (cursorWight*4*myPixelWight), (int) (cursorHeight*myPixelHeight), null);
        g.drawImage(cursorImage, (int) (cursorX*myPixelWight), (int) (cursorY*myPixelHeight), (int) (cursorWight*myPixelWight), (int) (cursorHeight*myPixelHeight), null);
        g.drawImage(singleImage, (int) ((cursorX+2*cursorWight)*myPixelWight), 0, (int) (cursorWight*3*myPixelWight), (int) (cursorHeight*myPixelHeight), null);
        g.drawImage(pvpImage, (int) ((cursorX+2*cursorWight)*myPixelWight), (int) ((cursorOfMenu.panelHeight/cursorOfMenu.amountOfFields)*myPixelHeight), (int) (cursorWight*3*myPixelWight), (int) (cursorHeight*myPixelHeight), null);
        g.drawImage(helpImage, (int) ((cursorX+2*cursorWight)*myPixelWight), (int) ((cursorOfMenu.panelHeight/cursorOfMenu.amountOfFields*2)*myPixelHeight), (int) (cursorWight*3*myPixelWight), (int) (cursorHeight*myPixelHeight), null);
        g.drawImage(quitImage, (int) ((cursorX+2*cursorWight)*myPixelWight), (int) ((cursorOfMenu.panelHeight/cursorOfMenu.amountOfFields*3)*myPixelHeight), (int) (cursorWight*3*myPixelWight), (int) (cursorHeight*myPixelHeight), null);
    }
}
