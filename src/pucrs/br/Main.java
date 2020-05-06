package pucrs.br;

import pucrs.br.reader.ReadTxt;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        ReadTxt fileProcessor = new ReadTxt(); //objeto da classe leitora de arquivo

        //vetor com os casos de testes possíveis
        Object[] options = {"lab10.txt", "teste1", "teste2", "teste3",
                "teste4", "teste5", "teste6", "teste7",
                "teste8", "teste9"};
        String response;

        //uma interface bem simples para o usuário escolher qual caso de teste deseja selecionar
        //tanto as opções no vetor como a interface, não permitem o usuário "burlar" a escolha.
        do {
            response = (String) JOptionPane.showInputDialog(null,
                    "Qual caso de labirinto deseja utilizar ?",
                    "Pergunta",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    "não");
        } while (response.equals("") || response.equals("não"));

        //um switch simples para selecionar e já executar o teste escolhido
        switch (response) {
            case ("lab10.txt") -> fileProcessor.readFileTxt("lab10");
            case ("teste0.txt") -> fileProcessor.readFileTxt("teste1.txt");
            case ("teste1.txt") -> fileProcessor.readFileTxt("teste2.txt");
            case ("teste2.txt") -> fileProcessor.readFileTxt("teste3.txt");
            case ("teste3.txt") -> fileProcessor.readFileTxt("teste4.txt");
            case ("teste4.txt") -> fileProcessor.readFileTxt("teste5.txt");
            case ("teste5.txt") -> fileProcessor.readFileTxt("teste6.txt");
            case ("teste6.txt") -> fileProcessor.readFileTxt("teste7.txt");
            case ("teste7.txt") -> fileProcessor.readFileTxt("teste8.txt");
            case ("teste8.txt") -> fileProcessor.readFileTxt("teste9.txt");
            default -> System.out.println("Nome de arquivo inválido");
        }
    }
}
