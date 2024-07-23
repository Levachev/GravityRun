package org.example.models.cursors;

import org.example.models.options.GameOverOptions;

import java.util.HashMap;
import java.util.Map;

public class CursorOfGameOver implements Cursor{
    private int X;
    private int Y;
    private int wight;
    private int height;
    public int panelWight;
    public int panelHeight;
    public int amountOfFields;
    private Map<Integer, GameOverOptions> fieldsCoordMap;
    public CursorOfGameOver(int panelWight, int panelHeight){
        this.panelWight=panelWight;
        this.panelHeight=panelHeight;
        fieldsCoordMap = new HashMap<>();
        amountOfFields= GameOverOptions.values().length;
        for(int i=0;i<amountOfFields;i++){
            fieldsCoordMap.put(panelHeight/amountOfFields*i, GameOverOptions.values()[i]);
        }
        X=panelWight/2;
        Y=0;
        wight=50;
        height=50;
    }
    private int getCurrentIndex(){
        return Y/(panelHeight/amountOfFields);
    }
    public void up(){
        int index=getCurrentIndex();
        index=(index-1+amountOfFields)%amountOfFields;
        Y=panelHeight/amountOfFields*index;
    }
    public void down(){
        int index=getCurrentIndex();
        index=(index+1)%amountOfFields;
        Y=panelHeight/amountOfFields*index;
    }
    public GameOverOptions getCurrentField(){
        return fieldsCoordMap.get(Y);
    }
    public int getX(){
        return X;
    }
    public int getY(){
        return Y;
    }
    public int getWight(){
        return wight;
    }
    public int getHeight(){
        return height;
    }
}
