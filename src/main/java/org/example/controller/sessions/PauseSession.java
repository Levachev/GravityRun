package org.example.controller.sessions;

import org.example.models.cursors.CursorOfPause;
import org.example.models.options.PauseOptions;
import org.example.view.Pause;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PauseSession implements Session{
    public CursorOfPause cursorOfPause;
    private Pause pause;
    public boolean flagOfAction;
    private int wightPanel;
    private int heightPanel;
    private int wightOfWorld=520;
    private int heightOfWorld=360;
    public PauseOptions selectedMode;
    public PauseSession(int wightPanel, int heightPanel){
        this.wightPanel=wightPanel;
        this.heightPanel=heightPanel;
        flagOfAction=false;

        setNewView(0);
    }
    @Override
    public JPanel getPanel() {
        return pause;
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
        pause.repaint();
    }

    private void setNewView(int message){
        double myPixelHeight= (double) heightPanel /heightOfWorld;
        double myPixelWight= (double) wightPanel /wightOfWorld;
        flagOfAction=false;
        cursorOfPause = new CursorOfPause(wightOfWorld, heightOfWorld);

        pause = new Pause(cursorOfPause, message, myPixelWight, myPixelHeight);
        pause.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    cursorOfPause.up();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    cursorOfPause.down();
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    selectedMode=cursorOfPause.getCurrentField();
                    flagOfAction=true;
                }
            }
        });
    }
}
