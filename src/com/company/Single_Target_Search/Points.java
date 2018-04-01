package com.company.Single_Target_Search;

import java.util.ArrayList;

public class Points {
    public int x, y;
    public PointsChecker pointType;

    public Points(int xCoord, int yCoord) {
        this.x = xCoord;
        this.y = yCoord;
        pointType = null;
    }

    public Points(int xCoord, int yCoord, PointsChecker type){
        x = xCoord;
        y = yCoord;
        this.pointType = type;
    }

    //    copy
    public Points(Points otherPoint){
        x = otherPoint.x;
        y = otherPoint.y;
        pointType = otherPoint.pointType;
    }

    public ArrayList<Points> getAdjacentPoints(TheMaze maze){
        ArrayList<Points> points = new ArrayList<Points>();

        /* Find up to 4 valid (not off the map) Points */
        if(maze.validPosition(new Points(x + 1, y))) {    //right
            points.add(maze.grid[y][x + 1]);
        }
        if(maze.validPosition(new Points(x - 1, y))) {
            //left
            points.add(maze.grid[y][x - 1]);
        }
        if(maze.validPosition(new Points(x, y + 1))) {    //up
            points.add(maze.grid[y + 1][x]);
        }
        if(maze.validPosition(new Points(x, y - 1))) {    //down
            points.add(maze.grid[y - 1][x]);
        }
        return points;
    }

    //    overrided the equals, hashcode and toString method for ease of use.

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Points)) {
            return false;
        }

        Points pos = (Points) obj;

        return (pos.x == x && pos.y == y);
    }

    @Override
    public int hashCode(){

        return 3 * x + 7 * y;
    }

    @Override
    public String toString(){

        if (pointType == PointsChecker.WALL) {
            return "#";
        } else if (pointType == PointsChecker.EMPTY) {
            return " ";
        } else if (pointType == PointsChecker.FOOD) {
            return ".";
        } else if (pointType == PointsChecker.START) {
            return "P";
        }

        return "";
    }
}
