package org.example.view;

import org.example.models.Chunk;
import org.example.models.World;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Math.abs;

public class PlayCanvas extends JPanel{
    private World world;
    private int flagOfRun;
    private double myPixelWight;
    private double myPixelHeight;

    public PlayCanvas(World world, double myPixelWight, double myPixelHeight) {
        flagOfRun = 0;
        this.world=world;
        this.myPixelWight=myPixelWight;
        this.myPixelHeight=myPixelHeight;
        this.setBackground(Color.PINK);
    }

    private void drawChunk(Graphics g, BufferedImage img, Chunk chunk, int coord){
        for(int i=0;i<world.maze.amount;i++) {
            if(chunk.getBlock(i)) {
                int x = (int)(coord*myPixelWight);
                int y = (int)(i*world.maze.height*myPixelHeight);
                int wight = (int)(world.maze.wight*myPixelWight);
                int height = (int)(world.maze.height*myPixelHeight);

                g.drawImage(img, x, y, wight, height, null);
            }
        }
    }

    private void drawQueue(Graphics g, BufferedImage img){
        for(int i=0;i<world.maze.queueOfChunks.size();i++){
            drawChunk(g, img, world.maze.queueOfChunks.get(i),world.maze.startCoord+i*world.maze.wight);
        }
    }

    public void paintComponent(@NotNull Graphics g) {
        super.paintComponent(g);

        BufferedImage imgChunk = null;
        try {
            imgChunk = ImageIO.read(getClass().getResource("/images/block.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage currentImgPlayer1;
        try {
            currentImgPlayer1 = ImageIO.read(getClass().getResource("/images/man.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage currentImgPlayer2;
        try {
            currentImgPlayer2 = ImageIO.read(getClass().getResource("/images/2man.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        drawQueue(g, imgChunk);
        g.drawImage(currentImgPlayer1, (int) (world.player1.getX()*myPixelWight), (int) (world.player1.getY()*myPixelHeight), (int) (world.player1.getWight()*myPixelWight), (int) (world.player1.getHeight()*myPixelHeight), null);
        if(world.player2!=null) {
            g.drawImage(currentImgPlayer2, (int) (world.player2.getX()*myPixelWight), (int) (world.player2.getY()*myPixelHeight), (int) (world.player2.getWight()*myPixelWight), (int) (world.player2.getHeight()*myPixelHeight), null);
        }
        if(world.player2==null) {
            AffineTransform affinetransform = new AffineTransform();
            FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
            Font font = new Font("TimesRoman", Font.PLAIN, (int) (abs(world.player1.height) * myPixelHeight));
            g.setFont(font);
            String message = "current score : " + world.player1.getScoringBank();
            g.setColor(Color.CYAN);
            g.fillRect((int) ((world.maze.mainWight) * myPixelWight) - (int) (font.getStringBounds(message, frc).getWidth()) - 5, 0, (int) (font.getStringBounds(message, frc).getWidth()), (int) (abs(world.player1.height) * myPixelHeight));
            g.setColor(Color.BLACK);
            g.drawString(message, (int) ((world.maze.mainWight) * myPixelWight) - (int) (font.getStringBounds(message, frc).getWidth()) - 5, (int) (abs(world.player1.height) * myPixelHeight));
        }
    }
}
