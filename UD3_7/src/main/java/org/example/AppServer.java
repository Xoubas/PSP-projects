package org.example;

import java.net.*;
import java.io.*;

public class RandomWordServer {
    public static void main(String[] args) {
        final int serverPort = 9876;

        try (DatagramSocket serverSocket = new DatagramSocket(serverPort)) {
            System.out.println("Random Word Server is running...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                // Create a worker thread to handle client request
                AppServerHandler worker = new AppServerHandler(serverSocket);
                new Thread(worker).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
