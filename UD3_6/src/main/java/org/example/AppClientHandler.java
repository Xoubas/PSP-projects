package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class AppClientHandler {
    private final Socket serverSocket;

    public AppClientHandler(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    private void getCommand() {
        try {
            DataInputStream input = new DataInputStream(serverSocket.getInputStream());
            String response = input.readUTF();
            while (!response.equals("QUIT")) {
                System.out.println("Server response: " + response);
                sendCommand();
            }
            serverSocket.close();
        } catch (IOException e) {
            System.err.println(e.getCause());
        }
    }


    public void sendCommand() {
        try {
            DataOutputStream output = new DataOutputStream(serverSocket.getOutputStream());
            System.out.println("Write command: ");
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            output.writeUTF(command);
            getCommand();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
