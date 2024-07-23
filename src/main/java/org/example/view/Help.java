package org.example.view;

import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Help extends JPanel {
    private double myPixelWight;
    private double myPixelHeight;
    public Help(double myPixelWight, double myPixelHeight) {
        this.myPixelWight=myPixelWight;
        this.myPixelHeight=myPixelHeight;
        this.setBackground(Color.LIGHT_GRAY);
    }

    public void paintComponent(@NotNull Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);

        BufferedImage helpMessageImage;
        try {
            helpMessageImage = ImageIO.read(getClass().getResource("/images/help_message.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g.drawImage(helpMessageImage, 0, 0, (int) (520*myPixelWight), (int) (360*myPixelHeight), null);

        /*g.drawString("There are 2 game modes: singleplayer and multiplayer. in", 50, 50);
        g.drawString("singleplayer your task is to play as long as possible, the",25, 65);
        g.drawString("character can change orientation if you press the 'q' button. in", 25,80);
        g.drawString("multiplayer mode, you have to play longer than your opponent,", 25, 95);
        g.drawString("the black character changes orientation with the 'q' button,", 25, 110);
        g.drawString("and the striped character changes his orientation with the 'p'", 25, 125);
        g.drawString("button. also in both modes if you press the 'space' button then", 25, 140);
        g.drawString("you will pause the game and then select the desired action and", 25, 155);
        g.drawString("press enter to perform it. Good luck!", 25, 170);

        g.drawString("tap Enter to return to menu ...", 25, 270);*/
    }
}
