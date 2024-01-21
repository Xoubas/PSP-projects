package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class NumberGameServer {
    private static int numberToGuess;
    private static int attempts;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(50000)) {
            System.out.println("Number Game Server is running...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream())
        ) {
            dataOutputStream.writeUTF("10 Number game server ready.");

            String input;
            while ((input = dataInputStream.readUTF()) != null) {
                handleCommand(input, dataOutputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleCommand(String command, DataOutputStream dataOutputStream) {
        String[] parts = command.split(" ");
        switch (parts[0]) {
            case "NEW":
                startNewGame(Integer.parseInt(parts[1]), dataOutputStream);
                break;
            case "NUM":
                checkGuess(Integer.parseInt(parts[1]), dataOutputStream);
                break;
            case "HELP":
                sendHelpInfo(dataOutputStream);
                break;
            case "QUIT":
                try {
                    dataOutputStream.writeUTF("11 BYE.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    dataOutputStream.writeUTF("90 UNKNOWN.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    private static void startNewGame(int maxAttempts, DataOutputStream dataOutputStream) {
        numberToGuess = new Random().nextInt(100) + 1;
        attempts = maxAttempts;
        try {
            dataOutputStream.writeUTF("15 Number game server ready.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkGuess(int guess, DataOutputStream dataOutputStream) {
        attempts--;

        try {
            if (guess == numberToGuess) {
                dataOutputStream.writeUTF("50 WIN. You guessed the number!");
            } else if (guess < numberToGuess) {
                dataOutputStream.writeUTF("25 LOW.");
            } else {
                dataOutputStream.writeUTF("35 HIGH.");
            }

            if (attempts == 0) {
                dataOutputStream.writeUTF("70 LOSE NUM " + numberToGuess + ". You ran out of attempts.");
            } else {
                dataOutputStream.writeUTF("40 INFO. Attempts left: " + attempts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendHelpInfo(DataOutputStream dataOutputStream) {
        try {
            dataOutputStream.writeUTF("Commands:");
            dataOutputStream.writeUTF("NEW <maxAttempts>");
            dataOutputStream.writeUTF("NUM <guess>");
            dataOutputStream.writeUTF("HELP");
            dataOutputStream.writeUTF("QUIT");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

