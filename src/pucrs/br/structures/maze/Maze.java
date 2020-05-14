package pucrs.br.structures.maze;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {

    private int rows;//rows of this maze
    private int columns;//columns of this maze
    private int size;
    private MazeCell[][] mazeLogic;//maze data
    private int[][] maze;
    private int startX;
    private int startY;
    private Point start;//startig point
    private Point goal;//goal point
    private Point current;//current point
    private ArrayList<MazeCell> solution;//current solution

    /**
     * empty constructor
     */
    public Maze() {
        rows = 0;
        columns = 0;
        mazeLogic = null;
        start = null;
        goal = null;
        current = null;
        solution = null;
    }



    /**
     * Builds a new empty maze of specific dimensions
     * @param rows number of rows in the maze
     * @param columns number of columns in the maze
     */
    public Maze(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        mazeLogic = new MazeCell[rows][columns];
        for (int i = 0;i< rows;i++){
            for (int j = 0;j< columns;j++){
                mazeLogic[i][j] = new MazeCell();
            }
        }
        start = null;
        goal = null;
        current = null;
        solution = null;
    }



    /**
     * Builds a new maze from file
     * @param path file path
     */
    public Maze (String path){
        this();
        try {
            Scanner scanner = new Scanner(new File(path));
            System.out.println("Chegando esse arquivo "+path );
            String input;
            int size = scanner.nextInt();
            this.rows = size;
            this.columns = size;
            System.out.println("Tamanho: "+size);
            mazeLogic = new MazeCell[rows][columns];
            for (int i = 0;i< rows;i++){
                for (int j = 0;j< columns;j++){
                    mazeLogic[i][j] = new MazeCell();
                }
            }
            for (int i = 0;i< rows;i++){
                for (int j = 0;j< columns;j++){
                    if (scanner.hasNext()){
                        input = scanner.next();
                        switch (input) {
                            case "0" -> {
                                mazeLogic[i][j].setIsObstacle(false);
                                mazeLogic[i][j].setIsHole(false);
                            }
                            case "E" -> {
                                mazeLogic[i][j].setIsObstacle(false);
                                start = new Point(i, j);
                            }
                            case "S" -> {
                                mazeLogic[i][j].setIsObstacle(false);
                                goal = new Point(i, j);
                            }
                            case "1" -> {
                                mazeLogic[i][j].setIsObstacle(true);
                            }
                            default -> {
                                mazeLogic[i][j].setIsHole(true);
                            }
                        }
                    }
                }
            }



        } catch (IOException e) {
            System.out.println("Input issue!");
        }
    }

    /**
     * Saves this maze to a text file
     * @param path file path
     * @return true if completed without IO errors
     */
    public boolean saveMaze (String path){

        try (PrintWriter printer = new PrintWriter(new FileWriter(new File(path)) {
        })) {


            printer.println(rows);
            printer.println(columns);




            for (int i = 0;i< rows;i++){
                for (int j = 0;j< columns;j++){
                    if (start != null && start.x == i && start.y == j){
                        printer.print("E ");
                    }
                    else if (goal != null && goal.x == i && goal.y == j){
                        printer.print("S ");
                    }
                    else if (mazeLogic[i][j].isObstacle()){
                        printer.print("1 ");
                    }
                    else if(mazeLogic[i][j].isHole()){
                        printer.print("B");
                    }
                    else{
                        printer.print("0");
                        }
                }
                printer.println();
            }

        } catch (Exception e) {
            System.out.println("Output issue!");
            return false;
        }


        return true;
    }


    /**
     * Set if a cell is obstacle
     * @param x row
     * @param y column
     * @param obstacle obstacle or not 
     */
    public void isObstacle(int x, int y, boolean obstacle) {
        mazeLogic[x][y].setIsObstacle(obstacle);
    }

    /** Check if position is wall
     * @param x
     *            position
     * @param y
     *            position
     * @return boolean
     */
    public boolean isWall(int x, int y) {

        return (this.getPositionValue(x, y) == 1);
    }

    /**
     * Set start of this maze
     * @param x row
     * @param y column
     */
    public void setStart(int x, int y){
        if (start != null){
            start.x = x;
            start.y = y;
        }
        else{
            start = new Point(x, y);
        }
    }

    /**
     * Set goal of this maze
     * @param x row 
     * @param y column
     */
    public void setGoal(int x, int y){
        if (goal != null){
            goal.x = x;
            goal.y = y;
        }
        else{
            goal = new Point(x, y);
        }
    }

    /**
     * gets rows
     * @return rows
     */
    public int getRows(){
        return rows;
    }

    /**
     * gets columns
     * @return columns
     */
    public int getColumns(){
        return columns;
    }

    /** Gets maximum index of x position
     * @return int Max index
     */
    public int getMaxX() {

        return this.mazeLogic.length - 1;
    }

    /** Gets maximum index of y position
     * @return int Max index
     */
    public int getMaxY() {
        return this.mazeLogic[0].length - 1;
    }


    /**
     * get an array with all the maze's cells
     * @return 2d array
     */
    public MazeCell[][] getMazeLogic(){
        return mazeLogic;
    }

    /**
     * gets maze start
     * @return maze start
     */
    public Point getStart(){
        return start;
    }

    /**
     * gets maze goal
     * @return maze goal
     */
    public Point getGoal(){
        return goal;
    }

    /** Gets value for position of maze
     * @param x
     *            position
     * @param y
     *            position
     * @return int Position value
     */
    public int getPositionValue(int x, int y) {
        if (x < 0 || y < 0 || x > getMaxX() || y > getMaxY()) {
            return 1;
        }
        return this.maze[x][y];
    }


    /**
     * sets current solution
     * @param solution new solution
     */
    public void setSolution(ArrayList<MazeCell> solution) {
        this.solution = solution;
    }

    /**
     * Sets all cells as obstacles
     */
    public void blacken(){
        for (int i = 0;i< rows;i++){
            for (int j = 0;j< columns;j++){
                mazeLogic[i][j].setIsObstacle(true);
            }
        }
        start = null;
        goal = null;
    }

    /**
     * Clears this maze
     */
    public void whiten(){
        for (int i = 0;i< rows;i++){
            for (int j = 0;j<columns;j++){
                mazeLogic[i][j].setIsObstacle(false);
            }
        }
        start = null;
        goal = null;
    }

    /** Gets index of row, that is  located start position */
    public int getStartX(){
        return startX;
    }

    /** Gets index of column, that is  located start position */
    public int getStartY(){
        return startY;
    }

    public void setStart(Point newStartPoint){
        start = newStartPoint;
    }

    public void setGoal(Point newGoalPoint){
        goal = newGoalPoint;
    }

        public ArrayList<MazeCell> getSolution() {
        return solution;
    }

    public Point getCurrent() {
        return current;
    }

    public void setCurrent(Point current) {
        this.current = current;
    }

    public void copyMazeObstacles(Maze otherMaze, int iStart, int jStart){
        for (int i = 0;i< rows;i++){
            for (int j = 0;j< columns;j++){
                if (i + iStart>= otherMaze.getRows() || j + jStart>= otherMaze.getColumns() ||
                        i + iStart<0 || j + jStart< 0){
                    mazeLogic[i][j].setIsObstacle(false);
                }
                else{
                    mazeLogic[i][j].setIsObstacle(otherMaze.getMazeLogic()[i + iStart]
                        [j + jStart].isObstacle());
                }
            }
        }
        if (otherMaze.getStart() != null && start == null){
            start = new Point(otherMaze.getStart().x, otherMaze.getStart().y);

        }
        else if (otherMaze.getStart() == null){
            start = null;
        }
        if (otherMaze.getGoal() != null && goal == null){
            goal = new Point(otherMaze.getGoal().x, otherMaze.getGoal().y);
        }
        else if (otherMaze.getGoal() == null){
            goal = null;
        }
    }

    public void copyMazeHoles(Maze otherMaze, int iStart, int jStart){
        for (int i = 0;i< rows;i++){
            for (int j = 0;j< columns;j++){
                if (i + iStart>= otherMaze.getRows() || j + jStart>= otherMaze.getColumns() ||
                        i + iStart<0 || j + jStart< 0){
                    mazeLogic[i][j].setIsHole(false);
                }
                else{
                    mazeLogic[i][j].setIsHole(otherMaze.getMazeLogic()[i + iStart]
                            [j + jStart].isHole());
                }
            }
        }
        if (otherMaze.getStart() != null && start == null){
            start = new Point(otherMaze.getStart().x, otherMaze.getStart().y);

        }
        else if (otherMaze.getStart() == null){
            start = null;
        }
        if (otherMaze.getGoal() != null && goal == null){
            goal = new Point(otherMaze.getGoal().x, otherMaze.getGoal().y);
        }
        else if (otherMaze.getGoal() == null){
            goal = null;
        }
    }


    public void addRow(Maze oldMaze){
        rows++;
        mazeLogic = new MazeCell[rows][columns];
        for (int i = 0;i< rows;i++){
            for (int j = 0;j< columns;j++){
                mazeLogic[i][j] = new MazeCell();
            }
        }
        copyMazeObstacles(oldMaze, 0, 0);
        copyMazeHoles(oldMaze, 0, 0);
    }

    public void addColumn(Maze oldMaze){
        columns++;
        mazeLogic = new MazeCell[rows][columns];
        for (int i = 0;i< rows;i++){
            for (int j = 0;j< columns;j++){
                mazeLogic[i][j] = new MazeCell();
            }
        }
        copyMazeObstacles(oldMaze, 0, 0);
        copyMazeHoles(oldMaze, 0, 0);
    }

    public void removeRow(){
        Maze temp = new Maze(rows, columns);
        temp.copyMazeObstacles(this, 0, 0);
        temp.copyMazeHoles(this, 0, 0);
        rows--;
        mazeLogic = new MazeCell[rows][columns];
        for (int i = 0;i< rows;i++){
            for (int j = 0;j< columns;j++){
                mazeLogic[i][j] = new MazeCell();
            }
        }
        if (start!= null && start.x>= rows){
            temp.setStart(null);
            setStart(null);
        }
        if (goal != null && goal.x>= rows){
            temp.setGoal(null);
            setGoal(null);
        }
        copyMazeObstacles(temp, 0, 0);
        copyMazeHoles(temp, 0,0);
    }

    public void removeColumn(){
        Maze temp = new Maze(rows, columns);
        temp.copyMazeObstacles(this, 0, 0);
        temp.copyMazeHoles(this,0,0);
        columns--;
        mazeLogic = new MazeCell[rows][columns];
        for (int i = 0;i< rows;i++){
            for (int j = 0;j< columns;j++){
                mazeLogic[i][j] = new MazeCell();
            }
        }
        if (goal != null && goal.y>= columns){
            temp.setGoal(null);
            setGoal(null);
        }
        if (start != null && start.y>= columns){
            temp.setStart(null);
            setStart(null);
        }
        copyMazeObstacles(temp, 0, 0);
        copyMazeHoles(temp, 0, 0);
    }

}
