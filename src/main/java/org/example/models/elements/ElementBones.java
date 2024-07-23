package org.example.models.elements;

import org.example.models.Chunk;

import java.util.ArrayList;

public class ElementBones implements Element{
    public ArrayList<Chunk> element;
    private int amount;
    private int currentPos;
    private int lengthOfElement;
    public ElementBones(int amount) {
        lengthOfElement=18;
        currentPos=0;
        this.amount=amount;
        element = new ArrayList<>(lengthOfElement);

        Chunk chunk1 = new Chunk(amount);
        chunk1.setBlock(amount*2/3+1);

        Chunk chunk2 = new Chunk(amount);
        chunk2.setBlock(amount/3-1);
        chunk2.setBlock(amount/3);
        chunk2.setBlock(amount*2/3+1);
        chunk2.setBlock(amount*2/3);

        Chunk chunk3 = new Chunk(amount);
        chunk3.setBlock(amount/3-1);

        for(int i=0;i<lengthOfElement/3;i++){
            element.add(chunk1);
        }
        for(int i=lengthOfElement/3;i<lengthOfElement*2/3;i++){
            element.add(chunk3);
        }
        for(int i=lengthOfElement*2/3;i<lengthOfElement;i++){
            element.add(chunk1);
        }
        element.set(0, chunk2);
        element.set(lengthOfElement/3, chunk2);
        element.set(lengthOfElement*2/3, chunk2);
        element.set(lengthOfElement-1, chunk2);
    }
    public Chunk getChunk(int ind){
        return element.get(ind);
    }
    public int getSize(){
        return element.size();
    }
}
