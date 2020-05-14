package pucrs.br.AStarGUI.structures.maze;

import pucrs.br.AStarGUI.structures.Heap;

/**
 * Best-First / A* maze solver
 */
public final class MazeSolverBF extends MazeSearch{

    private final Heap<MazeCell> front;
    private final boolean aStar;
    private final boolean hf;
    
    /**
     * BF initialization
     * @param mazeInput
     * @param aStar true for A*, false for Best-First
     * @param hf true: manhattan, false: euclidean
     */
    public MazeSolverBF(int mazeInput[][], boolean aStar, boolean hf, Maze mazeData) {
        super(mazeInput, mazeData);
        front = new Heap<>();
        this.aStar = aStar;
        this.hf = hf;
        addFront(x, y);
    }
    
    @Override
    public boolean nextStep(int speed) throws InterruptedException{
        if(speed>0) Thread.sleep(speed);
        if(!front.isEmpty()){
            MazeCell box = front.deleteMin();
            if(box.isVisited){
                return nextStep(0);
            }
            
            visit(box.x, box.y);
            
            if(isSolved()){
                return false;
            }
            
            addFront(x+1, y);
            addFront(x, y-1);
            addFront(x, y+1);
            addFront(x-1, y); 
            
            return true;
        }
        return false;
    }
 
    /**
     * Return the manhattan distance from the end
     * @param x MazeCell x coordinate
     * @param y MazeCell y coordinate
     * @return distance
     */
    private int manhattanDistance(int x, int y){
        return Math.abs(x-end_x)+Math.abs(y-end_y);
    }
    
    /**
     * Return the euclidean distance from the end
     * @param x MazeCell x coordinate
     * @param y MazeCell y coordinate
     * @return distance
     */
    private double euclideanDistance(int x, int y){
        return Math.sqrt(Math.pow(x-end_x,2)+Math.pow(y-end_y,2));
    }
    
    @Override
    protected void addFront(int x, int y){
        if(validPosition(x, y)){
            if(maze[y][x].isAdded){
                return;
            }
            maze[y][x].isAdded = true;
            if(step>0){
                maze[y][x].previous = maze[this.y][this.x];
                if(aStar){
                    maze[y][x].so_far = maze[y][x].previous.so_far+1;
                }
            }
            if(hf){
                maze[y][x].to_go = manhattanDistance(x, y);
            }else{
                maze[y][x].to_go = euclideanDistance(x, y);
            }            
            front.insert(maze[y][x]);
            int fsize = front.size();
            if(fsize>maxFront){
                maxFront = fsize;
            }
            super.addFront(x, y);
        }
    }
}
