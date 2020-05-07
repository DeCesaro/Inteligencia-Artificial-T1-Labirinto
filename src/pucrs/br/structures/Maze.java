package pucrs.br.structures;

import java.util.Arrays;

public class Maze {

    private static String[][] maze;

    public Maze(String[][] c) {
        maze = c;
    }

    /* Imprime labirinto */
    public String imprimeLabirinto() {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(maze).forEach(lines -> {
            StringBuilder line = new StringBuilder();
            Arrays.stream(lines).forEach(string -> line.append(string).append(" "));
            stringBuilder.append(line).append("\n");
        });
        return stringBuilder.toString();
    }

    /* Retorna o labirinto */
    public String[][] getMaze() {
        return maze;
    }

    /* Retorna o tamanho do labirinto */
    public int getMazeSize() {
        return maze[0].length;
    }

    /* Retorna a quantidade de paredes */
    public int qtdBlocks() {
        int tamanho = 0;
        for (String[] strings : maze) {
            for (String string : strings) {
                if (string.equals("1")) {
                    tamanho++;
                }
            }
        }
        return tamanho;
    }

    /*Retorna a quantidade de campos livres*/
    public int qtdFreeFields() {
        int qtd = 0;
        for (String[] lines : maze) {
            for (String line : lines) {
                if (line.equals("0")) {
                    qtd++;
                }
            }
        }
        return qtd;
    }

    /* Retorna a quantidade de buracos */
    public int qtdHoles() {
        int tamanho = 0;
        for (String[] strings : maze) {
            for (String string : strings) {
                if (string.equals("B")) {
                    tamanho++;
                }
            }
        }
        return tamanho;
    }

    /* Retorna a posição da saida */
    public Path posExit() {
        int posX = 0;
        int posY = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                //System.out.println(maze[i][j]);
                if (maze[i][j].equals("S")) {
                    posX = i;
                    posY = j;
                    break;
                }
            }
        }
        return new Path(posX, posY);
    }

    public String getPos(int x, int y) {
        return maze[x][y];
    }

}
