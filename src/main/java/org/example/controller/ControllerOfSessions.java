package org.example.controller;

import org.example.controller.sessions.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerOfSessions implements ActionListener {
    private Timer timer;
    private JFrame jFrame;
    private Session currentSession;
    private Session prevSession;
    private MenuSession menuSession;
    private ArcadeSession arcadeSession;
    private PvPSession pvPSession;
    private GameOverSession gameOverSession;
    private PauseSession pauseSession;
    private HelpSession helpSession;
    public ControllerOfSessions(JFrame jFrame, int wightPanel, int heightPanel){
        this.jFrame=jFrame;
        menuSession = new MenuSession(wightPanel, heightPanel);
        arcadeSession = new ArcadeSession(wightPanel, heightPanel);
        pvPSession = new PvPSession(wightPanel, heightPanel);
        gameOverSession = new GameOverSession(wightPanel, heightPanel);
        pauseSession = new PauseSession(wightPanel, heightPanel);
        helpSession = new HelpSession(wightPanel, heightPanel);

        currentSession=menuSession;
        addToFrame();
        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(currentSession.getFlagOfAction()){
            switchSession();
        }
        currentSession.updateFlagOfAction();
        currentSession.updateSession();
        //currentSession.getPanel().repaint();
    }
    private void switchSession(){
        Session tmp = currentSession;
        if(currentSession.getClass()==ArcadeSession.class){
            switch (arcadeSession.selectedAction) {
                case GAME_OVER:
                    gameOverSession.reload(currentSession.getMessage());
                    currentSession=gameOverSession;
                    break;
                case PAUSE:
                    pauseSession.reload(currentSession.getMessage());
                    currentSession=pauseSession;
                    break;
            }
        } else if(currentSession.getClass()==PvPSession.class){
            switch (pvPSession.selectedAction) {
                case GAME_OVER:
                    gameOverSession.reload(currentSession.getMessage());
                    currentSession=gameOverSession;
                    break;
                case PAUSE:
                    pauseSession.reload(currentSession.getMessage());
                    currentSession=pauseSession;
                    break;
            }
        } else if(currentSession.getClass()==GameOverSession.class) {
            switch (gameOverSession.selectedMode) {
                case RESTART:
                    prevSession.reload(currentSession.getMessage());
                    currentSession=prevSession;
                    break;
                case EXIT:
                    menuSession.reload(currentSession.getMessage());
                    currentSession=menuSession;
                    break;
            }
        } else if(currentSession.getClass()==PauseSession.class) {
            switch (pauseSession.selectedMode) {
                case RESUME:
                    currentSession=prevSession;
                    break;
                case RESTART:
                    prevSession.reload(currentSession.getMessage());
                    currentSession=prevSession;
                    break;
                case EXIT:
                    menuSession.reload(currentSession.getMessage());
                    currentSession=menuSession;
                    break;
            }
        } else if(currentSession.getClass()==HelpSession.class) {
            prevSession.reload(currentSession.getMessage());
            currentSession=menuSession;
        } else{
            switch (menuSession.selectedMode) {
                case SINGLE:
                    arcadeSession.reload(currentSession.getMessage());
                    currentSession=arcadeSession;
                    break;
                case PVP:
                    pvPSession.reload(currentSession.getMessage());
                    currentSession=pvPSession;
                    break;
                case HELP:
                    helpSession.reload(currentSession.getMessage());
                    currentSession=helpSession;
                    break;
                case EXIT:
                    System.exit(0);
                    break;
            }
        }
        prevSession=tmp;
        addToFrame();
    }
    private void addToFrame(){
        jFrame.add(currentSession.getPanel());
        jFrame.revalidate();
        currentSession.getPanel().requestFocusInWindow();
    }
}
