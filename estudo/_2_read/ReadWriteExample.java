package estudo._2_read;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class ReadWriteExample {

    // Escreve no arquivo com flush e close
    public void writeWithFlush(String filename, String message) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(message);
            writer.flush(); // Garante que tudo seja gravado imediatamente
            System.out.println("Mensagem gravada com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lê o conteúdo do arquivo
    public void readMessage(String filename) {
        try (FileReader reader = new FileReader(filename);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            System.out.println("Lendo arquivo:");
            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        ReadWriteExample example = new ReadWriteExample();
        example.writeWithFlush("texto.txt", "Este é um exemplo de escrita com flush.\n");
        example.readMessage("texto.txt");
    }
}
