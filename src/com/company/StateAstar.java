package com.company;

import java.util.ArrayList;

public class StateAstar {
    public Points pacmanLocation;
    public int costSoFar;
    public Heuristic heuristic;

    public StateAstar(Points p, int cost, TheMaze maze) {
        pacmanLocation = p;
        costSoFar = cost;
        heuristic = new Heuristic(pacmanLocation, maze);
    }

    public ArrayList<StateAstar> getAdjacentStates(TheMaze theMaze) {
        ArrayList<StateAstar> states = new ArrayList<StateAstar>();
        for (Points p: pacmanLocation.getAdjacentPoints(theMaze)) {
            if (p.pointType != PointsChecker.WALL) {
                Points deepCopyPoint = new Points(p);
                states.add(new StateAstar(deepCopyPoint, costSoFar + 1, theMaze));
            }
        }
        return states;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof StateAstar))
            return false;
        StateAstar otherState = (StateAstar) obj;
        return pacmanLocation.equals(otherState.pacmanLocation) &&
                costSoFar == otherState.costSoFar &&
                heuristic.equals(otherState.heuristic);
    }

    @Override
    public int hashCode(){
        return (3 * pacmanLocation.x) + (7 * pacmanLocation.y) + (11 * costSoFar) + (13 * heuristic.distanceHeuristic);
    }
}
