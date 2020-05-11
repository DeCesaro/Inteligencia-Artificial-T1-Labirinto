package pucrs.br.structures;

import java.util.ArrayList;

public class Chromosome implements Comparable<Chromosome> {

    private ArrayList<Double> genes = new ArrayList<Double>();
    public ArrayList<Path> path = new ArrayList<Path>();
    private int x = 0;
    private int y = 0;
    private double score = 0;

    public Chromosome(){
        this.path.add(new Path(0,0));
    }

    /*Adiciona Gene ao cromossomo*/
    public void addGene(double gene) {
        genes.add(gene);
    }

    /*Retorna genes do cromossomo*/
    public ArrayList<Double> getGenes() {
        return genes;
    }

    /*Atualiza posição do cromossomo*/
    public void atualizaPosicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String printPath() {
        StringBuilder s = new StringBuilder();
        for (Path value : this.path) {
            s.append("(").append(value.x).append(",").append(value.y).append(")").append(" \n");
        }
        return s.toString();
    }

    public ArrayList<Path> getPath() {
        return path;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getScore() {
        return score;
    }

    /*Imprime cromossomo*/
    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        for (Double gene : genes) {
            toString.append(gene).append(" - ");
        }
        toString.append("\nPontuacao: ").append(this.score).append("\n");
        toString.append("Path: ").append(this.printPath()).append("\n");
        return toString + "\n";
    }

    /**
     * Method responsible for compare the fitness value between
     * two different chromosomes
     * @param otherChromosome
     * @return int
     */
    @Override
    public int compareTo(Chromosome otherChromosome) {
        if (this.score < otherChromosome.getScore()) {
            return -1;
        }

        if (this.score > otherChromosome.getScore()) {
            return 1;
        }

        return 0;
    }

}
