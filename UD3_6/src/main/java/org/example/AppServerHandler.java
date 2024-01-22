package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AppServerHandler {
    private final Socket clientSocket;

    public AppServerHandler(Socket socket) {
        clientSocket = socket;
    }

    public void getCommand() {
        try {
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            String command = input.readUTF();
            System.out.println("Command received: " + command);
            handleCommand(command);
        } catch (IOException e) {
            System.err.println("Error reading command from client.");
            e.printStackTrace();
        }
    }

    private void handleCommand(String command) {
        switch (command) {
            case "NEW":
                startNewGame();
                break;
            case "NUM":
                // Add logic for the "NUM" command
                break;
            case "HELP":
                // Add logic for the "HELP" command
                break;
            case "QUIT":
                quitGame();
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    private void startNewGame() {
        try (DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())) {
            output.writeUTF("Game started. Have fun!");
            getCommand();
        } catch (IOException e) {
            System.err.println("Error sending response to client.");
            e.printStackTrace();
        }
    }

    private void quitGame() {
        try (DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())) {
            output.writeUTF("Game quitting. Goodbye!");
        } catch (IOException e) {
            System.err.println("Error sending response to client.");
            e.printStackTrace();
        }
    }
}
