import java.util.Random;

/**
 * An "Individual" represents a single candidate solution. The core piece of
 * information about an individual is its "chromosome", which is an encoding of
 * a possible solution to the problem at hand. A chromosome can be a string, an
 * array, a list, etc -- in this class, the chromosome is an integer array.
 *
 * An individual position in the chromosome is called a gene, and these are the
 * atomic pieces of the solution that can be manipulated or mutated. When the
 * chromosome is a string, as in this case, each character or set of characters
 * can be a gene.
 *
 * An individual also has a "fitness" score; this is a number that represents
 * how good a solution to the problem this individual is. The meaning of the
 * fitness score will vary based on the problem at hand.
 *
 * References 'Genetic Algorithms in Java Basics' - Lee Jacobson, Burak Kanber
 *
 * @author Meriton Çela
 *
 */

public class Individual {
    private int[] chromosome;
    private double fitness = -1;
    private int goodGenes = -1;

    /** Initializes individual with specific chromosome
     * @param chromosome The chromosome to give individual
     */
    public Individual(int[] chromosome) {
        // Create individual chromosome
        this.chromosome = chromosome;
    }

    /** Initializes random individual.
     *
     * The chromosome is made of 1s, 2s, 3s and 4s, presenting the directions of robot
     * 1 - move up
     * 2 - move left
     * 3 - move right
     * 4 - move down
     *
     * @param chromosomeLength The length of the individuals chromosome
     */
    public Individual(int chromosomeLength) {

        this.chromosome = new int[chromosomeLength];
        for (int gene = 0; gene < chromosomeLength; gene++) {
            int g = randInt(1,4);
            this.setGene(gene, g);
        }
    }

    /** Gets a random integer from 'min' to 'max'
     *  @param min The minimum value
     *  @param max The maximum value
     *
     *  @return int The rand number
     */
    private static int randInt(int min, int max){
        Random rand = new Random();
        int randomNum = rand.nextInt((max-min)+1)+min;
        return randomNum;
    }

    /** Gets individual's chromosome
     * @return The individual's chromosome
     */
    public int[] getChromosome() {
        return this.chromosome;
    }

    /** Gets individual's chromosome length
     * @return The individual's chromosome length
     */
    public int getChromosomeLength() {
        return this.chromosome.length;
    }

    /** Set gene at offset
     * @param gene
     * @param offset
     * @return gene
     */
    public void setGene(int offset, int gene) {
        this.chromosome[offset] = gene;
    }

    /** Get gene at offset
     * @param offset
     * @return gene
     */
    public int getGene(int offset) {
        return this.chromosome[offset];
    }

    /** Store individual's fitness
     * @param fitness The individuals fitness
     */
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    /** Gets individual's fitness
     * @return The individual's fitness
     */
    public double getFitness() {
        return this.fitness;
    }

    /** Sets number of genes that robot is alive after n directions that have made */
    public void setGoodGenes(int g){
        goodGenes = g;
    }


    /** Display the chromosome as a string.
     * @return string representation of the chromosome
     */
    public String toString() {
        String output = "";

        // To display the entire chromosome
        /*for (int gene = 0; gene < this.chromosome.length; gene++) {
            output += this.chromosome[gene]+" ";
        }*/

        // To display the chromosome until the robot is destroyed
        for (int gene = 0; gene < this.goodGenes; gene++) {
            output += this.chromosome[gene]+" ";
        }
        return output;
    }

}

