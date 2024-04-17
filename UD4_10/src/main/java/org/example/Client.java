package org.example;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 50000;

        try (Socket socket = new Socket(serverAddress, port)) {
            new ClientHandler(socket).sendCommand();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}