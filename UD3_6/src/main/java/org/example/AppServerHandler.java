package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class AppServerHandler implements Runnable {
    private final Socket clientSocket;
    private int number;
    private int lives;

    public AppServerHandler(Socket socket) {
        clientSocket = socket;
    }

    private void getCommand() {
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
        String[] parts = command.split(" ");
        switch (parts[0]) {
            case "NEW":
                startNewGame(Integer.parseInt(parts[1]));
                break;
            case "NUM":
                guessNumber(Integer.parseInt(parts[1]));
                break;
            case "HELP":
                returnInfo();
                break;
            case "QUIT":
                quitGame();
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    private void startNewGame(int num) {
        number = new Random().nextInt(10) + 1;
        lives = num;
        System.out.println(number);
        try (DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())) {
            output.writeUTF("20 PLAY " + num);
            getCommand();
        } catch (IOException e) {
            System.err.println("Error sending response to client.");
            e.printStackTrace();
        }
    }

    private void guessNumber(int num) {
        try (DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())) {
            if (num < number) {
                lives--;
                if (lives < 0) {
                    output.writeUTF("70 LOSE " + number);
                    getCommand();
                }
                output.writeUTF("25 LOW");
                getCommand();

            } else if (num > number) {
                lives--;
                if (lives < 0) {
                    output.writeUTF("70 LOSE " + number);
                    getCommand();
                }
                output.writeUTF("35 HIGH");
                getCommand();

            } else {
                if (lives >= 0) {
                    output.writeUTF("50 WIN");
                    getCommand();
                }
            }
        } catch (IOException e) {
            System.err.println("Error sending response to client.");
            e.printStackTrace();
        }
    }

    private void quitGame() {
        try (DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())) {
            output.writeUTF("11 BYE");
        } catch (IOException e) {
            System.err.println("Error sending response to client.");
            e.printStackTrace();
        }
    }

    private void returnInfo() {
        try (DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())) {
            output.writeUTF("NEW <number of lives> - starts a new game with the given number of lives +\n" +
                    "NUM <number> - guesses the number +\n" +
                    "HELP - returns this list of commands +\n" +
                    "QUIT - quits the game\n");
            getCommand();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        getCommand();
    }
}
