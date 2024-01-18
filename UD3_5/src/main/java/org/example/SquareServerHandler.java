package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SquareServerHandler {
    private Socket clientSocket;
    private int number;

    public SquareServerHandler(Socket socket) {
        clientSocket = socket;
    }

    private void getNumber() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            number = Integer.parseInt(reader.readLine());
            System.out.println("Number received: " + number);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendNumber() {
        try {
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
            number *= number;
            System.out.println("Number squared: " + number);
            writer.println(number);
            writer.flush(); // Ensure data is sent immediately
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void squareNumber() throws IOException {
        getNumber();
        sendNumber();
        clientSocket.close();
    }
}
