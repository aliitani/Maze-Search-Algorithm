package com.company.RandomGeneratedGraph;
// created as of Apr 3 after meeting.

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class RandomGenerateGraph {
    private static int dimensionX, dimensionY; // dimension of maze
    private static int gridDimensionX, gridDimensionY; // dimension of output grid
    private static char[][] grid; // output grid
    private Cell[][] cells; // 2d array of Cells
    private Random random = new Random(); // The random object

    // initialize with x and y the same
    public RandomGenerateGraph(int aDimension) {
        // Initialize
        this(aDimension, aDimension);
    }
    // constructor
    public RandomGenerateGraph(int xDimension, int yDimension) {
        dimensionX = xDimension;
        dimensionY = yDimension;
        gridDimensionX = xDimension * 4 + 1;
        gridDimensionY = yDimension * 2 + 1;
        grid = new char[gridDimensionX][gridDimensionY];
        init();
        generateMaze();
        updateGrid();
        try {
            createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        // create cells
        cells = new Cell[dimensionX][dimensionY];
        for (int x = 0; x < dimensionX; x++) {
            for (int y = 0; y < dimensionY; y++) {
                cells[x][y] = new Cell(x, y, false); // create cell (see Cell constructor)
            }
        }
    }

    // inner class to represent a cell
    private class Cell {
        int x, y; // coordinates
        // cells this cell is connected to
        ArrayList<Cell> neighbors = new ArrayList<>();
        boolean wall = true;

        boolean open = true;

        Cell(int x, int y) {
            this(x, y, true);
        }
        // construct Cell at x, y and with whether it isWall
        Cell(int x, int y, boolean isWall) {
            this.x = x;
            this.y = y;
            this.wall = isWall;
        }
        // add a neighbor to this cell, and this cell as a neighbor to the other
        void addNeighbor(Cell other) {
            if (!this.neighbors.contains(other)) { // avoid duplicates
                this.neighbors.add(other);
            }
            if (!other.neighbors.contains(this)) { // avoid duplicates
                other.neighbors.add(this);
            }
        }
        // used in updateGrid()
        boolean isCellBelowNeighbor() {

            return this.neighbors.contains(new Cell(this.x, this.y + 1));
        }
        // used in updateGrid()
        boolean isCellRightNeighbor() {
            return this.neighbors.contains(new Cell(this.x + 1, this.y));
        }
        // useful Cell representation
        @Override
        public String toString() {
            return String.format("Cell(%s, %s)", x, y);
        }
        // useful Cell equivalence
        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Cell)) return false;
            Cell otherCell = (Cell) other;
            return (this.x == otherCell.x && this.y == otherCell.y);
        }
        // should be overridden with equals
        @Override
        public int hashCode() {
            // random hash code method designed to be usually unique
            return this.x + this.y * 256;
        }
    }
    // generate from upper left (In computing the y increases down often)
    private void generateMaze() {
        generateMaze(0, 0);
    }
    // generate the maze from coordinates x, y
    private void generateMaze(int x, int y) {
        generateMaze(getCell(x, y)); // generate from Cell
    }
    private void generateMaze(Cell startAt) {
        // don't generate from cell not there
        if (startAt == null) {
            return;

        }
        startAt.open = false; // indicate cell closed for generation
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(startAt);

        while (!cells.isEmpty()) {
            Cell cell;
            // this is to reduce but not completely eliminate the number
            //   of long twisting halls with short easy to detect branches
            //   which results in easy mazes
            if (random.nextInt(10)==0) {
                cell = cells.remove(random.nextInt(cells.size()));
            } else {
                cell = cells.remove(cells.size() - 1);
            }
            // for collection
            ArrayList<Cell> neighbors = new ArrayList<>();
            // cells that could potentially be neighbors
            Cell[] potentialNeighbors = new Cell[]{
                    getCell(cell.x + 1, cell.y),
                    getCell(cell.x, cell.y + 1),
                    getCell(cell.x - 1, cell.y),
                    getCell(cell.x, cell.y - 1)
            };
            for (Cell other : potentialNeighbors) {
                // skip if outside, is a wall or is not opened
                if (other==null || other.wall || !other.open) {
                    continue;
                }
                neighbors.add(other);
            }
            if (neighbors.isEmpty()) {
                continue;
            }
            // get random cell
            Cell selected = neighbors.get(random.nextInt(neighbors.size()));
            // add as neighbor
            selected.open = false; // indicate cell closed for generation
            cell.addNeighbor(selected);
            cells.add(cell);
            cells.add(selected);
        }

    }

    // used to get a Cell at x, y; returns null out of bounds
    public Cell getCell(int x, int y) {
        try {
            return cells[x][y];
        } catch (ArrayIndexOutOfBoundsException e) { // catch out of bounds
            return null;
        }
    }

    public static boolean insertPacMan() {

        grid[gridDimensionX-2][gridDimensionY-2] = 'P';

        return true;
    }

    public static boolean insertFood() {

        grid[1][1] = '.';


        return true;
    }

    // draw the maze
    public void updateGrid() {
        char  wallChar = '#';
        char cellChar = ' ';

        // fill background
        for (int x = 0; x < gridDimensionX; x ++) {
            for (int y = 0; y < gridDimensionY; y ++) {
                grid[x][y] = cellChar;
            }
        }
        // build walls
        for (int x = 0; x < gridDimensionX; x ++) {
            for (int y = 0; y < gridDimensionY; y ++) {
                if (x % 4 == 0 || y % 2 == 0) {
                    grid[x][y] = wallChar;
                }
            }
        }

//         make meaningful representation
        for (int x = 0; x < dimensionX; x++) {
            for (int y = 0; y < dimensionY; y++) {

                Cell current = getCell(x, y);

                int gridX = x * 4 + 2;
                int  gridY = y * 2 + 1;

                grid[gridX][gridY] = cellChar;

                if (current.isCellBelowNeighbor()) {
                    grid[gridX][gridY + 1] = cellChar;
                    grid[gridX + 1][gridY + 1] = cellChar;
                    grid[gridX - 1][gridY + 1] = cellChar;
                }
                if (current.isCellRightNeighbor()) {
                    grid[gridX + 2][gridY] = cellChar;
                    grid[gridX + 1][gridY] = cellChar;
                    grid[gridX + 3][gridY] = cellChar;
                }
            }
        }
        insertFood();
        insertPacMan();
    }

    public String getFileName() {
        return "maze_autoGeneratedGraph";
    }

    public void createFile() throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("mazes/maze_autoGeneratedGraph"));
        try {

            out.write(this.toString());  //Replace with the string
            //you are trying to write
        } catch (IOException e) {
            System.out.println("Exception ");

        } finally {
            out.close();
        }
    }

    // forms a meaningful representation
    @Override
    public String toString() {
        String output = "";
        for (int y = 0; y < gridDimensionY; y++) {
            for (int x = 0; x < gridDimensionX; x++) {
                output += grid[x][y];
            }
            output += "\n";
        }
        return output;
    }

//    // for testing only
//    public static void main(String[] args) {
//        RandomGenerateGraph maze = new RandomGenerateGraph(20,40);
//        System.out.println(maze);
//
//    }
}