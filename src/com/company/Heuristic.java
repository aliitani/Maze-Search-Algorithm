package com.company;

public class Heuristic {
    public int manhattanDistance;

    public Heuristic(Points point, TheMaze theMaze){
        manhattanDistance = Math.abs(point.x - theMaze.endPoint.x) + Math.abs(point.y - theMaze.endPoint.y);
    }
}
