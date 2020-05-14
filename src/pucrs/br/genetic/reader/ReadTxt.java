package pucrs.br.genetic.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadTxt {

    public static int[][] readFileTxt(String lab) {
        int[][] vectorMaze = null;
        //System.out.print("\nConteúdo do labirinto:\n");

        try {
            FileReader arquivo = new FileReader(lab);
            BufferedReader buffer = new BufferedReader(arquivo);


            String line = buffer.readLine();
            int size = Integer.parseInt(line);
            //System.out.printf("%s\n", size);
            vectorMaze = new int[size][size];
            int cont = 0;
            while (line != null) {
                line = buffer.readLine(); // lê da segunda até a última line

                if(line == null) break;
                String[] conteudo = line.split(" ");
                for(int i = 0; i < size; i++){
                    //System.out.print("\nLinha: " + (cont+2) + " " + "conteudo(char): " + conteudo[i] + "\n");
                    switch (conteudo[i]) {
                        case "0" -> vectorMaze[cont][i] = 0;
                        case "B", "1" -> vectorMaze[cont][i] = 1;
                        case "S" -> vectorMaze[cont][i] = 4;
                        case "E" -> vectorMaze[cont][i] = 2;
                    }

                    //vectorMaze[cont][i] = conteudo[i];
                }
//                    0 = Empty
//                            * 1 = Wall
//                            * 2 = Starting position
//                            * 4 = Goal position
                cont++;
            }
            arquivo.close();
        } catch (IOException e) {
            System.err.printf("Problema ao abrir arquivo!\n", e.getMessage());
        }
        System.out.println("Matriz"+vectorMaze.toString());
        return vectorMaze;
    }
}