package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class DFS {
    public Stack<Points> stack;
    public HashSet<Points> visited;
    public HashMap<Points, Points> predecessor;
    public int nodesExpanded, scoreDistance;

    public DFS() {
        stack = new Stack<Points>();
        visited = new HashSet<Points>();
        predecessor = new HashMap<Points, Points>();
        nodesExpanded = 0;
        scoreDistance = 0;
    }

    public void findSolution(TheMaze theMaze) {
        stack.add(theMaze.startPoint);
        visited.add(theMaze.startPoint);

        while(!stack.isEmpty()) {
            Points currentPoint = stack.pop();
            nodesExpanded++;

            for(Points point: currentPoint.getAdjacentPoints(theMaze)) {
                if((point.pointType == PointsChecker.EMPTY || point.pointType == PointsChecker.DOT) && !visited.contains(point)) {
                    stack.push(point);
                    visited.add(point);
                    predecessor.put(point, currentPoint);
                    if(point == theMaze.endPoint) {
                        scoreDistance = theMaze.putSolutionDotOnMaze(predecessor, point);
                        return;
                    }
                }
            }
        }
    }
}
