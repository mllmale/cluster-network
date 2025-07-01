package estudo._1_flush;
import java.io.FileWriter;
import java.io.IOException;

/*
 * 
 * this class implements two methods to demonstrate the use of flush() in file writing.
 * The first method writes to a file and uses flush() to ensure data is written immediately.
 * The second method writes to a file without using flush(), which may lead to data loss if the program terminates unexpectedly.
 */

public class FlushExample {

    // Método que escreve corretamente com flush()
    public void writeWithFlush(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("Este texto será gravado corretamente com flush.\n");
            writer.flush(); // Força a escrita no arquivo
            writer.close(); // Fecha o arquivo
            System.out.println("Gravado com sucesso usando flush.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método que escreve sem flush()
    public void writeWithoutFlush(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("Este texto pode ser perdido sem flush.\n");
            // Sem flush e sem close aqui!
            // Apenas para mostrar o que acontece se esquecer de usar
            writer.close();
            System.out.println("Tentou gravar sem flush (dados podem ser perdidos).");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     public static void main(String[] args) {
        FlushExample example = new FlushExample();

        // Caminho do arquivo
        String file1 = "saida_com_flush.txt";
        String file2 = "saida_sem_flush.txt";

        // Testa com flush
        example.writeWithFlush(file1);

        // Testa sem flush
        example.writeWithoutFlush(file2);

        // Aguarde e depois verifique os arquivos
    }
}
