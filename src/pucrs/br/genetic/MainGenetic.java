package pucrs.br.genetic;

import pucrs.br.genetic.agent.Agent;
import pucrs.br.genetic.interfaces.DrawMaze;
import pucrs.br.genetic.reader.ReadTxt;
import pucrs.br.genetic.structures.Individual;
import pucrs.br.genetic.structures.MazeGenetic;
import pucrs.br.genetic.structures.Population;

import java.util.Arrays;

import static pucrs.br.genetic.reader.ReadTxt.readFileTxt;

public class MainGenetic {

    private static int maxGenerations = 200;

    public static void main(String[] args) {

        /** * Initialize a mazeGenetic. We'll write this by hand.
         *
         * As a reminder:
         * 0 = Empty
         * 1 = Wall
         * 2 = Starting position
         * 4 = Goal position
         */
        String path = "C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\PUCRS\\Inteligencia Artificial\\Inteligencia-Artificial-T1-Labirinto\\src\\pucrs\\br\\genetic\\lab10.txt";
        MazeGenetic mazeGenetic = new MazeGenetic(readFileTxt(path));


        DrawMaze draw_maze = new DrawMaze(mazeGenetic.getMaze(), "Algoritmo Genético");


        // Create genetic algorithm
        GeneticAlgorithm ga = new GeneticAlgorithm(200, 2.1, 0.9, 4, 10);
        Population population = ga.initPopulation(350);
        ga.evalPopulation(population, mazeGenetic);
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
            ga.evalPopulation(population, mazeGenetic);

            // Increment the current generation1
            generation++;

            // If agent reach the goal
            if (fittest.getFitness() > 100){
                found = true;
            }

        }

        System.out.println("Stopped after " + (generation-1) + " generations.");
        Individual fittest = population.getFittest(0);
        System.out.println("Best solution (" + fittest.getFitness() + "): " + fittest.toString());

        Agent agent = new Agent(fittest.getChromosome(), mazeGenetic, 500);
        agent.run();
        int[][] solvemaze = agent.copymaze;
        for (int i = 0; i < solvemaze.length; i++){
            System.arraycopy(agent.copymaze[i], 0, solvemaze[i], 0, solvemaze[0].length);
        }
        draw_maze.setMaze(solvemaze);
        draw_maze.repaint();

    }
}
