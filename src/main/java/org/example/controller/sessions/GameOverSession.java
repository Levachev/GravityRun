package org.example.controller.sessions;

import org.example.models.cursors.CursorOfGameOver;
import org.example.models.options.GameOverOptions;
import org.example.view.GameOver;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameOverSession implements Session{
    public CursorOfGameOver cursorOfGameOver;
    private GameOver gameOver;
    public boolean flagOfAction;
    private int wightPanel;
    private int heightPanel;
    private int wightOfWorld=520;
    private int heightOfWorld=360;

    public GameOverOptions selectedMode;
    public GameOverSession(int wightPanel, int heightPanel){
        this.wightPanel=wightPanel;
        this.heightPanel=heightPanel;
        flagOfAction=false;
        setNewView(0);
    }
    @Override
    public JPanel getPanel() {
        return gameOver;
    }

    @Override
    public boolean getFlagOfAction() {
        return flagOfAction;
    }

    @Override
    public void reload(int message) {
        setNewView(message);
    }

    @Override
    public void updateFlagOfAction() {
    }

    @Override
    public int getMessage() {
        return 0;
    }

    @Override
    public void updateSession() {
        gameOver.repaint();
    }

    private void setNewView(int message){
        double myPixelHeight= (double) heightPanel /heightOfWorld;
        double myPixelWight= (double) wightPanel /wightOfWorld;
        flagOfAction=false;
        cursorOfGameOver = new CursorOfGameOver(wightOfWorld, heightOfWorld);

        gameOver = new GameOver(cursorOfGameOver, message, myPixelWight, myPixelHeight);
        gameOver.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    cursorOfGameOver.up();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    cursorOfGameOver.down();
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    selectedMode=cursorOfGameOver.getCurrentField();
                    flagOfAction=true;
                }
            }
        });
    }
}
