package org.example.models.elements;

import org.example.models.Chunk;

import java.util.ArrayList;

public class ElementVerticalWall implements Element{
    public ArrayList<Chunk> element;
    private int amount;
    private int currentPos;
    private int lengthOfElement;
    public ElementVerticalWall(int amount) {
        lengthOfElement=21;
        currentPos=0;
        this.amount=amount;
        element = new ArrayList<>(lengthOfElement);
        Chunk chunk = new Chunk(amount);
        chunk.setBlock(0);
        chunk.setBlock(amount-1);

        Chunk chunk1 = new Chunk(amount);
        for(int i=0;i<amount/2;i++) {
            chunk1.setBlock(i);
        }
        chunk1.setBlock(amount-1);

        Chunk chunk2 = new Chunk(amount);
        for(int i=amount/2;i<amount;i++) {
            chunk2.setBlock(i);
        }
        chunk2.setBlock(0);

        for(int i=0;i<lengthOfElement;i++){
            element.add(chunk);
        }
        element.set(lengthOfElement/3, chunk1);
        element.set(lengthOfElement*2/3, chunk2);
    }
    public Chunk getChunk(int ind){
        return element.get(ind);
    }
    public int getSize(){
        return element.size();
    }
}
