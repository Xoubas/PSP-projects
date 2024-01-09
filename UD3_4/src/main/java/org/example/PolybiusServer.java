package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PolybiusServer {
    public static void main(String[] args) {
        int portNumber = 49152;

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

//    private static void handleClient(Socket clientSocket) {
//        try (
//                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
//        ) {
//            String clientMessage = reader.readLine();
//            System.out.println("Received message from client: " + clientMessage);
//
//            // Encrypt the message using the Polybius cipher
//            String encryptedMessage = encryptPolybius(clientMessage);
//            System.out.println("Encrypted message: " + encryptedMessage);
//
//            // Send the encrypted message back to the client
//            writer.println(encryptedMessage);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String encryptPolybius(String message) {
//        // Implement your Polybius cipher encryption logic here
//        // Use the provided table for encryption
//        // You can create a mapping or lookup table for characters
//        // and their corresponding Polybius cipher representations
//        // Return the encrypted message
//        return message;
//    }
}

