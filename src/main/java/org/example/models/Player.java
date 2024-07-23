package org.example.models;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class Player {
    private int x;
    private int y;
    public int wight;
    public int height;
    private int orient;
    private boolean flagIsFly;
    private int speed;
    private int flySpeed;
    private Chunk underPrev;
    private Chunk underCurrent;
    private int oppositeUpNumber;
    private int oppositeDownNumber;
    private boolean flagOfNewSpeed;
    public boolean flagOfGameOver;
    private boolean flagOfFall;
    private int scoringBank;
    private int scoringBankByPixels;


    public Player(int x, int y, int wight, int height, int orient){
        this.x=x;
        this.y=y;
        flagOfGameOver=false;
        this.wight=wight;
        this.height=height;
        this.orient=orient;
        flagOfNewSpeed=false;
        if(orient==-1){
            this.height=-height;
            this.y--;
        }
        flagOfFall=false;
        scoringBank=0;
        scoringBankByPixels=0;
        speedUp();
        flySpeed=height/2;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getWight() {
        return wight;
    }

    public int getHeight() {
        return height;
    }

    public void turnOver(){
        orient*=-1;
        height=-1*height;
        y=y+orient;
        y=y-orient*(abs(height));
    }
    public int getScoringBank(){
        return scoringBank;
    }
    public void move(Maze maze){
        getUnderChunks(maze);
        if(!(isStandOnChunk(underPrev, maze) || isStandOnChunk(underCurrent, maze))){
            int len1=getLengthToNearestBlock(underPrev, maze);
            int len2=getLengthToNearestBlock(underCurrent, maze);
            if(len1>=flySpeed && len2>=flySpeed){
                y += orient*flySpeed;
            } else{
                y += orient*(min(len1, len2));
            }
            flagIsFly=true;
        } else{
            flagIsFly=false;
        }
        if(isTouched(maze)){
            speed=0;
        } else{
            if(!flagOfNewSpeed) {
                speedUp();
            } else{
                flagOfNewSpeed=false;
            }
        }

        x=(x+speed- maze.getSpeed());
        if(x<0 || flagOfFall){
            flagOfGameOver=true;
        }
        scoringBankByPixels=(scoringBankByPixels+speed);
        if(scoringBankByPixels>wight){
            scoringBank++;
            scoringBankByPixels=scoringBankByPixels%wight;
            speedUp();
            maze.speed=speed;
        }
    }
    private void speedUp(){
        speed=(scoringBank/200+2);
    }
    public boolean getFlagToFly(){
        return flagIsFly;
    }

    public boolean isTouched(Maze maze){
        getNumberOfOppositeBlocks();
        for(int i = 0; i< maze.queueOfChunks.size(); i++){
            if (x== maze.startCoord+i*wight){
                if(maze.queueOfChunks.get(i+1).getBlock(oppositeUpNumber) || maze.queueOfChunks.get(i+1).getBlock(oppositeDownNumber)){

                    return true;
                }
            }
            else if (x> maze.startCoord+i*wight && x< maze.startCoord+i*wight+wight){
                if(maze.startCoord+(i+1)*wight-x<speed) {
                    if (maze.queueOfChunks.get(i + 2).getBlock(oppositeUpNumber) || maze.queueOfChunks.get(i + 2).getBlock(oppositeDownNumber)) {
                        speed= maze.startCoord+(i+1)*wight-x;
                        flagOfNewSpeed=true;
                        return false;
                    }
                }
            }
        }
       return false;
    }

    public void getNumberOfOppositeBlocks(){
        if(orient==1){
            if(y%height==0){
                oppositeUpNumber=y/height;
                oppositeDownNumber=oppositeUpNumber;
            } else{
                oppositeUpNumber=(y-y%height)/height;
                oppositeDownNumber=oppositeUpNumber+1;
            }
        } else{
            int newY=(y-abs(height)-1);
            if(newY%abs(height)==0){
                oppositeUpNumber=newY/abs(height);
                oppositeDownNumber=oppositeUpNumber;
            } else{
                oppositeUpNumber=(newY-newY%abs(height))/abs(height);
                oppositeDownNumber=oppositeUpNumber+1;
            }
        }
    }

    public void getUnderChunks(Maze maze){
        for(int i = 0; i< maze.queueOfChunks.size(); i++){
            if (x== maze.startCoord+i*wight){
                underPrev=null;
                underCurrent= maze.queueOfChunks.get(i);
                return;
            }
            else if (x> maze.startCoord+i*wight && x< maze.startCoord+i*wight+wight){
                underPrev= maze.queueOfChunks.get(i);
                underCurrent= maze.queueOfChunks.get(i+1);
                return;
            }
        }
    }

    private int getLengthToNearestBlock(Chunk chunk, Maze maze){
        if(chunk==null){
            return 100000;
        }

        int length=0;
        if(orient==1){
            if(y%height==0){
                int numberOfBlock=y/height+1;
                while(numberOfBlock< maze.amount && !chunk.getBlock(numberOfBlock)){
                    numberOfBlock++;
                    length+=height;
                }
            } else{
                length+=(height-y%height);
                int numberOfBlock=(y-y%height)/height+2;
                while(numberOfBlock< maze.amount && !chunk.getBlock(numberOfBlock)){
                    numberOfBlock++;
                    length+=height;
                }
            }
        } else{
            int newY=(y-abs(height)-1);
            if(newY%abs(height)==0){
                int numberOfBlock=newY/abs(height)-1;
                while(numberOfBlock>=0 && !chunk.getBlock(numberOfBlock)){
                    numberOfBlock--;
                    length+=abs(height);
                }
            } else{

                length+=(newY%abs(height));
                int numberOfBlock=(newY - (newY%abs(height)))/abs(height)-1;
                while(numberOfBlock>=0 && !chunk.getBlock(numberOfBlock)){
                    numberOfBlock--;
                    length+=abs(height);
                }
            }
        }
        return length;
    }

    public boolean isStandOnChunk(Chunk chunk, Maze maze){

        if(chunk==null){
            return false;
        }

        if(orient==1){
            if(y%height==0){
                int numberOfBlock=y/height+1;
                if(numberOfBlock>= maze.amount){
                    flagOfFall=true;
                    return true;
                }
                return chunk.getBlock(numberOfBlock);
            } else{
                return false;
            }
        } else{
            int newY=(y-abs(height)-1);
            if(newY%abs(height)==0){
                int numberOfBlock=newY/abs(height)-1;
                if(numberOfBlock<0){
                    flagOfGameOver=true;
                    return true;
                }
                return chunk.getBlock(numberOfBlock);
            } else{
                return false;
            }
        }
    }
}
