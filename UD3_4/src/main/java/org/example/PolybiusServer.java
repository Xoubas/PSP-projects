package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PolybiusServer {
    public static void main(String[] args) {
        int portNumber = 49153;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server is waiting for connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String clientMessage = reader.readLine();
            System.out.println("Received message from client: " + clientMessage);
            String encryptedMessage = encrypt(clientMessage);
            System.out.println("Encrypted message: " + encryptedMessage);
            writer.println(encryptedMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[][] fillMatrix() {
        int[][] polybius = new int[6][6];
        int character = 65;
        int number = 48;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (i >= 4 && j >= 2) {
                    polybius[j][i] = number;
                    number++;
                } else {
                    polybius[j][i] = character;
                    character++;
                }
            }
        }
        return polybius;
    }

    private static String encrypt(String message) {
        message = message.toUpperCase();
        int[][] table = fillMatrix();
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            for (int x = 0; x < 6; x++) {
                for (int j = 0; j < 6; j++) {
                    if ((int)message.charAt(i) == table[j][x]) {
                        encryptedMessage.append(x + 1).append(j + 1).append(" ");
                    }
                }
            }
        }
        return encryptedMessage.toString().trim(); // Remove trailing space
    }
}

