package org.example.models.cursors;

import org.example.models.options.MenuOptions;
import org.example.models.options.Options;

public interface Cursor {
    public void up();
    public void down();
    public Options getCurrentField();
    public int getX();
    public int getY();
    public int getWight();
    public int getHeight();
}
