package org.example.models;

public class World {
    public Maze maze;
    public Player player1;
    public Player player2;
    public boolean flagOfPause;
    public World(Maze maze, Player player1, Player player2){
        this.maze=maze;
        this.player1=player1;
        this.player2=player2;
        flagOfPause=false;
    }
}
