package pucrs.br.agent;

import pucrs.br.structures.maze.Maze;
import pucrs.br.structures.maze.MazeCell;

public class Agent {
    private int xPosition;
    private int yPosition;
    public int maxMoves;
    public int moves;
    private int[] directions;
    private Maze maze;
    private MazeCell[][] mazeLogic;
    public int[][] copymaze;
    private int currentX;
    private int currentY;
    public int step;

    /**
     * Initalize a agent with controller
     *
     * @param directions The directions that agent have to follow
     * @param maze       The maze the agent will use
     * @param maxMoves   The maximum number of moves the agent can make
     */
    public Agent(int[] directions, Maze maze, int maxMoves) {
        this.maze = maze;
        this.copymaze = new int[maze.getMazeLogic().length][maze.getMazeLogic()[0].length];
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
     * Runs the agent's actions based on directions
     */
    public void run() {
        while (true) {
            this.moves++;


            // Break if we reach the goal
            if (this.maze.getPositionValue(this.currentX, this.currentY) == 4) {
                moves = moves + 100;
                return;
            }

            // Break if we reach a maximum number of moves
            if (this.moves > this.maxMoves) {
                return;
            }

            // Run action. Break if agent is destroyed.
            if (this.makeNextAction() == -1) {
                return;
            }

        }
    }


    /**
     * Runs the next action and check if agent is destroyed
     */
    public int makeNextAction() {
        int rcode = 0;
        // gets the next direction
        int what_direction = getNextAction();
        switch (what_direction) {
            // If move up
            case 1 -> {
                currentX = currentX - 1;
                if (maze.isWall(currentX, currentY)) {
                    rcode = -1;
                    break;
                }
                if (currentX < 0 || currentY < 0 || currentX > maze.getMaxX() || currentY > maze.getMaxY()) {
                    rcode = -1;
                    break;
                }
                copymaze[currentX][currentY] = 5;
            }
            // If move left
            case 2 -> {
                currentY = currentY - 1;
                if (maze.isWall(currentX, currentY)) {
                    rcode = -1;
                    break;
                }
                if (currentX < 0 || currentY < 0 || currentX > maze.getMaxX() || currentY > maze.getMaxY()) {
                    rcode = -1;
                    break;
                }
                copymaze[currentX][currentY] = 5;
            }
            // If move right
            case 3 -> {
                currentY = currentY + 1;
                if (maze.isWall(currentX, currentY)) {
                    rcode = -1;
                    break;
                }
                if (currentX < 0 || currentY < 0 || currentX > maze.getMaxX() || currentY > maze.getMaxY()) {
                    rcode = -1;
                    break;
                }
                copymaze[currentX][currentY] = 5;
            }
            // If move down
            case 4 -> {
                currentX = currentX + 1;
                if (maze.isWall(currentX, currentY)) {
                    rcode = -1;
                    break;
                }
                if (currentX < 0 || currentY < 0 || currentX > maze.getMaxX() || currentY > maze.getMaxY()) {
                    rcode = -1;
                    break;
                }
                copymaze[currentX][currentY] = 5;
            }
        }

        return rcode;
    }

    /**
     * Get next action depending on directions which has taken
     *
     * @return int Next action
     */
    public int getNextAction() {
        int nxtA = this.directions[this.step];
        this.step = step + 1;
        return nxtA;
    }

    /** Copies the maze (2d array) */

    public void setCopymaze(){
        for (int i = 0; i < copymaze.length; i++){
            for (int j = 0; j < copymaze[0].length;j++){
                copymaze[i][j] = (int) maze.getPositionValue(i,j);
            }
        }
    }
}
