package pucrs.br.genetic.agent;

import pucrs.br.genetic.structures.MazeGenetic;

public class Agent {
    private int xPosition;
    private int yPosition;
    public int maxMoves;
    public int moves;
    private int[] directions;
    private MazeGenetic mazeGenetic;
    public int[][] copymaze;
    private int currentX;
    private int currentY;
    public int step;

    /**
     * Initalize a robot with controller
     *
     * @param directions The directions that robot have to follow
     * @param mazeGenetic The mazeGenetic the robot will use
     * @param maxMoves The maximum number of moves the robot can make
     */
    public Agent(int[] directions, MazeGenetic mazeGenetic, int maxMoves){
        this.mazeGenetic = mazeGenetic;
        this.copymaze = new int[mazeGenetic.getMaze().length][mazeGenetic.getMaze()[0].length];
        this.maxMoves = maxMoves;
        xPosition = mazeGenetic.getStartX();
        yPosition = mazeGenetic.getStartY();
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
            if (this.mazeGenetic.getPositionValue(this.currentX, this.currentY) == 4) {
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
        switch (what_direction) {
            // If move up
            case 1 -> {
                currentX = currentX - 1;
                if (mazeGenetic.isWall(currentX, currentY)) {
                    rcode = -1;
                    break;
                }
                if (currentX < 0 || currentY < 0 || currentX > mazeGenetic.getMaxX() || currentY > mazeGenetic.getMaxY()) {
                    rcode = -1;
                    break;
                }
                copymaze[currentX][currentY] = 9;
            }
            // If move left
            case 2 -> {
                currentY = currentY - 1;
                if (mazeGenetic.isWall(currentX, currentY)) {
                    rcode = -1;
                    break;
                }
                if (currentX < 0 || currentY < 0 || currentX > mazeGenetic.getMaxX() || currentY > mazeGenetic.getMaxY()) {
                    rcode = -1;
                    break;
                }
                copymaze[currentX][currentY] = 9;
            }
            // If move right
            case 3 -> {
                currentY = currentY + 1;
                if (mazeGenetic.isWall(currentX, currentY)) {
                    rcode = -1;
                    break;
                }
                if (currentX < 0 || currentY < 0 || currentX > mazeGenetic.getMaxX() || currentY > mazeGenetic.getMaxY()) {
                    rcode = -1;
                    break;
                }
                copymaze[currentX][currentY] = 9;
            }
            // If move down
            case 4 -> {
                currentX = currentX + 1;
                if (mazeGenetic.isWall(currentX, currentY)) {
                    rcode = -1;
                    break;
                }
                if (currentX < 0 || currentY < 0 || currentX > mazeGenetic.getMaxX() || currentY > mazeGenetic.getMaxY()) {
                    rcode = -1;
                    break;
                }
                copymaze[currentX][currentY] = 9;
            }
            // If move up and left
            case 5 -> {
                currentX = currentX - 1;
                currentY = currentY - 1;
                if (mazeGenetic.isWall(currentX, currentY)) {
                    rcode = -1;
                    break;
                }
                if (currentX < 0 || currentY < 0 || currentX > mazeGenetic.getMaxX() || currentY > mazeGenetic.getMaxY()) {
                    rcode = -1;
                    break;
                }
                copymaze[currentX][currentY] = 9;
            }
            // If move up and right
            case 6 -> {
                currentX = currentX - 1;
                currentY = currentY + 1;
                if (mazeGenetic.isWall(currentX, currentY)) {
                    rcode = -1;
                    break;
                }
                if (currentX < 0 || currentY < 0 || currentX > mazeGenetic.getMaxX() || currentY > mazeGenetic.getMaxY()) {
                    rcode = -1;
                    break;
                }
                copymaze[currentX][currentY] = 9;
            }
            // If move down and left
            case 7 -> {
                currentX = currentX + 1;
                currentY = currentY - 1;
                if (mazeGenetic.isWall(currentX, currentY)) {
                    rcode = -1;
                    break;
                }
                if (currentX < 0 || currentY < 0 || currentX > mazeGenetic.getMaxX() || currentY > mazeGenetic.getMaxY()) {
                    rcode = -1;
                    break;
                }
                copymaze[currentX][currentY] = 9;
            }
            // If move down and right
            case 8 -> {
                currentX = currentX + 1;
                currentY = currentY + 1;
                if (mazeGenetic.isWall(currentX, currentY)) {
                    rcode = -1;
                    break;
                }
                if (currentX < 0 || currentY < 0 || currentX > mazeGenetic.getMaxX() || currentY > mazeGenetic.getMaxY()) {
                    rcode = -1;
                    break;
                }
                copymaze[currentX][currentY] = 9;
            }
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

    /** Copies the mazeGenetic (2d array) */

    public void setCopymaze(){
        for (int i = 0; i < copymaze.length; i++){
            for (int j = 0; j < copymaze[0].length;j++){
                copymaze[i][j] = mazeGenetic.getPositionValue(i,j);
            }
        }
    }


}
