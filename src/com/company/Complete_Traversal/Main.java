package com.company.Complete_Traversal;

import com.company.Single_Target_Search.TheMaze;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException{

        TheMaze mediumMaze = new TheMaze("mediumMaze_Complete");
        AstarPacMan mediumSolution = new AstarPacMan(mediumMaze);
        mediumSolution.findSolution();

        System.out.println(mediumMaze);
        System.out.println("Nodes Expanded are: " + mediumSolution.nodesExpanded);
        System.out.println("Solution Distance is: " + mediumSolution.solutionDistance);
        System.out.println();
    }
}
