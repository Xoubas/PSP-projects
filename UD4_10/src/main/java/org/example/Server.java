package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 50000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server waiting for connection...");
            do {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected" + clientSocket.getInetAddress().getHostAddress());
                Thread thread = new Thread(new ServerHandler(clientSocket));
                thread.start();
            } while (true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
