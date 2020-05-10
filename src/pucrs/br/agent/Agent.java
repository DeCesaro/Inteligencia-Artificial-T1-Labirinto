package pucrs.br.agent;

import pucrs.br.structures.Chromosome;
import pucrs.br.structures.Maze;
import pucrs.br.structures.Path;

import java.util.ArrayList;
import java.util.Collections;

import static pucrs.br.utils.Utils.randNumbersInterval0to100;

public class Agent {

    private ArrayList<Chromosome> individuals = new ArrayList<Chromosome>();
    private ArrayList<Chromosome> individualsIntermediate = new ArrayList<Chromosome>();
    public static Path agent = new Path(0,0);
    private Maze maze = null;
    private final int maxGenerations = 1000;
    private int population = 100;
    private int mutation = 0;
    private int sizeChromosome = 0;
    private boolean isGoal;

    public Agent(){}

    public void aplicarAG(Maze maze, int valueMut, int population){
        this.population = population;
        this.maze = maze;
        System.out.println("\nIniciando Algoritmo Genético...\n");
        criarPopulacaoInicial(this.maze.qtdFreeFields(), valueMut);
    }

    public void criarPopulacaoInicial(int tam, int valueMut){
        System.out.println("\nCriando Populacao Inicial...\n");
        double gene;
        System.out.println("Tamanho: "+tam);
        this.sizeChromosome = tam;

        for(int i = 0; i < population; i++) {
            Chromosome chromosome = new Chromosome();
            for (int j = 0; j < tam; j++) {
                gene = randNumbersInterval0to100();
                chromosome.addGene(gene);
            }
            this.individuals.add(chromosome);
            //System.out.println(chromosome.toString());
        }
        this.mutation = ((tam * population)*valueMut)/100;
        System.out.println("Mutacao: " + this.mutation);
    }

    public void imprimeMatrizPopulacao(){
        System.out.println("Número de individuos: " + individuals.size());
        for (Chromosome individual : this.individuals) {
            System.out.println(individual.toString());
        }
    }

    /**
     * Method responsible for ordering population by chromosome fitness value
     * @param individuals ArrayList<Chromosome>
     * @return individuals
     */
    private ArrayList<Chromosome> sort(ArrayList<Chromosome> individuals) {
        Collections.sort(individuals);

        return individuals;
    }

    public void aplicarAptidao(){
        String[][] campo = maze.getMaze();
        int index = 0;
        for (Chromosome chromosome : this.individuals) {
            index++;
            System.out.println(chromosome.toString());
            //TODO: fazer apitidao
        }
    }




    public ArrayList<Chromosome> getIndividuals() {
        return individuals;
    }

    public int getPopulation() {
        return population;
    }

    public int getMutation() {
        return mutation;
    }

    public int getSizeChromosome() {
        return sizeChromosome;
    }

    public boolean isGoal() {
        return isGoal;
    }
}
