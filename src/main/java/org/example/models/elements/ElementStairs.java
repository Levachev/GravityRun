package org.example.models.elements;

import org.example.models.Chunk;

import java.util.ArrayList;

public class ElementStairs implements Element{
    public ArrayList<Chunk> element;
    private int amount;
    private int currentPos;
    private int lengthOfElement;
    public ElementStairs(int amount) {
        lengthOfElement=12;
        currentPos=0;
        this.amount=amount;
        element = new ArrayList<>(lengthOfElement);
        Chunk chunk = new Chunk(amount);
        chunk.setBlock(0);
        chunk.setBlock(amount-1);
        for(int i=0;i<lengthOfElement/2;i++){
            Chunk chunk1 = new Chunk(amount);
            for(int j=0;j<i+1;j++) {
                chunk1.setBlock((j));
                chunk1.setBlock((amount - 1 - j));
            }
            element.add(chunk1);
        }
        for(int i=lengthOfElement/2;i<lengthOfElement;i++){
            Chunk chunk1 = new Chunk(amount);
            for(int j=lengthOfElement-i;j>=0;j--) {
                chunk1.setBlock((j));
                chunk1.setBlock((amount - 1 - j));
            }
            element.add(chunk1);
        }
        Chunk chunk2 = new Chunk(amount);

    }
    public Chunk getChunk(int ind){
        return element.get(ind);
    }
    public int getSize(){
        return element.size();
    }
}
