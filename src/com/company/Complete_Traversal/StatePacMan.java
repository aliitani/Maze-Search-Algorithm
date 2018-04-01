package com.company.Complete_Traversal;

import com.company.Single_Target_Search.Points;
import com.company.Single_Target_Search.PointsChecker;
import com.company.Single_Target_Search.TheMaze;

import java.util.ArrayList;

public class StatePacMan {
    public Points pacmanLocation;
    public ArrayList<Points> dots;
    public int costSoFar;
    public Heuristic heuristic;

    public StatePacMan(Points pacmanLocation, int costSoFar, ArrayList<Points> dots) {
        this.pacmanLocation = pacmanLocation;
        this.dots = dots;
        this.costSoFar = costSoFar;
        heuristic = new Heuristic(this);
    }


    public ArrayList<StatePacMan> getAdjacentNonWallStates(TheMaze maze) {
        ArrayList<StatePacMan> states = new ArrayList<>();

        for(Points p : pacmanLocation.getAdjacentPoints(maze)) {
            if ( p.pointType != PointsChecker.WALL) {
                Points deepCopyPoint = new Points(p);
                ArrayList<Points> deepCopyDots = new ArrayList<>(dots);
                if(deepCopyDots.contains(deepCopyPoint)) {
                    deepCopyDots.remove(deepCopyPoint);
                }
                StatePacMan tempState = new StatePacMan(deepCopyPoint, costSoFar + 1, deepCopyDots);

                states.add(tempState);
            }
        }
        return states;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof StatePacMan)) {
            return false;
        }
        StatePacMan otherState = (StatePacMan) obj;
        return pacmanLocation.equals(otherState.pacmanLocation) && dots.containsAll(otherState.dots) &&
                otherState.dots.containsAll(dots) && costSoFar == otherState.costSoFar &&
                heuristic.equals(otherState.heuristic);
    }

    @Override
    public int hashCode() {
        return (3 * pacmanLocation.x) + (7 * pacmanLocation.y) + (11 * costSoFar);
    }
}
