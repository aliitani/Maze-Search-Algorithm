package com.company;


import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TheMaze {
    public int rows;
    public int columns;
    public Points[][] grid;
    public Points startPoint;
    public Points endPoint;

    public TheMaze(String fileName) throws FileNotFoundException, IOException {
        BufferedReader readMaze = new BufferedReader(new FileReader("mazes/" + fileName));

        String line = readMaze.readLine();
        columns = line.length();

        while(line != null) {
            rows++;
            line = readMaze.readLine();
        }

        readMaze.close();
        grid = new Points[rows][columns];

        readMaze = new BufferedReader(new FileReader("mazes/" + fileName));

        PointsChecker type = null;
        int row = 0;
        line = readMaze.readLine();
        while(line != null) {
            for(int col = 0; col < columns; col++) {
                char checker = line.charAt(col);
                if(checker == ' ') {
                    type = PointsChecker.EMPTY;
                } else if(checker == '#') {
                    type = PointsChecker.WALL;
                } else if(checker == '.') {
                    type = PointsChecker.FOOD;
                } else if( checker == 'P') {
                    type = PointsChecker.START;
                }

                grid[row][col] = new Points(col, row, type);

                if ( checker == '.') {
                    endPoint = grid[row][col];
                } else if(checker == 'P') {
                    startPoint = grid[row][col];
                }
            }
            line = readMaze.readLine();
            row++;
        }
        readMaze.close();
    }

    public boolean validPosition(Points point) {
        return (point.x >= 0 && point.x < columns && point.y >= 0 && point.y < rows);
    }

    public int putSolutionDotOnMaze(HashMap<Points, Points> solutionMap, Points point) {
        int solutionDistance = 0;

        while(solutionMap.containsKey(point)) {
            solutionDistance++;
            point = solutionMap.get(point);

            if(!point.equals(startPoint)) {
                point.pointType = PointsChecker.FOOD;
            }
        }

        return solutionDistance;
    }

    public int putSolutionDotOnMaze(HashMap<StateAstar, StateAstar> solutionMap, StateAstar state) {
        int solutionDistance = 0;

        while(solutionMap.containsKey(state)) {
            solutionDistance++;
            state = solutionMap.get(state);

            if(!state.pacmanLocation.equals(startPoint)) {
                grid[state.pacmanLocation.y][state.pacmanLocation.x].pointType = PointsChecker.FOOD;
            }
        }
        return solutionDistance;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < rows; row++){
            for (int col = 0; col < columns; col++){
                sb.append(grid[row][col]);
            }
            sb.append(System.lineSeparator());	//a newline that works with Unix and Windows
        }
        return sb.toString();
    }
}
