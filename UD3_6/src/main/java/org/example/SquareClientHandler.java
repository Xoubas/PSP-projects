package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SquareClientHandler {
    private String command;
    private Socket serverSocket;

    public SquareClientHandler(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void sendCommand() {
        try (DataInputStream input = new DataInputStream(serverSocket.getInputStream());
             DataOutputStream output = new DataOutputStream(serverSocket.getOutputStream())) {
            System.out.println("Write command: ");
            Scanner sc = new Scanner(System.in);
            command = sc.nextLine();
            output.writeUTF(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
