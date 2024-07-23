package org.example.controller.sessions;

import org.example.view.Help;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HelpSession implements Session{
    private Help help;
    public boolean flagOfAction;
    private int wightPanel;
    private int heightPanel;
    private int wightOfWorld=520;
    private int heightOfWorld=360;
    public HelpSession(int wightPanel, int heightPanel){
        this.wightPanel=wightPanel;
        this.heightPanel=heightPanel;
        flagOfAction=false;

        setNewMenu();
    }
    @Override
    public JPanel getPanel() {
        return help;
    }

    @Override
    public boolean getFlagOfAction() {
        return flagOfAction;
    }

    @Override
    public void reload(int message) {
        setNewMenu();
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
        help.repaint();
    }

    private void setNewMenu(){
        double myPixelHeight= (double) heightPanel /heightOfWorld;
        double myPixelWight= (double) wightPanel /wightOfWorld;
        flagOfAction=false;

        help = new Help(myPixelWight, myPixelHeight);
        help.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    flagOfAction=true;
                }
            }
        });
    }
}
