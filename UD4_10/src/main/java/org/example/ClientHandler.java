package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientHandler {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void sendCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce a command: ");
        String command = scanner.nextLine();
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getInfo();
    }

    public void getInfo() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String message; // To store the current message

            while (true) {
                message = in.readUTF(); // Read the message once per loop iteration
                if ("QUIT".equals(message)) {
                    System.out.println("Quit command received, shutting down.");
                    closeResources(); // Gracefully close resources
                    break; // Exit the loop
                } else if ("EXIT".equals(message)) {
                    System.out.println("Exit command received, stopping further messages.");
                    break; // Exit the loop
                } else {
                    System.out.println(message); // Output the received message
                }
            }
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
            throw new RuntimeException("Failed to receive information", e);
        } finally {
            sendCommand(); // Proceed to send the next command or close resources
        }
    }

    private void closeResources() {
        // Close your DataInputStream, Socket, or any other resources here
        try {
            if (socket != null) {
                socket.close(); // Example of closing the socket
            }
        } catch (IOException e) {
            System.err.println("Failed to close resources: " + e.getMessage());
        }
    }

    private void checkQuit(DataInputStream in) {
        try {
            String message1 = in.readUTF();
            if (message1.equals("QUIT")) {
                System.exit(0);
            } else {
                System.out.println(message1);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
