package org.example.models.elements;

import org.example.models.Chunk;

import java.util.ArrayList;

public class ElementStart implements Element{
    public ArrayList<Chunk> element;
    private int amount;
    private int currentPos;
    private int lengthOfElement;
    public ElementStart(int amount) {
        lengthOfElement=50;
        currentPos=0;
        this.amount=amount;
        element = new ArrayList<>(lengthOfElement);

        Chunk chunk = new Chunk(amount);
        chunk.setBlock(0);
        chunk.setBlock(amount-1);

        for(int i=0;i<lengthOfElement;i++){
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
