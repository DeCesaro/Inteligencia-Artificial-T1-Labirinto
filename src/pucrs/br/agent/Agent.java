package pucrs.br.agent;

import pucrs.br.structures.Chromosome;
import pucrs.br.structures.Maze;
import pucrs.br.structures.Path;

import java.util.ArrayList;
import java.util.Collections;

import static pucrs.br.utils.Utils.randNumbersInterval0to100;

public class Agent {

    private final String[] moves = {"Norte", "Sul", "Leste", "Oeste", "Noroeste", "Nordeste", "Sudeste", "Sudoeste"};
    private ArrayList<Chromosome> individuals = new ArrayList<Chromosome>();
    private ArrayList<Chromosome> individualsIntermediate = new ArrayList<Chromosome>();
    public static Path agent = new Path(0,0);
    private Maze maze = null;
    private final int maxGenerations = 1000;
    private int population = 100;
    private double mutation = 1;
    private int sizeChromosome = 0;
    private boolean isGoal;
    private int cont = 0;

    public Agent(){}

    public void aplicarAG(Maze maze, int valueMut, int population){
        this.population = population;
        this.maze = maze;
        System.out.println("\nIniciando Algoritmo Genético...\n");
        createPopulation0(this.maze.qtdFreeFields(), valueMut);

        while(cont < population ) {
            System.out.println("\nGERAÇÃO : " + cont );
            applySelectionBasic();
            this.individuals = this.individualsIntermediate;
            this.individualsIntermediate = new ArrayList<Chromosome>();
            cont++;
        }
    }

    public void createPopulation0(int tam, int valueMut){
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
        this.mutation = Double.parseDouble(String.valueOf(((tam * population)*valueMut)/100));
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
        for (Chromosome chromosome : this.individuals) {
            System.out.println(chromosome.toString());
            //TODO: fazer apitidao
        }
    }

    public void applySelectionBasic() {
        System.out.println("Aplicando seleção");
        ArrayList<Chromosome> melhores = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            melhores.add(this.individuals.get(i));
        }
        for(int i = 4; i < this.individuals.size(); i++) {
            for(int j = 0; j < melhores.size(); j++) {
                if(this.individuals.get(i).getScore() > melhores.get(j).getScore()) {
                    melhores.remove(j);
                    melhores.add(this.individuals.get(i));
                }
            }
        }
        this.individualsIntermediate = melhores;
        for (Chromosome chromosome : this.individualsIntermediate) {
            //System.out.println(i + ". Pontuação: " + this.individuosIntermediario.get(i).pontuacao);
            //System.out.println("" + this.individuosIntermediario.get(i).printPath());
            System.out.println(chromosome.toString());
        }
    }

    public static void walkOnMaze(){
        //TODO: Fazer agent caminhar, porém tem que criar vários agentes ao mesmo tempo nesta classe...
    }

    public static void setAgentPath(int x, int y) {
        agent.x = x;
        agent.y = y;
    }

    public ArrayList<Chromosome> getIndividuals() {
        return individuals;
    }

    public int getPopulation() {
        return population;
    }

    public double getMutation() {
        return mutation;
    }

    public int getSizeChromosome() {
        return sizeChromosome;
    }

    public boolean isGoal() {
        return isGoal;
    }

    public void setIndividuals(ArrayList<Chromosome> individuals) {
        this.individuals = individuals;
    }
}
