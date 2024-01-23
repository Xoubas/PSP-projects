package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class AppServerHandler {
    private final Socket clientSocket;
    private int number;

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
                guessNumber();
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
            output.writeUTF("20 PLAY 5");
            getCommand();
        } catch (IOException e) {
            System.err.println("Error sending response to client.");
            e.printStackTrace();
        }
    }

    private void quitGame() {
        try (DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())) {
            number = new Random().nextInt(10)+1;
            output.writeUTF("11 BYE");
            System.exit(5);
        } catch (IOException e) {
            System.err.println("Error sending response to client.");
            e.printStackTrace();
        }
    }

    private void guessNumber(){

    }
}
