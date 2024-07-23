package org.example.models.elements;

import org.example.models.Chunk;

import java.util.ArrayList;

public class ElementOnlyDownTrap implements Element{
    public ArrayList<Chunk> element;
    private int amount;
    private int currentPos;
    private int lengthOfElement;
    public ElementOnlyDownTrap(int amount) {
        lengthOfElement=15;
        currentPos=0;
        this.amount=amount;
        element = new ArrayList<>(lengthOfElement);
        Chunk chunk1 = new Chunk(amount);
        chunk1.setBlock(0);
        chunk1.setBlock(amount-1);

        Chunk chunk2 = new Chunk(amount);
        chunk2.setBlock(0);
        chunk2.setBlock(amount/2);
        chunk2.setBlock(amount-1);

        Chunk chunk3 = new Chunk(amount);
        chunk3.setBlock(0);
        for(int i=amount/2;i<amount-1;i++){
            chunk3.setBlock(i);
        }
        chunk3.setBlock(amount-1);

        for(int i=0;i<lengthOfElement;i++){
            element.add(chunk1);
        }
        element.set(lengthOfElement-1, chunk3);
        for(int i=lengthOfElement*2/3;i<lengthOfElement-1;i++){
            element.set(i, chunk2);
        }
    }
    public Chunk getChunk(int ind){
        return element.get(ind);
    }
    public int getSize(){
        return element.size();
    }
}
