package pucrs.br.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadTxt {

    public String[][] readFileTxt(String lab) {
        String[][] vectorMaze = null;
        System.out.print("\nConteúdo do labirinto:\n");

        try {
            FileReader arquivo = new FileReader("C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\PUCRS\\Inteligencia Artificial\\Inteligencia-Artificial-T1-Labirinto\\src\\pucrs\\br\\mazes\\" + lab + ".txt");
            BufferedReader buffer = new BufferedReader(arquivo);

            String line = buffer.readLine();
            int size = Integer.parseInt(line);
            //System.out.printf("%s\n", size);
            vectorMaze = new String[size][size];
            int cont = 0;
            while (line != null) {
                line = buffer.readLine(); // lê da segunda até a última line

                if(line == null) break;
                String[] conteudo = line.split(" ");
                for(int i = 0; i < size; i++){
                    System.out.print("\nLinha: " + (cont+2) + " " + "conteudo(char): " + conteudo[i] + "\n");
                    vectorMaze[cont][i] = conteudo[i];
                }
                cont++;
            }
            arquivo.close();
        } catch (IOException e) {
            System.err.printf("Problema ao abrir arquivo!\n", e.getMessage());
        }
        return vectorMaze;
    }
}