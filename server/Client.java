package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 4000);
             Scanner scanner = new Scanner(System.in);
             PrintStream output = new PrintStream(socket.getOutputStream())) {

            Scanner serverInput = new Scanner(socket.getInputStream());

            // Exibe mensagem de boas-vindas do servidor
            System.out.println(serverInput.nextLine()); // "Digite seu nome:"
            String nome = scanner.nextLine();
            output.println(nome);

            // Thread para escutar mensagens do servidor
            ClientThread listener = new ClientThread(socket);
            listener.start();

            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("sair")) {
                    output.println("sair");
                    break;
                }
                output.println(input);
            }

            System.out.println("Você saiu do chat.");

        } catch (IOException e) {
            System.err.println("Erro no cliente: " + e.getMessage());
        }
    }
}
