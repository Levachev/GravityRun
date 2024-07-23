package org.example.models.elements;

import org.example.models.Chunk;

import java.util.ArrayList;

public class ElementHorizontalWall implements Element{
    public ArrayList<Chunk> element;
    private int amount;
    private int currentPos;
    private int lengthOfElement;
    public ElementHorizontalWall(int amount) {
        lengthOfElement=21;
        currentPos=0;
        this.amount=amount;
        element = new ArrayList<>(lengthOfElement);
        Chunk chunk = new Chunk(amount);
        chunk.setBlock(amount/3);

        Chunk chunk1 = new Chunk(amount);
        chunk1.setBlock(amount*2/3);
        for (int i=0;i<lengthOfElement/3;i++) {
            element.add(chunk);
        }
        for (int i=lengthOfElement/3;i<lengthOfElement*2/3;i++) {
            element.add(chunk1);
        }
        for (int i=lengthOfElement*2/3;i<lengthOfElement;i++) {
            element.add(chunk);
        }
    }
    public Chunk getChunk(int ind){
        return element.get(ind);
    }
    public int getSize(){
        return element.size();
    }
}
