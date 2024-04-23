package org.example;

import com.google.gson.*;

import java.io.*;
import java.net.Socket;
import java.net.URL;

public class ServerHandler implements Runnable {
    private final Socket clientSocket;
    private String word;
    private String hiddenWord;
    private int lives = 5;

    public ServerHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private void getWord() {
        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new URL("https://random-word-api.herokuapp.com/word?length=5").openStream()));) {
            JsonArray array = gson.fromJson(br, JsonArray.class);
            if (array != null && !array.isEmpty()) {
                word = array.get(0).getAsString();
            } else {
                word = "default"; // default word or handle appropriately
            }
        } catch (IOException e) {
            // Log the exception or handle it according to your application's requirements
            throw new RuntimeException("Failed to get word: " + e.getMessage(), e);
        }
    }

    private void getCommand() {
        try {
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            String command = inputStream.readUTF();
            System.out.println("Command received: " + command);
            handleCommand(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendInfo(String info) {
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            dataOutputStream.writeUTF(info);
            dataOutputStream.writeUTF(hiddenWord);
            dataOutputStream.writeUTF("EXIT");
            dataOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert dataOutputStream != null;
                dataOutputStream.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        getCommand();
    }


    private void handleCommand(String command) {
        String[] commandSplit = command.toUpperCase().split(" ");
        switch (commandSplit[0]) {
            case "START" -> startGame();
            case "WORD" -> {
                if (guessWord(commandSplit[1])) {
                    sendInfo("You got it");
                } else {
                    sendInfo("Try again");
                }
            }
            case "QUIT" -> quit();
        }
    }

    private void startGame() {
        System.out.println("Game started");
        getWord();
        System.out.println("Word: " + word);
        hiddenWord = word.replaceAll(".", "*");
        sendInfo("Game has started");
        getCommand();
    }

    private boolean guessWord(String guess) {
        if (guess.equals(this.word)) {
            return true;
        } else {
            StringBuilder sb = new StringBuilder(hiddenWord);
            for (int i = 0; i < word.length(); i++) {
                if (guess.charAt(i) == word.charAt(i)) {
                    sb.setCharAt(i, guess.charAt(i));
                }
            }
            hiddenWord = sb.toString();
            lives--;
            return false;
        }
    }

    private boolean checkEnd() {
        return lives <= 0;
    }

    private void quit() {
        sendInfo("QUIT");
    }

    @Override
    public void run() {
        getCommand();
    }
}
