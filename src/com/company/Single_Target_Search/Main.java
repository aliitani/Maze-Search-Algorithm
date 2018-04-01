package com.company.Single_Target_Search;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        GenerateMazes smallMaze = new GenerateMazes();
        smallMaze.generateMazes();
        // difference as discussed in proposal is that the bfs is different from dfs in implemenetation is
//      dfs takes a stack while bfs takes a queue
        TheMaze smallMazeBFS = new TheMaze("smallMaze_target");
        TheMaze smallMazeDFS = new TheMaze("smallMaze_target");
        TheMaze smallMazeAstar = new TheMaze("smallMaze_target");

        BFS smallMazeSolutionBFS = new BFS();
        DFS smallMazeSolutionDFS = new DFS();
        AStar smallMazeSolutionAstar = new AStar(smallMazeAstar);

        smallMazeSolutionBFS.findSolution(smallMazeBFS);
        smallMazeSolutionDFS.findSolution(smallMazeDFS);
        smallMazeSolutionAstar.findSolution(smallMazeAstar);

        System.out.printf("%s\n" , "RESULTS FOR SMALL MAZE: \n");

        System.out.println("BFS Search: \n");
        System.out.println(smallMazeBFS);
        System.out.println("DFS Search: \n");
        System.out.println(smallMazeDFS);
        System.out.println("A-STAR Search: \n");
        System.out.println(smallMazeAstar);

        System.out.println("\n");

        System.out.printf("%s\n" , "RESULTS FOR MEDIUM MAZE: \n");

        TheMaze mediumMazeBFS = new TheMaze("mediumMaze_target");
        TheMaze mediumMazeDFS = new TheMaze("mediumMaze_target");
        TheMaze mediumMazeAstar = new TheMaze("mediumMaze_target");

        BFS mediumMazeSolutionBFS = new BFS();
        DFS mediumMazeSolutionDFS = new DFS();
        AStar mediumMazeSolutionAStar = new AStar(mediumMazeAstar);

        mediumMazeSolutionBFS.findSolution(mediumMazeBFS);
        mediumMazeSolutionDFS.findSolution(mediumMazeDFS);
        mediumMazeSolutionAStar.findSolution(mediumMazeAstar);

        System.out.println("Breadth First Search: \n");
        System.out.println(mediumMazeBFS);
        System.out.println("Depth First Search: \n");
        System.out.println(mediumMazeDFS);
        System.out.println("A* Search: \n");
        System.out.println(mediumMazeAstar);

        System.out.println("\n");

        System.out.printf("%s\n" , "RESULTS FOR LARGE MAZE: \n");

        TheMaze BigMazeBFS = new TheMaze("bigMaze_target");
        TheMaze BigMazeDFS = new TheMaze("bigMaze_target");
        TheMaze BigMazeAstar = new TheMaze("bigMaze_target");

        BFS BigMazeSolutionBFS = new BFS();
        DFS BigMazeSolutionDFS = new DFS();
        AStar BigMazeSolutionAStar = new AStar(BigMazeAstar);

        BigMazeSolutionBFS.findSolution(BigMazeBFS);
        BigMazeSolutionDFS.findSolution(BigMazeDFS);
        BigMazeSolutionAStar.findSolution(BigMazeAstar);

        System.out.println("Breadth First Search: \n");
        System.out.println(BigMazeBFS);
        System.out.println("Depth First Search: \n");
        System.out.println(BigMazeDFS);
        System.out.println("A* Search: \n");
        System.out.println(BigMazeAstar);

        System.out.println("\n");

        System.out.println("Results:");

        System.out.printf("%35s\n", "Maze Size");
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf( "%-20s   |       %-20s|      %-20s |    %-30s\n", " ", "Small", "Medium", "Large");
        System.out.printf( "%-20s   |   %-10s  %-10s  |   %-10s  %-10s  |   %-10s  %-10s\n", "Search Type", "S", "NE", "S", "NE", "S", "NE");
        System.out.printf ("%-20s   |   %-10s  %-10s  |   %-10s  %-10s  |   %-10s  %-10s  \n",
                "BFS", smallMazeSolutionBFS.scoreDistance, smallMazeSolutionBFS.nodesExpanded,
                mediumMazeSolutionBFS.scoreDistance, mediumMazeSolutionBFS.nodesExpanded,
                BigMazeSolutionBFS.scoreDistance, BigMazeSolutionBFS.nodesExpanded);
        System.out.printf("%-20s   |   %-10s  %-10s  |   %-10s  %-10s  |   %-10s  %-10s  \n",
                "DFS", smallMazeSolutionDFS.scoreDistance, smallMazeSolutionDFS.nodesExpanded,
                mediumMazeSolutionDFS.scoreDistance, mediumMazeSolutionDFS.nodesExpanded,
                BigMazeSolutionDFS.scoreDistance, BigMazeSolutionDFS.nodesExpanded);
        System.out.printf("%-20s   |   %-10s  %-10s  |   %-10s  %-10s  |   %-10s  %-10s  \n",
                "ASTAR - Manhattan ", smallMazeSolutionAstar.scoreDistance, smallMazeSolutionAstar.nodesExpanded,
                mediumMazeSolutionAStar.scoreDistance, mediumMazeSolutionAStar.nodesExpanded,
                BigMazeSolutionAStar.scoreDistance, BigMazeSolutionAStar.nodesExpanded);

    }
}
