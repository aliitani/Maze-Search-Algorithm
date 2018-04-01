package com.company.Single_Target_Search;

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

                if((point.pointType == PointsChecker.EMPTY || point.pointType == PointsChecker.FOOD) && !visited.contains(point)) {

                    queue.add(point);
                    visited.add(point);
                    predecessor.put(point, currentPoint);

                    if(point == maze.endPoint) {

//                        reached the endpoint set the dots on maze and return
                        scoreDistance = maze.putSolutionDotOnMaze(predecessor, point);
                        return;
                    }
                }
            }
        }
    }
}
