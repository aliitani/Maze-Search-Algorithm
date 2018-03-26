package com.company;

public class Heuristic {
    public int distanceHeuristic;

    public Heuristic(Points point, TheMaze theMaze){
        distanceHeuristic = Math.abs(point.x - theMaze.endPoint.x) + Math.abs(point.y - theMaze.endPoint.y);
    }
}
