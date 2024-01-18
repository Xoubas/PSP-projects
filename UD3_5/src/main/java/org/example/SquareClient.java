package org.example;

import java.io.IOException;
import java.net.Socket;

public class SquareClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 49154;

        try (Socket socket = new Socket(serverAddress, port)){
            new SquareClientHandler(socket).changeNumber();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
