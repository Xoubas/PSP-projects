package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServer {

    public static void main(String[] args) {
        /*
        The port is added to the socket so that clients can connect to the server.
         */
        int port = 49154;
        try (ServerSocket socket = new ServerSocket(port, 6)) {
            System.out.println("Waiting for client...");
            /*
            The server awaits for a client to connect to the server, and when it does
            accept the client the program enters a loop so that more clients can connect to
            the server and creates several threads so the petitions can be taken care of.
             */
            while (true) {
                Socket clientSocket = socket.accept();
                System.out.println("10 Number game server ready");

                Thread thread = new Thread(new AppServerHandler(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
