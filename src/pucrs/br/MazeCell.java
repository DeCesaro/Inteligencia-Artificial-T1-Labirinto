package pucrs.br;

import java.awt.Rectangle;

/**Individual maze cell
 *
 */
public class MazeCell implements Comparable<MazeCell>{

    private boolean hole;
    private boolean obstacle;
    public boolean isVisited;
    private boolean solution;
    public MazeCell previous;
    public int x;
    public int y;
    public int so_far;
    public double to_go;
    public boolean isAdded;
    private Rectangle cell;
    
    MazeCell(){
        isAdded = false;
        obstacle = false;
        isVisited = false;
        previous = null;
        hole = false;
    }

    @Override
    public int compareTo(MazeCell o) {
        if(so_far+to_go>o.so_far+o.to_go) return 1;
        else if(so_far+to_go==o.so_far+o.to_go) return 0;
        else return -1;
    }

    public void setIsObstacle(boolean b) {
        this.obstacle = b;
    }

    public void setIsHole(boolean h) { this.hole = h;}

    public boolean isObstacle() {
        return obstacle;
    }

    public boolean isHole(){
        return hole;
    }

    public void putCell(Rectangle cell) {
        this.cell = cell;
    }

    public Rectangle getCell() {
        return cell;
    }

    public boolean isVisited() {
        return isVisited;
    }
    
        public boolean isSolution() {
        return solution;
    }

    public void setSolution(boolean solution) {
        this.solution = solution;
    }

    private boolean front;

    public boolean isIsFront() {
        return front;
    }

    public void setIsFront(boolean isFront) {
        this.front = isFront;
    }
    
}
