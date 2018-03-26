package com.company;

import java.util.Comparator;

public class StateComparator implements Comparator<StateAstar> {

    @Override
    public int compare(StateAstar a1, StateAstar a2) {
        return (a1.heuristic.distanceHeuristic + a1.costSoFar) - (a2.heuristic.distanceHeuristic + a2.costSoFar);
    }

}
