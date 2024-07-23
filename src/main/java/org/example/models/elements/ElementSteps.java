package org.example.models.elements;

import org.example.models.Chunk;

import java.util.ArrayList;

public class ElementSteps implements Element{
    public ArrayList<Chunk> element;
    private int amount;
    private int currentPos;
    private int lengthOfElement;
    public ElementSteps(int amount) {
        lengthOfElement=23;
        currentPos=0;
        this.amount=amount;
        element = new ArrayList<>(lengthOfElement);

        Chunk chunk1 = new Chunk(amount);
        chunk1.setBlock(amount*2/3+4);

        Chunk chunk2 = new Chunk(amount);
        chunk2.setBlock(amount*2/3+5);

        Chunk chunk3 = new Chunk(amount);
        chunk3.setBlock(amount/3-3);

        Chunk chunk4 = new Chunk(amount);
        chunk4.setBlock(amount/3-4);

        Chunk chunk5 = new Chunk(amount);
        chunk5.setBlock(amount*2/3+4);
        chunk5.setBlock(amount/3-4);

        Chunk chunk6 = new Chunk(amount);
        chunk6.setBlock(amount*2/3+4);
        chunk6.setBlock(amount*2/3+5);
        chunk6.setBlock(amount/3-4);
        chunk6.setBlock(amount/3-5);


        for(int i=0;i<(lengthOfElement-3)/4;i++){
            element.add(chunk1);
        }
        for(int i=(lengthOfElement-3)/4;i<(lengthOfElement-3)*2/4;i++){
            element.add(chunk2);
        }
        for(int i=(lengthOfElement-3)*2/4;i<(lengthOfElement-3)*3/4;i++){
            element.add(chunk3);
        }
        for(int i=(lengthOfElement-3)*3/4;i<(lengthOfElement-3);i++){
            element.add(chunk4);
        }
        for(int i=(lengthOfElement-3);i<lengthOfElement;i++){
            element.add(chunk5);
        }
        element.set(0, chunk6);
        element.set(1, chunk6);
    }
    public Chunk getChunk(int ind){
        return element.get(ind);
    }
    public int getSize(){
        return element.size();
    }
}
