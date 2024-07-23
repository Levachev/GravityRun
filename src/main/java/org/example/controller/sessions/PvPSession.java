package org.example.controller.sessions;

import org.example.models.Maze;
import org.example.models.Player;
import org.example.models.World;
import org.example.models.options.GameOptions;
import org.example.view.PlayCanvas;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PvPSession implements Session{
    private PlayCanvas canvas;
    private World world;
    private int beginSpeed=2;
    private boolean flagOfAction;
    private int wightPanel;
    private int heightPanel;
    private int wightOfWorld=520;
    private int heightOfWorld=360;
    public GameOptions selectedAction;

    public PvPSession(int wightPanel, int heightPanel){
        this.wightPanel=wightPanel;
        this.heightPanel=heightPanel;
        flagOfAction=false;

        setNewCanvas();
    }

    @Override
    public JPanel getPanel() {
        return canvas;
    }

    @Override
    public boolean getFlagOfAction() {
        return flagOfAction;
    }

    @Override
    public void reload(int message) {
        setNewCanvas();
    }
    private void setNewCanvas(){
        int amountHeight=20;
        int amountWight=40;
        double myPixelHeight= (double) heightPanel /heightOfWorld;
        double myPixelWight= (double) wightPanel /wightOfWorld;
        int wight=wightOfWorld/amountWight;
        int height=heightOfWorld/amountHeight;
        //System.out.println(height);
        flagOfAction=false;

        Maze maze = new Maze(wight, height, wightOfWorld, amountHeight);

        Player player1 = new Player(wightOfWorld/2, heightOfWorld/2, wight, height, -1);
        Player player2 = new Player(wightOfWorld/2, heightOfWorld/2, wight, height,1);

        world = new World(maze, player1, player2);
        canvas = new PlayCanvas(world, myPixelWight, myPixelHeight);

        canvas.requestFocusInWindow();

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == KeyEvent.VK_Q && !player1.getFlagToFly()) {
                    player1.turnOver();
                }
                if (e.getKeyCode() == KeyEvent.VK_P && !player2.getFlagToFly()) {
                    player2.turnOver();
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    selectedAction= GameOptions.PAUSE;
                    flagOfAction=true;
                }
            }
        });
    }
    @Override
    public void updateFlagOfAction(){
        flagOfAction=world.player1.flagOfGameOver || world.player2.flagOfGameOver;
        if(flagOfAction){
            selectedAction= GameOptions.GAME_OVER;
        }
    }

    @Override
    public int getMessage() {
        if(world.player1.flagOfGameOver){
            return -1;
        } else{
            return -2;
        }
    }

    public void updateStateOfPlayers(){
        world.player1.move(world.maze);
        world.player2.move(world.maze);
    }
    public void updateStateOfMaze(){
        world.maze.moveStartCoord();
    }
    public void updateView() {
        canvas.repaint();
    }
    @Override
    public void updateSession() {
        updateStateOfPlayers();
        updateStateOfMaze();
        updateView();
    }
}
