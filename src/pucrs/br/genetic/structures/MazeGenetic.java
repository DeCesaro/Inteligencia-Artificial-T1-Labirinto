package pucrs.br.genetic.structures;

public class MazeGenetic {
    private final int maze[][];
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int freeSpaces;

    public MazeGenetic(int maze[][]) {
        this.maze = maze;
        set_start_finish_positions();
        setFreeSpaces();
    }

    /** Set start & finish positions in maze */
    private void set_start_finish_positions(){
        for (int i = 0; i <= getMaxX(); i++){
            for (int j = 0; j <= getMaxY(); j++){
                if (maze[i][j] == 2){
                    startX = i;
                    startY = j;
                }

                if (maze[i][j]== 4){
                    endX = i;
                    endY = j;
                }
            }
        }
    }

    /** Count number of free spaces in maze */
    private void setFreeSpaces() {
        for (int i = 0; i <= getMaxX(); i++) {
            for (int j = 0; j <= getMaxY(); j++) {
                if (maze[i][j] == 0) {
                    freeSpaces++;
                }
            }
        }
    }

    /** Gets index of row, that is  located start position */
    public int getStartX(){
        return startX;
    }

    /** Gets index of column, that is  located start position */
    public int getStartY(){
        return startY;
    }

    /** Gets index of row, that is  located finish position */
    public int getEndX(){
        return endX;
    }

    /** Gets index of column, that is  located finish position */
    public int getEndY(){
        return endY;
    }

    /** Gets number of free spaces in maze */
    public int getNumOfFreeSpaces(){
        return freeSpaces;
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

    /** Gets maximum index of x position
     * @return int Max index
     */
    public int getMaxX() {

        return this.maze.length - 1;
    }

    /** Gets maximum index of y position
     * @return int Max index
     */
    public int getMaxY() {
        return this.maze[0].length - 1;
    }

    /** Gets 2d array of maze
     * @return  int[][] */
    public int[][] getMaze(){
        return maze;
    }


}
