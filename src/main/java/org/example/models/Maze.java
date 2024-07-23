package org.example.models;

import org.example.models.elements.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import static java.lang.Math.abs;

public class Maze {
    public int startCoord;
    public int mainWight;
    public int wight;
    public int height;
    public int speed;
    public int amount;
    public Chunk currentChunk;
    public Chunk middleChunkPrev;
    public Chunk middleChunkNext;
    public LinkedList<Chunk> queueOfChunks;
    private Element endElement;
    private int indexOfEndElement;
    private ArrayList<Element> poolOfElements;
    private int currentElementNumber;

    public Maze(int wight, int height, int mainWight, int amount){
        startCoord=0;
        this.wight=wight;
        this.height=height;
        this.mainWight=mainWight;
        this.amount=amount;
        currentChunk=null;
        middleChunkPrev=null;
        middleChunkNext=null;
        speed=1;
        queueOfChunks = new LinkedList<>();
        poolOfElements = new ArrayList<>();
        poolOfElements.add(new ElementVerticalWall(amount));
        poolOfElements.add(new ElementStairs(amount));
        poolOfElements.add(new ElementHorizontalWall(amount));
        poolOfElements.add(new ElementPipe(amount));
        poolOfElements.add(new ElementOnlyUpTrap(amount));
        poolOfElements.add(new ElementOnlyDownTrap(amount));
        poolOfElements.add(new ElementBones(amount));
        poolOfElements.add(new ElementPit(amount));
        poolOfElements.add(new ElementSteps(amount));
        poolOfElements.add(new ElementStart(amount));

        currentElementNumber=poolOfElements.size()-1;
        createQueue();
    }
    public void moveStartCoord() {
        startCoord = (startCoord - speed);
        if(abs(startCoord)>=wight)
        {
            startCoord=startCoord%wight;
            updateQueue();
        }
    }

    public int getSpeed() {
        return speed;
    }

    private void getNumberOfNextElement(int exceptionNumber){
        Random rn = new Random();
        int randIndex=rn.nextInt(poolOfElements.size()-1);
        if(randIndex==exceptionNumber){
            randIndex=(randIndex+1)%(poolOfElements.size()-1);
        }
        currentElementNumber=randIndex;
    }

    private Element getElement(){
        Element tmp=poolOfElements.get(currentElementNumber);
        getNumberOfNextElement(currentElementNumber);
        return tmp;
    }
    private void reload(){
        indexOfEndElement=(indexOfEndElement+1)%endElement.getSize();
        if(indexOfEndElement==0){
            endElement=null;
        }
    }
    private void createQueue(){
        while(true){
            Element tmpElement = getElement();
            endElement = tmpElement;
            for(int i=0;i<tmpElement.getSize();i++){
                queueOfChunks.offer(tmpElement.getChunk(i));
                indexOfEndElement=i;
                if(queueOfChunks.size()==mainWight/wight+2){
                    reload();
                    return;
                }
            }
        }
    }
    private void updateQueue(){
        queueOfChunks.poll();
        if(endElement==null){
            endElement=getElement();
        }
        queueOfChunks.offer(endElement.getChunk(indexOfEndElement));
        reload();
    }


}
