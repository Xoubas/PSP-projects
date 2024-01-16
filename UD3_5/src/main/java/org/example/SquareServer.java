package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SquareServer {
    public static void main(String[] args) {
        int port = 49154;
        try (ServerSocket socket = new ServerSocket(port)) {
            System.out.println("Waiting for client...");

            while (true) {
                Socket clientSocket = socket.accept();
                new SquareServerHandler(clientSocket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void changeNumber(Socket clientSocket){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
