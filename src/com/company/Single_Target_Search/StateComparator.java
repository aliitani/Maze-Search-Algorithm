package com.company.Single_Target_Search;

import java.util.Comparator;

public class StateComparator implements Comparator<StateAstar> {
    // comparator here
    @Override
    public int compare(StateAstar a1, StateAstar a2) {
        return (a1.heuristic.manhattanDistance + a1.costSoFar) - (a2.heuristic.manhattanDistance + a2.costSoFar);
    }

}
