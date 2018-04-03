package com.company.Complete_Traversal;

import com.company.Single_Target_Search.Points;
import com.company.Single_Target_Search.TheMaze;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AstarPacMan {

    public PriorityQueue<StatePacMan> priorityQueue;
    public HashSet<StateInfo> visited;
    public HashMap<StatePacMan, StatePacMan> predecessor;
    public int solutionDistance;
    public int nodesExpanded;

    public AstarPacMan(TheMaze theMaze) {
        priorityQueue = new PriorityQueue<StatePacMan>(1000, new StateComparator_Complete());
        visited = new HashSet<StateInfo>();
        predecessor = new HashMap<StatePacMan, StatePacMan>();
        nodesExpanded = 0;
        solutionDistance = 0;
    }

    public void findSolution(TheMaze maze) {
        StatePacMan initial = new StatePacMan(new Points(maze.startPoint), 0, maze.dots);
        priorityQueue.add(initial);
        visited.add(new StateInfo(initial));

        // Algorithm
        while (!priorityQueue.isEmpty()) {
            StatePacMan currentState = priorityQueue.remove();
            if( !visited.contains(new StateInfo(currentState))) {
                // bug here
                nodesExpanded++;
            }

            visited.add(new StateInfo(currentState));

            for( StatePacMan state: currentState.getAdjacentNonWallStates(maze)) {
                if ( !visited.contains(new StateInfo(state))) {
                    priorityQueue.add(state);
                    predecessor.put(state, currentState);

                    if(state.dots.size() == 0 ) {
                        solutionDistance = maze.calculateSolutionDistance(predecessor, state);
                        return;
                    }
                }
            }
        }
    }
}
