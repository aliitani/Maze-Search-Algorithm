package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class BFS {
    public Queue<Points> queue;
    public HashSet<Points> visited;
    public HashMap<Points, Points> predecessor;
    public int nodesExpanded;
    public int scoreDistance;

    public BFS() {
        queue = new LinkedList<Points>();
        visited = new HashSet<Points>();
        predecessor = new HashMap<Points, Points>();
        nodesExpanded = 0;
        scoreDistance = 0;
    }

    public void findSolution(TheMaze maze) {
        queue.add(maze.startPoint);
        visited.add(maze.startPoint);

        while(!queue.isEmpty()) {
            Points currentPoint = queue.remove();
            nodesExpanded++;

            for(Points point: currentPoint.getAdjacentPoints(maze)) {
                if((point.pointType == PointsChecker.EMPTY || point.pointType == PointsChecker.DOT) && !visited.contains(point)) {
                    queue.add(point);
                    visited.add(point);
                    predecessor.put(point, currentPoint);
                    if(point == maze.endPoint) {
                        scoreDistance = maze.putSolutionDotOnMaze(predecessor, point);
                        return;
                    }
                }
            }
        }
    }
}
