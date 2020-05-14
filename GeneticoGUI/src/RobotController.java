/** The main executive class for the Robot Controller problem.
 *
 * We'll create a maze by hand, and feed it to the GeneticAlgorithm's
 * `evalPopulation` method, which is then responsible for scoring an abstract robot
 *
 * References 'Genetic Algorithms in Java Basics' - Lee Jacobson, Burak Kanber
 *
 * @author Meriton Çela
 */


public class RobotController{

    /** Upper bound for the number of generations to run for. This number can be lower or higher,
     *  depending on the complexity of maze.
     */
    private static int maxGenerations = 200;

    public static void main(String[] args) {

        /** * Initialize a maze. We'll write this by hand.
         *
         * As a reminder:
         * 0 = Empty
         * 1 = Wall
         * 2 = Starting position
         * 4 = Goal position
         */

        Maze maze = new Maze(new int[][]{
                {2, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 1, 0, 0, 1, 1},
                {0, 1, 0, 1, 1, 0, 1, 0},
                {1, 1, 0, 0, 4, 0, 0, 0},
                {1, 1, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 1, 0, 1, 1}}
        );


        DrawMaze draw_maze = new DrawMaze(maze.getMaze(), "Maze");


        // Create genetic algorithm
        GeneticAlgorithm ga = new GeneticAlgorithm(200, 0.12, 0.9, 2, 10);
        Population population = ga.initPopulation(150);
        ga.evalPopulation(population, maze);
        // Keep track of current generation
        int generation = 1;
        boolean found = false;
        // Start evolution loop
        while (ga.isTerminationConditionMet(generation, maxGenerations) == false && !found) {
            // Print fittest individual from population
            Individual fittest = population.getFittest(0);
            System.out.println(
                    "Generation " + generation + " Best solution (" + fittest.getFitness() + "): " + fittest.toString());


            // Apply crossover
            population = ga.crossoverPopulation(population);

            // Apply mutation
            population = ga.mutatePopulation(population);

            // Evaluate population
            ga.evalPopulation(population, maze);

            // Increment the current generation
            generation++;

            // If robot reach the goal
            if (fittest.getFitness() > 100){
                found = true;
            }

        }

        System.out.println("Stopped after " + (generation-1) + " generations.");
        Individual fittest = population.getFittest(0);
        System.out.println("Best solution (" + fittest.getFitness() + "): " + fittest.toString());

        Robot robot = new Robot(fittest.getChromosome(), maze, 150);
        robot.run();
        int[][] solvemaze = robot.copymaze;
        for (int i = 0; i < solvemaze.length; i++){
            for (int j = 0; j < solvemaze[0].length; j++){
                solvemaze[i][j] = robot.copymaze[i][j];
            }
        }
        draw_maze.setMaze(solvemaze);
        draw_maze.repaint();

    }
}
