package org.example.controller.sessions;

import javax.swing.*;

public interface Session {
    JPanel getPanel();
    boolean getFlagOfAction();
    void reload(int message);
    void updateFlagOfAction();
    int getMessage();
    void updateSession();

}
