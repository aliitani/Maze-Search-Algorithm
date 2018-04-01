package com.company.Single_Target_Search;


import com.company.Complete_Traversal.StatePacMan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TheMaze {
    public int rows;
    public int columns;
    public Points[][] grid;
    public Points startPoint;
    public Points endPoint;

    // for Complete Traversal
    public ArrayList<Points> dots = new ArrayList<>();

    public TheMaze(String fileName) throws FileNotFoundException, IOException {
        // buffer read the file.

        BufferedReader readMaze = new BufferedReader(new FileReader("mazes/" + fileName));

        // if error throws an IOException.
        String line = readMaze.readLine();

        // get column & row lengths for grid.
        columns = line.length();

        while(line != null) {
            rows++;
            line = readMaze.readLine();
        }

        System.out.println("The Column Size is: " + columns);
        System.out.println("The Row Size is: " + rows);

        readMaze.close();

        // create the grid.
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
                    // add the dots to the arraylist
                    dots.add(new Points(col, row));

                } else if( checker == 'P') {

                    type = PointsChecker.START;

                }

                grid[row][col] = new Points(col, row, type);

                if ( checker == '.') {
                    // keeps adding dots, until last one is added making it the endPoint.
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

    // method to check if the point is a valid position and not off the grid.
    public boolean validPosition(Points point) {
        return (point.x >= 0 && point.x < columns && point.y >= 0 && point.y < rows);
    }

    // puts the solution to console eventually. BFS DFS
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
    public int calculateSolutionDistance(HashMap<StatePacMan, StatePacMan> solutionMap, StatePacMan state){
        int solutionDistance = 0;
        while(solutionMap.containsKey(state)) {
            solutionDistance++;
            state = solutionMap.get(state);
        }
        return solutionDistance;
    }
    // puts the solution to console eventually. A-star
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


    // override the tostring method.
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
