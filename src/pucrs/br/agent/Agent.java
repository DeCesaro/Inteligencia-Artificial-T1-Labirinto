package pucrs.br.agent;

import pucrs.br.structures.Chromosome;
import pucrs.br.structures.Maze;

import java.util.ArrayList;

import static pucrs.br.utils.Utils.rand100NumbersBetween0and1;

public class Agent {

    private ArrayList<Chromosome> individuals = new ArrayList<Chromosome>();
    public Maze maze = null;
    public int population = 100;
    public int mutation = 0;
    public int sizeChromosome = 0;

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
                gene = rand100NumbersBetween0and1();
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

}
