package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SquareServer {
    public static void main(String[] args) {
        int port = 49154;
        try (ServerSocket socket = new ServerSocket(port)) {
            System.out.println("Waiting for client...");
            while (true) {
                Socket clientSocket = socket.accept();
                Thread t = new Thread(new SquareServerHandler(clientSocket));
                t.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
