package org.example.models;

import java.util.ArrayList;
import java.util.Collections;

public class Chunk {
    private ArrayList<Boolean> chunk;
    private int amount;

    public Chunk(int amount){
        this.amount=amount;
        chunk = new ArrayList<>(Collections.nCopies(amount, false));
    }
    public void setBlock(int index){
            chunk.set(index, true);
    }

    public boolean getBlock(int index){
        return chunk.get(index);
    }
    public void clearChunk(int index){
        for(int i=0;i<chunk.size();i++){
            chunk.set(i, false);
        }
    }
}
