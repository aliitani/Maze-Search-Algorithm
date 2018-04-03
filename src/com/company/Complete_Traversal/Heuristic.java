package com.company.Complete_Traversal;

import com.company.Single_Target_Search.Points;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Heuristic {

    public int heuristic;

    public int dotsLeft;
    public int closestDot;
    public int farthestDot;

    public int closestPlusRest;

    public int twoDotDistance;
    public int xyRange;
    public int fancyHeuristic;
    public int fancyHeuristic2;

    public Heuristic(StatePacMan state) {
        setDotsLeftHeuristic(state);
        setClosestDotHeuristic(state);
        setFarthestDotHeuristic(state);
        setTwoDotDistanceHeuristic(state.dots);
        setXYRangeHeuristic(state);
        setFancyHeuristic(state);
        setFancyHeuristic2(state);

        closestPlusRest = closestDot + (dotsLeft - 1);
        heuristic = Math.max(closestPlusRest, farthestDot);
        heuristic = Math.max(heuristic, twoDotDistance);
        heuristic = Math.max(heuristic, xyRange);
        heuristic = Math.max(heuristic, fancyHeuristic);
        heuristic = Math.max(heuristic, fancyHeuristic2);
    }

    public void setFancyHeuristic(StatePacMan state){
        fancyHeuristic = 0;
        if (state.dots.size() == 0)
            return;

        LinkedList<Points> dots = new LinkedList<Points>(state.dots);
        ArrayList<Points> processedDots = new ArrayList<Points>();
        ArrayList<Points> columnOfDots = new ArrayList<Points>();

        Collections.sort(dots, new PointComparator());


        Points firstDot = dots.remove();
        processedDots.add(firstDot);
        int currColumn = firstDot.x;

        while (!dots.isEmpty() && dots.peek().x == currColumn){
            Points currentDot = dots.remove();
            processedDots.add(currentDot);
            int distance = manhattanDistance(firstDot, currentDot);
            fancyHeuristic = Math.max(fancyHeuristic, distance);
        }

        while (!dots.isEmpty()){
            while(currColumn != dots.peek().x){
                currColumn++;
            }
            while(!dots.isEmpty() && dots.peek().x == currColumn){
                Points currentDot = dots.remove();
                columnOfDots.add(currentDot);
            }
            fancyHeuristic += farthestDotDistance(processedDots, columnOfDots);
            processedDots.addAll(columnOfDots);
            columnOfDots.clear();
        }
    }

    public void setFancyHeuristic2(StatePacMan state) {
        fancyHeuristic2 = 0;
        if (state.dots.size() == 0)
            return;

        LinkedList<Points> dots = new LinkedList<Points>(state.dots);

        ArrayList<Points> processedDots = new ArrayList<Points>();
        ArrayList<Points> rowOfDots = new ArrayList<Points>();

        Collections.sort(dots, new PointComparator2());

        Points firstDot = dots.remove();
        processedDots.add(firstDot);
        int currRow = firstDot.y;

        while (!dots.isEmpty() && dots.peek().y == currRow){
            Points currentDot = dots.remove();
            processedDots.add(currentDot);
            int distance = manhattanDistance(firstDot, currentDot);
            fancyHeuristic2 = Math.max(fancyHeuristic2, distance);
        }
        while (!dots.isEmpty()){

            while(currRow != dots.peek().y){
                currRow++;
            }

            while(!dots.isEmpty() && dots.peek().y == currRow){
                Points currentDot = dots.remove();
                rowOfDots.add(currentDot);
            }

            fancyHeuristic2 += farthestDotDistance(processedDots, rowOfDots);
            processedDots.addAll(rowOfDots);
            rowOfDots.clear();
        }
    }

    private int farthestDotDistance(ArrayList<Points> processedDots, ArrayList<Points> columnOfDots){
        int maxDistance = 0;
        for (Points dot : columnOfDots){
            int currDistance = closestDotDistance(processedDots, dot);
            maxDistance = Math.max(maxDistance, currDistance);
        }
        return maxDistance;
    }

    private int closestDotDistance(ArrayList<Points> processedDots, Points currentDot){
        int minDistance = Integer.MAX_VALUE;
        for (Points p : processedDots){
            int currDistance = manhattanDistance(p, currentDot);
            minDistance = Math.min(minDistance, currDistance);
        }
        return minDistance;
    }

    public void setDotsLeftHeuristic(StatePacMan state){
        dotsLeft = state.dots.size();
    }

    public void setClosestDotHeuristic(StatePacMan state){
        closestDot = Integer.MAX_VALUE;
        for (Points point : state.dots) {
            int distanceToDot = manhattanDistance(point, state.pacmanLocation);
            closestDot = Math.min(closestDot, distanceToDot);
        }
        if (state.dots.isEmpty())
            closestDot = 0;
    }

    public void setFarthestDotHeuristic(StatePacMan state){
        farthestDot = 0;
        for (Points point: state.dots) {
            int distanceToDot = manhattanDistance(state.pacmanLocation, point);
            farthestDot = Math.max(farthestDot, distanceToDot);
        }
    }

    public void setTwoDotDistanceHeuristic(ArrayList<Points> dots){
        twoDotDistance = 0;
        int numDots = dots.size();
        for (int i = 0; i < numDots - 1; i++){
            Points p1 = dots.get(i);
            for (int j = 0; j < numDots; j++){
                Points p2 = dots.get(j);
                int currDistance = manhattanDistance(p1, p2);
                twoDotDistance = Math.max(twoDotDistance, currDistance);
            }
        }
    }

    public void setXYRangeHeuristic(StatePacMan state){
        if (state.dots.size() == 0){
            xyRange = 0;
            return;
        }
        if (state.dots.size() == 1){
            xyRange = manhattanDistance(state.pacmanLocation, state.dots.get(0));
            return;
        }
        int xMin = Integer.MAX_VALUE;
        int yMin = Integer.MAX_VALUE;
        int xMax = 0;
        int yMax = 0;

        for (Points p : state.dots){
            xMin = Math.min(xMin, p.x);
            yMin = Math.min(yMin, p.y);
            xMax = Math.max(xMax, p.x);
            yMax = Math.max(yMax, p.y);
        }
        xyRange = (xMax - xMin) + (yMax - yMin);
    }

    private int manhattanDistance(Points p1, Points p2){

        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

//    might not be needed.
    private int manhattanDistance(SimplePoint p1, SimplePoint p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}
