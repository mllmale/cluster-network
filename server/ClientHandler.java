package server;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private PrintStream writer;
    private String clientName;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            System.err.println("Erro ao configurar cliente: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            writer.println("Digite seu nome:");
            this.clientName = reader.readLine();
            System.out.println(clientName + " entrou no chat.");
            Main.broadcast(clientName + " entrou no chat!", this);
            writer.println("Olá " + clientName + "! Você entrou no chat. Digite 'sair' para sair.");

            String msg;
            while ((msg = reader.readLine()) != null) {
                if (msg.equalsIgnoreCase("sair")) {
                    writer.println("Você saiu do chat.");
                    break;
                }

                String mensagemFormatada = clientName + ": " + msg;
                System.out.println(mensagemFormatada);
                Main.broadcast(mensagemFormatada, this);
            }

        } catch (IOException e) {
            System.err.println("Erro com cliente " + clientName + ": " + e.getMessage());
        } finally {
            try {
                Main.removeClient(this);
                socket.close();
                System.out.println(clientName + " foi desconectado.");
            } catch (IOException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    public String getClientName() {
        return clientName;
    }
}
