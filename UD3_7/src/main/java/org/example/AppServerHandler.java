package org.example;

import java.net.*;
import java.io.*;
import java.util.Random;

public class RandomWordServerWorker implements Runnable {
    private DatagramSocket socket;
    private DatagramPacket receivePacket;

    public RandomWordServerWorker(DatagramSocket socket, DatagramPacket receivePacket) {
        this.socket = socket;
        this.receivePacket = receivePacket;
    }

    @Override
    public void run() {
        try {
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            // Extract the command from the client's request
            String clientCommand = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Process the command and generate a response
            String serverResponse = processCommand(clientCommand);

            // Send the response back to the client
            byte[] sendData = serverResponse.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            socket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processCommand(String clientCommand) {
        String[] parts = clientCommand.split("\\s+");
        if (parts.length == 1 || (parts.length == 2 && parts[1].matches("\\d+"))) {
            int numberLetters = (parts.length == 2) ? Integer.parseInt(parts[1]) : 5;
            return getRandomWord(numberLetters);
        } else {
            return "Hello from Random Word Server!";
        }
    }

    private String getRandomWord(int length) {
        // Simulate fetching a random word from a web service
        String[] words = {"apple", "banana", "orange", "grape", "melon", "peach", "kiwi", "pear", "plum", "berry"};
        Random random = new Random();
        int randomIndex = random.nextInt(words.length);

        String selectedWord = words[randomIndex];
        if (selectedWord.length() == length) {
            return selectedWord;
        } else {
            // Retry until a word with the specified length is found
            return getRandomWord(length);
        }
    }
}
