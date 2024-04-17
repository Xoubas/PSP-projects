package org.example;

import java.io.*;
import java.net.Socket;
import java.net.URL;

public class ServerHandler implements Runnable {
    private final Socket clientSocket;
    private String word;
    private int lives;

    public ServerHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private String getWord() {
        String word = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new URL("https://random-word-api.herokuapp.com/word?number=5").openStream()));
            word = br.readLine().toUpperCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return word;
    }

    private String getCommand() {
        try {
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            String command = inputStream.readUTF();
            System.out.println("Command received: " + command);
            handleCommand(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private void sendInfo(String info) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            dataOutputStream.writeUTF(info);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleCommand(String command) {
        String[] commandSplit = command.split(" ");
        switch (commandSplit[0]) {
            case "START" -> startGame();
            case "WORD" -> guessWord(commandSplit[1]);
            case "QUIT" -> quit();
        }

    }

    private void startGame() {
        sendInfo("Game has started");
        sendInfo(word.replaceAll(".", "*"));
    }

    private boolean guessWord(String guess) {
        if (guess.equals(this.word)) {
            return true;
        } else {
            for(int i = 0; i < word.length(); i++) {
                if(guess.charAt(i) == word.charAt(i)){

                }
            }
            return false;
        }
    }

    private void quit() {

    }

    @Override
    public void run() {
        getWord();
    }
}
