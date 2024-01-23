package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServer {

    public static void main(String[] args) {
        int port = 49154;
        try (ServerSocket socket = new ServerSocket(port, 6)) {
            System.out.println("Waiting for client...");

            while (true) {
                Socket clientSocket = socket.accept();
                System.out.println("10 Number game server ready");

                new AppServerHandler(clientSocket).getCommand();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
