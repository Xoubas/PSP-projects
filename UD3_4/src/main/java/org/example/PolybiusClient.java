package org.example;

import java.io.*;
import java.net.Socket;

public class PolybiusClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 49152;

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);) {
            String message = "cosa";
            writer.println(message);
            System.out.println("Encrypted message: " + reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
