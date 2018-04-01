package com.company.Complete_Traversal;

import java.util.Comparator;

public class StateComparator_Complete implements Comparator<StatePacMan> {

    @Override
    public int compare(StatePacMan p1, StatePacMan p2) {
        return (p1.heuristic.heuristic + p1.costSoFar) - (p2.heuristic.heuristic + p2.heuristic.heuristic);
    }
}
