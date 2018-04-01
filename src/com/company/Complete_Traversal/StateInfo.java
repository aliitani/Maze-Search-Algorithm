package com.company.Complete_Traversal;

import com.company.Single_Target_Search.Points;

import java.util.ArrayList;

public class StateInfo {
    Points pacmanLocation;
    ArrayList<Points> dots;

    public StateInfo(StatePacMan state) {
        pacmanLocation = new Points(state.pacmanLocation);
        dots = new ArrayList<Points>(state.dots);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof StateInfo)) {
            return false;
        }
        StateInfo otherState = (StateInfo) obj;
        return pacmanLocation.equals(otherState.pacmanLocation) &&
                dots.containsAll(otherState.dots) && otherState.dots.containsAll(dots);

    }

    @Override
    public int hashCode() {
        return (3 * pacmanLocation.x) + (5 * pacmanLocation.y);
    }
}
