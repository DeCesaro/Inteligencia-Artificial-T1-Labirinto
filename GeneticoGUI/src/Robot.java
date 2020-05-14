
/**
 * A robot abstraction. Give it a maze and an instruction set, and it will
 * attempt to reach the finish.
 *
 * References 'Genetic Algorithms in Java Basics' - Lee Jacobson, Burak Kanber
 *
 * @author Meriton Çela
 *
 */
public class Robot{
    private int xPosition;
    private int yPosition;
    int maxMoves;
    int moves;
    private int[] directions;
    private Maze maze;
    int[][] copymaze;
    private int currentX;
    private int currentY;
    int step;

    /**
     * Initalize a robot with controller
     *
     * @param directions The directions that robot have to follow
     * @param maze The maze the robot will use
     * @param maxMoves The maximum number of moves the robot can make
     */
    public Robot(int[] directions, Maze maze, int maxMoves){
        this.maze = maze;
        this.copymaze = new int[maze.getMaze().length][maze.getMaze()[0].length];
        this.maxMoves = maxMoves;
        xPosition = maze.getStartX();
        yPosition = maze.getStartY();
        currentX = xPosition;
        currentY = yPosition;
        moves = 0;
        this.directions = directions;
        step = 0;
        setCopymaze();
    }

    /**
     * Runs the robot's actions based on directions
     */
    public void run(){
        while(true){
            this.moves++;


            // Break if we reach the goal
            if (this.maze.getPositionValue(this.currentX, this.currentY) == 4) {
                moves = moves +100;
                return;
            }

            // Break if we reach a maximum number of moves
            if (this.moves > this.maxMoves) {
                return;
            }

            // Run action. Break if robot is destroyed.
            if (this.makeNextAction() == -1){
                return;
            }

        }
    }


    /**
     * Runs the next action and check if robot is destroyed
     */
    public int makeNextAction(){
        int rcode = 0;
        // gets the next direction
        int what_direction = getNextAction();
        switch (what_direction){
            case 1: // If move up
                currentX = currentX -1;
                if (maze.isWall(currentX,currentY)){
                    rcode = -1;
                    break;
                }
                if (currentX < 0 || currentY < 0 || currentX > maze.getMaxX() || currentY > maze.getMaxY()){
                    rcode = -1;
                    break;
                }
                copymaze[currentX][currentY] = 5;
                break;
            case 2: // If move left
                currentY = currentY - 1;
                if (maze.isWall(currentX,currentY)){
                    rcode = -1;
                    break;
                }
                if (currentX < 0 || currentY < 0 || currentX > maze.getMaxX() || currentY > maze.getMaxY()){
                    rcode = -1;
                    break;
                }
                copymaze[currentX][currentY] = 5;
                break;
            case 3: // If move right
                currentY = currentY + 1;
                if (maze.isWall(currentX,currentY)){
                    rcode = -1;
                    break;
                }
                if (currentX < 0 || currentY < 0 || currentX > maze.getMaxX() || currentY > maze.getMaxY()){
                    rcode = -1;
                    break;
                }
                copymaze[currentX][currentY] = 5;
                break;
            case 4: // If move down
                currentX = currentX + 1;
                if (maze.isWall(currentX,currentY)){
                    rcode = -1;
                    break;
                }
                if (currentX < 0 || currentY < 0 || currentX > maze.getMaxX() || currentY > maze.getMaxY()){
                    rcode = -1;
                    break;
                }
                copymaze[currentX][currentY] = 5;
                break;
        }

        return rcode;
    }

    /** Get next action depending on directions which has taken
     *
     * @return int Next action
     */
    public int getNextAction(){
        int nxtA = this.directions[this.step];
        this.step = step+1;
        return nxtA;
    }

    /** Copies the maze (2d array) */

    public void setCopymaze(){
        for (int i = 0; i < copymaze.length; i++){
            for (int j = 0; j < copymaze[0].length;j++){
                copymaze[i][j] = maze.getPositionValue(i,j);
            }
        }
    }


}
