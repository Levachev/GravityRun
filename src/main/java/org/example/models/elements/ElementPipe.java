package org.example.models.elements;

import org.example.models.Chunk;

import java.util.ArrayList;

public class ElementPipe implements Element{
    public ArrayList<Chunk> element;
    private int amount;
    private int currentPos;
    private int lengthOfElement;
    public ElementPipe(int amount) {
        lengthOfElement=25;
        currentPos=0;
        this.amount=amount;
        element = new ArrayList<>(lengthOfElement);
        Chunk chunk1 = new Chunk(amount);
        chunk1.setBlock(amount/2-4);
        chunk1.setBlock(amount/2-3);
        chunk1.setBlock(amount/2+2);

        Chunk chunk2 = new Chunk(amount);
        chunk2.setBlock(amount/2-4);
        chunk2.setBlock(amount/2+1);
        chunk2.setBlock(amount/2+2);

        Chunk chunk3 = new Chunk(amount);
        chunk3.setBlock(amount/2-4);
        chunk3.setBlock(amount/2+2);

        element.add(chunk3);
        for(int i=0;i<lengthOfElement/6;i++){
            element.add(chunk1);
            element.add(chunk3);
            element.add(chunk3);
            element.add(chunk2);
            element.add(chunk3);
            element.add(chunk3);
        }
    }
    public Chunk getChunk(int ind){
        return element.get(ind);
    }
    public int getSize(){
        return element.size();
    }
}
