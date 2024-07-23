package org.example.models.elements;

import org.example.models.Chunk;

public interface Element {
    public Chunk getChunk(int ind);
    public int getSize();
}
