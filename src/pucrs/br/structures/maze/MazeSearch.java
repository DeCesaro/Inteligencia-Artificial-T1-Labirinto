package pucrs.br.structures.maze;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

public abstract class MazeSearch {
    
    protected int x, y; // current position
    protected int end_x, end_y; // end position
    protected MazeCell[][] maze; // the maze boxes
    protected int width, height; // maze dimensions
    protected int step; // solver step
    protected ArrayList<MazeCell> solution; // maze solution
    protected int maxFront; // max front set size
    protected Maze mazeData;
    
    /**
     * Create maze from input
     * @param mazeInput 2D array with 0 and 1 for obstacles
     * @param x start x coordinate
     * @param y start y coordinate
     */
    MazeSearch(int[][] mazeInput, Maze mazeData){
        x= -1;
        y = -1;
        end_x = -1;
        end_y = -1;
        maxFront = 0;
        solution = new ArrayList<>();
        step = 0;
        width = mazeInput[0].length;
        height = mazeInput.length;
        maze = new MazeCell[height][width];
        this.mazeData = mazeData;
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                maze[i][j] = new MazeCell();
                maze[i][j].setIsObstacle(mazeInput[i][j]==3);
                maze[i][j].x = j;
                maze[i][j].y = i;
                if(mazeInput[i][j]==1){
                    x = j;
                    y = i;
                }else if(mazeInput[i][j]==2){
                    end_x = j;
                    end_y = i;
                }
            }
        }
    }
    
    /**
     * Perform the next step of search
     * @param speed in milliseconds
     * @return true if step performed
     * @throws java.lang.InterruptedException
     */
    public abstract boolean nextStep(int speed) throws InterruptedException;
    
    /**
     * Returns the number of steps
     * @return number of steps
     */
    public int getSteps(){
        return step;
    }
    
    /**
     * Returns the max front set size
     * @return max front set size
     */
    public int getMaxFront(){
        return maxFront;
    }
    
    /**
     * Visit MazeCell in x, y position
     * @param x MazeCell x coordinate
     * @param y MazeCell y coordinate
     * @return true if MazeCell is visited
     */
    protected boolean visit(int x, int y){
        if(!validPosition(x, y)){
            return false;
        }
        this.x = x;
        this.y = y;
        maze[y][x].isVisited = true;
        step++;
        //GUI
        mazeData.getMazeLogic()[y][x].isVisited = true;
        mazeData.getMazeLogic()[y][x].setIsFront(false);
        mazeData.setCurrent(new Point(y, x));
        return true;
    }
    
    /**
     * If MazeCell can be visited in x, y position
     * @param x MazeCell x coordinate
     * @param y MazeCell y coordinate
     * @return true if MazeCell can be visited in x, y position
     */
    protected boolean validPosition(int x, int y){
        return x>=0 && x<width && y>=0 && y<height && !maze[y][x].isObstacle();
    }
    
    /**
     * Add MazeCell in x, y position to front set
     * @param x MazeCell x coordinate
     * @param y MazeCell y coordinate
     */
    protected void addFront(int x, int y){
        //GUI
        mazeData.getMazeLogic()[y][x].setIsFront(true);
    }
    
    /**
     * Get current maze solution
     * @return ArrayList with current MazeCell solution (MazeCell items)
     */
    public ArrayList<MazeCell> getSolution(){
        solution.clear();
        if(step==0) return null;
        MazeCell box = maze[y][x];
        int c = 0;
        while(c<2){
            solution.add(box);
            if(box!=null) box = box.previous;
            if(box==null || box.previous==null){
                c++;
            }
        }
        Collections.reverse(solution);
        return solution;
    }
    
    /**
     * Solve the maze
     * @param speed
     * @return
     * @throws InterruptedException 
     */
    public ArrayList<MazeCell> solve(int speed) throws InterruptedException{
        while(nextStep(speed)){
            // continue tree search
        }
        if(isSolved()) return getSolution();
        return null;
    }
    
    /**
     * Checks if the maze is solved
     * @return true if solution is found
     */
    public boolean isSolved(){     
        return x==end_x && y==end_y;
    }
}
