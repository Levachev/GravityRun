package org.example.models.elements;

import org.example.models.Chunk;

import java.util.ArrayList;

public class ElementPit implements Element{
    public ArrayList<Chunk> element;
    private int amount;
    private int currentPos;
    private int lengthOfElement;
    public ElementPit(int amount) {
        lengthOfElement=18;
        currentPos=0;
        this.amount=amount;
        element = new ArrayList<>(lengthOfElement);

        Chunk chunk1 = new Chunk(amount);
        chunk1.setBlock(amount*2/3+5);

        Chunk chunk2 = new Chunk(amount);
        chunk2.setBlock(amount/3-1);
        chunk2.setBlock(amount/3-2);

        Chunk chunk3 = new Chunk(amount);
        chunk3.setBlock(amount/3-3);

        Chunk chunk4 = new Chunk(amount);
        chunk1.setBlock(amount*2/3+5);
        chunk4.setBlock(amount/3-1);
        chunk4.setBlock(amount/3-2);

        for(int i=0;i<lengthOfElement/3;i++){
            element.add(chunk1);
        }

        for(int i=lengthOfElement/3;i<lengthOfElement*4/9;i++){
            element.add(chunk2);
        }
        for(int i=lengthOfElement*4/9;i<lengthOfElement*5/9;i++){
            element.add(chunk3);
        }
        for(int i=lengthOfElement*5/9;i<lengthOfElement*2/3;i++){
            element.add(chunk2);
        }

        for(int i=lengthOfElement*2/3;i<lengthOfElement;i++){
            element.add(chunk1);
        }
        element.set(7, chunk4);
        element.set(10, chunk4);
    }
    public Chunk getChunk(int ind){
        return element.get(ind);
    }
    public int getSize(){
        return element.size();
    }
}
