package org.example;

import java.io.*;
import java.net.Socket;

public class SquareServerHandler {
    private Socket clientSocket;
    private String command;

    public SquareServerHandler(Socket socket) {
        clientSocket = socket;
    }

    public void getCommand() {
        try {
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            command = input.readUTF();
            System.out.println("Command received: " + command);
            while ((command = input.readUTF()) != null) {
                handleCommand(command);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleCommand(String command) {
//        String[] parts = command.split(" ");
        switch (command) {
            case "NEW":
                startNewGame();
                break;
            case "NUM":

                break;
            case "HELP":

                break;
            case "QUIT":

                break;
        }
    }

    private void startNewGame() {
        try (DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());) {
            output.writeUTF("15 Number game server ready.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void sendNumber() {
//        try {
//            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
//            number *= number;
//            System.out.println("Number squared: " + number);
//            writer.println(number);
//            writer.flush(); // Ensure data is sent immediately
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public void squareNumber() throws IOException {
//        getNumber();
//        sendNumber();
//        clientSocket.close();
//    }
}
