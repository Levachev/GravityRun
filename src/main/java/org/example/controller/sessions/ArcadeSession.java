package org.example.controller.sessions;

import org.example.models.Maze;
import org.example.models.Player;
import org.example.models.World;
import org.example.models.options.GameOptions;
import org.example.view.PlayCanvas;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ArcadeSession implements Session{
    private PlayCanvas canvas;
    private World world;
    private boolean flagOfAction;
    private int wightPanel;
    private int heightPanel;
    private int wightOfWorld=520;
    private int heightOfWorld=360;
    public GameOptions selectedAction;

    public ArcadeSession(int wightPanel, int heightPanel){
        this.wightPanel=wightPanel;
        this.heightPanel=heightPanel;
        setNewCanvas();
    }

    @Override
    public JPanel getPanel() {
        return canvas;
    }
    public void updateStateOfPlayer(){
        world.player1.move(world.maze);
    }
    public void updateStateOfMaze(){
        world.maze.moveStartCoord();
    }
    public void updateView(){
        canvas.repaint();
    }
    @Override
    public void updateSession(){
        updateStateOfPlayer();
        updateStateOfMaze();
        updateView();
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

        flagOfAction=false;

        Maze maze = new Maze(wight, height, wightOfWorld, amountHeight);

        Player player1 = new Player(wightOfWorld/2, heightOfWorld/2, wight, height, -1);

        world = new World(maze, player1, null);

        canvas = new PlayCanvas(world, myPixelWight, myPixelHeight);

        canvas.requestFocusInWindow();

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == KeyEvent.VK_Q && !world.player1.getFlagToFly()) {
                    world.player1.turnOver();
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
        flagOfAction=world.player1.flagOfGameOver;
        if(flagOfAction){
            selectedAction= GameOptions.GAME_OVER;
        }
    }

    @Override
    public int getMessage() {
        return world.player1.getScoringBank();
    }


}
