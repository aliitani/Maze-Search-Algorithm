package com.company.Single_Target_Search;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

// astar as mentioned in the paper that it is the algoirhtm that is capable of very closely approximating the true solution to the traveling salesman problem.
public class AStar {
    public Queue<StateAstar> queue;
    public HashSet<Points> visited;
    public HashMap<StateAstar, StateAstar> predecessor;
    public int nodesExpanded;
    public int scoreDistance;
    private TheMaze maze = null;

    public AStar(TheMaze theMaze) {
        queue = new PriorityQueue<StateAstar>(500, new StateComparator());
        visited = new HashSet<Points>();
        predecessor = new HashMap<StateAstar, StateAstar>();
        nodesExpanded = 0;
        scoreDistance = 0;
        maze = theMaze;
    }

    public void findSolution() {
        StateAstar stateAstar = new StateAstar(new Points(maze.startPoint), 0, maze);
        queue.add(stateAstar);
        visited.add(stateAstar.pacmanLocation);

        // The A-Star Algorithm implemented with help  https://www.geeksforgeeks.org/a-search-algorithm/

        while (!queue.isEmpty()) {
            StateAstar currentState = queue.remove();

            if(!visited.contains(currentState.pacmanLocation)) {

                nodesExpanded++;
            }

            visited.add(currentState.pacmanLocation);

            for (StateAstar state: currentState.getAdjacentStates(maze)) {

                if (!visited.contains(state.pacmanLocation)) {

                    queue.add(state);
                    predecessor.put(state, currentState);

                    if (state.pacmanLocation.equals(maze.endPoint)) {

                        scoreDistance = maze.putSolutionDotOnMaze(predecessor, state);
                        return;
                    }
                }
            }
        }
    }


}

