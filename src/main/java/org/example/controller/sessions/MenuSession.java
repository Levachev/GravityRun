package org.example.controller.sessions;

import org.example.models.cursors.CursorOfMenu;
import org.example.models.options.MenuOptions;
import org.example.view.Menu;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuSession implements Session{
    public CursorOfMenu cursorOfMenu;
    private Menu menu;
    public boolean flagOfAction;
    private int wightPanel;
    private int heightPanel;
    private int wightOfWorld=520;
    private int heightOfWorld=360;
    public MenuOptions selectedMode;
    public MenuSession(int wightPanel, int heightPanel){
        this.wightPanel=wightPanel;
        this.heightPanel=heightPanel;
        flagOfAction=false;

        setNewMenu();
    }
    @Override
    public JPanel getPanel() {
        return menu;
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
        menu.repaint();
    }

    private void setNewMenu(){
        double myPixelHeight= (double) heightPanel /heightOfWorld;
        double myPixelWight= (double) wightPanel /wightOfWorld;

        flagOfAction=false;
        cursorOfMenu = new CursorOfMenu(wightOfWorld, heightOfWorld);

        menu = new Menu(cursorOfMenu, myPixelWight, myPixelHeight);
        menu.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    cursorOfMenu.up();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    cursorOfMenu.down();
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    selectedMode=cursorOfMenu.getCurrentField();
                    flagOfAction=true;
                }
            }
        });
    }
}
